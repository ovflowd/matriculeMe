package com.unb.matriculeme.helpers;

import com.mchange.v2.c3p0.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;


public class PersistenceHelper {

    private ConnectionHelper conHelper = new ConnectionHelper();

    public static void SetShit() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3307/exampledb");
            cpds.setUser("root");
            cpds.setPassword("123456");
        } catch (PropertyVetoException ex) {
        }
    }

    public static <T> void Persist(T t) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public static <T> void Delete(T t) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        T dynamicDelete = em.merge(t);

        em.remove(dynamicDelete);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static List queryCustomJoin(String dia, String horarioInicio) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("from Turma turma inner join turma.horario horar where horar.dia like :dia AND horar.horarioInicio like :horarioInicio");

        List objects = query.setParameter("dia", "%" + dia + "%").setParameter("horarioInicio", "%" + horarioInicio + "%").getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return objects;
    }

    public static List queryCustom(String table, String toQuery, String t) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table + " where " + toQuery + "=:t ");
        int valor = 0;
        List objects = new ArrayList<>();

        objects = query.setParameter("t", t).getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return objects;
    }

    public static List queryCustom(String table, String toQuery, int t) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table + " where " + toQuery + "=:t ");
        int valor = 0;
        List objects = new ArrayList<>();
        
        objects = query.setParameter("t", t).getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return objects;
    }


    public static List queryGetList(String table) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("from " + table);

        List objects = query.getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return objects;
    }

    public static List queryCustomLike(String table, String toQuery, String intext) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Query query = em.createQuery("from " + table + " where " + toQuery + " LIKE :finder");
        List objects = query.setParameter("finder", "%" + intext + "%").getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return objects;
    }

    public static List queryCustom(String table, String toQuery, String value, String andQuery, String andValue) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table + " where " + toQuery + "=:valor_um AND " + andQuery + "=:valor_dois");

        List objects = query.setParameter("valor_um", value).setParameter("valor_dois", andValue).getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return objects;
    }

    public static List queryCustomTurma(String table, String toQuery, int value, String andQuery, int andValue) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table + " where " + toQuery + "=:valor1 AND " + andQuery + "=:andValue");
        List objects = query.setParameter("valor1", value).setParameter("andValue", andValue).getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return objects;
    }

    public static List queryCustom(String table, String toQuery, String value, String andQuery, String andValue, String otherQuery, String othervalue) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table + " where " + toQuery + "=:value AND " + andQuery + "=:andValue AND " + otherQuery + "=:otherValue");

        List objects = query.setParameter("value", value).setParameter("andValue", andValue).setParameter("otherValue", othervalue).getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return objects;
    }
}
