package com.whut.work.comment.controller;

import com.whut.work.base.model.Page;
import com.whut.work.comment.model.Comment;
import com.whut.work.comment.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentCtrl {

    @Autowired
    private ICommentService commentService;

    @RequestMapping(value="/addOneComment",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOneComment(HttpServletRequest request,Integer userId,Integer scenicId,String text){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Map<String,Object> cartResult = commentService.addOneComment(userId, scenicId, text);

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

    @RequestMapping(value="/getCommentsOfScenic",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCommentsOfScenic(HttpServletRequest request,Integer currentPage,Integer pageSize,Integer scenicId){
        Map<String,Object> returnMap = new HashMap<String,Object>();

        try {
            Page<Comment> cartResult = commentService.getCommentsOfScenic(currentPage,pageSize,scenicId);

            returnMap.put("page", cartResult);
            returnMap.put("message", "获取成功");
            returnMap.put("success", true);
        } catch (Exception e) {
            returnMap.put("message", "异常：操作失败!");
            returnMap.put("success", false);
            e.printStackTrace();
        }
        return returnMap;
    }

}
