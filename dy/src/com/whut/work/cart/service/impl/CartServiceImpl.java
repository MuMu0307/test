package com.whut.work.cart.service.impl;

import com.whut.work.base.model.Page;
import com.whut.work.base.util.JavaStringUtil;
import com.whut.work.cart.dao.impl.CartDaoImpl;
import com.whut.work.cart.model.Cart;
import com.whut.work.cart.service.ICartService;
import com.whut.work.ticket.dao.impl.TicketDaoImpl;
import com.whut.work.ticket.model.Ticket;
import com.whut.work.user.dao.impl.UserDaoImpl;
import com.whut.work.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CartServiceImpl implements ICartService{

    @Autowired
    private CartDaoImpl cartDao;
    @Autowired
    private TicketDaoImpl ticketDao;
    @Autowired
    private UserDaoImpl userDao;

    public Map<String, Object> addToCart(Integer id,Integer ticketId,Integer ticketNums) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();

        String hql = " from Cart c where c.user.id='"+id+"' and c.ticket.id='"+ticketId+"' and c.haveDone="+false+" ";
        Cart cart = cartDao.findOne(hql);
        if(cart != null){//改变数量（增加）
            cart.setTicketNums(ticketNums+cart.getTicketNums());
            cart.setCreateTime(new Date());
            cartDao.update(cart);
        }else{
            cart = new Cart();
            User user = userDao.findOne(" from User u where u.id='"+id+"' ");
            if(user != null){
                cart.setUser(user);
            }else {
                returnMap.put("message", "异常： 用户不存在!");
                returnMap.put("success", false);
                return returnMap;
            }
            Ticket ticket = ticketDao.findOne(" from Ticket t where t.id='"+ticketId+"' ");
            if(ticket != null){
                cart.setTicket(ticket);
            }else {
                returnMap.put("message", "异常： 门票不存在!");
                returnMap.put("success", false);
                return returnMap;
            }

            cart.setTicketNums(ticketNums);
            cart.setCreateTime(new Date());
            cartDao.save(cart);
        }

        returnMap.put("value", cart);
        returnMap.put("message", "将商品加入购物车成功");
        returnMap.put("success", true);
        return returnMap;
    }

    public Page<Cart> getGoodsListOfCart(Integer currentPage, Integer pageSize, Integer cartUserId) throws Exception {
        String queryHql = " from Cart c where c.user.id='"+cartUserId+"' and c.haveDone="+false+" ";
        String countHql = " select count(*) from Cart c where c.user.id='"+cartUserId+"' and c.haveDone="+false+" ";
        Page<Cart> page = cartDao.findPage(currentPage, pageSize, queryHql, countHql);

        return page;
    }

    public Map<String, Object> removeGoodsFromCart(Integer id, String cartGoodsArray) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Integer idhql = 0;
        //List<Cart> goodsCollection = new ArrayList<Cart>();
        List<Integer> goodsList = JavaStringUtil.stringToList(cartGoodsArray,"-");
        for(int i=0;i<goodsList.size();i++){
            idhql = goodsList.get(i);
            String hql = " from Cart c where c.id='"+idhql+"' and c.user.id='"+id+"' ";
            String delhql = " delete Cart c where c.id='"+idhql+"' and c.user.id='"+id+"' ";
            if(cartDao.findOne(hql) != null){
                cartDao.deleteWithHql(delhql);
            }
        }

        JavaStringUtil.setListInt(new ArrayList<Integer>());
        returnMap.put("message", "已从购物车中删除指定商品");
        returnMap.put("success", true);
        return returnMap;
    }

    @Override
    public Map<String, Object> editGoodsNums(Integer id, Integer cartId, Integer ticketNums) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Cart cart = cartDao.findOne(" from Cart c where c.user.id='"+id+"' and c.id='"+cartId+"' ");
        if(cart != null){
            cart.setTicketNums(ticketNums);
            cart.setCreateTime(new Date());
            cartDao.update(cart);

            returnMap.put("message", "编辑商品数量成功");
            returnMap.put("success", true);
        }else {
            returnMap.put("message", "错误");
            returnMap.put("success", false);
        }

        return returnMap;
    }

    @Override
    public Map<String, Object> getOneCartItem(Integer userId, Integer cartId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Cart cart = cartDao.findOne(" from Cart c where c.user.id='"+userId+"' and c.id='"+cartId+"' ");
        if(cart != null){
            returnMap.put("value", cart);
            returnMap.put("success", true);
        }else {
            returnMap.put("message", "错误");
            returnMap.put("success", false);
        }

        return returnMap;
    }

}
