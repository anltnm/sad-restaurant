package com.se0865.sad.dao;

import com.se0865.sad.entities.User;

/**
 * Created by AnLTNM-SE60906 on 26/07/2015.
 */
public interface UserDao {
    public User loginAccount(String username, String password);
}
