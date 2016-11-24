package com.unb.matriculeme.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

public class Connection {

    private static EntityManagerFactory factory;

    private EntityManager manager;

    private static CriteriaBuilder builder;

    public static CriteriaBuilder getBuilder() {
        return builder == null ? builder = getFactory().getCriteriaBuilder() : builder;
    }

    public static EntityManagerFactory getFactory() {
        return factory == null ? factory = Persistence.createEntityManagerFactory("myDB") : factory;
    }

    public Connection() {
        manager = getFactory().createEntityManager();
    }

    public EntityManager getManager() {
        return manager;
    }

    public void closeManager() {
        manager.close();
    }

    public static void closeFactory() {
        factory.close();
    }
}