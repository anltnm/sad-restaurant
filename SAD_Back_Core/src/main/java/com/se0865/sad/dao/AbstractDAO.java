package com.se0865.sad.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 18/05/2015.
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//public abstract class AbstractDAO<E, K extends Serializable> implements GenericDao<E, K> {
public class AbstractDAO<E, K extends Serializable> implements GenericDao<E, K> {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    protected Class<? extends E> daoType;

    public AbstractDAO() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected final Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    public void add(E entity) {
        currentSession().save(entity);
    }

    public void update(E entity) {
        currentSession().merge(entity);
    }

    public void remove(E entity) {
        currentSession().delete(entity);
    }

    public E find(K id) {
        return (E) currentSession().get(daoType, id);
    }

    public List<E> findAll() {
        return currentSession().createCriteria(daoType).list();
    }
}
