package com.se0865.sad.impl;

import com.se0865.sad.dao.AbstractDAO;
import com.se0865.sad.entities.ExtraFoodGroup;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 20/07/2015.
 */
public class ExtraFoodGroupDaoImpl extends AbstractDAO<ExtraFoodGroup, Long> {

    public List<ExtraFoodGroup> getAll() {
        String query = "SELECT ex FROM ExtraFoodGroup ex WHERE ex.status = 1 order by ex.name";
        Query query1 = currentSession().createQuery(query);
        return query1.list();
    }

    public List<ExtraFoodGroup> getExtraFoodGroupByName(String name) {
        String query = "SELECT ex FROM ExtraFoodGroup ex WHERE ex.status = 1 AND ex.name LIKE :extraFoodGroupName";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("extraFoodGroupName", new StringBuilder("%").append(name).append("%").toString());
        return query1.list();
    }
}
