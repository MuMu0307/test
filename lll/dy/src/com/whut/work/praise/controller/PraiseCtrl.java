package com.whut.work.praise.controller;

import com.whut.work.base.model.Page;
import com.whut.work.comment.model.Comment;
import com.whut.work.comment.service.ICommentService;
import com.whut.work.praise.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/praise")
public class PraiseCtrl {

    @Autowired
    private IPraiseService praiseService;

    @RequestMapping(value="/addOnePraise",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOnePraise(HttpServletRequest request,Integer userId,Integer praise,Integer scenicId){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> cartResult = praiseService.addOnePraise(userId, praise, scenicId);

            returnMap.put("value", cartResult.get("value"));
            returnMap.put("message", cartResult.get("message"));
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value="/getOnePraise",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getOnePraise(HttpServletRequest request,Integer userId,Integer praise,Integer scenicId){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> cartResult = praiseService.addOnePraise(userId, praise, scenicId);

            returnMap.put("value", cartResult.get("value"));
            returnMap.put("message", cartResult.get("message"));
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

}
