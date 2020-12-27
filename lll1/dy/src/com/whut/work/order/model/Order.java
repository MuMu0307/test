package com.whut.work.order.model;

import com.whut.work.cart.model.Cart;
import com.whut.work.ticket.model.Ticket;
import com.whut.work.user.model.User;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="order_form")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name="user_id")
    private User user;
    @Column(name="create_time")
    private Date createTime;
    @Column(name="have_done")
    private boolean haveDone;
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name="order_cart",
            joinColumns={ @JoinColumn(name="order_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="cart_id",referencedColumnName="id")})
    private List<Cart> carts = new ArrayList<Cart>();

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

    public boolean isHaveDone() {
        return haveDone;
    }

    public void setHaveDone(boolean haveDone) {
        this.haveDone = haveDone;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
