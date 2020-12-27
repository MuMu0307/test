package com.whut.work.comment.service.impl;

import com.whut.work.base.model.Page;
import com.whut.work.comment.dao.impl.CommentDaoImpl;
import com.whut.work.comment.model.Comment;
import com.whut.work.comment.service.ICommentService;
import com.whut.work.scenic.dao.IScenicDao;
import com.whut.work.scenic.model.Scenic;
import com.whut.work.user.dao.impl.UserDaoImpl;
import com.whut.work.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentDaoImpl commentDao;
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private IScenicDao scenicDao;

    @Override
    public Map<String, Object> addOneComment(Integer userId, Integer scenicId, String text) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();

        Comment comment = commentDao.findOne(" from Comment c where c.user.id='"+userId+"' and c.scenic.id='"+scenicId+"' ");
        if(comment != null){
            comment.setText(text);
            comment.setCreateTime(new Date());
            commentDao.update(comment);
            returnMap.put("value", comment);
            returnMap.put("message", "已成功评论!");
            returnMap.put("success", true);
        }else {
            comment = new Comment();
            User user = userDao.findOne(" from User u where u.id='"+userId+"' ");
            Scenic scenic = scenicDao.findOne(" from Scenic s where s.id='"+scenicId+"' ");
            if(user != null && scenic != null){
                comment.setUser(user);
                comment.setScenic(scenic);
                comment.setText(text);
                comment.setCreateTime(new Date());
                commentDao.save(comment);

                returnMap.put("value", comment);
                returnMap.put("message", "已成功评论!");
                returnMap.put("success", true);
            }else {
                returnMap.put("message", "评论失败!");
                returnMap.put("success", false);
            }
        }

        return returnMap;
    }

    @Override
    public Page<Comment> getCommentsOfScenic(Integer currentPage, Integer pageSize,Integer scenicId) throws Exception {
        String hql = " from Comment c where c.scenic.id='" + scenicId + "' ";
        String hqlCount = "select count(*) from Comment c where c.scenic.id='" + scenicId + "' ";

        Page<Comment> commentPage = commentDao.findPage(currentPage,pageSize,hql,hqlCount);
        return commentPage;
    }

    @Override
    public Map<String, Object> deleteOneComment(Integer userId, Integer commentId) throws Exception {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Comment comment = commentDao.findOne(" from Comment c where c.id='"+commentId+"' ");
        if(userId == 8 || comment.getUser().getId().equals(userId)){
            commentDao.delete(comment);
            returnMap.put("message", "操作成功!");
            returnMap.put("success", false);
        }else {
            returnMap.put("message", "权限不够!");
            returnMap.put("success", false);
        }

        return returnMap;
    }

}
