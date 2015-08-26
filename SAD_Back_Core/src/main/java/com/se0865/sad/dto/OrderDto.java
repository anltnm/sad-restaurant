package com.se0865.sad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 18/05/2015.
 */
@XmlRootElement
public class OrderDto {
    private long orderId;
    private long severOrderId;
    private int status;
    private long createdDate;
    private long lastUpdate;
    private long closedDate;
    private int tableId;
    private int userId;
    private int cusQuantity;
    private String description;

    @JsonProperty("orderDetail")
    private List<OrderDetailDto> orderDetailDtoList;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getSeverOrderId() {
        return severOrderId;
    }

    public void setSeverOrderId(long severOrderId) {
        this.severOrderId = severOrderId;
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

    public long getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(long closedDate) {
        this.closedDate = closedDate;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OrderDetailDto> getOrderDetailDtoList() {
        return orderDetailDtoList;
    }

    public void setOrderDetailDtoList(List<OrderDetailDto> orderDetailDtoList) {
        this.orderDetailDtoList = orderDetailDtoList;
    }

    public int getCusQuantity() {
        return cusQuantity;
    }

    public void setCusQuantity(int cusQuantity) {
        this.cusQuantity = cusQuantity;
    }
}
