package com.whut.work.file.service;

import com.whut.work.file.model.File;

import java.util.List;

public interface IFileService {

    //获取文件列表
    public List<File> getFileList() throws Exception;
}
