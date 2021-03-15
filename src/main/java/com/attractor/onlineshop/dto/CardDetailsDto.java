package com.attractor.onlineshop.dto;

import com.attractor.onlineshop.model.User;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardDetailsDto {
    private Long id;
    @NotBlank
    private String cardName;
    @NotBlank
    private String cardType;
    @Pattern(regexp = "[0-9]{16}")
    private String securityCode;
    @Pattern(regexp = "[0-9]{3}")
    private String cardNumber;
    @Future
    private LocalDate expirationDate;
}
