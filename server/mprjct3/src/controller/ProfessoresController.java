package controller;
//TODO: esse ta liso, refazer o controller

import helpers.Queries;
import modules.Professor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/professor")
public class ProfessoresController {

    @Path("/setProfessor")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sayPlainTextHello(Professor professor) throws Exception {

        Professor p1 = new Professor();

        p1.setNome(professor.getNome());

        Queries.Persist(p1);

        return Response.status(200).build();
    }

    @Path("/getProfessorNome/name={nome}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response convertFeetToInch(@PathParam("nome") String nome) {

        List professores = Queries.queryCustom("Professor", "nome", nome, true);

        Professor professor = (Professor) professores.get(0);

        return professores.size() > 0 ? Response.ok("{\n\t\"id:\":" + professor.getId()
                + ",\n\t\"nome\":\"" + professor.getNome() + "\n}", MediaType.APPLICATION_JSON).build() : Response.status(404).build();
    }
}
