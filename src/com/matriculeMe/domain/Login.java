package com.matriculeMe.domain;

import javax.persistence.*;


@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 40)
    private String password;
    @Column(nullable = false)
    private String accessKey;

    public Login() {
        this.id = 0;
        this.accessKey = "admin";
        this.password = "12345";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
