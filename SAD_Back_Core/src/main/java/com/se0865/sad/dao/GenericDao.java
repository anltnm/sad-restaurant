package com.se0865.sad.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 18/05/2015.
 */
public interface GenericDao<E, I extends Serializable> {
    public void add(E entity);

    public void update(E entity);

    public void remove(E entity);

    public E find(I id);

    public List<E> findAll();
}
