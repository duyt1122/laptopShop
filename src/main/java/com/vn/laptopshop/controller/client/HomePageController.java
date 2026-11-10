package com.vn.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vn.laptopshop.domain.Product;
import com.vn.laptopshop.service.ProductService;

@Controller 
public class HomePageController {

    private final ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/")
    public String homePage(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }
    @GetMapping("/register")
    public String registerPage() {
        return "client/auth/register";
    }
}
