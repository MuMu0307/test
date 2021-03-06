package com.whut.work.resource.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.resource.dao.IResourceDao;
import com.whut.work.resource.model.Resource;
import org.springframework.stereotype.Component;

@Component
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements IResourceDao {

    public ResourceDaoImpl(){
        super(Resource.class);
    }
}
