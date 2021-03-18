package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.dto.ProductDto;
import com.attractor.onlineshop.model.Product;

public class ProductMapper {
    public static ProductDto fromAll(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .name(product.getName())
                .unitPrice(product.getUnitPrice())
                .build();
    }

    public static ProductDto mainPage(Product product){
        return ProductDto.builder()
//                .id(product.getId())
                .imageUrl(product.getImageUrl())
                .name(product.getName())
                .unitPrice(product.getUnitPrice())
                .build();
    }

}
