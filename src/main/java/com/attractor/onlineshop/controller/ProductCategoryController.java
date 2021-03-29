package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.mapper.ProductCategoryMapper;
import com.attractor.onlineshop.services.ProductCategoryService;
import com.attractor.onlineshop.services.ProductCategoryServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/shop")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryServiceImpl productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("product-category")
    public ResponseEntity<?> getAll() {
        var pageable= PageRequest.of(0,10);
        var categories=productCategoryService.findAll(pageable).map(ProductCategoryMapper::toDto);
        return ResponseEntity.ok(categories);
    }
}
