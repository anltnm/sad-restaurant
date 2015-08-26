package com.se0865.sad.impl;

import com.se0865.sad.dao.AbstractDAO;
import com.se0865.sad.dao.TableDao;
import com.se0865.sad.entities.SadTable;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 19/07/2015.
 */
@Repository("TableDaoImpl")
public class TableDaoImpl extends AbstractDAO<SadTable, Long> implements TableDao {

    public List<SadTable> getLastUpdatedTable(long lastUpdateTime) {
        String q = "SELECT t FROM SadTable t where t.lastUpdate > :lastUpdate order by t.lastUpdate asc";
        Query query = currentSession().createQuery(q);
        query.setParameter("lastUpdate", lastUpdateTime);
        return query.list();
    }

    public List<SadTable> getListTable() {
        String q = "SELECT t FROM SadTable t order by t.lastUpdate asc";
        Query query = currentSession().createQuery(q);
        return query.list();
    }

    public List<SadTable> getAllOrderByTableName() {
        String q = "SELECT t FROM SadTable t order by t.name asc";
        Query query = currentSession().createQuery(q);
        return query.list();
    }
}
