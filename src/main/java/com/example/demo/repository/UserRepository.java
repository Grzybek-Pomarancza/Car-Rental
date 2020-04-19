package com.example.demo.repository;


import java.util.List;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor {

    List<User> findByLastName(String lastName);
    User findByEmail(String email);
    User findById(long id);
}