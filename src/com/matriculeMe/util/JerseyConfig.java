package com.matriculeMe.util;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

//http://127.0.0.1:8080/matriculeme/web/test
@ApplicationPath("web")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("projeto.matriculeMe.REST");
    }
}
