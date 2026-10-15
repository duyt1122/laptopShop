package com.vn.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vn.laptopshop.domain.User;
import com.vn.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user){
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
    public User getUserById(long id){
        return this.userRepository.findById(id).orElse(null);
    }
}
