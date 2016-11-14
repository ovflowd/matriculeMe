package controller;
//TODO: esse ta liso, refazer o controller. tb tirar as alteracoes que deram problema no fk  perfil->departamento
//@TODO Concordo, colocou query direto aqui.

import helpers.Queries;
import modules.Perfil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/perfil")
public class PerfilController {

    public Integer queryGetPerfilAluno(int alunoId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("SELECT  FROM perfil WHERE alunoId='" + alunoId + "'");

        List perfil = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return alunoId;
    }

    public Integer queryGetCursosDepartamento(int departamento) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("SELECT departamento FROM Perfil WHERE nome='" + departamento + "'");

        // Variável não é usada..
        List departamentoId = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return departamento;
    }

    public void Update(Perfil perfil, Perfil newPerfil) {
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
    public Response sayPlainTextHello(Perfil perfil) throws Exception {
        Perfil perfil1 = new Perfil();
        perfil1.setAluno(perfil.getAluno());
        perfil1.setDepartamento(perfil.getDepartamento());
        perfil1.setMetrica(perfil.getMetrica());
        Queries.Persist(perfil1);
        return Response.status(200).build();
    }

    @Path("/getPerfilInfo/Id={id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertFeetToInch(@PathParam("id") int id) {

        List perfis = Queries.queryCustom("Perfil", "perfil_id", String.valueOf(id), false);

        Perfil perfil = (Perfil) perfis.get(0);
        //MachineLearning  firstThread = new MachineLearning();
        if (perfis.size() > 0) {
            //firstThread.start();
        }

        return perfis.size() > 0 ? Response.ok("{\n\t\"departamento:\":" + perfil.getDepartamento()
                + ",\n\t\"aluno_id\":\"" + perfil.getAluno() + "\""
                + ",\n\t\"metrica\":\"" + perfil.getMetrica() + "\""
                + ",\n\t\"Id\":" + perfil.getId() + "\n}", MediaType.APPLICATION_JSON).build() : Response.status(404).build();
    }
}
