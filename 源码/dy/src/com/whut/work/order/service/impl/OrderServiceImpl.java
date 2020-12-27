package com.whut.work.order.service.impl;

import com.whut.work.base.model.Page;
import com.whut.work.base.util.JavaStringUtil;
import com.whut.work.cart.dao.impl.CartDaoImpl;
import com.whut.work.cart.model.Cart;
import com.whut.work.goods.dao.impl.GoodsDaoImpl;
import com.whut.work.goods.model.Goods;
import com.whut.work.order.dao.impl.OrderDaoImpl;
import com.whut.work.order.model.Order;
import com.whut.work.order.service.IOrderService;
import com.whut.work.ticket.dao.impl.TicketDaoImpl;
import com.whut.work.ticket.model.Ticket;
import com.whut.work.user.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private CartDaoImpl cartDao;
    @Autowired
    private OrderDaoImpl orderDao;
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private TicketDaoImpl ticketDao;

    public Map<String, Object> addOneOrder(Integer id, String orderGoodsArray) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        List<Integer> goodsListOfOneOrder = JavaStringUtil.stringToList(orderGoodsArray,"-");
        Order order = new Order();
        order.setUser(userDao.findOne(" from User u where u.id='"+id+"' "));
        order.setCreateTime(new Date());
        order.setHaveDone(false);
        //操作order_cart中间表(非直接dao方式)
        List<Cart> carts = new ArrayList<Cart>();
        Cart cart;
        for(int i=0;i<goodsListOfOneOrder.size();i++){
            cart = cartDao.findOne(" from Cart c where c.id='"+goodsListOfOneOrder.get(i)+"' ");
            carts.add(cart);
            //同时把cart表的对应项的have_done置为true（表示已生成订单）
            cart.setHaveDone(true);
            cartDao.update(cart);
        }
        order.setCarts(carts);
        orderDao.save(order);

        JavaStringUtil.setListInt(new ArrayList<Integer>());
        returnMap.put("message", "已成功生成订单!");
        returnMap.put("success", true);
        return returnMap;
    }

    public Page<Order> getOrders(int currentPage, int pageSize,Integer userId,String orderType) throws Exception {
        //List<String> conditions = new ArrayList<String>();
        String conditions = "";
        if(orderType.equals("isDone")){
            conditions += " where o.haveDone=true and o.user.id='"+userId+"' ";
        }else if(orderType.equals("notDone")){
            conditions += " where o.haveDone=false and o.user.id='"+userId+"' ";
        }else{
            conditions += " where o.user.id='"+userId+"' ";
        }
        String queryHql = " from Order o "+conditions+" ";
        //System.out.println(queryHql);
        String countHql = " select count(*) from Order o "+conditions+" ";
        Page<Order> page = orderDao.findPage(currentPage, pageSize, queryHql, countHql);

        return page;
    }

    public Map<String, Object> deleteOneOrder(Integer id, String orderId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        String hql = " delete Order o where o.user.id='"+id+"' and o.id='"+orderId+"' ";
        orderDao.deleteWithHql(hql);

        returnMap.put("message", "已成功删除订单!");
        returnMap.put("success", true);
        return returnMap;
    }

    public Map<String, Object> completeOrder(Integer id, String orderId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        String hql = " from Order o where o.user.id='"+id+"' and o.id='"+orderId+"' ";
        Order order = orderDao.findOne(hql);
        if(order != null){
            order.setHaveDone(true);
            orderDao.update(order);
            //同时更新门票销量
            List<Cart> carts = order.getCarts();
            if(carts != null){
                Ticket ticket;
                for(Cart cart : carts){
                    ticket = ticketDao.findOne(" from Ticket t where t.id='"+cart.getTicket().getId()+"' ");
                    ticket.setSalesCount(cart.getTicketNums()+ticket.getSalesCount());
                    ticketDao.update(ticket);
                }
            }

            returnMap.put("message", "已成功支付订单!");
            returnMap.put("success", true);
        }else {
            returnMap.put("message", "错误");
            returnMap.put("success", false);
        }

        return returnMap;
    }

}
