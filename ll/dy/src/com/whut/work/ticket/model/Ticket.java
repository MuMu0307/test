package com.whut.work.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whut.work.scenic.model.Scenic;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="ticket_name")
    private String ticketName;
    private String picture;
    private String description;
    @Column(name="unit_price")
    private Float unitPrice;
    /*@JsonIgnore
    @OneToOne(mappedBy = "ticket",fetch = FetchType.LAZY)
    private Scenic scenic;*/
    @Column(name="sales_count")
    private Integer salesCount;
    @Column(name="create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /*public Scenic getScenic() {
        return scenic;
    }

    public void setScenic(Scenic scenic) {
        this.scenic = scenic;
    }*/
}
