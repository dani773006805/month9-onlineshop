package com.attractor.onlineshop.dto;

import lombok.*;

@Builder
@Data
@ToString
public class ProductCategoryDto {
    private Long id;
    private String name;

}