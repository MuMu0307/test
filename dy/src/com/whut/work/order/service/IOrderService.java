package com.whut.work.order.service;

import com.whut.work.base.model.Page;
import com.whut.work.order.model.Order;

import java.util.Map;

public interface IOrderService {

    //生成订单
    public Map<String,Object> addOneOrder(Integer id, String orderGoodsArray) throws Exception;

    //获取订单
    public Page<Order> getOrders(int currentPage, int pageSize, Integer userId, String orderType) throws Exception;

    //删除订单
    public Map<String,Object> deleteOneOrder(Integer id, String orderId) throws Exception;

    //支付订单
    public Map<String,Object> completeOrder(Integer id, String orderId) throws Exception;

}
