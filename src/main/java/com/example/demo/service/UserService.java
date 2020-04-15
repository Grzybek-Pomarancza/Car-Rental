package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);
    User findByEmail(String email);
    
}
