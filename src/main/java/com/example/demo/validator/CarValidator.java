package com.example.demo.validator;

import com.example.demo.repository.CarRepository;
import com.example.demo.model.Car;
import com.example.demo.validator.components.RentCarValidator;
import com.example.demo.validator.components.RentDateValidator;
import com.example.demo.validator.components.RentUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarValidator {

    private final CarRepository carRepository;
    private final ModelValidator modelValidator;
    private final RankValidator rankValidator;

    private final RentCarValidator rentCarValidator;
    private final RentDateValidator rentDateValidator;
    private final RentUserValidator rentUserValidator;

    @Autowired
    public CarValidator(CarRepository carRepository,
                        ModelValidator modelValidator,
                        RankValidator rankValidator,
                        RentCarValidator rentCarValidator,
                        RentDateValidator rentDateValidator,
                        RentUserValidator rentUserValidator) {
        this.carRepository=carRepository;
        this.modelValidator=modelValidator;
        this.rankValidator=rankValidator;
        this.rentCarValidator=rentCarValidator;
        this.rentDateValidator=rentDateValidator;
        this.rentUserValidator=rentUserValidator;
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