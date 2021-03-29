package com.attractor.onlineshop.dto.mapper;


import com.attractor.onlineshop.dto.OrderItemDto;
import com.attractor.onlineshop.model.OrderItem;

public class OrderItemDtoMapper {
    public static OrderItemDto fromAll(OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .imageUrl(orderItem.getImageUrl())
                .name(orderItem.getName())
                .unitPrice(orderItem.getUnitPrice())
                .units(orderItem.getUnitsI())
                .description(orderItem.getDescription())
                .productId(orderItem.getProductId())
                .build();
    }
}
