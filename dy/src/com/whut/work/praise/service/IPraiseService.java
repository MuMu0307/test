package com.whut.work.praise.service;

import java.util.Map;

public interface IPraiseService {

    //对景区进行评分
    public Map<String,Object> addOnePraise(Integer userId,Integer praise,Integer scenicId) throws Exception;

    //获取评分
    public Map<String,Object> getOnePraise(Integer userId,Integer scenicId) throws Exception;

}
