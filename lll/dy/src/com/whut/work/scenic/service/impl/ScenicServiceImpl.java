package com.whut.work.scenic.service.impl;

import com.whut.work.base.model.Page;
import com.whut.work.cart.dao.ICartDao;
import com.whut.work.cart.model.Cart;
import com.whut.work.comment.dao.ICommentDao;
import com.whut.work.comment.model.Comment;
import com.whut.work.order.dao.IOrderDao;
import com.whut.work.order.model.Order;
import com.whut.work.praise.dao.IPraiseDao;
import com.whut.work.praise.model.Praise;
import com.whut.work.scenic.dao.IScenicDao;
import com.whut.work.scenic.model.Scenic;
import com.whut.work.scenic.service.IScenicService;
import com.whut.work.ticket.dao.ITicketDao;
import com.whut.work.ticket.model.Ticket;
import com.whut.work.user.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ScenicServiceImpl implements IScenicService {

    @Autowired
    private IScenicDao scenicDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ITicketDao ticketDao;
    @Autowired
    private ICommentDao commentDao;
    @Autowired
    private IPraiseDao praiseDao;
    @Autowired
    private ICartDao cartDao;
    /*@Autowired
    private IOrderDao orderDao;*/


    @Override
    public Page<Scenic> getScenicPageList(Integer currentPage, Integer pageSize, String blurScenicName, String orderType) throws Exception {
        String conditions = "";String conditionsforcount = "";
        //System.out.println(orderType);
        if(!blurScenicName.equals("")){
            conditions += " where s.scenicName like '%"+blurScenicName+"%' ";
            conditionsforcount = conditions;
        }
        if(orderType.equals("100000")){
            conditions += " order by s.praise desc ";
        }else if(orderType.equals("010000")){
            conditions += " order by s.praise asc ";
        }else {
            conditions += " order by s.praise desc ";
        }
        String queryHql = " from Scenic s "+conditions+" ";
        String countHql = " select count(*) from Scenic s "+conditionsforcount+" ";
        Page<Scenic> page = scenicDao.findPage(currentPage, pageSize, queryHql, countHql);

        return page;
    }

    @Override
    public Map<String,Object> getOneScenic(Integer id, Integer scenicId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();

        String hql = " from Scenic s where s.id='"+scenicId+"' ";
        Scenic scenic = scenicDao.findOne(hql);
        if(scenic != null){
            returnMap.put("value",scenic);
            returnMap.put("success",true);
        }else {
            returnMap.put("success",false);
        }

        return returnMap;
    }

    @Override
    public Map<String, Object> getOneScenicByTicketId(Integer id, Integer ticketId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();

        String hql = " from Scenic s where s.ticket.id='"+ticketId+"' ";
        Scenic scenic = scenicDao.findOne(hql);
        if(scenic != null){
            returnMap.put("value",scenic);
            returnMap.put("success",true);
        }else {
            returnMap.put("success",false);
        }

        return returnMap;
    }

    @Override
    public Map<String, Object> editLeaderOfScenic(Integer scenicId, Integer userId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();

        String hql = " from Scenic s where s.id='"+scenicId+"' ";
        Scenic scenic = scenicDao.findOne(hql);
        if(scenic != null){
            scenic.setUser(userDao.findOne(" from User u where u.id='"+userId+"' "));
            scenicDao.update(scenic);
            returnMap.put("value",scenic);
            returnMap.put("success",true);
        }else {
            returnMap.put("success",false);
        }

        return returnMap;
    }

    @Override
    public Map<String, Object> addOneScenic(String scenicName, String scenicDescription,
                                            String ticketName, String picture, Float unitPrice,
                                            String ticketDescription, Integer userId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();

        //先保存门票信息
        Ticket ticket = new Ticket();
        ticket.setTicketName(ticketName);
        ticket.setPicture(picture);
        ticket.setUnitPrice(unitPrice);
        ticket.setDescription(ticketDescription);
        ticket.setCreateTime(new Date());
        ticket.setSalesCount(0);
        ticketDao.save(ticket);

        //再保存景区信息
        Scenic scenic = new Scenic();
        scenic.setScenicName(scenicName);
        scenic.setDescription(scenicDescription);
        scenic.setTicket(ticket);
        scenic.setCreateTime(new Date());
        scenic.setPraise(0);
        if(userId <= 0){//不指定负责人
            scenicDao.save(scenic);
        }else {//指定负责人
            scenic.setUser(userDao.findOne(" from User u where u.id='"+userId+"' "));
            scenicDao.save(scenic);
        }

        returnMap.put("value",scenic);
        returnMap.put("success",true);
        return returnMap;
    }

    //不建议删除景区
    @Override
    public Map<String, Object> deleteOneScenic(Integer userId, Integer scenicId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Scenic scenic;
        if(userId == 8){
            scenic = scenicDao.findOne(" from Scenic s where s.id='"+scenicId+"' ");
        }else {
            scenic = scenicDao.findOne(" from Scenic s where s.id='"+scenicId+"' and  s.user.id='"+userId+"' ");
        }

        if(scenic != null || userId == 8){
            scenicDao.delete(scenic);

            //删除对应门票
            Ticket ticket = scenic.getTicket();
            if(ticket != null){
                ticketDao.delete(scenic.getTicket());
            }
            //删除对应评论
            Comment comment = commentDao.findOne(" from Comment c where c.scenic.id='"+scenicId+"' ");
            if(comment != null){
                commentDao.delete(comment);
            }
            //删除对应评分
            Praise praise = praiseDao.findOne(" from Praise p where p.scenic.id='"+scenicId+"' ");
            if(praise != null){
                praiseDao.delete(praise);
            }
            //删除对应购物车
            Cart cart = cartDao.findOne(" from Cart c where c.ticket.id='"+scenic.getTicket().getId()+"' and c.haveDone="+false+" ");
            if(cart != null){
                cartDao.delete(cart);
            }

            returnMap.put("value","删除成功");
            returnMap.put("success",true);
        }else {
            returnMap.put("value","删除失败");
            returnMap.put("success",false);
        }

        return returnMap;
    }

    @Override
    public Map<String, Object> editOneScenic(String scenicName, String scenicDescription,
                                             String ticketName, String picture, Float unitPrice,
                                             String ticketDescription, Integer userId, Integer scenicId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Scenic scenic = scenicDao.findOne(" from Scenic s where s.id='"+scenicId+"' ");
        if(scenic != null){
            //更新信息
            //先更新门票信息
            Ticket ticket = scenic.getTicket();
            ticket.setTicketName(ticketName);
            if(!picture.equals("")){
                ticket.setPicture(picture);
            }
            ticket.setUnitPrice(unitPrice);
            ticket.setDescription(ticketDescription);
            ticket.setCreateTime(new Date());
            //ticket.setSalesCount(0);
            ticketDao.update(ticket);
            //再更新景区信息
            scenic.setScenicName(scenicName);
            scenic.setDescription(scenicDescription);
            if(userId <= 0){//不指定负责人(不做操作)
                scenicDao.update(scenic);
            }else {//指定负责人
                scenic.setUser(userDao.findOne(" from User u where u.id='"+userId+"' "));
                scenicDao.update(scenic);
            }

            returnMap.put("value",scenic);
            returnMap.put("message","编辑成功");
            returnMap.put("success",true);
        }else {
            returnMap.put("message","编辑失败");
            returnMap.put("success",false);
        }

        return returnMap;
    }

    @Override
    public Map<String, Object> getOneScenicOfMine(Integer id) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Scenic scenic = scenicDao.findOne(" from Scenic s where s.user.id='"+id+"' ");
        if(scenic != null){
            returnMap.put("value",scenic);
            returnMap.put("message","获取成功");
            returnMap.put("success",true);
        }else {
            returnMap.put("message","错误");
            returnMap.put("success",false);
        }

        return returnMap;
    }

}
