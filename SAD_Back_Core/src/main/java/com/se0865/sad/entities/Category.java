package com.se0865.sad.entities;


import com.se0865.sad.dto.CategoryDto;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by AnLTNM-SE60906 on 13/07/2015.
 */
@Entity
@Table(name = "sad_category")
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cat_id", unique = true, nullable = false)
    private long id;

    @Column(name = "cat_name", nullable = false)
    private String name;

    @Column(name = "created_date")
    private long createdDate;

    @Column(name = "last_updated")
    private long lastUpdate;

    @Column(name = "cat_status", nullable = false)
    private int status;

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

    public CategoryDto parseToCategoryDto() {
        CategoryDto cat = new CategoryDto();
        cat.setId(this.id);
        cat.setName(this.name);
        cat.setStatus(this.status);
        return cat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
