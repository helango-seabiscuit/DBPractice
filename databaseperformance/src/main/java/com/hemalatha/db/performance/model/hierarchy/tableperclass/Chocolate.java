package com.hemalatha.db.performance.model.hierarchy.tableperclass;


import javax.persistence.*;

@Entity
@Table(name = "chocolate")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Chocolate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

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
}
