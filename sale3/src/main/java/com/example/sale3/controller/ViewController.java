package com.example.sale3.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/order-user")
    public String orderUser() {
        return "order-user";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("/category")
    public String category() {
        return "category";
    }
    @GetMapping("/product")
    public String product() {
        return "product";
    }
    @GetMapping("/order-page")
    public String order() {
        return "order";
    }
}



















