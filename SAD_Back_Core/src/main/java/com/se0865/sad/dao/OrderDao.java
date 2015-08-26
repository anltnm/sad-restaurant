package com.se0865.sad.dao;

import com.se0865.sad.entities.Order;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 27/07/2015.
 */
public interface OrderDao {
    public List<Order> getListOrder(long lastUpdate);
}
