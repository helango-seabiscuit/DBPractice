package com.hemalatha.db.performance.model.hierarchy.tableperclass;

import javax.persistence.Entity;

@Entity
public class Kitkat extends Chocolate{

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
