package com.hemalatha.db.performance.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DogFather {
    private String fatherName;

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
}
