package com.se0865.sad.dto;

import com.se0865.sad.entities.ExtraFoodDetail;
import com.se0865.sad.entities.Food;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 21/07/2015.
 */
public class OrderDetailForBomDto {
    private long orDetailId;
    private long orderId;
    private Food food;
    private int quantity;
    private double foodPrice;
    private int order;
    private String description;
    private List<ExtraFoodDetail> extraFoods;

    public long getOrDetailId() {
        return orDetailId;
    }

    public void setOrDetailId(long orDetailId) {
        this.orDetailId = orDetailId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
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

    public List<ExtraFoodDetail> getExtraFoods() {
        return extraFoods;
    }

    public void setExtraFoods(List<ExtraFoodDetail> extraFoods) {
        this.extraFoods = extraFoods;
    }
}
