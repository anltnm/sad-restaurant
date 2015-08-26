package com.se0865.sad.logic;

import com.se0865.sad.entities.SadTable;
import com.se0865.sad.impl.TableDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 19/07/2015.
 */
public class TableManager {
    @Autowired
    TableDaoImpl tableDao;

    public List<SadTable> getTableList() {
        return tableDao.getListTable();
    }

    public List<SadTable> getLastedUpdateTable(long lastUpdated) {
        return tableDao.getLastUpdatedTable(lastUpdated);
    }
}

