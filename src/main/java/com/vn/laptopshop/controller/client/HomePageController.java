package com.vn.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class HomePageController {
    
    @GetMapping("/client")
    public String homePage(){
        return "client/homepage/show";
    }
}
