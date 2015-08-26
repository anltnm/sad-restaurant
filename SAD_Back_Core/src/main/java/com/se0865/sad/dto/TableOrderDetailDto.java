package com.se0865.sad.dto;

import com.se0865.sad.entities.SadTable;
import com.se0865.sad.entities.User;

import java.util.Date;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 05/08/2015.
 */
public class TableOrderDetailDto {
    private long orderId;
    private User user;
    private Date createdDate;
    private Date closedDate;
    private Date lastUpdate;
    private String description;
    private int status;
    private int cusQuantity;
    private SadTable sadTable;
    private double totalOrder;
    private List<OrderDetailForBomDto> orderDetails;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCusQuantity() {
        return cusQuantity;
    }

    public void setCusQuantity(int cusQuantity) {
        this.cusQuantity = cusQuantity;
    }

    public SadTable getSadTable() {
        return sadTable;
    }

    public void setSadTable(SadTable sadTable) {
        this.sadTable = sadTable;
    }

    public double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(double totalOrder) {
        this.totalOrder = totalOrder;
    }

    public List<OrderDetailForBomDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailForBomDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
