package com.example.demo.controller;

import com.example.demo.ResponseStatus.ResponseStatus;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/registration")
    public ResponseStatus registration(@RequestBody User userForm) {

        try {
            this.userValidator.validate(userForm);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input.", e);
        } catch (ObjectAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists.", e);
        }
        Role role = roleRepository.findById(1);
        userForm.setRole(role);
        String s = userForm.getFirstName().substring(0, 1).toUpperCase() + userForm.getFirstName().substring(1).toLowerCase();
        userForm.setFirstName(s);
        s = userForm.getLastName().substring(0, 1).toUpperCase() + userForm.getLastName().substring(1).toLowerCase();
        userForm.setLastName(s);
        userService.save(userForm);
        securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirm());
        return new ResponseStatus("Registered successfully.");
    }
}