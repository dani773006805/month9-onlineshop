package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findByUserEmail(String email);

    Order save(String email);

}
