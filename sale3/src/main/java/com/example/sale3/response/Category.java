package com.example.sale3.response;

import com.example.sale3.entity.CategoryEntity;
import com.example.sale3.entity.ProductEntity;
import com.example.sale3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Category extends Categories{
    private List<Product> products = new ArrayList<>();

    public Category(CategoryEntity categoryEntity, int page, int totalPages) {
        super(categoryEntity, page, totalPages);
        for(ProductEntity productEntity: categoryEntity.getProductEntities())
            products.add(new Product(productEntity, page, totalPages));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
