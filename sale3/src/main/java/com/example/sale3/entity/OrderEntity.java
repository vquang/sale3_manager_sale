package com.example.sale3.entity;

import com.example.sale3.constant.OrderStatus;
import com.example.sale3.request.OrderRequest;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;
    @Column(name = "totalPrice")
    private int totalPrice;
    @Column(name = "address")
    private String address;
    @Column(name = "createTime")
    private String createTime;
    @OneToMany(mappedBy = "orderEntity")
    private List<ItemEntity> itemEntities = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    public OrderEntity() {
    }

    public OrderEntity(OrderRequest orderRequest, String createTime) {
        status = orderRequest.getStatus();
        address = orderRequest.getAddress();
        totalPrice = orderRequest.getTotalPrice();
        this.createTime = createTime;
    }
    public void pay(OrderRequest orderRequest) {
        address = orderRequest.getAddress();
        status = OrderStatus.PAID;
    }

    public OrderEntity(OrderStatus status, int totalPrice, String address, String createTime, UserEntity userEntity) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.address = address;
        this.createTime = createTime;
        this.userEntity = userEntity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public List<ItemEntity> getItemEntities() {
        return itemEntities;
    }

    public void setItemEntities(List<ItemEntity> itemEntities) {
        this.itemEntities = itemEntities;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}




















