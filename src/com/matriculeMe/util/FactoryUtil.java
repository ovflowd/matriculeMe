package com.matriculeMe.util;


import com.google.gson.Gson;
import com.matriculeMe.domain.Login;
import com.matriculeMe.domain.Token;
import com.matriculeMe.domain.URL;
import com.matriculeMe.domain.User;

import java.lang.reflect.InvocationTargetException;

public class FactoryUtil {
    public static Token newToken(String token) {
        Gson gson = new Gson();
        Token out = gson.fromJson(token, Token.class);

        out.setToken(Security.tokenGenerator(out.getToken()));
        out.setValid(true);
        out.setTime(System.currentTimeMillis());

        return out;
    }

    public static User newUser(String user) {
        return new Gson().fromJson(user, User.class);
    }

    public static Login newLogin(String login) {
        Gson gson = new Gson();
        Login out = gson.fromJson(login, Login.class);

        out.setAccessKey(out.getAccessKey());
        out.setPassword(Security.stringToSHA1(out.getPassword()));

        return out;
    }

    public static URL newURL(String url) {
        Gson gson = new Gson();
        URL out = gson.fromJson(url, URL.class);
        out.setTime(System.currentTimeMillis());

        return out;
    }

    public static Object executeMethod(Object object, String method, Class[] paramClass, Object[] param) {
        try {
            return object.getClass().getDeclaredMethod(method, paramClass).invoke(object, param);
        } catch (NoSuchMethodException |
                SecurityException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException e) {
            System.out.println("ERROR: " + e.getClass());
        }
        return null;
    }

    public static Class[] createParameters(String setter) {
        switch (setter) {
            case "Edit":
                return new Class[]{String.class, String.class};
            case "Insert":
            case "Delete":
            case "Get":
                return new Class[]{String.class};
            default:
                return null;
        }
    }

    public static Object[] createArgs(String setter) {

        String[] parameters = setter.replace("&", "=").split("=");
        String result = "";

        for (int i = 0; i < parameters.length; i++) {
            if (i % 2 != 0) {
                result += parameters[i] + "=";
            }
        }

        return result.substring(0, result.length() - 1).split("=");
    }

    public static Object[] createArgsJSON(String setter) {
        return setter.split("&");
    }
}
