package domain;

import com.google.gson.Gson;
import helpers.PersistenceHelper;
import dao.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/cursos")
public class CourseController {

    private void Update(Curso curso, Curso newCurso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Curso c2 = em.merge(curso);

        c2.setNome(newCurso.getNome());
        c2.setCodigo(newCurso.getCodigo());

        em.getTransaction().commit();
        em.close();
    }

    @Path("/alterCurso/name={nome}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterCurso(@PathParam("nome") String nome, Curso curso) throws Exception {
        List cursos = PersistenceHelper.queryCustom("Curso", "nome", nome, true);

        if (cursos.size() > 0) {
            Update((Curso) cursos.get(0), curso);
        }

        return cursos.size() > 0 ? Response.status(200).build() : Response.status(404).build();
    }

    @Path("/setCurso")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sayPlainTextHello(Curso curso) throws Exception {
        PersistenceHelper.Persist(curso);
        return Response.status(200).build();
    }

    @Path("/getCurso/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertFeetToInch(@PathParam("nome") String nome) {
        List cursos = PersistenceHelper.queryCustom("Curso", "nome", nome, true);

        return cursos.size() > 0 ? Response.ok(new Gson().toJson(cursos), MediaType.APPLICATION_JSON).build() :
                Response.status(404).build();
    }

    @Path("/getCurso/nome={nome}&codigo={codigo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome, @PathParam("codigo") int codigo) {
        List cursos = PersistenceHelper.queryCustom("Curso", "nome", nome, "codigo", String.valueOf(codigo));
        return cursos.size() > 0 ? Response.ok(new Gson().toJson(cursos), MediaType.APPLICATION_JSON).build() :
                Response.status(404).build();
    }
}