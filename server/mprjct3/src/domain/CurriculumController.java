package domain;


import com.google.gson.Gson;
import dao.Course;
import dao.Curriculum;
import helpers.PersistenceHelper;
import dao.Discipline;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/curriculos")
public class CurriculumController  {

    @Path("/getCurriculos/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculos() throws Exception {
        List curriculos = PersistenceHelper.queryGetList("Curriculum");
        Gson gson = new Gson();
        String json = gson.toJson(curriculos);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @Path("/setAllCurr")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setAllCoisas(List<Curriculum> curriculos) throws Exception {
        //Problema: se o cara nao passar id, nao eh possivel fazer a referencia, dai tem q instanciar
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        for (Curriculum curriculo : curriculos) {
            Curriculum curr = new Curriculum();

            curr.setSemester(curriculo.getSemester());

            List cursos = PersistenceHelper.queryCustom("Course", "codigo", String.valueOf(curriculo.getCourse().getCode()), false);

            curr.setCourse((Course) cursos.get(0));

            List disciplinas = PersistenceHelper.queryCustom("Discipline", "codigo", String.valueOf(curriculo.getDiscipline().getCode()), false);

            curr.setDiscipline((Discipline) disciplinas.get(0));

            PersistenceHelper.persist(curr);
        }
        return Response.status(200).build();
    }
}
