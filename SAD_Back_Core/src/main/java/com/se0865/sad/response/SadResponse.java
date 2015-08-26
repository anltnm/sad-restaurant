package com.se0865.sad.response;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by AnLTNM-SE60906 on 19/05/2015.
 */
public class SadResponse {
    private String message = "ok";
    private Integer statusCode = 0;
    private Object data;

    public javax.ws.rs.core.Response ok() {
        SadResponse entity = new SadResponse();
        entity.setMessage(message);
        entity.setStatusCode(statusCode);
        entity.setData(data);
        return Response.status(200)
                .entity(entity)
                .type(MediaType.APPLICATION_JSON).
                        build();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
