package com.se0865.sad.entities;

import com.se0865.sad.dto.ExtraFoodGroupDto;
import com.se0865.sad.dto.FoodDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by AnLTNM-SE60906 on 19/07/2015.
 */
@Entity
@Table(name = "sad_food")
public class Food {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "food_id", unique = true, nullable = false)
    private long id;

    @Column(name = "food_name", nullable = false)
    private String name;

    @Column(name = "food_price", nullable = false)
    private float price;

    @Column(name = "food_status", nullable = false)
    private int status;

    @Column(name = "food_ingredient")
    private String ingredient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "sad_group_extra_food",
            joinColumns = {@JoinColumn(name = "food_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ex_food_group_id",
                    nullable = false, updatable = false)}
    )
    private List<ExtraFoodGroup> extraFoodGroupList;

    @Column(name = "created_date")
    private long createdDate;

    @Column(name = "last_updated")
    private long lastUpdate;

    @Column(name = "img_url")
    private String imageUrl;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<ExtraFoodGroup> getExtraFoodGroupList() {
        return extraFoodGroupList;
    }

    public void setExtraFoodGroupList(List<ExtraFoodGroup> extraFoodGroupList) {
        this.extraFoodGroupList = extraFoodGroupList;
    }

    public FoodDto parseToFoodDto() {
        FoodDto f = new FoodDto();
        f.setCategory(this.category.parseToCategoryDto());
        f.setName(this.name);
        f.setId(this.id);
        f.setIngredient(this.ingredient);
        f.setLastUpdate(this.lastUpdate);
        f.setPrice(this.price);
        f.setImageUrl(this.imageUrl);
        f.setStatus(this.status);

        List<ExtraFoodGroupDto> extraFoodGroupDtoList = new ArrayList<ExtraFoodGroupDto>();
        for (ExtraFoodGroup e : this.extraFoodGroupList) {
            extraFoodGroupDtoList.add(e.parseToGroupDto());
        }
        f.setExtraFoodGroupList(extraFoodGroupDtoList);
        return f;
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
