package com.example.demo.repository;

import com.example.demo.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankRepository extends JpaRepository <Rank,Long> {
}
