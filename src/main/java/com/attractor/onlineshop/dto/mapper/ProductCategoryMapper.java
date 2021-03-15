package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.dto.ProductCategoryDto;
import com.attractor.onlineshop.model.ProductCategory;

public class ProductCategoryMapper {
    public static ProductCategoryDto toDto(ProductCategory category){
        return ProductCategoryDto.builder()
                .name(category.getName())
                .id(category.getId())
                .build();
    }
}
