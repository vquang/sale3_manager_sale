package com.example.sale3.controller;

import com.example.sale3.request.UserRequest;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import com.example.sale3.response.UserResponse;
import com.example.sale3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    private Response<Status, UserResponse> register(@RequestBody UserRequest userRequest) {
        return userService.register(userRequest);
    }
    @GetMapping("/user")
    private Response<Status, UserResponse> show() {
        return userService.show();
    }
}
























