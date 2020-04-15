package com.example.demo.repository;


import java.util.List;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);
    //User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
}