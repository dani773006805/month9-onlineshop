package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Product;
import com.attractor.onlineshop.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findByCategoryId(Long id, Pageable pageable){
        return productRepository.findByCategoryIdAndActiveIsTrue(id,pageable);
    }
    public Page<Product> findByName(String name,Pageable pageable){
        return productRepository.findByNameContaining(name,pageable);
    }
    public Page<Product> findByPrice(BigDecimal price,Pageable pageable){
        return productRepository.findByUnitPriceLessThanEqual(price,pageable);
    }
}
