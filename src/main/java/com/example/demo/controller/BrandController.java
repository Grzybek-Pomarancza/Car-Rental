package com.example.demo.controller;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.Brand;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(method=RequestMethod.POST,value="/newbrand")
    public String addNewBrand(@RequestBody Brand newBrand) {

        try{
            brandService.addNewObject(newBrand);
        } catch (ObjectAlreadyExistsException exception) {
            return "Brand already exists.";
        } catch (InvalidDataException exception) {
            return "Invalid input.";
        }
        return "Brand added succesfuly.";
    }
}
