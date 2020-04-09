package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String street;
    private int number;
    private String code;
    private String city;
    @ManyToOne
    private Model model;
    @ManyToOne
    private Rank rank;

    Car(){}

    public Car(String street, int number, String code, String city, Model model, Rank rank) {
        this.street = street;
        this.number = number;
        this.code = code;
        this.city = city;
        this.model = model;
        this.rank = rank;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }
}
