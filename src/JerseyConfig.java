package projeto.matriculeme.REST;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
//http://127.0.0.1:8080/matriculeme/web/test
@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig
{
	public JerseyConfig()
	{
		packages("projeto.matriculeme.REST");
	}
}