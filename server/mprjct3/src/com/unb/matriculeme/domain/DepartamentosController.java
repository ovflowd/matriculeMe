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
    public Response getDepartment(@PathParam("nome") String name) {
        List departments = PersistenceHelper.queryCustom(Departamento.class, "nome", name);

        return departments.size() > 0 ? ClientUtils.sendResponse(departments.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("The department wasn't found on the system."));
    }

    @Path("/setAllDeps/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addManyDepartments(List<Departamento> allDepartments) throws Exception {
        allDepartments.forEach(PersistenceHelper::insert);

        return ClientUtils.sendMessage(new AllRightMessage("All departments were added successfully in the system."));
    }

    @Path("/setDepartamento/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorarios(Departamento department) throws Exception {
        PersistenceHelper.insert(department);

        return ClientUtils.sendMessage(new AllRightMessage("The Department was added successfully on the system."));
    }
}
