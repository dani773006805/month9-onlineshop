package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.mapper.ProductCategoryMapper;
import com.attractor.onlineshop.services.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("product-category")
    public ResponseEntity<?> getAll() {
        var categories = productCategoryService.findAll().stream()
                .map(category -> ProductCategoryMapper.toDto(category)).collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }
}
