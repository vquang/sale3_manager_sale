package com.example.sale3.response;

import com.example.sale3.entity.ItemEntity;

public class Item {
    private Integer id;
    private String name;
    private String image;
    private int cost;
    private int amount;
    private String createTime;
    private Integer productId;
    private int page;
    private int totalPages;

    public Item() {
    }

    public Item(ItemEntity itemEntity, int page,int totalPages) {
        id = itemEntity.getId();
        name = itemEntity.getProductEntity().getName();
        image = itemEntity.getProductEntity().getImage();
        cost = itemEntity.getProductEntity().getCost();
        amount = itemEntity.getAmount();
        createTime = itemEntity.getCreateTime();
        productId = itemEntity.getProductEntity().getId();
        this.page = page;
        this.totalPages = totalPages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
