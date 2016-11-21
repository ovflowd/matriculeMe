package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class ReserveType {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private int reserveType;

    @Column
    private int reserveValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReserveType() {
        return reserveType;
    }

    public void setReserveType(int reserveType) {
        this.reserveType = reserveType;
    }

    public int getReserveValue() {
        return reserveValue;
    }

    public void setReserveValue(int reserveValue) {
        this.reserveValue = reserveValue;
    }
}
