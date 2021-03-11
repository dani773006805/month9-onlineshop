package com.attractor.onlineshop.repositories;

import com.attractor.onlineshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByCategoryIdAndActiveIsTrue(Long id, Pageable pageable);
    Page<Product> findByNameContaining(String name,Pageable pageable);
    Page<Product> findByUnitPriceLessThanEqual(BigDecimal price, Pageable pageable);
}
