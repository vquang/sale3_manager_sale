package com.example.sale3.response;

import com.example.sale3.entity.ItemEntity;
import com.example.sale3.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Order extends Orders{
    private List<Item> items = new ArrayList<>();
    public Order(OrderEntity orderEntity, int page, int totalPages) {
        super(orderEntity, page, totalPages);
        for(ItemEntity itemEntity: orderEntity.getItemEntities())
            items.add(new Item(itemEntity, page, totalPages));
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
