package com.unb.matriculeme.helpers;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.manager.Connection;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public final class Persistence {

    public static <T> void insert(T t) {
        Connection c = new Connection();

        c.getManager().getTransaction().begin();
        c.getManager().persist(t);
        c.getManager().getTransaction().commit();

        c.closeManager();
    }

    public static <T> void update(T oldClass, T updateClass) throws IllegalAccessException {
        Connection c = new Connection();

        c.getManager().getTransaction().begin();

        for (Field field : oldClass.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            field.set(c.getManager().merge(oldClass), updateClass.getClass());
        }

        c.getManager().getTransaction().commit();
        c.closeManager();
    }

    public static <T> void update(T updatedClass) throws IllegalAccessException {
        Connection c = new Connection();

        c.getManager().getTransaction().begin();
        c.getManager().merge(updatedClass);

        c.getManager().getTransaction().commit();
        c.closeManager();
    }

    public static <T> void delete(T t) {
        Connection c = new Connection();

        c.getManager().getTransaction().begin();

        c.getManager().remove(c.getManager().merge(t));
        c.getManager().getTransaction().commit();

        c.closeManager();
    }

    @SafeVarargs
    public static List<Pair<String, Object>> createExpression(Pair<String, Object>... pair) {
        return Arrays.asList(pair);
    }

    public static <T> List<T> select(Class from, List<Pair<String, Object>> expressionList, boolean isEqual) {
        Connection c = new Connection();

        c.getManager().getTransaction().begin();

        CriteriaQuery q = c.getManager().getCriteriaBuilder().createQuery();

        Root r = q.from(from);

        expressionList.forEach(p -> q.where(c.getManager().getCriteriaBuilder().and(isEqual ? c.getManager().
                getCriteriaBuilder().equal(r.get(p.getFirst()), p.getSecond()) : c.getManager().
                getCriteriaBuilder().like(r.get(p.getFirst()), "%" + p.getSecond() + "%"))));

        q.select(r);

        List<T> result = c.getManager().createQuery(q).getResultList();

        c.getManager().getTransaction().commit();
        c.closeManager();

        return result;
    }

    public static <T> List<T> select(Class from) {
        Connection c = new Connection();

        c.getManager().getTransaction().begin();

        CriteriaQuery q = c.getManager().getCriteriaBuilder().createQuery();

        Root r = q.from(from);

        q.select(r);

        List<T> result = c.getManager().createQuery(q).getResultList();

        c.getManager().getTransaction().commit();
        c.closeManager();

        return result;
    }
}
