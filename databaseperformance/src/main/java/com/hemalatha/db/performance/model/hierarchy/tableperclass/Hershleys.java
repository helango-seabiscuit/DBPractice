package com.hemalatha.db.performance.model.hierarchy.tableperclass;

import javax.persistence.Entity;

@Entity
public class Hershleys extends Chocolate {

    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
