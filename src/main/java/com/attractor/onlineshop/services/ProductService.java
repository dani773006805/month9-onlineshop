package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> findByCategoryId(Long id, Pageable pageable);

    Page<Product> findByName(String name, Pageable pageable);

    Page<Product> findByPrice(BigDecimal price, Pageable pageable);

    Optional<Product> findById(Long id);

    Optional<Product> findByIdAndActive(Long id);

    Product update(Product product);

    Product save(Product product);
    List<Product> findAll();
}
