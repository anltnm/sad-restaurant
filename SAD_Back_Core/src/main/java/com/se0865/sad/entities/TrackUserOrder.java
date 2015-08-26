package com.se0865.sad.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by AnLTNM-SE60906 on 18/07/2015.
 */
@Entity
@Table(name = "sad_track_user_order")
public class TrackUserOrder {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "track_user_order_id", unique = true, nullable = false)
    private long trackUserOrder;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "order_status")
    private int status;

    @Column(name = "created_date")
    private long createdDate;

    @Column(name = "last_updated")
    private long lastUpdate;

    public long getTrackUserOrder() {
        return trackUserOrder;
    }

    public void setTrackUserOrder(long trackUserOrder) {
        this.trackUserOrder = trackUserOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
