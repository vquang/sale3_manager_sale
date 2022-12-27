package com.example.sale3.controller;

import com.example.sale3.request.ItemRequest;
import com.example.sale3.response.Item;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import com.example.sale3.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping("/item/{id}")
    public Response<Status, Item> add(@PathVariable("id") Integer id,
                                      @RequestBody ItemRequest itemRequest,
                                      @RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "limit") Integer limit) {
        return itemService.add(id, itemRequest, page, limit);
    }
    @PutMapping("/item/{id}")
    public Response<Status, Item> update(@PathVariable("id") Integer id,
                                      @RequestBody ItemRequest itemRequest,
                                      @RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "limit") Integer limit) {
        return itemService.update(id, itemRequest, page, limit);
    }
    @DeleteMapping("/item/{id}")
    public Response<Status, Item> delete(@PathVariable("id") Integer id,
                                         @RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "limit") Integer limit) {
        return itemService.delete(id, page, limit);
    }
    @GetMapping("/item/{id}")
    public Response<Status, Item> show(@PathVariable("id") Integer id,
                                       @RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "limit") Integer limit) {
        return itemService.show(id, page, limit);
    }
}
























