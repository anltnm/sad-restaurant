package com.se0865.sad.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by AnLTNM-SE60906 on 13/07/2015.
 */
@Entity
@Table(name = "sad_extra_food")
public class ExtraFoodDetail {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ex_food_id", unique = true, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ex_food_group_id")
    private ExtraFoodGroup extraFoodGroup;

    @Column(name = "ex_food_name", nullable = false)
    private String name;

    @Column(name = "ex_food_price", nullable = false)
    private float price;

    @Column(name = "ex_food_status", nullable = false)
    private int status;

    @Column(name = "ex_food_selected", nullable = false)
    private boolean selected;

    @Column(name = "created_date")
    private long createdDate;

    @Column(name = "last_updated")
    private long lastUpdate;

    @PrePersist
    protected void onCreate() {
        createdDate = System.currentTimeMillis();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = System.currentTimeMillis();
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

    public ExtraFoodGroup getExtraFoodGroup() {
        return extraFoodGroup;
    }

    public void setExtraFoodGroup(ExtraFoodGroup extraFoodGroup) {
        this.extraFoodGroup = extraFoodGroup;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
