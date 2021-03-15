package com.attractor.onlineshop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_details")
public class CardDetails extends BaseEntity {
    private String cardName;
    private String cardType;
    private String securityCode;
    private String cardNumber;
    private LocalDate expirationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
