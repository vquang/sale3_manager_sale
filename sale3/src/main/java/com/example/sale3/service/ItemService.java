package com.example.sale3.service;

import com.example.sale3.base.Base;
import com.example.sale3.base.Check;
import com.example.sale3.base.Error;
import com.example.sale3.base.Success;
import com.example.sale3.entity.ItemEntity;
import com.example.sale3.entity.OrderEntity;
import com.example.sale3.entity.ProductEntity;
import com.example.sale3.entity.UserEntity;
import com.example.sale3.repository.ItemRepository;
import com.example.sale3.repository.OrderRepository;
import com.example.sale3.repository.ProductRepository;
import com.example.sale3.repository.UserRepository;
import com.example.sale3.request.ItemRequest;
import com.example.sale3.response.Item;
import com.example.sale3.response.Response;
import com.example.sale3.response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private Check check;
    @Autowired
    private Error error;
    @Autowired
    private Success success;
    @Autowired
    private Base base;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    public Response<Status, Item> add(Integer productId, ItemRequest itemRequest, int page, int limit) {
        int totalPages = base.totalPages((int)itemRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        ProductEntity productEntity = productRepository.findById(productId).orElse(null);
        if(productEntity == null) return new Response<>(error.notExist(), null);
        UserEntity userEntity = userRepository.findByUsername(base.currentUser());
        Integer orderId = userEntity.getWaitingId();
        int totalPrice = itemRequest.getAmount() * productEntity.getCost();
        if(orderId == 0) orderService.create();
        orderId = userEntity.getWaitingId();
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        orderEntity.setTotalPrice(orderEntity.getTotalPrice() + totalPrice);
        orderRepository.save(orderEntity);
        ItemEntity itemEntity = new ItemEntity(itemRequest, productEntity, orderEntity, base.time());
        itemEntity = itemRepository.save(itemEntity);
        Item item = new Item(itemEntity, page, totalPages);
        return new Response<>(success.success("add item successful!"), item);
    }
    public Response<Status, Item> update(Integer id, ItemRequest itemRequest,int page, int limit) {
        int totalPages = base.totalPages((int)itemRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        ItemEntity itemEntity = itemRepository.findById(id).orElse(null);
        if(itemEntity == null) return new Response<>(error.notExist(), null);
        OrderEntity orderEntity = itemEntity.getOrderEntity();
        ProductEntity productEntity = itemEntity.getProductEntity();
        int oldPrice = itemEntity.getAmount() * productEntity.getCost();
        itemEntity.update(itemRequest);
        int newPrice = itemEntity.getAmount() * productEntity.getCost();
        orderEntity.setTotalPrice(orderEntity.getTotalPrice() - oldPrice + newPrice);
        orderRepository.save(orderEntity);
        itemRepository.save(itemEntity);
        Item item = new Item(itemEntity, page, totalPages);
        return new Response<>(success.success("update item successful!"), item);
    }
    public Response<Status, Item> delete(Integer id, int page, int limit) {
        int totalPages = base.totalPages((int)itemRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        ItemEntity itemEntity = itemRepository.findById(id).orElse(null);
        if(itemEntity == null) return new Response<>(error.notExist(), null);
        OrderEntity orderEntity = itemEntity.getOrderEntity();
        ProductEntity productEntity = itemEntity.getProductEntity();
        int oldPrice = itemEntity.getAmount() * productEntity.getCost();
        orderEntity.setTotalPrice(orderEntity.getTotalPrice() - oldPrice);
        orderRepository.save(orderEntity);
        Item item = new Item(itemEntity, page, totalPages);
        itemRepository.delete(itemEntity);
        return new Response<>(success.success("delete item successful!"), item);
    }
    public Response<Status, Item> show(Integer id, int page, int limit) {
        int totalPages = base.totalPages((int)itemRepository.count(), limit);
        if(check.check(page, totalPages)) return new Response<>(error.page(), null);
        ItemEntity itemEntity = itemRepository.findById(id).orElse(null);
        if(itemEntity == null) return new Response<>(error.notExist(), null);
        Item item = new Item(itemEntity, page, totalPages);
        return new Response<>(success.success("show item successful!"), item);
    }
}


























