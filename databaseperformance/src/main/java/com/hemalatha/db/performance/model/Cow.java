package com.hemalatha.db.performance.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Cow {

    @EmbeddedId
    private CowId cowId;

    private String name;

    public CowId getCowId() {
        return cowId;
    }

    public void setCowId(CowId cowId) {
        this.cowId = cowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
