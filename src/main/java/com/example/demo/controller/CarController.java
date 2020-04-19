package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.RentRepository;
import com.example.demo.service.UserService;
import com.example.demo.validator.RentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private RentValidator rentValidator;

    @Autowired
    private UserService userService;



    @GetMapping("/rent")
    public String rent(Model model) {
        model.addAttribute("rentForm", new Rent());
        return "registration";
    }

    // do tego post muszę dostać rentForm z Car i User już
    @PostMapping("/rent")
    public List<String> rent(@RequestBody Rent rentForm) {

        //to jakbym nie dostała User z frontu
        /*
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();
        User user = userService.findByEmail(email);
         */

        List<String> messages = rentValidator.validate(rentForm);
        if(messages.isEmpty()) {
            rentRepository.save(rentForm);
        }
        return messages;
    }


    //zrobilam klase ListOfFilters, ktora zawiera rzeczy mozliwe do wpisaniaw filtrach (model, marka, cena min/max, data min/max)
    @PostMapping("/filters")
    public List<Car> filters(@RequestBody ListOfFilters filter) {
        // results to lista samochodow, ktore beda sie zgadzac z filtrami
        List<Car> results = carRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //Robie join dla tabel, ktore beda mi potrzebne, poki co bez rent. Ogolnie dziala to polaczenie z rent
                // , ale nie umiem jeszcze tego zrobic tak, zeby były zwrocone samochody dostepne pomiedzy dwoma datami
                // i te,ktore w ogole nie maja rezerwacji
                Join<Car,Model> model = root.join("model");
                Join<Model,Brand> brand = model.join("brand");
                Join<Car,Rank> rank = root.join("rank");
                //Join<Car,Rent> rent = root.join("rent", JoinType.LEFT);

                //tworze liste zapytan do bazy
                final List<Predicate> predicates = new ArrayList<>();

                // sprawdzam czy sa poszczegolne filtry wpisane i jak tak to szukam w bazie pasujacych samochodow
                if(filter.getModel()!=null)
                    predicates.add(criteriaBuilder.equal(model.get("name"), filter.getModel()));
                if(filter.getBrand()!=null)
                    predicates.add(criteriaBuilder.equal(brand.get("name"), filter.getBrand()));
                if(filter.getMin()!=0)
                    predicates.add(criteriaBuilder.ge(rank.get("price"), filter.getMin()));
                if(filter.getMax()!=0)
                    predicates.add(criteriaBuilder.le(rank.get("price"), filter.getMax()));
      //        if(filter.getRentDate()!= null)
      //            predicates.add(criteriaBuilder.lessThan(rent.get("returnDate"), filter.getRentDate()));
      //        if(filter.getReturnDate()!= null)
      //            predicates.add(criteriaBuilder.greaterThan(rent.get("rentDate"), filter.getReturnDate()));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        return results;
    }




}
