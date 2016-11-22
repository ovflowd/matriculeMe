package com.unb.matriculeme.dao;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinTable(name = "login_user")
    private Login login;

    @OneToOne
    @JoinTable(name = "token_user")
    private Token token;

    @Column(nullable = false, length = 100)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
