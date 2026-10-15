package com.vn.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vn.laptopshop.domain.User;
import com.vn.laptopshop.service.UserService;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("test", "Test Model");
        return "hello";
    }

    @RequestMapping (value = "/admin/users")
    public String getUsersPage(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping (value = "/admin/users1", method = RequestMethod.POST)
    public String getUsersPage1(Model model, @ModelAttribute("newUser") User user){
        model.addAttribute("users", "user");
        System.out.println("user: " + user);
        return "hello";
    }

}
