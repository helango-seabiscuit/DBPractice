package com.hemalatha.db.performance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cellular {

    @Id
    @GeneratedValue
    private int id;

    private int number;


    //below used in case of bidirectional relationship
    @OneToOne(mappedBy = "cellular")
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
