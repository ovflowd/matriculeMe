package controller;
//TODO: esse ta liso, refazer o controller
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import helpers.Querys;
import modules.Professor;

@Path("/professor")
public class ProfessoresController {
 

 @Path("/setProfessor")
 @POST
 @Consumes(MediaType.APPLICATION_JSON)
 public Response sayPlainTextHello(Professor professor) throws Exception
 {
  Professor p1 = new Professor();
  p1.setNome(professor.getNome());
  Querys.Persist(p1);
  return Response.status(200).build();
 }
 
 @Path("/getProfessorNome/name={nome}")
 @GET
 @Consumes(MediaType.APPLICATION_JSON)
 public Response convertFeetToInch(@PathParam("nome") String nome){
  
  List<Professor> professores = Querys.queryCustom("Professor", "nome", nome, true);
  if (professores.size() > 0)
  {
   return Response.ok("{\n\t\"id:\":" + professores.get(0).getId()
    + ",\n\t\"nome\":\"" + professores.get(0).getNome() + "\n}", MediaType.APPLICATION_JSON).build();
  }
  
  return Response.status(404).build();
 }
}