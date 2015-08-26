package com.se0865.sad.logic;

import com.se0865.sad.dao.UserDao;
import com.se0865.sad.dto.UserDto;
import com.se0865.sad.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 26/07/2015.
 */
public class UserManager {
    @Autowired
    UserDao userDao;

    public UserDto loginAccount(String username, String password) {
        User u = userDao.loginAccount(username, password);
        UserDto userDto = null;
        if (null != u) {
            if (u.getUserName().equals(username) && u.getUserPassword().equals(password)) {
                userDto = new UserDto();
                userDto.setDisplayName(u.getDisplayName());
                userDto.setUserId((int) u.getUserId());
            }
        }
        return userDto;
    }

}
