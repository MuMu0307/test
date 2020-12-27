package com.whut.work.goods.service.impl;

import com.whut.work.base.model.Page;
import com.whut.work.cart.dao.impl.CartDaoImpl;
import com.whut.work.goods.dao.impl.GoodsDaoImpl;
import com.whut.work.goods.model.Goods;
import com.whut.work.goods.service.IGoodsService;
import com.whut.work.order.dao.impl.OrderDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GoodsServiceImpl implements IGoodsService{

    @Autowired
    private GoodsDaoImpl goodsDao;
    @Autowired
    private CartDaoImpl cartDao;
    @Autowired
    private OrderDaoImpl orderDao;

    public Map<String, Object> addOneGoods(String name, String picture, Float unitPrice,
                                           String description, Integer ownerId, String ownerName) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();

        //String hql = "from Goods g where g.name='"+name+"'";
        Goods goods = new Goods();
        /*if(goodsDao.findOne(hql) != null){
            returnMap.put("message", "该商品已存在");
            returnMap.put("success", false);
            return returnMap;
        }else{*/
            goods.setName(name);
            goods.setPicture(picture);
            goods.setUnitPrice(unitPrice);
            goods.setDescription(description);
            goods.setSalesCount(0);
            goods.setOwnerId(ownerId);
            goods.setOwnerName(ownerName);
            goods.setCreateTime(new Date());

            goodsDao.save(goods);
            returnMap.put("value", goods);
            returnMap.put("message", "新增商品成功");
            returnMap.put("success", true);
            return returnMap;
        //}
    }

    public Page<Goods> getGoodsPageList(int currentPage, int pageSize,String blurGoodsName,String sortRuleArray) throws Exception {
        //List<String> conditions = new ArrayList<String>();
        String conditions = "";String conditionsforcount = "";
        System.out.println(sortRuleArray);
        if(!blurGoodsName.equals("") && blurGoodsName!=null){
            conditions += " where g.name like '%"+blurGoodsName+"%' ";
            conditionsforcount = conditions;
        }
        if(sortRuleArray.equals("000000")){
            conditions += " order by g.createTime desc ";
        }else if(sortRuleArray.equals("0p0100")){
            conditions += " order by g.unitPrice asc ";
        }else if(sortRuleArray.equals("0p1000")){
            conditions += " order by g.unitPrice desc ";
        }else if(sortRuleArray.equals("00tcr0")){
            conditions += " order by g.createTime desc ";
        }else if(sortRuleArray.equals("00trc0")){
            conditions += " order by g.createTime asc ";
        }else if(sortRuleArray.equals("000sms")){
            conditions += " order by g.salesCount desc ";
        }else if(sortRuleArray.equals("000ssm")){
            conditions += " order by g.salesCount asc ";
        }else {
            conditions += " order by g.createTime desc ";
        }
        String queryHql = " from Goods g "+conditions+" ";
        //System.out.println(queryHql);
        String countHql = " select count(*) from Goods g "+conditionsforcount+" ";
        Page<Goods> page = goodsDao.findPage(currentPage, pageSize, queryHql, countHql);

        return page;
    }

    public Page<Goods> getMyPubGoods(int currentPage, int pageSize, String blurGoodsName, Integer userId) throws Exception {
        String queryHql = " from Goods g where g.ownerId='"+userId+"' ";
        //System.out.println(queryHql);
        String countHql = " select count(*) from Goods g where g.ownerId='"+userId+"' ";
        Page<Goods> page = goodsDao.findPage(currentPage, pageSize, queryHql, countHql);

        return page;
    }

    public Map<String, Object> deleteOneGoods(Integer goodsId, Integer userId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        //String hql = " from Cart c where c.id='"+hql+"' ";

        String delhql_goods = " delete Goods where id='"+goodsId+"' ";
        goodsDao.deleteWithHql(delhql_goods);

        String delhql_cart = " delete Cart where goodsId='"+goodsId+"' ";
        cartDao.deleteWithHql(delhql_cart);

        String delhql_order = " delete Order where goodsId='"+goodsId+"' ";
        orderDao.deleteWithHql(delhql_order);

        returnMap.put("message", "已成功下架指定商品!");
        returnMap.put("success", true);
        return returnMap;
    }

}
