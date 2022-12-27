package com.example.sale3.request;

import com.example.sale3.constant.OrderStatus;

public class OrderRequest {
    private OrderStatus status;
    private String address;
    private int totalPrice;

    public OrderRequest() {
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
