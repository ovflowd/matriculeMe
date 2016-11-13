package com.matriculeMe.core.REST;

import com.google.gson.Gson;
import com.matriculeMe.core.DAO.GenericDAO;
import com.matriculeMe.domain.Login;
import com.matriculeMe.domain.Metadata;
import com.matriculeMe.util.FactoryUtil;

import java.util.Objects;

public class REST {
    final static String PATH = "projeto.matriculeMe.domain.";

    public static String Insert(String className, String json) {
        if ((Objects.equals(className, "Token")) || (Objects.equals(className, "URL"))) {
            return "Access denied to this class.";
        } else {
            try {
                Gson gSON = new Gson();

                if (Objects.equals(className, "Login")) {
                    Login login = FactoryUtil.newLogin(json);

                    GenericDAO<Login> dao = new GenericDAO<>(Login.class);

                    return dao.Save(login) ? gSON.toJson(login) : "Failed to Save the object Login.";
                } else {
                    Object object = gSON.fromJson(json, Class.forName(className));

                    GenericDAO<Object> dao = new GenericDAO<>(Object.class);

                    return dao.Save(object) ? gSON.toJson(object) : "Failed to Save the object " + className + ".";
                }
            } catch (ClassNotFoundException e) {
                return "The class " + className + " cannot be located.";
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static String Delete(String className, String json) {
        if ((Objects.equals(className, "Token")) || (Objects.equals(className, "URL")) || (Objects.equals(className, "Login"))) {
            return "Access denied to this class.";
        } else {
            try {
                Gson gSON = new Gson();

                GenericDAO<Metadata> daoMetadata = new GenericDAO<>(Metadata.class);

                String jsonReference = daoMetadata.searchMetadata(className).getJson();

                GenericDAO<Object> dao = new GenericDAO<>(Class.forName(PATH + className));

                Object object = dao.Search(json, jsonReference);

                return dao.Delete(object) ? gSON.toJson(object) : "Failed to Delete the object " + className + ".";
            } catch (ClassNotFoundException e) {
                return "The class " + className + " cannot be located.";
            }
        }
    }

    public static String Get(String className, String json) {
        try {
            Gson gSON = new Gson();

            GenericDAO<Metadata> daoMetadata = new GenericDAO<>(Metadata.class);

            String jsonReference = daoMetadata.searchMetadata(className).getJson();

            GenericDAO<Object> dao = new GenericDAO<>(Class.forName(PATH + className));

            return gSON.toJson(dao.Search(json, jsonReference));
        } catch (ClassNotFoundException e) {
            return "The class " + className + " cannot be located.";
        }
    }

    @SuppressWarnings("unchecked")
    public String Edit(String className, String json, String jsonUpdate) {
        try {
            Gson gSON = new Gson();

            //GenericDAO<Metadata> daoMetadata = new GenericDAO<>(Metadata.class);

            //Metadata base = daoMetadata.searchMetadata(className);

            //String json_reference = base.getJson();

            Class type = Class.forName(PATH + className);

            GenericDAO<Object> dao = new GenericDAO<>(type);

            //Object object = dao.Search(json, json_reference);

            //Object object_update = gSON.fromJson(json, type);

            Object object = gSON.fromJson(json, type);

            return dao.Edit(object) ? gSON.toJson(object) : "Failed to Edit the object " + className + ".";
        } catch (ClassNotFoundException e) {
            return "The class " + className + " cannot be located.";
        }
    }
}
