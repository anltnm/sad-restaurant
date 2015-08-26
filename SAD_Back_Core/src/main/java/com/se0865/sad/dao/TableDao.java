package com.se0865.sad.dao;

import com.se0865.sad.entities.SadTable;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 19/07/2015.
 */
public interface TableDao {
    List<SadTable> getLastUpdatedTable(long lastUpdateTime);
}
