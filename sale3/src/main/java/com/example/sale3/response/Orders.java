package com.example.sale3.response;

import com.example.sale3.constant.OrderStatus;
import com.example.sale3.entity.OrderEntity;

public class Orders {
    protected Integer id;
    protected OrderStatus status;
    protected int totalPrice;
    protected String address;
    protected String username;
    protected String createTime;
    protected int page;
    protected int totalPages;

    public Orders() {
    }

    public Orders(OrderEntity orderEntity, int page, int totalPages) {
        id = orderEntity.getId();
        status = orderEntity.getStatus();
        totalPrice = orderEntity.getTotalPrice();
        address = orderEntity.getAddress();
        username = orderEntity.getUserEntity().getUsername();
        createTime = orderEntity.getCreateTime();
        this.page = page;
        this.totalPages = totalPages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
