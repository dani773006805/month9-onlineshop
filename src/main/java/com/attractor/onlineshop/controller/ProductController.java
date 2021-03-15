package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.mapper.ProductMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.services.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/shop")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product-category/{id}/products")
    public ResponseEntity<?> getByCategoryId(@PathVariable String id, Pageable pageable){
        var products=productService.findByCategoryId(Long.parseLong(id),pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/findByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name,Pageable pageable){
        var products=productService.findByName(name,pageable);
        var productDtos=products.map(product -> ProductMapper.mainPage(product));
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/findByPrice/{price}")
    public ResponseEntity<?> getByName(@PathVariable BigDecimal price,Pageable pageable){
        var products=productService.findByPrice(price,pageable);
        var productDto=products.map(product -> ProductMapper.mainPage(product));
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        var product=productService.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format("No product with id %d",id)));
        var productDto= ProductMapper.fromAll(product);
        return ResponseEntity.ok(productDto);
    }
}
