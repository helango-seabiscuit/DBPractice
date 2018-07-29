package com.hemalatha.db.performance.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 20)
public class Person {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name = "cellular_id")
    private Cellular cellular;

    @OneToMany(mappedBy = "person",orphanRemoval = false)  //many to many with extra fields
    private List<PersonDog> dogs = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name ="person_has_emails")
    private Set<String> emails;

    @ElementCollection(targetClass = CarBrands.class)
    @Enumerated(EnumType.STRING)
    private List<CarBrands> brands;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonDog> getDogs() {
        return dogs;
    }

    public void setDogs(List<PersonDog> dogs) {
        this.dogs = dogs;
    }

    public Cellular getCellular() {
        return cellular;
    }

    public void setCellular(Cellular cellular) {
        this.cellular = cellular;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public List<CarBrands> getBrands() {
        return brands;
    }

    public void setBrands(List<CarBrands> brands) {
        this.brands = brands;
    }
}
