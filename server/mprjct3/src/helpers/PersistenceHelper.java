package helpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

public final class PersistenceHelper {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    private static EntityManager getInstance() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("myDB");
        }

        if (em == null) {
            em = emf.createEntityManager();
        }

        return em;
    }

    public static <T> void persist(T t) {
        EntityManager em = getInstance();

        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();

        //em.close();
        //emf.close();
    }

    public static <T> void update(T old, T updated) throws IllegalAccessException {
        EntityManager em = getInstance();

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

        //em.close();
    }

    public static <T> void delete(T t) {
        EntityManager em = getInstance();

        em.getTransaction().begin();

        T dynamicDelete = em.merge(t);

        em.remove(dynamicDelete);
        em.getTransaction().commit();

        //em.close();
    }

    public static List queryCustom(String table, String toQuery, String t, boolean isString) {
        EntityManager em = getInstance();

        em.getTransaction().begin();

        Query query = isString ? em.createQuery("from " + table + " where " + toQuery + "='" + t + "'") : em.createQuery("from " + table + " where " + toQuery + "=" + t);

        List objects = query.getResultList();

        em.getTransaction().commit();

        //em.close();

        return objects;
    }

    public static List queryGetList(String table) {
        EntityManager em = getInstance();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table);

        List objects = query.getResultList();

        em.getTransaction().commit();

        //em.close();

        return objects;
    }

    public static List queryCustomLike(String table, String toQuery, String intext) {
        EntityManager em = getInstance();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table + " where " + toQuery + " LIKE '%" + intext + "%'");

        List objects = query.getResultList();

        em.getTransaction().commit();

        //em.close();

        return objects;
    }

    public static List queryCustom(String table, String toQuery, String value, String andQuery, String andValue) {
        EntityManager em = getInstance();

        em.getTransaction().begin();

        Query query = em.createQuery("from " + table + " where " + toQuery + "='" + value + "' AND " + andQuery + "='" + andValue + "'");

        List objects = query.getResultList();

        em.getTransaction().commit();

        //em.close();

        return objects;
    }
}
