package com.whut.work.scenic.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.scenic.dao.IScenicDao;
import com.whut.work.scenic.model.Scenic;
import org.springframework.stereotype.Component;

@Component
public class ScenicDaoImpl extends BaseDaoImpl<Scenic> implements IScenicDao {
public ScenicDaoImpl(){
        super(Scenic.class);
        }

        }
