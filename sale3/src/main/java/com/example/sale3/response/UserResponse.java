package com.example.sale3.response;

import com.example.sale3.constant.UserRole;
import com.example.sale3.entity.UserEntity;

public class UserResponse {
    private Integer id;
    private String username;
    private UserRole role;

    public UserResponse() {
    }

    public UserResponse(UserEntity userEntity) {
        id = userEntity.getId();
        username = userEntity.getUsername();
        role = userEntity.getRole();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
