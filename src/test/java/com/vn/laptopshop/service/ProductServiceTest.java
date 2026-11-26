package com.vn.laptopshop.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.vn.laptopshop.domain.Cart;
import com.vn.laptopshop.domain.CartDetail;
import com.vn.laptopshop.repository.CartDetailRepository;
import com.vn.laptopshop.repository.CartRepository;
import com.vn.laptopshop.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

class ProductServiceTest {

    @Test
    void removingLastCartDetailKeepsCartAndSetsSumToZero() {
        ProductRepository productRepository = mock(ProductRepository.class);
        CartRepository cartRepository = mock(CartRepository.class);
        CartDetailRepository cartDetailRepository = mock(CartDetailRepository.class);
        UserService userService = mock(UserService.class);
        HttpSession session = mock(HttpSession.class);
        ProductService productService = new ProductService(
                productRepository, cartRepository, cartDetailRepository, userService);

        Cart cart = new Cart();
        cart.setId(7L);
        cart.setSum(1);
        CartDetail detail = new CartDetail();
        detail.setCart(cart);
        when(cartDetailRepository.findById(12L)).thenReturn(Optional.of(detail));

        productService.handleRemoveCartDetail(12L, session);

        verify(cartDetailRepository).delete(detail);
        verify(cartRepository).save(cart);
        verify(cartRepository, never()).deleteById(7L);
        verify(session).setAttribute("sum", 0);
    }
}
