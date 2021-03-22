package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User save(User user);
}
