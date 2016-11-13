package controller;
//TODO: esse ta liso, refazer o controller. tb tirar as alteracoes que deram problema no fk  perfil->departamento
import modules.*;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import helpers.*;

@Path("/perfil")
public class PerfilController {
 
 Perfil pf = new Perfil();
 
 
 public Integer queryGetPerfilAluno(int alunoId)
 {
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
  EntityManager em = emf.createEntityManager();
  em.getTransaction().begin();
  
  Query query = em.createQuery("SELECT  FROM perfil WHERE alunoId='" + alunoId + "'");
  
  List<Integer> perfil = query.getResultList();
  
  em.getTransaction().commit();
  em.close();
  
  return alunoId;
 }
 
 public Integer queryGetCursosDepartamento(int departamento)
 {
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
  EntityManager em = emf.createEntityManager();
  em.getTransaction().begin();
  
  Query query = em.createQuery("SELECT departamento FROM Perfil WHERE nome='" + departamento + "'");
  
  List<Integer> departamentoId= query.getResultList();
  
  em.getTransaction().commit();
  em.close();
  
  return departamento;
 }
 
 public void Update(Perfil perfil, Perfil newPerfil)
 { 
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
  EntityManager em = emf.createEntityManager();
  em.getTransaction().begin();
  Perfil perfil2 = em.merge(perfil);
  //perfil2.set(perfil.getNome());
  //perfil2.setCodigo(newPerfil.getCodigo());
  perfil2.setAluno(newPerfil.getAluno());
  em.getTransaction().commit();
  em.close();
 }
 @Path("/setPerfil")
 @POST
 @Consumes(MediaType.APPLICATION_JSON)
 public Response sayPlainTextHello(Perfil perfil) throws Exception
 {
  Perfil perfil1 = new Perfil();
  perfil1.setAluno(perfil.getAluno());
  perfil1.setDepartamento(perfil.getDepartamento());
  perfil1.setMetrica(perfil.getMetrica());
  Querys.Persist(perfil1);
  return Response.status(200).build();
 }
 
 @Path("/getPerfilInfo/Id={id}")
    @GET  
    @Produces(MediaType.APPLICATION_JSON)  
    public Response convertFeetToInch(@PathParam("id") int id) {
        
  List<Perfil> perfil = Querys.queryCustom("Perfil", "perfil_id", String.valueOf(id), false);
  //Machinelearning  firstThread = new Machinelearning(); 
  if (perfil.size() > 0)
  {
   //firstThread.start();
   return Response.ok("{\n\t\"departamento:\":" + perfil.get(0).getDepartamento()
    + ",\n\t\"aluno_id\":\"" + perfil.get(0).getAluno() + "\""
    + ",\n\t\"metrica\":\"" + perfil.get(0).getMetrica() + "\""
    + ",\n\t\"Id\":" + perfil.get(0).getId() + "\n}", MediaType.APPLICATION_JSON).build();
  }
  else
  {
   return Response.status(404).build();
  }
    }  
}