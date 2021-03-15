package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.CardDetails;
import com.attractor.onlineshop.repositories.CardDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class CardDetailsService {
    private CardDetailsRepository cardDetailsRepository;

    public CardDetailsService(CardDetailsRepository cardDetailsRepository) {
        this.cardDetailsRepository = cardDetailsRepository;
    }

    public CardDetails save(CardDetails cardDetails){
        return cardDetailsRepository.save(cardDetails);
    }
}
