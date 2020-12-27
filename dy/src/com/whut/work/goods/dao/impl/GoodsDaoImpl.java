package com.whut.work.goods.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.goods.dao.IGoodsDao;
import com.whut.work.goods.model.Goods;
import org.springframework.stereotype.Component;

@Component
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements IGoodsDao {
    public GoodsDaoImpl(){
        super(Goods.class);
    }

}