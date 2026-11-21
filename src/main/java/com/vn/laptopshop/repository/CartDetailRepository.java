package com.vn.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.laptopshop.domain.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    
}
