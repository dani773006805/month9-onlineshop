package com.attractor.onlineshop.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShoppingCartDto {
    private Long id;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
}
