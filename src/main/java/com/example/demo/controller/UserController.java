package com.example.demo.controller;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dao.ResponseStatus;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository, UserValidator userValidator) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userValidator = userValidator;
    }

    @GetMapping("/user")
    public User user() {
        try {
            return userService.getAuthorizedUser();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found!");
        }

    }

    @PostMapping("/registration")
    public ResponseStatus registration(@RequestBody User userForm) {

        try {
            this.userValidator.validate(userForm);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input.");
        } catch (ObjectAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists.");
        }
        Role role = roleRepository.findById(1);
        userForm.setRole(role);
        String s = userForm.getFirstName().substring(0, 1).toUpperCase() + userForm.getFirstName().substring(1).toLowerCase();
        userForm.setFirstName(s);
        s = userForm.getLastName().substring(0, 1).toUpperCase() + userForm.getLastName().substring(1).toLowerCase();
        userForm.setLastName(s);
        userService.save(userForm);
        return new ResponseStatus("Registered successfully.");
    }
}
