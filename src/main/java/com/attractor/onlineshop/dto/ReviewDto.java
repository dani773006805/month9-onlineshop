package com.attractor.onlineshop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Data
@ToString
public class ReviewDto {
    private Long id;
    private String userEmail;
    private Long productId;
    private String text;
    private LocalDateTime date;
}
