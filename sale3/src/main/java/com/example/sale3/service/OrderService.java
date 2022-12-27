package com.example.sale3.service;

import com.example.sale3.base.Base;
import com.example.sale3.base.Check;
import com.example.sale3.base.Error;
import com.example.sale3.base.Success;
import com.example.sale3.constant.OrderStatus;
import com.example.sale3.entity.ItemEntity;
import com.example.sale3.entity.OrderEntity;
import com.example.sale3.entity.UserEntity;
import com.example.sale3.repository.OrderRepository;
import com.example.sale3.repository.UserRepository;
import com.example.sale3.request.OrderRequest;
import com.example.sale3.response.Order;
import com.example.sale3.response.Orders;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private Check check;
    @Autowired
    private Error error;
    @Autowired
    private Success success;
    @Autowired
    private Base base;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    public Response<Status, List<Orders>> showAll(int page, int limit) {
        int totalPages = base.totalPages((int)orderRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        List<Orders> orders = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findAll(base.page(page, limit)).getContent();
        for(OrderEntity orderEntity: orderEntities)
            orders.add(new Orders(orderEntity, page, totalPages));
        return new Response<>(success.success("show all orders successful!"), orders);
    }
    public void create() {
        UserEntity userEntity = userRepository.findByUsername(base.currentUser());
        OrderEntity orderEntity = new OrderEntity(OrderStatus.WAITING, 0, "", base.time(), userEntity);
        orderEntity = orderRepository.save(orderEntity);
        userEntity.setWaitingId(orderEntity.getId());
    }
    public Response<Status, Order> cancel(Integer id, int page, int limit) {
        int totalPages = base.totalPages((int)orderRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        if(orderEntity == null) return new Response<>(error.notExist(), null);
        orderEntity.setStatus(OrderStatus.CANCEL);
        UserEntity userEntity = userRepository.findByUsername(base.currentUser());
        userEntity.setWaitingId(0);
        orderRepository.save(orderEntity);
        userRepository.save(userEntity);
        Order order = new Order(orderEntity, page, totalPages);
        return new Response<>(success.success("cancel order successful!"), order);
    }
    public Response<Status, Order> pay(Integer id, OrderRequest orderRequest, int page, int limit) {
        int totalPages = base.totalPages((int)orderRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        if(orderEntity == null) return new Response<>(error.notExist(), null);
        orderEntity.pay(orderRequest);
        UserEntity userEntity = userRepository.findByUsername(base.currentUser());
        userEntity.setWaitingId(0);
        orderRepository.save(orderEntity);
        userRepository.save(userEntity);
        Order order = new Order(orderEntity, page, totalPages);
        return new Response<>(success.success("pay order successful!"), order);
    }
    public Response<Status, Order> show(Integer id, int page, int limit) {
        int totalPages = base.totalPages((int)orderRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        if(orderEntity == null) return new Response<>(error.notExist(), null);
        Order order = new Order(orderEntity, page, totalPages);
        return new Response<>(success.success("show order successful!"), order);
    }
    public Response<Status, Order> showPublic(int page, int limit) {
        int totalPages = base.totalPages((int)orderRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        UserEntity userEntity = userRepository.findByUsername(base.currentUser());
        Integer waitingId = userEntity.getWaitingId();
        if(waitingId == 0) return new Response<>(error.notExist(), null);
        OrderEntity orderEntity = orderRepository.findById(waitingId).orElse(null);
        if(orderEntity == null) return new Response<>(error.notExist(), null);
        Order order = new Order(orderEntity, page, totalPages);
        return new Response<>(success.success("show order successful!"), order);
    }
}

























