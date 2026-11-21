package com.vn.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vn.laptopshop.domain.Cart;
import com.vn.laptopshop.domain.CartDetail;
import com.vn.laptopshop.domain.Product;
import com.vn.laptopshop.domain.User;
import com.vn.laptopshop.repository.CartDetailRepository;
import com.vn.laptopshop.repository.CartRepository;
import com.vn.laptopshop.repository.ProductRepository;

@Service
public class ProductService{
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository, final CartDetailRepository cartDetailRepository,UserService userService) {
        this.productRepository = productRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    public void deleteProduct(Long id){
        this.productRepository.deleteById(id);
    }
    public Product getProductById(Long id){
        return this.productRepository.findById(id).orElse(null);
    }
    public Product findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            return null;
        }else{
            return optionalProduct.get();
        }
    }

    public void handleAddProductToCart(String email,long id){

        User user = this.userService.findUserByEmail(email);
        if(user != null){
             Cart cart = this.cartRepository.findByUser(user);
            if(user.getCart() == null){
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(1);
                cart = this.cartRepository.save(otherCart);
            }

           

            Optional<Product> optionalProduct = this.productRepository.findById(id);
            if(optionalProduct.isPresent()){
                Product p = optionalProduct.get();
                CartDetail cd = new CartDetail();
                cd.setCart(cart);
                cd.setProduct(p);
                cd.setPrice(p.getPrice());
                cd.setQuantity(1);
            this.cartDetailRepository.save(cd);

            }

            
        }

    }
}
