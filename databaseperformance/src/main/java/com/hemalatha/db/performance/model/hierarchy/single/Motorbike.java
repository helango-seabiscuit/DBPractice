package com.hemalatha.db.performance.model.hierarchy.single;

import com.hemalatha.db.performance.model.hierarchy.single.Vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("motorbike")
public class Motorbike extends Vehicle {

    private int seats;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
