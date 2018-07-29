package com.hemalatha.db.performance.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class CowId implements Serializable {

private static final long serialVersionUID = 1L;

//@Id
//@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

private String breed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public int hashCode() {
        return breed.hashCode() + id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CowId){
            CowId cowId = (CowId) obj;
            return cowId.id == this.id && cowId.breed.equalsIgnoreCase(this.breed);
        }
        return false;
    }
}
