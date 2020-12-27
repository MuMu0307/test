package com.whut.work.scenic.service;

import com.whut.work.base.model.Page;
import com.whut.work.order.model.Order;
import com.whut.work.scenic.model.Scenic;

import java.util.Map;

public interface IScenicService {

    //获取景区分页列表
    public Page<Scenic> getScenicPageList(Integer currentPage, Integer pageSize, String blurScenicName, String orderType) throws Exception;

    //获取一个景区的信息
    public Map<String,Object> getOneScenic(Integer id, Integer scenicId) throws Exception;

    //获取一个景区的信息by ticketId
    public Map<String,Object> getOneScenicByTicketId(Integer id, Integer ticketId) throws Exception;

    //指定景区负责人
    public Map<String,Object> editLeaderOfScenic(Integer scenicId,Integer userId) throws Exception;

    //增加一个景区
    public Map<String,Object> addOneScenic(String scenicName,String scenicDescription,String ticketName,
                                           String picture,Float unitPrice,String ticketDescription,Integer userId) throws Exception;

    //删除一个景区
    public Map<String,Object> deleteOneScenic(Integer userId, Integer scenicId) throws Exception;

    //编辑一个景区
    public Map<String,Object> editOneScenic(String scenicName,String scenicDescription,String ticketName,
                                            String picture,Float unitPrice,String ticketDescription,Integer userId, Integer scenicId) throws Exception;

    //获取负责人所管理的景区的信息
    public Map<String,Object> getOneScenicOfMine(Integer id) throws Exception;

}
