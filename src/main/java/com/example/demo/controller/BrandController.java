package com.example.demo.controller;

import com.example.demo.ResponseStatus.ResponseStatus;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.Brand;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(method=RequestMethod.POST,value="/newbrand")
    public ResponseStatus addNewBrand(@RequestBody Brand newBrand) {

        try{
            brandService.addNewObject(newBrand);
        } catch (ObjectAlreadyExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brand Already Exists.", exception);
        } catch (InvalidDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input.", exception);
        }
        return new ResponseStatus("Brand SuccesfulLy Added.");
    }
}