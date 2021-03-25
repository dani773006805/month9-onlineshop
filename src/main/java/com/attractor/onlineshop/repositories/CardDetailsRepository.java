package com.attractor.onlineshop.repositories;

import com.attractor.onlineshop.model.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails,Long> {
    Optional<CardDetails> findByUserEmail(String email);
}
