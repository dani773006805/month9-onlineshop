package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Review;
import com.attractor.onlineshop.repositories.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository repository;
    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    public Page<Review> findByProductId(Long productId, Pageable pageable){
        return repository.findByProductId(productId,pageable);
    }

    public Review save(Review review){
        review.setDate(LocalDateTime.now());
        return repository.save(review);
    }
}
