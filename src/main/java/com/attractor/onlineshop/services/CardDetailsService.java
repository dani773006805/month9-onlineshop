package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.CardDetails;

import java.util.Optional;

public interface CardDetailsService {
    CardDetails save(CardDetails cardDetails);
    Optional<CardDetails> findByEmail(String email);
}
