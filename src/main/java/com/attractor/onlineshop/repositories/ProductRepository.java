package com.attractor.onlineshop.repositories;

import com.attractor.onlineshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "products",path = "pro")
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findProductByCategoryIdAndActiveTrue(Long id, Pageable pageable);
    Page<Product> findByNameContaining(String name,Pageable pageable);
    Page<Product> findByUnitPriceIsLessThanEqual(BigDecimal price, Pageable pageable);
    Optional<Product> findByIdAndActiveIsTrue(Long id);
    Page<Product> findByNameContainingOrDescriptionContaining(String name,String description,Pageable pageable);
}
