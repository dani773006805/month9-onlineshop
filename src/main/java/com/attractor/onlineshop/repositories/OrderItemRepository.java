package com.attractor.onlineshop.repositories;

import com.attractor.onlineshop.model.Order;
import com.attractor.onlineshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findAllByOrderId(Long orderId);
}
