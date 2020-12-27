package com.whut.work.praise.service.impl;

import com.whut.work.praise.dao.impl.PraiseDaoImpl;
import com.whut.work.praise.model.Praise;
import com.whut.work.praise.service.IPraiseService;
import com.whut.work.scenic.dao.impl.ScenicDaoImpl;
import com.whut.work.scenic.model.Scenic;
import com.whut.work.user.dao.impl.UserDaoImpl;
import com.whut.work.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class PraiseServiceImpl implements IPraiseService{

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private ScenicDaoImpl scenicDao;
    @Autowired
    private PraiseDaoImpl praiseDao;

    @Override
    public Map<String, Object> addOnePraise(Integer userId, Integer praise, Integer scenicId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        User user = userDao.findOne(" from User u where u.id='"+userId+"' ");
        Scenic scenic = scenicDao.findOne(" from Scenic s where s.id='"+scenicId+"' ");
        Praise praiseTemp = praiseDao.findOne(" from Praise p where p.user.id='"+userId+"' and p.scenic.id='"+scenicId+"' " );
        if(praiseTemp == null && user != null && scenic != null){
            praiseTemp = new Praise();
            praiseTemp.setCreateTime(new Date());
            praiseTemp.setUser(user);
            praiseTemp.setScenic(scenic);
            praiseTemp.setPraise(praise);
            praiseDao.save(praiseTemp);

            scenic.setPraise(scenic.getPraise()+praise);//增加评分（更新）
            scenicDao.update(scenic);

            returnMap.put("message", "已成功评分!");
            returnMap.put("success", true);
        }else if(praiseTemp != null){
            praiseTemp.setPraise(praiseTemp.getPraise()+praise);
            praiseDao.update(praiseTemp);
            scenic.setPraise(scenic.getPraise()+praise);//增加评分（更新）
            scenicDao.update(scenic);

            returnMap.put("message", "已成功评分!");
            returnMap.put("success", true);
        }

        return returnMap;
    }

    @Override
    public Map<String, Object> getOnePraise(Integer userId, Integer scenicId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Praise praise = praiseDao.findOne(" from Praise p where p.user.id='"+userId+"' and p.scenic.id='"+scenicId+"' ");
        if(praise != null){
            returnMap.put("value", praise);
            returnMap.put("message", "已评分");
            returnMap.put("success", true);
        }else {
            returnMap.put("value", praise);
            returnMap.put("message", "未评分");
            returnMap.put("success", false);
        }

        return returnMap;
    }

}
