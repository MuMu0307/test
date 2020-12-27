package com.whut.work.order.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.order.dao.IOrderDao;
import com.whut.work.order.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDaoImpl extends BaseDaoImpl<Order> implements IOrderDao {
    public OrderDaoImpl(){
        super(Order.class);
    }

}