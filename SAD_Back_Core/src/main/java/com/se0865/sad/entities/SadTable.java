package com.se0865.sad.entities;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by AnLTNM-SE60906 on 13/07/2015.
 */
@Entity
@Table(name = "sad_table")
public class SadTable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tbl_id", unique = true, nullable = false)
    private long id;

    @Column(name = "tbl_status", nullable = false)
    private int status;

    @Column(name = "tbl_name", nullable = false)
    private String name;

    @Column(name = "tbl_floor_name", nullable = false)
    private String floorName;

    @Column(name = "tbl_seat_num", nullable = false)
    private int seatNumber;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "last_updated")
    private Long lastUpdate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
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
}
