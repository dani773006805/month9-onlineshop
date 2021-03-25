package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    Page<Review> findByProductId(Long productId, Pageable pageable);

    Review save(Review review);
}
