package com.hemalatha.db.performance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(PersonDogId.class)
public class PersonDog {


    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @Temporal(TemporalType.DATE)
    private Date adoptionDate;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Date getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(Date adoptionDate) {
        this.adoptionDate = adoptionDate;
    }
}
