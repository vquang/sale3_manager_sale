package com.example.sale3.entity;

import com.example.sale3.request.CategoryRequest;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "updateTime")
    private String updateTime;
    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> productEntities = new ArrayList<>();

    public CategoryEntity() {
    }

    public CategoryEntity(CategoryRequest categoryRequest, String createTime, String updateTime) {
        this.name = categoryRequest.getName();
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    public void update(CategoryRequest categoryRequest, String updateTime) {
        this.name = categoryRequest.getName();
        this.updateTime = updateTime;
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

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }
}























