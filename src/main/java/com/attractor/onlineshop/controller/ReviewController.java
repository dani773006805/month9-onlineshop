package com.attractor.onlineshop.controller;

import com.attractor.onlineshop.dto.mapper.ReviewMapper;
import com.attractor.onlineshop.exceptions.ResourceNotFoundException;
import com.attractor.onlineshop.model.Review;
import com.attractor.onlineshop.services.ProductService;
import com.attractor.onlineshop.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private ProductService productService;

    public ReviewController(ReviewService reviewService, ProductService productService) {
        this.reviewService = reviewService;
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findByProductId(@PathVariable Long id){
        var reviews1=reviewService.findByProductId(id);
        var reviews=reviewService.findByProductId(id).stream()
                .map(review -> ReviewMapper.fromAll(review)).collect(Collectors.toList());
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<?> save(@RequestBody @Valid Review review,@PathVariable Long productId){
        var product=productService.findById(productId).orElseThrow(()->
                new ResourceNotFoundException(String.format("no product with id %d",productId)));
        review.setProduct(product);
        var savedReview=reviewService.save(review);
        var reviewDto=ReviewMapper.fromAll(savedReview);
        return ResponseEntity.status(201).body(reviewDto);
    }
}
