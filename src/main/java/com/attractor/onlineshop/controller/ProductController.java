package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.mapper.ProductMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.model.Product;
import com.attractor.onlineshop.services.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shop")
public class ProductController {
    private final ProductService productServiceImpl;

    public ProductController(ProductService productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }


    @GetMapping("/product-category/{id}/products")
    public ResponseEntity<?> getByCategoryId(@PathVariable Long id, Pageable pageable){
        var products= productServiceImpl.findByCategoryId(id,pageable).map(ProductMapper::fromAll);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/products/findByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name,Pageable pageable){
        var products= productServiceImpl.findByName(name,pageable);
        var productDtos=products.map(ProductMapper::fromAll);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/findByPrice/{price}")
    public ResponseEntity<?> getByName(@PathVariable BigDecimal price,Pageable pageable){
        var productDto= productServiceImpl.findByPrice(price,pageable).map(ProductMapper::fromAll);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/products/findByNameAndDescriptionContaining/{keyword}")
    public ResponseEntity<?> getByDescription(@PathVariable String keyword,
                                              Pageable pageable){
        var productDto=productServiceImpl.findByDescription(keyword,pageable)
                .map(ProductMapper::fromAll);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        var product= productServiceImpl.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format("No product with id %d",id)));
        var productDto= ProductMapper.fromAll(product);
        return ResponseEntity.ok(productDto);
    }
}
