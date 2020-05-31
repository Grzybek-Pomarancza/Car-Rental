package com.example.demo.repository;

import com.example.demo.model.Rank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankRepository extends CrudRepository<Rank, Long> {

    Optional<Rank> findByName(String name);
    Optional<Rank> findById(long id);
}