package helpers;

import dao.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PersistenceHelper {

    public static <T> void persist(T t) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public static <T> void update(T old, T updated) throws IllegalAccessException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        T c2 = em.merge(old);

        Class<?> oldObject = old.getClass();
        Class<?> newObject = updated.getClass();
        Field[] fields = oldObject.getDeclaredFields();

        for(Field field : fields ){
            field.setAccessible(true);
            field.set(c2, newObject);
        }

        em.getTransaction().commit();
        em.close();
    }

    public static <T> void delete(T t) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        T dynamicDelete = em.merge(t);

        em.remove(dynamicDelete);
        em.getTransaction().commit();
        em.close();
    }

    public static List queryCustom(String table, String toQuery, String t, boolean isString) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = isString ? em.createQuery("from " + table + " where " + toQuery + "='" + t + "'") : em.createQuery("from " + table + " where " + toQuery + "=" + t);

        List objects = query.getResultList();

        em.getTransaction().commit();
        em.close();

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

        return objects;
    }

    public static List queryCustomLike(String table, String toQuery, String intext) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table + " where " + toQuery + " LIKE '%" + intext + "%'");

        List objects = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return objects;
    }

    public static List queryCustom(String table, String toQuery, String value, String andQuery, String andValue) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table + " where " + toQuery + "='" + value + "' AND " + andQuery + "='" + andValue + "'");

        List objects = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return objects;
    }
}
