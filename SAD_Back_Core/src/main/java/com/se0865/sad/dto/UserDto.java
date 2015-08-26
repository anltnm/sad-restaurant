package com.se0865.sad.dto;

/**
 * Created by AnLTNM-SE60906 on 26/07/2015.
 */
public class UserDto {
    private int userId;
    private String userName;
    private String displayName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
