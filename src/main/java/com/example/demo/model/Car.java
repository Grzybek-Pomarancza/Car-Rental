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
    private Double cordX;
    private Double cordY;
    @ManyToOne
    private Model model;
    @ManyToOne
    private Rank rank;
    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Rent> rent;


    public Car(){}

    public Car(String license, Integer year, Model model, Rank rank, Double cordX, Double cordY) {
        this.license = license;
        this.year = year;
        this.model = model;
        this.rank = rank;
        this.cordX=cordX;
        this.cordY=cordY;
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
}
