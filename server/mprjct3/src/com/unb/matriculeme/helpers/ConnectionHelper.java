package com.unb.matriculeme.helpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionHelper {

    private EntityManagerFactory emf;
    private EntityManager em;

    public EntityManagerFactory GetEmf() {
        if (emf == null) {
            this.emf = Persistence.createEntityManagerFactory("myDB");
        }
        return emf;
    }

    public EntityManager GetEm() {
        if (em == null) {
            this.em = this.emf.createEntityManager();
        }
        return this.em;
    }

}
