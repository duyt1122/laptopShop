package com.vn.laptopshop.controller;

import com.vn.laptopshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(){
        return "test.html";
    }

}
