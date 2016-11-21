package domain;


import com.google.gson.Gson;
import dao.Course;
import helpers.PersistenceHelper;
import dao.Curriculo;
import dao.Disciplina;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/curriculos")
public class CurriculumController {

    @Path("/getCurriculos/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculos() throws Exception {
        List curriculos = PersistenceHelper.queryGetList("Curriculo");
        Gson gson = new Gson();
        String json = gson.toJson(curriculos);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @Path("/setAllCurr")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setAllCoisas(List<Curriculo> curriculos) throws Exception {
        //Problema: se o cara nao passar id, nao eh possivel fazer a referencia, dai tem q instanciar
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        for (Curriculo curriculo : curriculos) {
            Curriculo curr = new Curriculo();

            curr.setSemestreDisciplina(curriculo.getSemestreDisciplina());

            List cursos = PersistenceHelper.queryCustom("Course", "codigo", String.valueOf(curriculo.getCourse().getCodigo()), false);

            curr.setCourse((Course) cursos.get(0));

            List disciplinas = PersistenceHelper.queryCustom("Disciplina", "codigo", String.valueOf(curriculo.getDisciplina().getCodigo()), false);

            curr.setDisciplina((Disciplina) disciplinas.get(0));

            PersistenceHelper.persist(curr);
        }
        return Response.status(200).build();
    }
}
