<<<<<<< HEAD
package com.example.demo.repository;


import java.util.List;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);

    User findById(long id);
=======
package com.example.demo.repository;


import java.util.List;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);

    User findById(long id);
>>>>>>> 9437c9efa08817ba060e4dd81a4c945cf1f836ea
}