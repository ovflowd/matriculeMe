
import javax.websocket.server.PathParam;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;
//http://127.0.0.1:8080/matriculeme/web/test
@ApplicationPath("rest")//http://ip:porta/nome do projeto/config do jersey/classe/metodo
public class JerseyConfig extends ResourceConfig
{
	public JerseyConfig()
	{
		packages("projeto.matriculeme.REST");
	}