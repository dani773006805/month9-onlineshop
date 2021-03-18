package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findByProductId(Long productId);

    Review save(Review review);
}
