package com.whut.work.cart.model;

import com.whut.work.ticket.model.Ticket;
import com.whut.work.user.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    @JoinColumn(name="cart_user_id")
    private User user;
    @Column(name="create_time")
    private Date createTime;
    @OneToOne
    @JoinColumn(name="ticket_id")
    private Ticket ticket;
    @Column(name="ticket_nums")
    private Integer ticketNums;
    @Column(name="have_done")
    private boolean haveDone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getTicketNums() {
        return ticketNums;
    }

    public void setTicketNums(Integer ticketNums) {
        this.ticketNums = ticketNums;
    }

    public boolean isHaveDone() {
        return haveDone;
    }

    public void setHaveDone(boolean haveDone) {
        this.haveDone = haveDone;
    }

}
