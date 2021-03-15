package com.attractor.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemDto{
    private Long id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private Integer units;
    private String imageUrl;
    private Long cartId;
}
