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

                //Robie join dla tabel
                Join<Car,Model> model = root.join("model");
                Join<Model,Brand> brand = model.join("brand");
                Join<Car,Rank> rank = root.join("rank");
                Join<Car,Rent> rent = root.join("rent", JoinType.LEFT);

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
                if((filter.getRentDate()!= null)&& (filter.getReturnDate()!= null)){

                    //sprawdzam daty

                    Predicate pk1 = criteriaBuilder.greaterThanOrEqualTo(rent.get("returnDate"), filter.getReturnDate());
                    Predicate pk2 = criteriaBuilder.lessThanOrEqualTo(rent.get("rentDate"), filter.getReturnDate());
                    Predicate pk = criteriaBuilder.and(pk1 , pk2);

                    Predicate pp1 = criteriaBuilder.greaterThanOrEqualTo(rent.get("returnDate"), filter.getRentDate());
                    Predicate pp2 = criteriaBuilder.lessThanOrEqualTo(rent.get("rentDate"), filter.getRentDate());
                    Predicate pp = criteriaBuilder.and(pp1 , pp2);

                    Predicate ip1 = criteriaBuilder.greaterThanOrEqualTo(rent.get("rentDate"), filter.getRentDate());
                    Predicate ip2 = criteriaBuilder.lessThanOrEqualTo(rent.get("rentDate"), filter.getReturnDate());
                    Predicate ip = criteriaBuilder.and(ip1 , ip2);

                    Predicate ik1 = criteriaBuilder.greaterThanOrEqualTo(rent.get("returnDate"), filter.getRentDate());
                    Predicate ik2 = criteriaBuilder.lessThanOrEqualTo(rent.get("returnDate"), filter.getReturnDate());
                    Predicate ik = criteriaBuilder.and(ik1 , ik2);

                    Predicate dates = criteriaBuilder.not(criteriaBuilder.or(pk , pp , ik , ip));

                    //dodaje samochody, które nie mają wypożyczeń
                    Predicate a = criteriaBuilder.isEmpty(root.get("rent"));

                    Predicate x = criteriaBuilder.or(dates,a);
                    predicates.add(x);

                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        return results;
    }




}
