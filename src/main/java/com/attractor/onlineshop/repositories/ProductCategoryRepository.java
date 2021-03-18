package com.attractor.onlineshop.repositories;

import com.attractor.onlineshop.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

}
