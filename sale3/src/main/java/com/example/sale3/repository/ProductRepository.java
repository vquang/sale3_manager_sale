package com.example.sale3.repository;

import com.example.sale3.entity.CategoryEntity;
import com.example.sale3.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByCategoryEntity(CategoryEntity categoryEntity);
}
