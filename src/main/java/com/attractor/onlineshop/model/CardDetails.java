package com.attractor.onlineshop.model;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Builder
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails extends BaseEntity {
    private String cardName;
    private String cardType;

    private String securityCode;

    private String cardNumber;

    private LocalDate expirationDate;
}
