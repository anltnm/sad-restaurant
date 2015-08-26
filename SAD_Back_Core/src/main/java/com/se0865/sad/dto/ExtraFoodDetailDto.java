package com.se0865.sad.dto;

/**
 * Created by AnLTNM-SE60906 on 20/07/2015.
 */
public class ExtraFoodDetailDto {

    private long id;
    private String name;
    private float price;
    private boolean selected;
    private long groupId;
    private String groupName;
    private long lastUpdate;
    private int status;

    public ExtraFoodDetailDto() {
    }

    public ExtraFoodDetailDto(long id, String name, float price, boolean selected, long groupId, long lastUpdate) {
        this.lastUpdate = lastUpdate;
        this.id = id;
        this.name = name;
        this.price = price;
        this.selected = selected;
        this.groupId = groupId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
