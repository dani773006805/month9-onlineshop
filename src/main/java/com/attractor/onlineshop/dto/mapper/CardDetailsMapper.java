package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.dto.CardDetailsDto;
import com.attractor.onlineshop.model.CardDetails;

import java.time.LocalDate;

public class CardDetailsMapper {
    public static CardDetailsDto fromEntToDto(CardDetails cardDetails){
        return CardDetailsDto.builder()
                .id(cardDetails.getId())
                .nameOnCard(cardDetails.getCardName())
                .cardType(cardDetails.getCardType())
                .cardNumber(cardDetails.getCardNumber())
                .securityCode(cardDetails.getSecurityCode())
                .expirationMonth(cardDetails.getExpirationDate().getMonthValue())
                .expirationYear(cardDetails.getExpirationDate().getYear())
                .build();

    }
    public static CardDetails fromDtoToEnt(CardDetailsDto cardDetailsDto){
        return CardDetails.builder()
                .cardName(cardDetailsDto.getNameOnCard())
                .cardType(cardDetailsDto.getCardType())
                .cardNumber(cardDetailsDto.getCardNumber())
                .securityCode(cardDetailsDto.getSecurityCode())
                .expirationDate(LocalDate.of(cardDetailsDto.getExpirationYear(),cardDetailsDto.getExpirationMonth(),1))
                .build();
    }

}
