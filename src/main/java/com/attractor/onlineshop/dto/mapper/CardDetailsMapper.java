package com.attractor.onlineshop.dto.mapper;

import com.attractor.onlineshop.dto.CardDetailsDto;
import com.attractor.onlineshop.model.CardDetails;

public class CardDetailsMapper {
    public static CardDetailsDto fromEntToDto(CardDetails cardDetails){
        return CardDetailsDto.builder()
                .id(cardDetails.getId())
                .cardName(cardDetails.getCardName())
                .cardType(cardDetails.getCardType())
                .cardNumber(cardDetails.getCardNumber())
                .securityCode(cardDetails.getSecurityCode())
                .expirationDate(cardDetails.getExpirationDate())
                .build();

    }
    public static CardDetails fromDtoToEnt(CardDetailsDto cardDetailsDto){
        return CardDetails.builder()
                .cardName(cardDetailsDto.getCardName())
                .cardType(cardDetailsDto.getCardType())
                .cardNumber(cardDetailsDto.getCardNumber())
                .securityCode(cardDetailsDto.getSecurityCode())
                .expirationDate(cardDetailsDto.getExpirationDate())
                .build();
    }

}
