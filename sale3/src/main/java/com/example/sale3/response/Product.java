package com.example.sale3.response;

import com.example.sale3.entity.ProductEntity;

public class Product {
    private Integer id;
    private String name;
    private String image;
    private int cost;
    private String createTime;
    private String updateTime;
    private int page;
    private int totalPages;

    public Product() {
    }

    public Product(ProductEntity productEntity, int page, int totalPage) {
        id = productEntity.getId();
        name = productEntity.getName();
        cost = productEntity.getCost();
        this.image = productEntity.getImage();
        createTime = productEntity.getCreateTime();
        updateTime = productEntity.getUpdateTime();
        this.page = page;
        this.totalPages = totalPage;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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












