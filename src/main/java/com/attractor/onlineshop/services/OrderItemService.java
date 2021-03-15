package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.OrderItem;
import com.attractor.onlineshop.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    public List<OrderItem> findByOrderId(Long orderId){
        return orderItemRepository.findAllByOrderId(orderId);
    }
}
