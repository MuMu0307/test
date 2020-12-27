package com.whut.work.cart.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.cart.dao.ICartDao;
import com.whut.work.cart.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartDaoImpl extends BaseDaoImpl<Cart> implements ICartDao {
    public CartDaoImpl(){
        super(Cart.class);
    }

}
