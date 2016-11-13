package com.matriculeMe.util;

import com.matriculeMe.core.DAO.GenericDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.matriculeMe.domain.Login;
import com.matriculeMe.domain.Token;
import com.matriculeMe.domain.URL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Random;

public class Security {
    private final static long TIMEOUT_CONECTION = 1200000;
    private final static long TIMEOUT_URL = 7000;

    public static String stringToMD5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(text.getBytes());

            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return String.valueOf(sb.toString());
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    static String stringToSHA1(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            md.update(text.getBytes());

            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return String.valueOf(sb.toString());
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public static boolean Authenticate(Login login) {

        try (Session session = HibernateUtil.getConnection().openSession()) {
            Criteria consult = session.createCriteria(Login.class);

            consult.add(Restrictions.eq("accessKey", login.getAccessKey()));
            consult.add(Restrictions.eq("password", Security.stringToSHA1(login.getPassword())));

            Login result = (Login) consult.uniqueResult();

            return result != null &&
                    (Objects.equals(login.getAccessKey(), result.getAccessKey()))
                    && (Objects.equals(login.getPassword(), result.getPassword()));
        } catch (RuntimeException error) {
            throw error;
        }
    }

    static String tokenGenerator(String token) {

        Random random = new Random();
        int multiplier = random.nextInt(9) + 1;

        long time = System.currentTimeMillis() * multiplier;

        String millis = String.valueOf(time);

        millis = millis.substring(3);
        multiplier = random.nextInt(10);

        int rows = (millis.charAt(multiplier) - 48 + multiplier) / 4;

        token += String.valueOf(time);

        for (int i = 0; i <= rows; i++) {
            token += Security.stringToMD5(token);
        }

        return Security.stringToMD5(token);
    }

    public static boolean tokenChecker(String token) {
        GenericDAO<Token> dao = new GenericDAO<>(Token.class);

        // @TODO Você chama métodos Inexistentes? Bruno?
        Token result = dao.searchByColumn("token", token);

        return result != null && System.currentTimeMillis() - result.getTime() > TIMEOUT_CONECTION;
    }

    public static boolean URLChecker(String url) {
        GenericDAO<URL> dao = new GenericDAO<>(URL.class);

        // @TODO Você chama métodos Inexistentes? Bruno?
        URL result = dao.searchByColumn("url", url);

        return result == null;
    }
}
