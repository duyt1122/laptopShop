package com.vn.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vn.laptopshop.domain.User;
import com.vn.laptopshop.service.UploadService;
import com.vn.laptopshop.service.UserService;


@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;

    public UserController(UserService userService, UploadService uploadService){
        this.userService = userService;
        this.uploadService = uploadService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("test", "Test Model");
        return "hello";
    }

    @RequestMapping ("/admin/users")
    public String getUsersPage(Model model){
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @RequestMapping ("/admin/user/{id}")
    public String getUsersDetailPage(Model model, @PathVariable("id") Long id){
        User user = this.userService.getUserById(id);
        // System.out.println("User: " + id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @PostMapping(value = "/admin/users/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User user, @RequestParam("avatarFile") MultipartFile file) {
     String avatar = this.uploadService.handleSaveUploadFile(file,"avatar");
        // this.userService.handleSaveUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping ("/admin/users/create")
    public String getAllUsers(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping ("/admin/user/update/{id}")
    public String uupdateUserPage(Model model, @PathVariable ("id") Long id){
        User user = this.userService.getUserById(id);
        model.addAttribute("newUser", user);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(@ModelAttribute ("newUser") User user){
    User currentUser = this.userService.getUserById(user.getId());
    if(currentUser != null){
        currentUser.setPhone(user.getPhone());
        currentUser.setFullName(user.getFullName());
        currentUser.setAddress(user.getAddress());
        this.userService.handleSaveUser(currentUser);
    }
    return "redirect:/admin/users";
    }

    @GetMapping ("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable("id") long id){
        model.addAttribute("newUser", new User());
           model.addAttribute("id", id);
        return "admin/user/delete";
    }
     @PostMapping ("/admin/user/delete")
    public String postDeleteUserPage(Model model, @ModelAttribute("newUser") User user){
           this.userService.deleteUserById(user.getId());
        return "redirect:/admin/users";
    }

}
