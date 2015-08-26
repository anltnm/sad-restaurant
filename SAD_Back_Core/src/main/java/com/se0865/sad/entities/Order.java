package com.se0865.sad.entities;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by AnLTNM-SE60906 on 18/07/2015.
 */
@Entity
@Table(name = "sad_order")
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private long orderId;

    @Column(name = "local_order_id", nullable = false)
    private int localOrderId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "closed_date")
    private Long closedDate;

    @Column(name = "last_updated")
    private Long lastUpdate;

    @Column(name = "order_description")
    private String description;

    @Column(name = "order_status")
    private int status;

    @Column(name = "order_customer_quantity")
    private int cusQuantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_id")
    private SadTable sadTable;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderDetail> orderDetailList;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Long closedDate) {
        this.closedDate = closedDate;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
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

    public SadTable getSadTable() {
        return sadTable;
    }

    public void setSadTable(SadTable sadTable) {
        this.sadTable = sadTable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLocalOrderId() {
        return localOrderId;
    }

    public void setLocalOrderId(int localOrderId) {
        this.localOrderId = localOrderId;
    }

    public int getCusQuantity() {
        return cusQuantity;
    }

    public void setCusQuantity(int cusQuantity) {
        this.cusQuantity = cusQuantity;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
