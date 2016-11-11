package projeto.matriculeme.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
//http://127.0.0.1:8080/matriculeme/web/test
@ApplicationPath("web")
public class JerseyConfig extends ResourceConfig
{
	public JerseyConfig()
	{
		packages("projeto.matriculeme.REST");
	}
}
