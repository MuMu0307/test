package com.whut.work.praise.model;

import com.whut.work.comment.dao.ICommentDao;
import com.whut.work.scenic.model.Scenic;
import com.whut.work.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="praise")
public class Praise {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer praise;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="scenic_id")
    private Scenic scenic;
    @Column(name="create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Scenic getScenic() {
        return scenic;
    }

    public void setScenic(Scenic scenic) {
        this.scenic = scenic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
