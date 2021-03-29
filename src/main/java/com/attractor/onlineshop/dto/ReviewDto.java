package com.attractor.onlineshop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@ToString
public class ReviewDto {
    private Long id;
    private String userEmail;
    private Long productId;
    @Size(min=4)
    @NotBlank
    private String text;
    private LocalDate date;
}
