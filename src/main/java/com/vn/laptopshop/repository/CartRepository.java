package com.vn.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.laptopshop.domain.Cart;
import com.vn.laptopshop.domain.User;

public interface  CartRepository extends JpaRepository<Cart, Long>{
    Cart findByUser(User user);
}
