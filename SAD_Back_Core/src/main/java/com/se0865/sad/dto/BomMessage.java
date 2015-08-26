package com.se0865.sad.dto;

/**
 * Created by AnLTNM-SE60906 on 02/08/2015.
 */
public class BomMessage {
    private String message = "OK";
    private int statusCode = 0;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
