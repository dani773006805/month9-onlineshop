package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.services.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }
    @GetMapping("product-category")
    public ResponseEntity<?> getAll(){
        var categories=productCategoryService.findAll();
        return ResponseEntity.ok(categories);
    }
}
