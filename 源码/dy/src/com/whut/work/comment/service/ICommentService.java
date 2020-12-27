package com.whut.work.comment.service;

import com.whut.work.base.model.Page;
import com.whut.work.comment.model.Comment;

import java.util.Map;

public interface ICommentService {

    //增加一条对一个景区的评论
    public Map<String,Object> addOneComment(Integer userId,Integer scenicId,String text) throws Exception;

    //获取对一个景区的评论
    public Page<Comment> getCommentsOfScenic(Integer currentPage, Integer pageSize,Integer scenicId) throws Exception;

    //删除一条对一个景区的评论
    public Map<String,Object> deleteOneComment(Integer userId,Integer commentId) throws Exception;

}
