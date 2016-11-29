package com.unb.matriculeme.helpers;

import com.mysema.commons.lang.Pair;
import com.unb.matriculeme.manager.Connection;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public final class Persistence {

    public static <T> void insert(T t) {
        Connection c = new Connection();

        c.getManager().getTransaction().begin();
        c.getManager().persist(t);
        c.getManager().getTransaction().commit();

        c.closeManager();
    }

    private static Hashtable<String, Object> fieldsToHT(Field[] fields, Object obj) throws IllegalAccessException {
        Hashtable<String, Object> hashtable = new Hashtable<>();

        for (Field field : fields) {
            field.setAccessible(true);

            Object retrievedObject = field.get(obj);

            if (retrievedObject != null) {
                hashtable.put(field.getName(), field.get(obj));
            }
        }

        return hashtable;
    }

    public static <T> void update(T oldClass, T updateClass) throws IllegalAccessException, NoSuchFieldException {
        Connection c = new Connection();

        c.getManager().getTransaction().begin();

        T mergedClass = c.getManager().merge(oldClass);

        Field[] newEntityFields = updateClass.getClass().getDeclaredFields();
        Hashtable newHT = fieldsToHT(newEntityFields, updateClass);

        Class oldEntityClass = mergedClass.getClass();
        Field[] oldEntityFields = oldEntityClass.getDeclaredFields();

        for (Field field : oldEntityFields) {

            field.setAccessible(true);

            Object classField = newHT.get(field.getName());

            if (classField != null) {
                Field newField = oldEntityClass.getDeclaredField(field.getName());
                newField.setAccessible(true);
                newField.set(mergedClass, classField);
            }
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

    public static <T> List<T> selectJoin(Class from, String argumentOne, String argumentTwo) {
        Connection c = new Connection();

        c.getManager().getTransaction().begin();

        //CriteriaQuery q = c.getManager().getCriteriaBuilder().createQuery();

        //Root r = q.from(from);

        Query q = c.getManager().createQuery("from Turma turma inner join turma.horario horar where horar.dia like '%" + argumentOne + "%' AND horar.horarioInicio like '%" + argumentTwo + "%'");

        List<T> o = q.getResultList();

        c.getManager().getTransaction().commit();
        c.closeManager();

        return o;
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
