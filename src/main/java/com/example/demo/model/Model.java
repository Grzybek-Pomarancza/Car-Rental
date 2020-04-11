<<<<<<< HEAD
package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "MODELS")
public class Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private Brand brand;

    public Model(){}

    public Model(String name, Brand brand) {
        this.name = name;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }
}

=======
package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "MODELS")
public class Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private Brand brand;

    public Model(){}

    public Model(String name, Brand brand) {
        this.name = name;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }
}

>>>>>>> 9437c9efa08817ba060e4dd81a4c945cf1f836ea
