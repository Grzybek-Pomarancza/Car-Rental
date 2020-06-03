package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private int year;
    private String license;
    @ManyToOne
    private Model model;
    @ManyToOne
    private Rank rank;
    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Rent> rent;


    public Car(){}

    public Car(String license, int year, Model model, Rank rank) {
        this.license = license;
        this.year = year;
        this.model = model;
        this.rank = rank;
    }

    public int getYear() {
        return year;
    }

    public List<Rent> getRent() {
        return rent;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
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
