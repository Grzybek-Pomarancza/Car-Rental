package com.example.demo.validator;

import com.example.demo.repository.RankRepository;
import com.example.demo.model.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankValidator {

    private final RankRepository rankRepository;

    @Autowired
    public RankValidator(RankRepository rankRepository){
        this.rankRepository=rankRepository;
    }

    public boolean checkIfObjectExists(Rank rankToValidate) {

        if(rankRepository.findByName(rankToValidate.getName()).isPresent())
            return false;
        else
            return true;
    }

    public boolean validateObject(Rank rankToValidate) {

        if( rankToValidate.getName() == null || rankToValidate.getName().isEmpty())
            return false;
        if( rankToValidate.getPrice() == null || rankToValidate.getDeposit() == null)
            return false;
        return true;
    }
}
