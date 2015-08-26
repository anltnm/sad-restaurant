package com.se0865.sad.bom.logic;

import com.se0865.sad.dao.UserDao;
import com.se0865.sad.dto.UserBomDto;
import com.se0865.sad.dto.UserDto;
import com.se0865.sad.entities.User;
import com.se0865.sad.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 09/08/2015.
 */
public class UserManager {

    @Autowired
    private UserDaoImpl userDao;

    public List<UserDto> getListUser() {
        List<User> userList = userDao.getListStaffUser();
        return parseUserListToUserDtoList(userList);
    }

    public UserBomDto getUserDetail(long id) {
        User u = userDao.find(id);
        if (u != null) {
            return parseUserToUserDto(u);
        }
        return null;
    }

    public List<UserDto> getUsersByUserName(String userName){
        List<User> userList = userDao.findUserListByUserName(userName);
        return parseUserListToUserDtoList(userList);
    }
    public List<UserDto> getUsersByUserDisplayName(String displayName){
        List<User> userList = userDao.findUserListByUserDisplayName(displayName);
        return parseUserListToUserDtoList(userList);
    }
    public boolean isUserNameExisted(String userName) {
        return userDao.findUserByUserName(userName).size() == 0 ? false : true;
    }

    public User getUserById(long id) {
        return userDao.find(id);
    }

    public void addUser(User u) {
        userDao.add(u);
    }
    public void updateUser(User u) {
        userDao.update(u);
    }

    private UserBomDto parseUserToUserDto(User user) {
        UserBomDto userDto = new UserBomDto();
        userDto.setUserName(user.getUserName());
        userDto.setDisplayName(user.getDisplayName());
        userDto.setPassword(user.getUserPassword());
        userDto.setUserId((int) user.getUserId());
        return userDto;
    }

    private List<UserDto> parseUserListToUserDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        UserDto userDto = null;
        for (User u : userList) {
            userDto = new UserDto();
            userDto.setUserId((int) u.getUserId());
            userDto.setDisplayName(u.getDisplayName());
            userDto.setUserName(u.getUserName());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
