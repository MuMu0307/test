package com.whut.work.praise.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.praise.dao.IPraiseDao;
import com.whut.work.praise.model.Praise;
import org.springframework.stereotype.Component;

@Component
public class PraiseDaoImpl extends BaseDaoImpl<Praise> implements IPraiseDao {
    public PraiseDaoImpl(){
        super(Praise.class);
    }

}