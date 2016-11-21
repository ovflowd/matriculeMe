package domain;

import com.google.gson.Gson;
import helpers.PersistenceHelper;
import dao.Departamento;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departamentos/")
public class DepartmentController {

    @Path("/getDepartamento/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome) {
        List departamentos = PersistenceHelper.queryCustom("Departamento", "nome", nome, true);

        return departamentos.size() > 0 ? Response
                .ok(new Gson().toJson(departamentos.get(0)), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build() : Response.status(404).build();
    }

    @Path("/setAllDeps/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setAllCoisas(List<Departamento> allDepartments) throws Exception {
        // Para que Esse System.out?
        System.out.println("received size: " + allDepartments.size());

        allDepartments.forEach(PersistenceHelper::persist);

        return Response.status(200).build();
    }

    @Path("/setDepartamento/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorarios(Departamento departamento) throws Exception {
        PersistenceHelper.persist(departamento);
        return Response.status(200).build();
    }
}
