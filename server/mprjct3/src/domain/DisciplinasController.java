package domain;

import com.google.gson.Gson;
import helpers.PersistenceHelper;
import dao.Departamento;
import dao.Disciplina;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disciplinas")
public class DisciplinasController {

    @Path("/getDisciplina/innome={intext}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trial(@PathParam("intext") String intext) {
        List disciplinas = PersistenceHelper.queryCustomLike("Disciplina", "nome", intext);

        return disciplinas.size() > 0 ?
                Response.ok(new Gson().toJson(disciplinas),
                        MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build() :
                Response.status(404).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build();
    }

    @Path("/getDisciplina/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome) {
        List disciplinas = PersistenceHelper.queryCustom("Disciplina", "nome", nome, true);

        return disciplinas.size() > 0 ? Response.ok(new Gson().toJson(disciplinas.get(0)),
                MediaType.APPLICATION_JSON).build() : Response.status(404).build();
    }

    @Path("/setDisciplina/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorarios(List<Disciplina> allDis) throws Exception {

        for (Disciplina dis : allDis) {
            Disciplina disciplina = new Disciplina();

            List department = PersistenceHelper.queryCustom("Departamento", "codigo", String.valueOf(allDis.get(0).getDepartamento().getCodigo()), false);

            disciplina.setCodigo(dis.getCodigo());
            disciplina.setCredito(dis.getCredito());
            disciplina.setNome(dis.getNome());

            disciplina.setDepartamento((Departamento) department.get(0));

            PersistenceHelper.Persist(disciplina);
        }
        return Response.status(200).build();
    }
}
