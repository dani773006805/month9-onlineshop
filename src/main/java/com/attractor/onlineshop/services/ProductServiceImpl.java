package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Product;
import com.attractor.onlineshop.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findByCategoryId(Long id, Pageable pageable) {
        return productRepository.findProductByCategoryIdAndActiveTrue(id, pageable);
    }

    public Page<Product> findByName(String name, Pageable pageable) {
        return productRepository.findByNameContaining(name, pageable);
    }

    public Page<Product> findByPrice(BigDecimal price, Pageable pageable) {
        return productRepository.findByUnitPriceIsLessThanEqual(price, pageable);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findByIdAndActive(Long id){
        return productRepository.findByIdAndActiveIsTrue(id);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
public List<Product> findAll(){
        return productRepository.findAll();
}
}
