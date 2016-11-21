package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
@Table(name = "motivo")
public class Reason {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;

    @Column
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
