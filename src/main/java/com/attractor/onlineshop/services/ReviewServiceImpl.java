package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Review;
import com.attractor.onlineshop.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository repository;
    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> findByProductId(Long productId){
        return repository.findByProductId(productId);
    }

    public Review save(Review review){
        review.setDate(LocalDateTime.now());
        return repository.save(review);
    }
}
