package com.example.sale3.controller;

import com.example.sale3.request.OrderRequest;
import com.example.sale3.response.Order;
import com.example.sale3.response.Orders;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import com.example.sale3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/orders")
    public Response<Status, List<Orders>> showAll( @RequestParam(value = "page") Integer page,
                                                   @RequestParam(value = "limit") Integer limit) {
        return orderService.showAll(page, limit);
    }
    @GetMapping("/order-cancel/{id}")
    public Response<Status, Order> cancel(@PathVariable Integer id,
                                          @RequestParam(value = "page") Integer page,
                                          @RequestParam(value = "limit") Integer limit) {
        return orderService.cancel(id, page, limit);
    }
    @PostMapping("/order/{id}")
    public Response<Status, Order> pay(@PathVariable Integer id,
                                       @RequestBody OrderRequest orderRequest,
                                       @RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "limit") Integer limit) {
        return orderService.pay(id, orderRequest, page, limit);
    }
    @GetMapping("/order/{id}")
    public Response<Status, Order> show(@PathVariable Integer id,
                                        @RequestParam(value = "page") Integer page,
                                        @RequestParam(value = "limit") Integer limit) {
        return orderService.show(id, page, limit);
    }
    @GetMapping("/order")
    public Response<Status, Order> showPublic(@RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "limit") Integer limit) {
        return orderService.showPublic(page, limit);
    }
}































