package com.whut.work.file.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.file.dao.IFileDao;
import com.whut.work.file.model.File;
import org.springframework.stereotype.Component;

@Component
public class FileDaoImpl extends BaseDaoImpl<File> implements IFileDao {
    public FileDaoImpl(){
        super(File.class);
    }

}
