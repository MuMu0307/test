package com.whut.work.file.vo;

import java.util.Date;

public class FileVo {
    private Integer id;
    private String fileName;
    private String suffix;
    private String description;
    private Date createTime;
    private String location;

    public FileVo() {
    }

    public FileVo(Integer id, String fileName, String suffix, String description, Date createTime, String location) {
        this.id = id;
        this.fileName = fileName;
        this.suffix = suffix;
        this.description = description;
        this.createTime = createTime;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
