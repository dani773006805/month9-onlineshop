package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.dto.OrderDto;
import com.attractor.onlineshop.model.Order;

public class OrderDtoMapper {
    public static OrderDto from(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .dateCreated(order.getDateCreated())
                .totalPrice(order.getTotalPrice())
                .totalQuantity(order.getTotalQuantity())
                .build();
    }
}
