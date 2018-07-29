package com.hemalatha.db.performance.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DogHouseB implements Serializable{

    @Id
    private int dogId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "DOG_ID")
    private Dog dog;

    private String brand;

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
