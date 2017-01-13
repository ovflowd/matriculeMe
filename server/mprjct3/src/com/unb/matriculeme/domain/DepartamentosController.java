package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Departamento;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departamentos/")
public class DepartamentosController {

    @Path("/getDepartamento/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String name) {

        List departments = PersistenceHelper.queryCustom("Departamento", "nome", name);

        return departments.size() > 0 ? ClientUtils.sendResponse(departments.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("The department wasn't found on the system."));
    }

    @Path("/setAllDeps/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setAllCoisas(List<Departamento> allDepartments) throws Exception {
        for (int i = 0; i < allDepartments.size(); i++) {
            PersistenceHelper.Persist(allDepartments.get(i));
        }

        return ClientUtils.sendMessage(new AllRightMessage("Department Set."));
    }

    @Path("/setDepartamento/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorarios(Departamento department) throws Exception {
        PersistenceHelper.Persist(department);
        
        return ClientUtils.sendMessage(new AllRightMessage("The Department was added successfully on the system."));
    }
}
