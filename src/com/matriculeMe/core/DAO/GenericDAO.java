package com.matriculeMe.core.DAO;

import com.google.gson.Gson;
import com.matriculeMe.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class GenericDAO<Entity> {
    private Class<Entity> classe;

    @SuppressWarnings("unchecked")
    public GenericDAO(@SuppressWarnings("rawtypes") Class temp) {
        this.classe = temp;
    }

    public boolean Save(Entity entity) {
        Session session = HibernateUtil.getConnection().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();

            return true;
        } catch (RuntimeException error) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();

            return false;
        }
    }

    public boolean Delete(Entity entity) {
        Session session = HibernateUtil.getConnection().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            session.close();

            return true;
        } catch (RuntimeException error) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();

            return false;
        }
    }

    public boolean Edit(Entity entity) {
        Session session = HibernateUtil.getConnection().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            session.close();

            return true;
        } catch (RuntimeException error) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();

            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Entity> List() {
        Session session = HibernateUtil.getConnection().openSession();

        try {
            List<Entity> entityList = session.createCriteria(classe).list();

            session.close();

            return entityList;
        } catch (RuntimeException error) {
            session.close();

            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Entity> listOrderedByColumn(String column) {
        Session session = HibernateUtil.getConnection().openSession();

        try {
            List<Entity> entityList = session.createCriteria(classe).addOrder(Order.asc(column)).list();

            session.close();

            return entityList;
        } catch (RuntimeException error) {
            session.close();

            return null;
        }
    }

    public Object Search(String json, String json_reference) throws HibernateException {
        // @TODO Que Miséria de Código é esse Bruno?

        String[] args = mapJSON(json);
        String[] args_reference = mapJSON(json_reference);

        try (Session session = HibernateUtil.getConnection().openSession()) {
            Criteria query = session.createCriteria(classe);

            for (int i = 0; i <= args.length - 1; i++) {

                String[] temp = args[i].split(":");
                String setter = typeJson(args_reference, temp[0]);

                try {
                    if (!args[i].contains("[") && !args[i].contains("]")) {

                        Object parameter = Class.forName(setter);

                        //System.out.println(parameter);

                        parameter = temp[1].replaceAll("\"", "");

                        //System.out.println(parameter);

                        query.add(Restrictions.ilike(temp[0].replaceAll("\"", ""), parameter));
                    }

                } catch (ClassNotFoundException e) {
                    return "Some parameter in the Json is not correct.";
                }
            }
            Gson gSON = new Gson();

            return gSON.toJson(query.uniqueResult());

        } catch (RuntimeException error) {
            // @TODO Que diabos você está fazendo, Bruno?
            System.out.println(error.getMessage());

            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public Entity searchMetadata(String className) {
        Session session = HibernateUtil.getConnection().openSession();

        try {
            Criteria query = session.createCriteria(className).add(Restrictions.eq("classe", className));

            Entity result = (Entity) query.uniqueResult();

            session.close();

            return result;
        } catch (RuntimeException error) {
            session.close();

            return null;
        }
    }

    private String[] mapJSON(String json) {
        // @TODO Que miséria de código é esse Bruno?

        json = json.replaceAll(", ", ",");
        json = json.replaceAll(" ,", ",");
        json = json.replaceAll(": ", ":");
        json = json.replaceAll(" :", ":");
        json = json.replaceAll("\" ", "\"");
        json = json.replaceAll(" \"", "\"");
        json = json.substring(1, json.length() - 1);

        String temp = "";

        for (int i = 0; i < json.length(); i++) {
            if (json.charAt(i) == ',') {
                temp += "&";
            } else {
                temp += json.charAt(i);
            }
        }

        return temp.split("&");
    }

    private String typeJson(String[] json, String match) {
        for (String aJson : json) {

            String[] temp = aJson.split(":");

            if (temp[0].equals(match)) {
                return temp[1];
            }
        }
        return null;
    }
}