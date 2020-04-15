package com.example.demo.controller;


import com.example.demo.exceptions.ObjectAlreadyExists;
import com.example.demo.model.Rank;
import com.example.demo.repository.RankRepository;
import com.example.demo.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RankAddController {

    private RankService rankService;
    private RankRepository rankRepository;

    @Autowired
    public RankAddController(RankService rankService) {
        this.rankService = rankService;
    }

    @PostMapping("/welcome")
    public Object addRank(@RequestBody Rank inputRank) {
        try {
            rankService.save(inputRank);
        } catch (ObjectAlreadyExists exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rank already exists!");
        }
        return new ResponseStatusException(HttpStatus.OK, "Car successfully added.");
    }
}
