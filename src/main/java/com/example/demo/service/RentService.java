package com.example.demo.service;

import com.example.demo.model.Rent;
import com.example.demo.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    @Autowired
    RentRepository rentRepository;

    public void save(Rent rent){
        rentRepository.save(rent);
    }
}
