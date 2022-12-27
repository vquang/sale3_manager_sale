package com.example.sale3.base;

import com.example.sale3.entity.CategoryEntity;
import com.example.sale3.entity.UserEntity;
import com.example.sale3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Check {
    @Autowired
    private UserRepository userRepository;
    public boolean check(String username) {
        List<UserEntity> userEntities = userRepository.findAll();
        for(UserEntity userEntity: userEntities)
            if(userEntity.getUsername().equals(username))
                return true;
        return false;
    }
    public boolean check(int page, int totalPages) {
        if(page == 1) return false;
        return page <= 0 || page > totalPages;
    }
    public boolean check(CategoryEntity categoryEntity) {
        return categoryEntity.getProductEntities().size() != 0;
    }
}

















