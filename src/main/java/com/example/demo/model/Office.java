package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "OFFICE")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double cordX;
    private Double cordY;
    @OneToMany(mappedBy = "office")
    private List<Car> cars;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    public Office() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCordX() {
        return cordX;
    }

    public void setCordX(Double cordX) {
        this.cordX = cordX;
    }

    public Double getCordY() {
        return cordY;
    }

    public void setCordY(Double cordY) {
        this.cordY = cordY;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
