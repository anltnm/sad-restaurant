package com.se0865.sad.entities;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by AnLTNM-SE60906 on 18/07/2015.
 */
@Entity
@Table(name = "sad_order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_detail_id", unique = true, nullable = false)
    private long orderDetailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "local_order_detail_id", nullable = false)
    private int localOrderDetailId;

    @Column(name = "order_detail_quantity")
    private int quantity;

    @Column(name = "order_detail_description")
    private String description;

    @Column(name = "order_detail_food_order")
    private int foodOrder;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "extra_foods")
    private List<ExtraFoodDetail> extraFoodList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(name = "created_date")
    private long createdDate;

    @Column(name = "last_updated")
    private long lastUpdate;

    @Column(name = "order_detail_status")
    private int status;

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExtraFoodDetail> getExtraFoodList() {
        return extraFoodList;
    }

    public void setExtraFoodList(List<ExtraFoodDetail> extraFoodList) {
        this.extraFoodList = extraFoodList;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(int foodOrder) {
        this.foodOrder = foodOrder;
    }

    public int getLocalOrderDetailId() {
        return localOrderDetailId;
    }

    public void setLocalOrderDetailId(int localOrderDetailId) {
        this.localOrderDetailId = localOrderDetailId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
