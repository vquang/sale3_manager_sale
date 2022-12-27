package com.example.sale3.controller;

import com.example.sale3.repository.ProductRepository;
import com.example.sale3.request.CategoryRequest;
import com.example.sale3.request.ProductRequest;
import com.example.sale3.response.Product;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import com.example.sale3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/product/{id}")
    public Response<Status, Product> create(@PathVariable("id") Integer id,
                                            @RequestParam("page") Integer page,
                                            @RequestParam("limit") Integer limit,
                                            @RequestBody ProductRequest productRequest) {
        return productService.create(id, productRequest, page, limit);
    }
    @GetMapping("/products/{id}")
    public Response<Status, List<Product>> showAll(@PathVariable("id") Integer id,
                                                   @RequestParam("page") Integer page,
                                                   @RequestParam("limit") Integer limit) {
        return productService.showAll(id, page, limit);
    }
    @GetMapping("/product/{id}")
    public Response<Status, Product> show(@PathVariable("id") Integer id,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("limit") Integer limit) {
        return productService.show(id, page, limit);
    }
    @PutMapping("/product/{id}")
    public Response<Status, Product> update(@PathVariable("id") Integer id,
                                            @RequestBody ProductRequest productRequest,
                                            @RequestParam("page") Integer page,
                                            @RequestParam("limit") Integer limit) {
        return productService.update(id, productRequest, page, limit);
    }
    @DeleteMapping("/product/{id}")
    public Response<Status, Product> delete(@PathVariable("id") Integer id,
                                            @RequestParam("page") Integer page,
                                            @RequestParam("limit") Integer limit) {
        return productService.delete(id, page, limit);
    }
}





















