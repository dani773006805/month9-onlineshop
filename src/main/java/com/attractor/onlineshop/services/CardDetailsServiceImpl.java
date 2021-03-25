package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.CardDetails;
import com.attractor.onlineshop.repositories.CardDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardDetailsServiceImpl implements CardDetailsService{
    private CardDetailsRepository cardDetailsRepository;

    public CardDetailsServiceImpl(CardDetailsRepository cardDetailsRepository) {
        this.cardDetailsRepository = cardDetailsRepository;
    }

    public CardDetails save(CardDetails cardDetails){
        return cardDetailsRepository.save(cardDetails);
    }

    @Override
    public Optional<CardDetails> findByEmail(String email) {
        return cardDetailsRepository.findByUserEmail(email);
    }
}
