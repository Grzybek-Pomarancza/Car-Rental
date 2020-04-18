package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

        UserValidator validator = new UserValidator();
        List<String> messages = validator.validate(userForm);
        if(messages.isEmpty()) {
            Role role = roleRepository.findById(1);
            userForm.setRole(role);
            userService.save(userForm);
            securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirm());
        }

        return messages;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}