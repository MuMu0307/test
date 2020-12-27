package com.whut.work.resource.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.resource.dao.IRoleResourceDao;
import com.whut.work.resource.model.RoleResource;
import org.springframework.stereotype.Component;

@Component
public class RoleResourceDaoImpl extends BaseDaoImpl<RoleResource> implements IRoleResourceDao {
    public RoleResourceDaoImpl(){
        super(RoleResource.class);
    }
}
