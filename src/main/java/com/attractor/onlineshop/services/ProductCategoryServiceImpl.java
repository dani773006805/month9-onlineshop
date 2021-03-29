package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.ProductCategory;
import com.attractor.onlineshop.repositories.ProductCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }
    public Page<ProductCategory> findAll(Pageable pageable){
        return productCategoryRepository.findAll(pageable);
    }
}
