package com.attractor.onlineshop.services;

import com.attractor.onlineshop.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }
}
