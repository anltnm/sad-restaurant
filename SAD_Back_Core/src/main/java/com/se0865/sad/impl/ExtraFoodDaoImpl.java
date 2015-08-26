package com.se0865.sad.impl;

import com.se0865.sad.dao.AbstractDAO;
import com.se0865.sad.dao.ExtraFoodDao;
import com.se0865.sad.entities.ExtraFoodDetail;
import com.se0865.sad.entities.ExtraFoodGroup;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 20/07/2015.
 */
public class ExtraFoodDaoImpl extends AbstractDAO<ExtraFoodDetail, Long> implements ExtraFoodDao {

    public List<ExtraFoodDetail> getAll() {
        String query = "SELECT ex FROM ExtraFoodDetail ex WHERE ex.status = 1";
        Query query1 = currentSession().createQuery(query);
        return query1.list();
    }

    public List<ExtraFoodDetail> getAllOrderByExtraFoodName() {
        String query = "SELECT ex FROM ExtraFoodDetail ex WHERE ex.status = 1 ORDER BY ex.name asc ";
        Query query1 = currentSession().createQuery(query);
        return query1.list();
    }

    public List<ExtraFoodDetail> getExtraFoodByExtraFoodName(String name) {
        String query = "SELECT ex FROM ExtraFoodDetail ex WHERE ex.status = 1 AND ex.name LIKE :foodName ORDER BY ex.name asc ";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("foodName", new StringBuilder("%").append(name).append("%").toString());
        return query1.list();
    }

    public List<ExtraFoodDetail> getExtraFoodByExtraFoodGroupName(String groupName) {
        String query = "SELECT ex FROM ExtraFoodDetail ex WHERE ex.status = 1 AND ex.extraFoodGroup.name LIKE :groupName ORDER BY ex.name asc ";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("groupName", new StringBuilder("%").append(groupName).append("%").toString());
        return query1.list();
    }

    public List<ExtraFoodDetail> getExtraFoodDetailsByGroup(ExtraFoodGroup extraFoodGroup) {
        String q = "SELECT e FROM ExtraFoodDetail e WHERE e.extraFoodGroup = :groupId";
        Query query = currentSession().createQuery(q);
        query.setParameter("groupId", extraFoodGroup);
        return query.list();
    }

    public List<ExtraFoodDetail> getExtraFoodDetailList(long lastUpdate) {
        String q = "SELECT e FROM ExtraFoodDetail e WHERE e.lastUpdate >:lastUpdate  order by e.lastUpdate";
        Query query = currentSession().createQuery(q);
        query.setParameter("lastUpdate", lastUpdate);
        return query.list();
    }
}
