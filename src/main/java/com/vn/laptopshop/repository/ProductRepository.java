package com.vn.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.laptopshop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
