package com.vn.laptopshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String test(){
        return "Only user can access this page";
    }

    @GetMapping("/admin")
    public String testAdmin(){
        return "Only Admin can access this page";
    }

}
