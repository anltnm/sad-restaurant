package com.se0865.sad.entities;

import com.se0865.sad.dto.ExtraFoodGroupDto;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by AnLTNM-SE60906 on 13/07/2015.
 */
@Entity
@Table(name = "sad_ex_food_group")
public class ExtraFoodGroup {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ex_food_group_id", unique = true, nullable = false)
    private long id;

    @Column(name = "ex_food_group_name", nullable = false)
    private String name;

    @Column(name = "ex_food_group_status")
    private int status;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "last_updated")
    private Long lastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = System.currentTimeMillis();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = System.currentTimeMillis();
    }

    public void setId(long id) {
        this.id = id;
    }

    public ExtraFoodGroupDto parseToGroupDto() {
        ExtraFoodGroupDto extraFoodGroupDto = new ExtraFoodGroupDto();
        extraFoodGroupDto.setId(this.id);
        extraFoodGroupDto.setName(this.name);
        extraFoodGroupDto.setStatus(this.status);
        return extraFoodGroupDto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
