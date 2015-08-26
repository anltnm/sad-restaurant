package com.se0865.sad.dao;

import com.se0865.sad.entities.Food;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 20/07/2015.
 */
public interface FoodDao {
    public List<Food> getAllEnableFood();
    public List<Food> getAllDisableFoodInOnlineOrderDetail();
}
