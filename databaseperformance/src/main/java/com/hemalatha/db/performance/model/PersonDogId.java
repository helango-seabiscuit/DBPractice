package com.hemalatha.db.performance.model;

import java.io.Serializable;

public class PersonDogId implements Serializable{

    private static final long serialVersionUID = 1L;

    private int person;

    private int dog;

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public int getDog() {
        return dog;
    }

    public void setDog(int dog) {
        this.dog = dog;
    }

    @Override
    public int hashCode() {
        return person + dog;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PersonDogId){
            PersonDogId personDogId = (PersonDogId) obj;
            return personDogId.dog == dog && personDogId.person == person;
        }
        return false;
    }
}
