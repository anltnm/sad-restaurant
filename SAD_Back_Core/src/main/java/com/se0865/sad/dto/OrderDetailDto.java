package com.se0865.sad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 21/07/2015.
 */
public class OrderDetailDto {
    private long orDetailId;
    private long orDetailServerId;
    private int orderId;
    private int foodId;
    private int quantity;
    private double foodPrice;
    private int order;
    private int status;
    private String description;
    private String foodName;

    @JsonProperty("extraFoods")
    private List<Integer> idExtraFoods;

    public long getOrDetailId() {
        return orDetailId;
    }

    public void setOrDetailId(long orDetailId) {
        this.orDetailId = orDetailId;
    }

    public long getOrDetailServerId() {
        return orDetailServerId;
    }

    public void setOrDetailServerId(long orDetailServerId) {
        this.orDetailServerId = orDetailServerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getIdExtraFoods() {
        return idExtraFoods;
    }

    public void setIdExtraFoods(List<Integer> idExtraFoods) {
        this.idExtraFoods = idExtraFoods;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
