package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findByOrderId(Long orderId);

}
