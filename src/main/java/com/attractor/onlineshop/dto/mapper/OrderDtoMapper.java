package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.dto.OrderDto;
import com.attractor.onlineshop.dto.OrderItemDto;
import com.attractor.onlineshop.model.Order;
import com.attractor.onlineshop.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDtoMapper {
    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .dateCreated(order.getDateCreated().toLocalDate())
                .totalPrice(order.getTotalPrice())
                .totalQuantity(order.getTotalQuantity())
                .orderItems(mapper(order.getOrderItems()))
                .build();
    }

    private static List<OrderItemDto> mapper(List<OrderItem> orderItems) {
        var orderItemDtos = new ArrayList<OrderItemDto>();
        if (orderItems.size() > 0) {
            for (var order : orderItems) {
                var orderItemDto = OrderItemDtoMapper.fromAll(order);
                orderItemDtos.add(orderItemDto);
            }
        }
        return orderItemDtos;
    }
}
