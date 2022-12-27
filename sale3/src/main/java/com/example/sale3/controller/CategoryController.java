package com.example.sale3.controller;

import com.example.sale3.request.CategoryRequest;
import com.example.sale3.response.Categories;
import com.example.sale3.response.Category;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import com.example.sale3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/category")
    public Response<Status, Categories> create(@RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "limit") Integer limit,
                                               @RequestBody CategoryRequest categoryRequest) {
        return categoryService.create(categoryRequest, page, limit);
    }
    @GetMapping("/categories")
    public Response<Status, List<Categories>> showAll(@RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "limit") Integer limit) {
        return categoryService.showAll(page, limit);
    }
    @PutMapping("/category/{id}")
    public Response<Status, Categories> update(@PathVariable("id") Integer id,
                                               @RequestParam("page") Integer page,
                                               @RequestParam("limit") Integer limit,
                                               @RequestBody CategoryRequest categoryRequest) {
        return categoryService.update(id, categoryRequest, page, limit);
    }
    @DeleteMapping("/category/{id}")
    public Response<Status, Categories> delete(@PathVariable("id") Integer id,
                                               @RequestParam("page") Integer page,
                                               @RequestParam("limit") Integer limit) {
        return categoryService.delete(id, page, limit);
    }
    @GetMapping("/category/{id}")
    public Response<Status, Category> show(@PathVariable("id") Integer id,
                                           @RequestParam(value = "page") Integer page,
                                           @RequestParam(value = "limit") Integer limit) {
        return categoryService.show(id, page, limit);
    }
}




















