package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.dto.ReviewDto;
import com.attractor.onlineshop.model.Review;

public class ReviewMapper {
    public static ReviewDto fromAll(Review review) {
        return ReviewDto.builder()
                .date(review.getDate())
                .text(review.getText())
                .id(review.getId())
                .productId(review.getProduct().getId())
                .userEmail(review.getUserEmail())
                .build();
    }
}
