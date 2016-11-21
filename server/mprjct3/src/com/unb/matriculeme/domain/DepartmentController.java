package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Department;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departamentos/")
public class DepartmentController {

    // Why not change "nome" to "name"?
    @Path("/getDepartamento/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String name) {
        List departments = PersistenceHelper.queryCustom("Department", "nome", name, true);
        return departments.size() > 0 ? ClientUtils.sendResponse(departments.get(0)) : ClientUtils.sendMessage(new NotFoundMessage("The department wasn't found on the system."));
    }

    // Why not /setAllDepartamentos?
    @Path("/setAllDeps/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAllDepartments(List<Department> allDepartments) throws Exception {
        // Why you added this SystemOut?
        //System.out.println("received size: " + allDepartments.size());

        allDepartments.forEach(PersistenceHelper::persist);
        return ClientUtils.sendMessage(new AllRightMessage("The set of Departments was added successfully on the system."));
    }

    @Path("/setDepartamento/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setHorarios(Department department) throws Exception {
        PersistenceHelper.persist(department);
        return ClientUtils.sendMessage(new AllRightMessage("The Department was added successfully on the system."));
    }
}
