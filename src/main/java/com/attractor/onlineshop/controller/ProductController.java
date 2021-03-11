package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.services.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/shop")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product-category/{id}/products")
    public ResponseEntity<?> getByCategoryId(@PathVariable Long id,
                                                 @RequestParam(name = "page") Integer pageNumber,
                                                @RequestParam(name = "size") Integer pageSize){
        var pageable= PageRequest.of(pageNumber,pageSize);
        var products=productService.findByCategoryId(id,pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/findByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name,
                                       @RequestParam(name = "page") Integer pageNumber,
                                       @RequestParam(name = "size") Integer pageSize){
        var pageable=PageRequest.of(pageNumber,pageSize);
        var products=productService.findByName(name,pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/findByPrice/{price}")
    public ResponseEntity<?> getByName(@PathVariable BigDecimal price,
                                       @RequestParam(name = "page") Integer pageNumber,
                                       @RequestParam(name = "size") Integer pageSize){
        var pageable=PageRequest.of(pageNumber,pageSize);
        var products=productService.findByPrice(price,pageable);
        return ResponseEntity.ok(products);
    }
}
