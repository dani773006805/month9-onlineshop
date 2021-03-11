package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.Product;
import com.attractor.onlineshop.model.ProductCategory;
import com.attractor.onlineshop.repositories.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }
    public List<ProductCategory> findAll(){
        return productCategoryRepository.findAll();
    }
}
