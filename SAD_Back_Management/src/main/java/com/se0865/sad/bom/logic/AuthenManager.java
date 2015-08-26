package com.se0865.sad.bom.logic;

import com.se0865.sad.dto.UserDto;
import com.se0865.sad.entities.User;
import com.se0865.sad.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AnLTNM-SE60906 on 03/08/2015.
 */
public class AuthenManager {
    @Autowired
    UserDaoImpl userDao;

    public UserDto validateUser(String username, String password) {
        User u = userDao.loginBomAccount(username, password);
        UserDto userDto = null;
        if (null != u) {
            if (u.getUserName().equals(username) && u.getUserPassword().equals(password)) {
                userDto = new UserDto();
                userDto.setDisplayName(u.getDisplayName());
                userDto.setUserId((int) u.getUserId());
                return userDto;
            }
        }
        return null;
    }

    public User validatePassword(String userId, String password, String newPassword) {
        User u = userDao.find(Long.valueOf(userId));
        if (null != u) {
            if (u.getUserName().equals(password)) {
                u.setUserPassword(newPassword);
                u.setLastUpdate(System.currentTimeMillis());
                userDao.update(u);
                return u;
            }
        }
        return null;
    }
}
