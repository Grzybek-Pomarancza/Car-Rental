package com.example.demo.validator;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarValidator {

    private final CarRepository carRepository;
    private final ModelValidator modelValidator;
    private final RankValidator rankValidator;


    @Autowired
    public CarValidator(CarRepository carRepository,
                        ModelValidator modelValidator,
                        RankValidator rankValidator) {
        this.carRepository = carRepository;
        this.modelValidator = modelValidator;
        this.rankValidator = rankValidator;
    }

    public boolean checkIfObjectExists(Car carToValidate) {

        return carRepository.findByLicense(carToValidate.getLicense()).isEmpty();
    }

    public boolean validateObject(Car carToValidate) {

        if (carToValidate.getLicense() == null || carToValidate.getLicense().isEmpty() || carToValidate.getYear() == 0)
            return false;
        if (!modelValidator.validateObject(carToValidate.getModel()))
            return false;
        return rankValidator.validateObject(carToValidate.getRank());
    }
}