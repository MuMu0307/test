package com.whut.work.comment.dao.impl;

import com.whut.work.base.dao.Impl.BaseDaoImpl;
import com.whut.work.comment.dao.ICommentDao;
import com.whut.work.comment.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements ICommentDao {
    public CommentDaoImpl(){
        super(Comment.class);
    }

}
