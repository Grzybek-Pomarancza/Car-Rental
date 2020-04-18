package com.example.demo.controller;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Model;
import com.example.demo.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(method=RequestMethod.POST,value="/newmodel")
    public String addNewBrand(@RequestBody Model newModel) {

        try{
            modelService.addNewObject(newModel);
        } catch (InvalidDataException exception) {
            return "Invalid input.";
        } catch (ObjectAlreadyExistsException exception) {
            return "Model already exists.";
        } catch (ObjectNotFoundException exception2) {
            return "Brand does not exists.";
        }
        return "Model sucessfully added.";
    }
}
