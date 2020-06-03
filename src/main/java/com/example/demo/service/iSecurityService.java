package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public interface iSecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
