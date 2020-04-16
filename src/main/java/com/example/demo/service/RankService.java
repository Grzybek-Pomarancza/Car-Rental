package com.example.demo.service;

import com.example.demo.model.Rank;
import com.example.demo.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankService {
    @Autowired
    private RankRepository rankRepository;

    public void save(Rank rank) {
        rankRepository.save(rank);
    }

    public Rank findByName(String email) {
        return rankRepository.findByName(email);
    }
}