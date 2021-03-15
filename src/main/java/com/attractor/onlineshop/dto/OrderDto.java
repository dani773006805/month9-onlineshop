package com.attractor.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Integer totalQuantity;
    private BigDecimal totalPrice;
    private LocalDateTime dateCreated;
    private List<OrderItemDto> orderItemDtos;
}
