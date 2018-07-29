package com.hemalatha.db.performance.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dog")
@Cacheable
@SecondaryTable(name = "dog_secondaary_a", pkJoinColumns = {@PrimaryKeyJoinColumn(name = "dog_id")})
public class Dog extends DogFather {

    @Id
    @TableGenerator(name = "TABLE_GEN",table = "ID_TABLE", pkColumnName = "ID_TABLE_NAME",
    pkColumnValue = "PERSON_ID", valueColumnName = "ID_TABLE_VALUE",allocationSize = 10,initialValue = 40)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private int id;

    private String name;

    private int age;

    @Column(table = "dog_secondaary_a")
    private double weight;

    @OneToMany(mappedBy = "dog")
    private List<PersonDog> persons = new ArrayList<>();

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

    public List<PersonDog> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDog> persons) {
        this.persons = persons;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
