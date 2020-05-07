package com.example.demo.controller;

import com.example.demo.ResponseStatus.ResponseStatus;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(method= RequestMethod.POST,value="/newcar")
    public ResponseStatus addNewCar(@RequestBody Car newCar)  {
        try{
            carService.addNewObject(newCar);
        } catch (ObjectAlreadyExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with given license already exists.", exception);
        } catch (ObjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Model, brand or rank does not exists.",exception);
        } catch (InvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input", exception);
        }
        return new ResponseStatus("Car sucessfully added.");
    }
}
