package com.se0865.sad.impl;

import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dao.AbstractDAO;
import com.se0865.sad.dao.OrderDao;
import com.se0865.sad.entities.Order;
import org.hibernate.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 27/07/2015.
 */
public class OrderDaoImpl extends AbstractDAO<Order, Long> implements OrderDao {

    public List<Order> getListOrder(long lastUpdate) {
        Query query = currentSession().createQuery("select o from Order o WHERE o.lastUpdate > :lastUpdate order by o.lastUpdate asc ");
        query.setParameter("lastUpdate", lastUpdate);
        return query.list();
    }

    public List<Order> getListOrderByOpeningAndRequestPayment() {

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Query query = currentSession().createQuery("select o from Order o WHERE o.lastUpdate > :today AND o.status != :status order by o.lastUpdate asc ");
        long today = Long.valueOf(new Date(df.format(new Date())).getTime());
        query.setParameter("today", today);
        query.setParameter("status", ConstantManager.TABLE_STATUS_CLOSE);
        return query.list();
    }

    public List<Order> getListOrderByCurrentDate(long tableId) {
        Query query = currentSession().createQuery("select o from Order o where o.sadTable.id = :tableId order by o.id desc ");
        query.setParameter("tableId", tableId);
        query.setMaxResults(1);
        return query.list();
    }
}
