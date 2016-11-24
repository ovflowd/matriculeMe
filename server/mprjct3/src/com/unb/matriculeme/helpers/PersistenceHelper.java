package com.unb.matriculeme.helpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.List;

public final class PersistenceHelper {

    public static <T> void insert(T t) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public static <T> void update(T old, T updated) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            T c2 = em.merge(old);

            Class<?> oldObject = old.getClass();
            Class<?> newObject = updated.getClass();
            Field[] fields = oldObject.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                field.set(c2, newObject);
            }

            em.getTransaction().commit();

            em.close();
            emf.close();
        } catch (IllegalAccessException ignored) {
        }
    }

    public static <T> void delete(T t) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        T dynamicDelete = em.merge(t);

        em.remove(dynamicDelete);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public static List queryCustom(Class table, String clause, Object clauseValue) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        CriteriaQuery<Object> query = em.getCriteriaBuilder().createQuery();
        Root root = query.from(table);
        query.select(root);
        query.where(em.getCriteriaBuilder().equal(root.get(clause), clauseValue));
        List<Object> objects = em.createQuery(query).getResultList();

        //Query query = isString ? em.createQuery("from " + table + " where " + toQuery + "='" + t + "'") : em.createQuery("from " + table + " where " + toQuery + "=" + t);

        em.getTransaction().commit();

        em.close();
        emf.close();

        return objects;
    }

    public static List queryCustom(Class table, String clause, int clauseValue) {
        return queryCustom(table, clause, Integer.valueOf(clauseValue));
    }

    public static List queryCustom(Class table, String clause, Object clauseValue, String otherClause, Object otherClauseValue) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        CriteriaQuery query = em.getCriteriaBuilder().createQuery();
        Root root = query.from(table);
        query.select(root);
        em.getCriteriaBuilder().and(em.getCriteriaBuilder().equal(root.get(clause), clauseValue), em.getCriteriaBuilder().equal(root.get(otherClause), otherClauseValue));
        List objects = em.createQuery(query).getResultList();

        //Query query = em.createQuery("from " + table + " where " + toQuery + "='" + value + "' AND " + andQuery + "='" + andValue + "'");

        em.getTransaction().commit();

        em.close();
        emf.close();

        return objects;
    }

    public static List queryCustom(Class table, String clause, Object clauseValue, String otherClause, Object otherClauseValue, String otherClauseAgain, Object OtherClauseValueAgain) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        CriteriaQuery query = em.getCriteriaBuilder().createQuery();
        Root root = query.from(table);
        query.select(root);
        em.getCriteriaBuilder().and(em.getCriteriaBuilder().equal(root.get(clause), clauseValue),
                em.getCriteriaBuilder().equal(root.get(otherClause), otherClauseValue), em.getCriteriaBuilder().equal(root.get(otherClauseAgain), OtherClauseValueAgain));
        List objects = em.createQuery(query).getResultList();

        //Query query = em.createQuery("from " + table + " where " + toQuery + "='" + value + "' AND " + andQuery + "='" + andValue + "' AND " + otherQuery + "='" + othervalue + "'");

        em.getTransaction().commit();

        em.close();
        emf.close();

        return objects;
    }

    public static List queryGetList(Class table) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        CriteriaQuery query = em.getCriteriaBuilder().createQuery();
        Root root = query.from(table);
        query.select(root);
        List objects = em.createQuery(query).getResultList();

        //Query query = em.createQuery("from " + table);

        em.getTransaction().commit();

        em.close();
        emf.close();

        return objects;
    }

    public static List queryCustomLike(Class table, String clause, Object clauseValue) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        CriteriaQuery query = em.getCriteriaBuilder().createQuery();
        Root root = query.from(table);
        query.select(root);
        query.where(em.getCriteriaBuilder().like(root.get(clause), "%" + clauseValue + "%"));
        List objects = em.createQuery(query).getResultList();

        //Query query = em.createQuery("from " + table + " where " + toQuery + " LIKE '%" + intext + "%'");

        em.getTransaction().commit();

        em.close();
        emf.close();

        return objects;
    }
}
