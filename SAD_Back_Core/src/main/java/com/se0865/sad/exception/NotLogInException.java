package com.se0865.sad.exception;

/**
 * Created by AnLTNM-SE60906 on 03/08/2015.
 */
public class NotLogInException extends Exception {
    private static final long serialVersionUID = -4744430183523721711L;

    private String message = "User is not login exception";

    public NotLogInException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
