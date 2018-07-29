package com.hemalatha.db.performance.model.hierarchy.joined;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("full_time")
public class FullTimeEmployee extends Employee{

    private int vacation_hours;

    public int getVacation_hours() {
        return vacation_hours;
    }

    public void setVacation_hours(int vacation_hours) {
        this.vacation_hours = vacation_hours;
    }
}
