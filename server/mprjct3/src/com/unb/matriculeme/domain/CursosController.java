package com.unb.matriculeme.domain;

import com.google.gson.Gson;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.helpers.PersistenceHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/cursos")
public class CursosController {

    private void Update(Curso curso, Curso newCurso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Curso c2 = em.merge(curso);

        c2.setNome(newCurso.getNome());
        c2.setCodigo(newCurso.getCodigo());

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Path("/alterCurso/name={nome}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterCurso(@PathParam("nome") String nome, Curso curso) throws Exception {
        List cursos = PersistenceHelper.queryCustom("Curso", "nome", nome);

        if (cursos.size() > 0) {
            Update((Curso) cursos.get(0), curso);
        }

        return ClientUtils.sendMessage(cursos.size() > 0 ? new AllRightMessage("Course changed successfully.") : new NotFoundMessage("Course not found."));
    }

    @Path("/setCursos")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sayPlainTextHello(List<Curso> cursos) throws Exception {
        for (int i = 0; i < cursos.size(); i++) {
            if (PersistenceHelper.queryCustom("Curso", "codigo", cursos.get(i).getCodigo()).size() == 0) {
                PersistenceHelper.Persist(cursos.get(i));
            }
        }

        ClientUtils.sendMessage(new AllRightMessage("Courses created successfully."));
    }

    @Path("/getCurso/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertFeetToInch(@PathParam("nome") String nome) {
        List cursos = PersistenceHelper.queryCustom("Curso", "nome", nome);

        return cursos.size() > 0 ? ClientUtils.sendResponse(cursos) : ClientUtils.sendMessage(new NotFoundMessage("Courses not Found."));
    }

    @Path("/getCurso/nome={nome}&codigo={codigo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome, @PathParam("codigo") int codigo) {

        List cursos = PersistenceHelper.queryCustom("Curso", "nome", nome, "codigo", codigo);

        return cursos.size() > 0 ? ClientUtils.sendResponse(cursos) : ClientUtils.sendMessage(new NotFoundMessage("Courses not Found."));
    }
}
