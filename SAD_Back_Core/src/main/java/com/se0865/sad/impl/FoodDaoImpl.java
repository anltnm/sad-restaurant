package com.se0865.sad.impl;

import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dao.AbstractDAO;
import com.se0865.sad.dao.FoodDao;
import com.se0865.sad.entities.Food;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 20/07/2015.
 */
public class FoodDaoImpl extends AbstractDAO<Food, Long> implements FoodDao {

    public List<Food> getAllEnableFood() {
        String query = "SELECT e FROM Food e WHERE e.status = 1 ORDER BY e.lastUpdate ASC";
        Query query1 = currentSession().createQuery(query);
        return query1.list();
    }

    public List<Food> getAllDisableFoodInOnlineOrderDetail() {
        String query = "SELECT e.food FROM OrderDetail e WHERE e.order.status = 1 AND e.food.status =:status  ORDER BY e.food.lastUpdate ASC";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("status", ConstantManager.STATUS_DISABLE);
        return query1.list();
    }

    public List<Food> getLastUpdateFood(long lastUpdate) {
        String query = "SELECT e FROM Food e WHERE e.lastUpdate > :lastUpdate ORDER BY e.lastUpdate ASC";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("lastUpdate", lastUpdate);
        return query1.list();
    }

    public List<Food> getFoodByCategory(long catId) {
        String query = "SELECT e FROM Food e WHERE e.status = 1 AND e.category.id = :catId ORDER BY  e.name ASC ";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("catId", catId);
        return query1.list();
    }

    public List<Food> getAllOrderByFoodName() {
        //http://stackoverflow.com/questions/5816417/how-to-properly-express-jpql-join-fetch-with-where-clause-as-jpa-2-criteriaq
        String query = "SELECT f FROM Food f WHERE f.status = 1 AND f.category.status = 1" +
                "  ORDER BY f.name ASC";
        Query query1 = currentSession().createQuery(query);
        return query1.list();
    }

    public List<Food> searchFoodByName(String foodName) {
        String query = "SELECT e FROM Food e WHERE e.status = 1 AND e.category.status = 1 AND e.name LIKE :foodName ORDER BY e.name ASC";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("foodName", new StringBuilder("%").append(foodName).append("%").toString());
        return query1.list();
    }

    public List<Food> searchFoodByCategoryName(String categoryName) {
        String query = "SELECT e FROM Food e WHERE e.status = 1 AND e.category.status = 1 AND e.category.name LIKE :categoryName ORDER BY e.name ASC";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("categoryName", new StringBuilder("%").append(categoryName).append("%").toString());
        return query1.list();
    }
}
