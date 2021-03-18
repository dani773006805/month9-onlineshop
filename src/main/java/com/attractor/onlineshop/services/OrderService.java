package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findByUserId(Long userId);

    Order save(Long userId);

}
