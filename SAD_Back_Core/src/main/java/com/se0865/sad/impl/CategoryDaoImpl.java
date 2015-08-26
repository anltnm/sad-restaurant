package com.se0865.sad.impl;

import com.se0865.sad.dao.AbstractDAO;
import com.se0865.sad.dao.CategoryDao;
import com.se0865.sad.entities.Category;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 30/07/2015.
 */
public class CategoryDaoImpl extends AbstractDAO<Category, Long> implements CategoryDao {

    public List<Category> getAll() {
        String query = "SELECT c FROM Category c WHERE c.status = 1";
        Query query1 = currentSession().createQuery(query);
        return query1.list();
    }
    public List<Category> getAllOrderByCategoryName() {
        String query = "SELECT c FROM Category c WHERE c.status = 1 ORDER BY c.name ASC ";
        Query query1 = currentSession().createQuery(query);
        return query1.list();
    }
    public List<Category> searchCategoryByName(String name) {
        String query = "SELECT c from Category c WHERE c.status = 1 AND c.name LIKE :categoryName ORDER BY c.name ASC ";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("categoryName", new StringBuilder("%").append(name).append("%").toString());
        return query1.list();
    }

}
