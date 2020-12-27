package com.whut.work.scenic.model;

import com.whut.work.ticket.model.Ticket;
import com.whut.work.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="scenic")
public class Scenic {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="scenic_name")
    private String scenicName;
    private String description;
    //景区门票
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    //景区负责人
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name="create_time")
    private Date createTime;
    private Integer praise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }
}
