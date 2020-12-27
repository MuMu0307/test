package com.whut.work.cart.service;

import com.whut.work.base.model.Page;
import com.whut.work.cart.model.Cart;

import java.util.Map;

public interface ICartService {

    //将商品加入购物车
    public Map<String,Object> addToCart(Integer id,Integer ticketId,Integer ticketNums) throws Exception;

    //获取购物车中的商品
    public Page<Cart> getGoodsListOfCart(Integer currentPage,
                                         Integer pageSize, Integer cartUserId) throws Exception;

    //从购物车的中移除商品
    public Map<String,Object> removeGoodsFromCart(Integer id, String cartGoodsArray) throws Exception;

    //编辑商品数量
    public Map<String,Object> editGoodsNums(Integer id,Integer cartId,Integer ticketNums) throws Exception;

    //获取一条记录（主要是为了获取购买数量）
    public Map<String,Object> getOneCartItem(Integer userId,Integer cartId) throws Exception;

}
