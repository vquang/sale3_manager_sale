package com.example.sale3.base;

import com.example.sale3.entity.ProductEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Base {
    public String currentUser() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
    public String time() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }
    public int totalPages(int amount, int limit) {
        return (int)Math.ceil((double)amount / limit);
    }
    public Pageable page(int page, int limit) {
        return PageRequest.of(page - 1, limit);
    }
    public List<ProductEntity> entitiesInPage(List<ProductEntity> productEntities, int page, int limit) {
        int start = limit * (page - 1);
        int end = start + limit - 1;
        List<ProductEntity> productEntities1 = new ArrayList<>();
        for(int i = start; i <= end && i < productEntities.size(); ++i) {
            productEntities1.add(productEntities.get(i));
        }
        return productEntities1;
    }
}






















