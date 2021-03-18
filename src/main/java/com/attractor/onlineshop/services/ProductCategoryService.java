package com.attractor.onlineshop.services;

import com.attractor.onlineshop.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService {
    Page<ProductCategory> findAll(Pageable pageable);
}
