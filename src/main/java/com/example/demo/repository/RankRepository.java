package com.example.demo.repository;


import java.util.List;

import com.example.demo.model.Rank;
import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends CrudRepository<Rank, Long> {

    Rank findByName(String name);
    Rank findById(long id);
}