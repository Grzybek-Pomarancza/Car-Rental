package com.example.demo.repository;


import java.util.List;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);

    User findById(long id);
}