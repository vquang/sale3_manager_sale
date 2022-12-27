package com.example.sale3.response;

import com.example.sale3.entity.CategoryEntity;

public class Categories {
    protected Integer id;
    protected String name;
    protected String createTime;
    protected String updateTime;
    protected int page;
    protected int totalPages;

    public Categories() {
    }

    public Categories(CategoryEntity categoryEntity, int page, int totalPage) {
        id = categoryEntity.getId();
        name = categoryEntity.getName();
        createTime = categoryEntity.getCreateTime();
        updateTime = categoryEntity.getUpdateTime();
        this.page = page;
        this.totalPages = totalPage;
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
}
