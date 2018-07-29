package com.hemalatha.db.performance.model;

import java.io.Serializable;

public class CarId implements Serializable {

    private static final long serialVersionUID = 1L;

    private int serial;
    private String brand;

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int hashCode() {
        return serial +brand.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CarId){
            CarId carId = (CarId) obj;
            return carId.brand.equalsIgnoreCase(this.brand) && carId.serial == this.serial;
        }
        return false;
    }
}
