package com.vn.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vn.laptopshop.domain.Product;

@Controller 
public class ProductController {
    
     @GetMapping ("/admin/product")
    private String getProduct(){

        return "admin/product/show";
    }

    @GetMapping ("/admin/product/create")
    private String getProductCreate(Model model){
        model.addAttribute("newProduct",new Product());
        return "admin/product/create";
    }
}
