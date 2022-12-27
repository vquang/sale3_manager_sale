package com.example.sale3.service;

import com.example.sale3.base.Base;
import com.example.sale3.base.Check;
import com.example.sale3.base.Error;
import com.example.sale3.base.Success;
import com.example.sale3.entity.CategoryEntity;
import com.example.sale3.repository.CategoryRepository;
import com.example.sale3.request.CategoryRequest;
import com.example.sale3.response.Categories;
import com.example.sale3.response.Category;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Check check;
    @Autowired
    private Error error;
    @Autowired
    private Success success;
    @Autowired
    private Base base;
    public Response<Status, Categories> create(CategoryRequest categoryRequest, int page, int limit) {
        int totalPages = base.totalPages((int)categoryRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        CategoryEntity categoryEntity = new CategoryEntity(categoryRequest, base.time(), base.time());
        categoryRepository.save(categoryEntity);
        totalPages = base.totalPages((int)categoryRepository.count(), limit);
        Categories category = new Categories(categoryEntity, page, totalPages);
        return new Response<>(success.success("create category successful!"), category);
    }
    public Response<Status, List<Categories>> showAll(int page, int limit) {
        int totalPages = base.totalPages((int)categoryRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        List<Categories> categories = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll(base.page(page, limit)).getContent();
        for(CategoryEntity categoryEntity: categoryEntities)
            categories.add(new Categories(categoryEntity, page, totalPages));
        return new Response<>(success.success("show all categories successful!"), categories);
    }
    public Response<Status, Categories> update(Integer id, CategoryRequest categoryRequest, int page, int limit) {
        int totalPages = base.totalPages((int)categoryRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if(categoryEntity == null) return new Response<>(error.notExist(), null);
        categoryEntity.update(categoryRequest, base.time());
        categoryEntity = categoryRepository.save(categoryEntity);
        Categories categories = new Categories(categoryEntity, page, totalPages);
        return new Response<>(success.success("update category successful!"), categories);
    }
    public Response<Status, Categories> delete(Integer id, int page, int limit) {
        int totalPages = base.totalPages((int)categoryRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if(categoryEntity == null) return new Response<>(error.notExist(), null);
        Categories categories = new Categories(categoryEntity, page, totalPages);
        if(check.check(categoryEntity)) return new Response<>(error.cannotDelete(), categories);
        categoryRepository.delete(categoryEntity);
        return new Response<>(success.success("delete category successful!"), categories);
    }
    public Response<Status, Category> show(Integer id, int page, int limit) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        if(categoryEntity == null) return new Response<>(error.notExist(), null);
        int totalPages = base.totalPages(categoryEntity.getProductEntities().size(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        Category category = new Category(categoryEntity, page, totalPages);
        return new Response<>(success.success("show category successful!"), category);
    }
}
























