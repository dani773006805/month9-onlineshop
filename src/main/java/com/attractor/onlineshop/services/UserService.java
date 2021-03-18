package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User save(User user);
}
