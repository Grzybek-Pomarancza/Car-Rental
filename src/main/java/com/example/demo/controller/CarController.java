package com.example.demo.controller;

import com.example.demo.model.Rank;
import com.example.demo.service.RankService;
import com.example.demo.service.iUserService;
import com.example.demo.validator.RankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    @Autowired
    private RankService rankService;

    @Autowired
    private RankValidator rankValidator;

    @GetMapping("/rank")
    public String rank(Model model) {
        model.addAttribute("rankForm", new Rank());
        return "rank";
    }

    @PostMapping("/rank")
    public String rank(@ModelAttribute("userForm") Rank rankForm, BindingResult bindingResult) {
        rankValidator.validate(rankForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "rank";
        }

        rankService.save(rankForm);
        return "redirect:/rank";
    }
}
