package com.matriculeMe.core.REST;


import com.matriculeMe.util.FactoryUtil;
import com.matriculeMe.util.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

//@TODO CARA TEM MUITO CÓDIGO REPETITIDO!!!!

@Path("/rest")
public class ManagerREST {
    @GET
    @Path("/login")//http://127.0.0.1:8080/matriculeme/web/rest/login
    public String login(@Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException {
        // @TODO Que merda de mensagem é essa Bruno?
        return "ACESSA AI DNV FERA";
    }

    @GET
    @Path("/{token}/{class}/Get/{parameters}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String restGET(@PathParam("token") String token, @PathParam("class") String className, String parameters, @Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // @TODO De onde vêm esse "method" ???

        if ((!Security.tokenChecker(token)) || (!Security.URLChecker(request.getRequestURL().toString()))) {
            response.sendRedirect("http://127.0.0.1:8080/matriculeMe/web/rest/login");
            return "";
        } else {
            try {
                Object out = FactoryUtil.executeMethod(REST.PATH + className, "Get", FactoryUtil.createParameters(method), FactoryUtil.createArgsJSON(parameters));
                return out != null ? out.toString() : null;
            } catch (NoSuchMethodException e) {
                return "The method required: " + method + "cannot be found.";
            } catch (SecurityException e) {
                return "The page cannot be accessed.";
            } catch (IllegalArgumentException e) {
                return "The parameters: " + parameters + "cannot be used as a parameter.";
            } catch (InvocationTargetException e) {
                return "The method required: " + method + "failed in execution for de parameters: " + parameters;
            }
        }
    }

    @DELETE//http://127.0.0.1:8080/matriculeme/web/rest/[TOKEN]/classREST/Delete?JSON
    @Path("/{token}/{class}/{method}?{parameters}")
    public String restDELETE(@PathParam("token") String token, @PathParam("class") String className, @PathParam("method") String method, @PathParam("parameters") String parameters, @Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if ((!Security.tokenChecker(token)) || (!Security.URLChecker(request.getRequestURL().toString()))) {
            response.sendRedirect("http://127.0.0.1:8080/matriculeMe/web/rest/login");
            return "";
        } else {
            try {
                Object out = FactoryUtil.executeMethod(REST.PATH + className + "REST", method, FactoryUtil.createParameters(method), FactoryUtil.createArgsJSON(parameters));
                return out != null ? out.toString() : null;
            } catch (SecurityException e) {
                return "The page cannot be accessed.";
            } catch (IllegalArgumentException e) {
                return "The parameters: " + parameters + "cannot be used as a parameter.";
            }
        }
    }

    @POST//http://127.0.0.1:8080/matriculeme/web/rest/[TOKEN]/classREST/Insert?JSON
    @Path("/{token}/{class}/{method}?{parameters}")
    public String restPUT(@PathParam("token") String token, @PathParam("class") String className, @PathParam("method") String method, @PathParam("parameters") String parameters, @Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if ((!Security.tokenChecker(token)) || (!Security.URLChecker(request.getRequestURL().toString()))) {
            response.sendRedirect("http://127.0.0.1:8080/matriculeMe/web/rest/login");
            return "";
        } else {
            try {
                Object out = FactoryUtil.executeMethod(REST.PATH + className + "REST", method, FactoryUtil.createParameters(method), FactoryUtil.createArgsJSON(parameters));
                return out != null ? out.toString() : null;
            } catch (SecurityException e) {
                return "The page cannot be accessed.";
            } catch (IllegalArgumentException e) {
                return "The parameters: " + parameters + "cannot be used as a parameter.";
            }
        }
    }

    @PUT//http://127.0.0.1:8080/matriculeme/web/rest/[TOKEN]/classREST/Edit?[JSON1]&[JSON2]
    @Path("/{token}/{class}/{method}?{parameters}")
    public String restPOST(@PathParam("token") String token, @PathParam("class") String className, @PathParam("method") String method, @PathParam("parameters") String parameters, @Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if ((!Security.tokenChecker(token)) || (!Security.URLChecker(request.getRequestURL().toString()))) {
            response.sendRedirect("http://127.0.0.1:8080/matriculeMe/web/rest/login");
            return "";
        } else {
            try {
                Object out = FactoryUtil.executeMethod(REST.PATH + className + "REST", method, FactoryUtil.createParameters(method), FactoryUtil.createArgsJSON(parameters));
                return out != null ? out.toString() : null;
            } catch (SecurityException e) {
                return "The page cannot be accessed.";
            } catch (IllegalArgumentException e) {
                return "The parameters: " + parameters + "cannot be used as a parameter.";
            }
        }
    }
}
