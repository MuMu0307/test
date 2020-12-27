package com.whut.work.goods.service;

import com.whut.work.base.model.Page;
import com.whut.work.goods.model.Goods;

import java.util.Map;

public interface IGoodsService {

    //新增商品
    public Map<String,Object> addOneGoods(String name, String picture, Float unitPrice,
                                          String description, Integer ownerId, String ownerName) throws Exception;

    //获取商品分页列表
    public Page<Goods> getGoodsPageList(int currentPage, int pageSize, String blurGoodsName, String sortRuleArray) throws Exception;

    //获取我发布的商品
    public Page<Goods> getMyPubGoods(int currentPage, int pageSize, String blurGoodsName, Integer userId) throws Exception;

    //删除我发布的商品
    public Map<String,Object> deleteOneGoods(Integer goodsId, Integer userId) throws Exception;

}
