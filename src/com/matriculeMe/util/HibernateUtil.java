package com.matriculeMe.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory connection = newConnection();

    public static SessionFactory getConnection() {
        return connection;
    }

    private static SessionFactory newConnection() {
        try {
            Configuration config = new Configuration().configure();

            return config.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build());
        } catch (Throwable ex) {
            System.err.println("Connection failed. ERROR: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}