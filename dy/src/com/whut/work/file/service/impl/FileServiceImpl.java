package com.whut.work.file.service.impl;

import com.whut.work.file.dao.impl.FileDaoImpl;
import com.whut.work.file.model.File;
import com.whut.work.file.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileServiceImpl implements IFileService {

    @Autowired
    private FileDaoImpl fileDao;

    @Override
    public List<File> getFileList() throws Exception {
        String queryHql = " select new com.whut.work.file.vo.FileVo(f.id,f.name,f.suffix,f.description," +
                "f.createTime,f.location) from File f where f.id>0 ";
        List<File> returnPage = fileDao.findList(queryHql);

        return returnPage;
    }
}
