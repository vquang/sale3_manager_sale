package com.example.sale3.service;

import com.example.sale3.base.Base;
import com.example.sale3.base.Check;
import com.example.sale3.base.Error;
import com.example.sale3.base.Success;
import com.example.sale3.entity.CategoryEntity;
import com.example.sale3.entity.ProductEntity;
import com.example.sale3.repository.CategoryRepository;
import com.example.sale3.repository.ProductRepository;
import com.example.sale3.request.ProductRequest;
import com.example.sale3.response.Product;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private Check check;
    @Autowired
    private Error error;
    @Autowired
    private Success success;
    @Autowired
    private Base base;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    public Response<Status, Product> create(Integer categoryId, ProductRequest productRequest, int page, int limit) {
        int totalPages = base.totalPages((int)productRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);
        if(categoryEntity == null) return new Response<>(error.notExist(), null);
        ProductEntity productEntity = new ProductEntity(productRequest, categoryEntity, base.time(), base.time());
        productEntity = productRepository.save(productEntity);
        Product product = new Product(productEntity, page, totalPages);
        return new Response<>(success.success("create product successful!"), product);
    }
    public Response<Status, List<Product>> showAll(Integer categoryId, int page, int limit) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);
        if(categoryEntity == null) return new Response<>(error.notExist(), null);
        List<ProductEntity> productEntityList = productRepository.findAllByCategoryEntity(categoryEntity);
        List<ProductEntity> productEntities = base.entitiesInPage(productEntityList, page, limit);
        int totalPages = base.totalPages((int)productEntityList.size(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        List<Product> products = new ArrayList<>();
        for(ProductEntity productEntity: productEntities)
            products.add(new Product(productEntity, page, totalPages));
        return new Response<>(success.success("show all products successful!"), products);
    }
    public Response<Status, Product> show(Integer id, int page, int limit) {
        int totalPages = base.totalPages((int)productRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if(productEntity == null) return new Response<>(error.notExist(), null);
        Product product = new Product(productEntity, page,totalPages);
        return new Response<>(success.success("show product successful!"), product);
    }
    public Response<Status, Product> update(Integer id, ProductRequest productRequest, int page, int limit) {
        int totalPages = base.totalPages((int)productRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if(productEntity == null) return new Response<>(error.notExist(), null);
        productEntity.update(productRequest, base.time());
        productEntity = productRepository.save(productEntity);
        Product product = new Product(productEntity, page, totalPages);
        return new Response<>(success.success("update product successful!"), product);
    }
    public Response<Status, Product> delete(Integer id, int page, int limit) {
        int totalPages = base.totalPages((int)productRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if(productEntity == null) return new Response<>(error.notExist(), null);
        Product product = new Product(productEntity, page, totalPages);
        productRepository.delete(productEntity);
        return new Response<>(success.success("delete product successful!"), product);
    }
}



























