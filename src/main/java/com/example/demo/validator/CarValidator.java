package com.example.demo.validator;

import com.example.demo.repository.CarRepository;
import com.example.demo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarValidator {

    private final CarRepository carRepository;
    private final ModelValidator modelValidator;
    private final RankValidator rankValidator;

    @Autowired
    public CarValidator(CarRepository carRepository, ModelValidator modelValidator, RankValidator rankValidator) {
        this.carRepository=carRepository;
        this.modelValidator=modelValidator;
        this.rankValidator=rankValidator;
    }

    public boolean checkIfObjectExists(Car carToValidate) {

        if(carRepository.findByLicense(carToValidate.getLicense()).isPresent())
            return false;
        else
            return true;
    }

    public boolean validateObject(Car carToValidate) {

        if(carToValidate.getLicense() == null || carToValidate.getLicense().isEmpty() || carToValidate.getYear() == 0)
            return false;
        if(!modelValidator.validateObject(carToValidate.getModel()))
            return false;
        if(!rankValidator.validateObject(carToValidate.getRank()))
            return false;
        if(carToValidate.getCordX() == null || carToValidate.getCordY() == null)
            return false;
        return true;
    }
}