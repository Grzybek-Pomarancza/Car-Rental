package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public List<String> registration(@RequestBody User userForm) {

        List<String> messages = userValidator.validate(userForm);
        if (messages.isEmpty()) {
            Role role = roleRepository.findById(1);
            userForm.setRole(role);
            String s = userForm.getFirstName().substring(0, 1).toUpperCase() + userForm.getFirstName().substring(1).toLowerCase();
            userForm.setFirstName(s);
            s = userForm.getLastName().substring(0, 1).toUpperCase() + userForm.getLastName().substring(1).toLowerCase();
            userForm.setLastName(s);
            userService.save(userForm);
            securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirm());
        }

        return messages;
    }
}