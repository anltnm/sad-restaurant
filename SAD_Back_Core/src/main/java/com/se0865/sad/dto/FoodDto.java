package com.se0865.sad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 21/07/2015.
 */
public class FoodDto {
    private long id;
    private String name;
    private float price;
    private String ingredient;
    private CategoryDto category;
    private String imageUrl;
    private int status;

    @JsonProperty("extraFoodGroup")
    private List<ExtraFoodGroupDto> extraFoodGroupList;

    private long lastUpdate;

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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<ExtraFoodGroupDto> getExtraFoodGroupList() {
        return extraFoodGroupList;
    }

    public void setExtraFoodGroupList(List<ExtraFoodGroupDto> extraFoodGroupList) {
        this.extraFoodGroupList = extraFoodGroupList;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
