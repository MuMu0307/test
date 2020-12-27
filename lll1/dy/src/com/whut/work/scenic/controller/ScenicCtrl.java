package com.whut.work.scenic.controller;

import com.whut.work.base.model.Page;
import com.whut.work.scenic.model.Scenic;
import com.whut.work.scenic.service.IScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/scenic")
public class ScenicCtrl {

    @Autowired
    private IScenicService scenicService;

    @RequestMapping(value="/getScenicPageList",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getScenicPageList(HttpServletRequest request,Integer currentPage, Integer pageSize, String blurScenicName, String orderType){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            Page<Scenic> page = scenicService.getScenicPageList(currentPage,pageSize,blurScenicName,orderType);

            returnMap.put("page", page);
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/getOneScenic",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getOneScenic(HttpServletRequest request,Integer id, Integer scenicId){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            Map<String,Object> map = scenicService.getOneScenic(id,scenicId);

            Object object = map.get("value");
            returnMap.put("value", object);
            returnMap.put("success", map.get("success"));
        } catch (Exception e) {
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/getOneScenicByTicketId",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getOneScenicByTicketId(HttpServletRequest request,Integer id, Integer ticketId){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            Map<String,Object> map = scenicService.getOneScenicByTicketId(id,ticketId);

            Object object = map.get("value");
            returnMap.put("value", object);
            returnMap.put("success", map.get("success"));
        } catch (Exception e) {
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/editLeaderOfScenic",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editLeaderOfScenic(HttpServletRequest request,Integer id, Integer scenicId,Integer userId){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            Map<String,Object> map = scenicService.editLeaderOfScenic(scenicId, userId);

            Object object = map.get("value");
            returnMap.put("value", object);
            returnMap.put("success", map.get("success"));
        } catch (Exception e) {
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/addOneScenic",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOneScenic(HttpServletRequest request,String scenicName, String scenicDescription,
                                           String ticketName, String picture, Float unitPrice,
                                           String ticketDescription, Integer userId){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            Map<String,Object> map = scenicService.addOneScenic(scenicName,scenicDescription,ticketName,
                                                    picture,unitPrice,ticketDescription,userId);

            Object object = map.get("value");
            returnMap.put("value", object);
            returnMap.put("success", map.get("success"));
        } catch (Exception e) {
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/deleteOneScenic",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteOneScenic(HttpServletRequest request,Integer userId, Integer scenicId){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            Map<String,Object> map = scenicService.deleteOneScenic(userId,scenicId);

            Object object = map.get("value");
            returnMap.put("value", object);
            returnMap.put("success", map.get("success"));
        } catch (Exception e) {
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/editOneScenic",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editOneScenic(HttpServletRequest request,String scenicName, String scenicDescription,
                                            String ticketName, String picture, Float unitPrice,
                                            String ticketDescription, Integer userId, Integer scenicId){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            Map<String,Object> map = scenicService.editOneScenic(scenicName,scenicDescription,
                    ticketName,picture,unitPrice,ticketDescription,userId,scenicId);

            Object object = map.get("value");
            returnMap.put("value", object);
            returnMap.put("success", map.get("success"));
        } catch (Exception e) {
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/getOneScenicOfMine",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getOneScenicOfMine(HttpServletRequest request,Integer id){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            Map<String,Object> map = scenicService.getOneScenicOfMine(id);

            Object object = map.get("value");
            returnMap.put("value", object);
            returnMap.put("success", map.get("success"));
        } catch (Exception e) {
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

}
