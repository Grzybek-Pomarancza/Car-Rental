package com.example.demo.service;

import com.example.demo.exceptions.ObjectAlreadyExists;
import com.example.demo.model.Car;
import com.example.demo.model.Rank;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RankRepository rankRepository;

    public void checkIfRankExists(Rank inputRank) {
        if(rankRepository.findById(inputRank.getId()).isPresent()) {
            throw new ObjectAlreadyExists();
        }
    }

    public void addNewCar(Car inputCar) throws RuntimeException {
        carRepository.save(new Car(inputCar.getLicense(),inputCar.getYear(),inputCar.getModel(),inputCar.getRank()));
    }

    public void addNewRank(Rank inputRank) throws RuntimeException {
        rankRepository.save(new Rank(inputRank.getName(),inputRank.getDeposit(),inputRank.getPrice()));
    }
}
