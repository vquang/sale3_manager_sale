package com.example.sale3.entity;

import com.example.sale3.request.ItemRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "createTime")
    private String createTime;
    @OneToOne
    @JoinColumn(name = "productId")
    private ProductEntity productEntity;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity orderEntity;

    public ItemEntity() {
    }

    public ItemEntity(ItemRequest itemRequest, ProductEntity productEntity, OrderEntity orderEntity, String createTime) {
        amount = itemRequest.getAmount();
        this.productEntity = productEntity;
        this.orderEntity = orderEntity;
        this.createTime = createTime;
    }
    public void update(ItemRequest itemRequest) {
        amount = itemRequest.getAmount();
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
