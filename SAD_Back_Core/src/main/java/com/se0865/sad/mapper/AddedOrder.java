package com.se0865.sad.mapper;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 22/07/2015.
 */
public class AddedOrder {
    private long orderId;
    private long localOrderId;
    private long lastUpdate;
    private List<AddedOrderDetail> addedOrderDetail;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getLocalOrderId() {
        return localOrderId;
    }

    public void setLocalOrderId(long localOrderId) {
        this.localOrderId = localOrderId;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<AddedOrderDetail> getAddedOrderDetail() {
        return addedOrderDetail;
    }

    public void setAddedOrderDetail(List<AddedOrderDetail> addedOrderDetail) {
        this.addedOrderDetail = addedOrderDetail;
    }
}
