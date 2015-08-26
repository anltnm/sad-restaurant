package com.se0865.sad.dto;

/**
 * Created by AnLTNM-SE60906 on 21/07/2015.
 */
public class ExtraFoodGroupDto {
    private long id;
    private String name;
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
