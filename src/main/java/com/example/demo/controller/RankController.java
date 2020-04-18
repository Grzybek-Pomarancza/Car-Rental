package com.example.demo.controller;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.model.Rank;
import com.example.demo.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    @RequestMapping(method=RequestMethod.POST,value="/newrank")
    public String addNewRank(@RequestBody Rank newRank) {
        try{
            rankService.addNewObject(newRank);
        } catch (ObjectAlreadyExistsException exception) {
            return "Rank already exists.";
        } catch (InvalidDataException exception) {
            return "Invalid input.";
        }
        return "Rank sucessfully added.";
    }
}
