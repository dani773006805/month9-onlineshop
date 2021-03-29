package com.attractor.onlineshop.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardDetailsDto {
    private Long id;
    @Size(min = 3,message = "min length is 3")
    @NotBlank
    private String nameOnCard;
    @Size(min = 3,message = "min length is 3")
    @NotBlank
    private String cardType;
    @Pattern(regexp = "[0-9]{16}")
    private String securityCode;
    @Pattern(regexp = "[0-9]{3}")
    private String cardNumber;
    @Size(min=1)
    private Integer expirationMonth;
    @Size(min = 4,max = 4)
    private Integer expirationYear;

}
