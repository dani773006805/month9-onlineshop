package com.attractor.onlineshop.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String imageUrl;

}