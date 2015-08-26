package com.se0865.sad.impl;

import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dao.AbstractDAO;
import com.se0865.sad.dao.UserDao;
import com.se0865.sad.dto.UserDto;
import com.se0865.sad.entities.User;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AnLTNM-SE60906 on 26/07/2015.
 */

public class UserDaoImpl extends AbstractDAO<User, Long> implements UserDao {

    public User loginBomAccount(String username, String password) {
        String query = "Select u From User u where u.userName = :userName AND u.userPassword = :userPassword AND u.userRole=:role";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("userName", username);
        query1.setParameter("userPassword", password);
        query1.setParameter("role", ConstantManager.USER_ROLE_ADMIN);
        List listUser = query1.list();
        if (listUser != null && listUser.size() != 0) {
            return (User) listUser.get(0);
        }
        return null;
    }

    public User loginAccount(String username, String password) {
        String query = "Select u From User u where u.userName = :userName AND u.userPassword = :userPassword ";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("userName", username);
        query1.setParameter("userPassword", password);
        List listUser = query1.list();
        if (listUser != null && listUser.size() != 0) {
            return (User) listUser.get(0);
        }
        return null;
    }

    public List<User> getListStaffUser() {
        String query = "Select u From User u Where u.userRole = :role Order By u.userName asc";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("role", ConstantManager.USER_ROLE_STAFF);
        return query1.list();
    }

    public List<User> findUserByUserName(String userName) {
        String query = "Select u From User u Where u.userName = :userName";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("userName", userName);
        return query1.setMaxResults(1).list();
    }

    public List<User> findUserListByUserName(String userName) {
        String query = "Select u From User u Where u.userName LIKE :userName order by u.userName";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("userName", new StringBuilder("%").append(userName).append("%").toString());
        return query1.list();
    }
    public List<User> findUserListByUserDisplayName(String displayName) {
        String query = "Select u From User u Where u.userName LIKE :displayName order by u.displayName";
        Query query1 = currentSession().createQuery(query);
        query1.setParameter("displayName", new StringBuilder("%").append(displayName).append("%").toString());
        return query1.list();
    }
}
