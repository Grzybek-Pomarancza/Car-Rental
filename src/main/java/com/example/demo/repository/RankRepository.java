package com.example.demo.repository;

import com.example.demo.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<Rank,Long> {

    Optional<Rank> findByName(String name);
}
