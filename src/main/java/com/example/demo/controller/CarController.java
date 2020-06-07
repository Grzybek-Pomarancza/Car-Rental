package com.example.demo.controller;


import com.example.demo.ResponseStatus.ResponseStatus;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.ObjectAlreadyExistsException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.service.CarService;
import com.example.demo.model.*;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.RentRepository;
import com.example.demo.validator.RentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
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
        return new ResponseStatus("Car successfully added.");
    }


    @RequestMapping(method= RequestMethod.POST,value="/rent")
    public ResponseStatus rent(@RequestBody Rent rent){
        try {
            this.rentValidator.validate(rent);
        } catch (InvalidDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input", e);
        }
        rentRepository.save(rent);
        return new ResponseStatus("Successful rent.");
    }


    //zrobilam klase ListOfFilters, ktora zawiera rzeczy mozliwe do wpisaniaw filtrach (model, marka, cena min/max, data min/max)
    @PostMapping("/filters")
    public List<Car> filters(@RequestBody ListOfFilters filter) {
        List<Car> results = carRepository.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //Robie join dla tabel
                Join<Car, Model> model = root.join("model");
                Join<Model, Brand> brand = model.join("brand");
                Join<Car, Rank> rank = root.join("rank");
                Join<Car, Rent> rent = root.join("rent", JoinType.LEFT);
                Join<Car,Office> office = root.join("office");

                final List<Predicate> predicates = new ArrayList<>();

                if (filter.getOfficeId() != null)
                    predicates.add(criteriaBuilder.equal(office.get("id"), filter.getOfficeId()));
                if (filter.getModel() != null)
                    predicates.add(criteriaBuilder.equal(model.get("name"), filter.getModel()));
                if (filter.getBrand() != null)
                    predicates.add(criteriaBuilder.equal(brand.get("name"), filter.getBrand()));
                if (filter.getMin() != 0)
                    predicates.add(criteriaBuilder.ge(rank.get("price"), filter.getMin()));
                if (filter.getMax() != 0)
                    predicates.add(criteriaBuilder.le(rank.get("price"), filter.getMax()));
                if ((filter.getRentDate() != null) && (filter.getReturnDate() != null)) {

                    Predicate pk1 = criteriaBuilder.greaterThanOrEqualTo(rent.get("returnDate"), filter.getReturnDate());
                    Predicate pk2 = criteriaBuilder.lessThanOrEqualTo(rent.get("rentDate"), filter.getReturnDate());
                    Predicate pk = criteriaBuilder.and(pk1, pk2);

                    Predicate pp1 = criteriaBuilder.greaterThanOrEqualTo(rent.get("returnDate"), filter.getRentDate());
                    Predicate pp2 = criteriaBuilder.lessThanOrEqualTo(rent.get("rentDate"), filter.getRentDate());
                    Predicate pp = criteriaBuilder.and(pp1, pp2);

                    Predicate ip1 = criteriaBuilder.greaterThanOrEqualTo(rent.get("rentDate"), filter.getRentDate());
                    Predicate ip2 = criteriaBuilder.lessThanOrEqualTo(rent.get("rentDate"), filter.getReturnDate());
                    Predicate ip = criteriaBuilder.and(ip1, ip2);

                    Predicate ik1 = criteriaBuilder.greaterThanOrEqualTo(rent.get("returnDate"), filter.getRentDate());
                    Predicate ik2 = criteriaBuilder.lessThanOrEqualTo(rent.get("returnDate"), filter.getReturnDate());
                    Predicate ik = criteriaBuilder.and(ik1, ik2);

                    Predicate dates = criteriaBuilder.not(criteriaBuilder.or(pk, pp, ik, ip));

                    //dodaje samochody, które nie mają wypożyczeń
                    Predicate a = criteriaBuilder.isEmpty(root.get("rent"));

                    Predicate x = criteriaBuilder.or(dates, a);
                    predicates.add(x);

                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        return results;
    }


}
