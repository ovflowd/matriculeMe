package com.unb.matriculeme.domain;


import com.google.gson.Gson;
import com.unb.matriculeme.dao.Curriculo;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Departamento;
import com.unb.matriculeme.dao.Disciplina;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;


@Path("/curriculos")
public class CurriculoController {

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
        for (Curriculo curriculo : curriculos) {
            Curriculo curr = new Curriculo();

            curr.setSemestreDisciplina(curriculo.getSemestreDisciplina());

            List cursos = PersistenceHelper.queryCustom("Curso", "codigo", String.valueOf(curriculo.getCurso().getCodigo()), false);

            if (cursos.size() > 0 ) {curr.setCurso((Curso) cursos.get(0));}

            List disciplinas = PersistenceHelper.queryCustom("Disciplina", "codigo", String.valueOf(curriculo.getDisciplina().getCodigo()), false);
            
            if (disciplinas.size() > 0 )
            {
                curr.setDisciplina((Disciplina) disciplinas.get(0));
            }
            else
            {  
            	List departamentox = PersistenceHelper.queryCustom("Departamento", "sigla", curriculo.getDisciplina().getDepartamento().getSigla(), true);
            	if (departamentox.size() > 0){
            		curriculo.getDisciplina().setDepartamento((Departamento)departamentox.get(0));                	
            	} 
            	else{  
            		PersistenceHelper.Persist(curriculo.getDisciplina().getDepartamento());
            		curriculo.getDisciplina().setDepartamento((Departamento)PersistenceHelper.queryCustom("Departamento", "sigla", curriculo.getDisciplina().getDepartamento().getSigla(), true).get(0));
            	}
            	PersistenceHelper.Persist(curriculo.getDisciplina());
            	curr.setDisciplina((Disciplina)(PersistenceHelper.queryCustom("Disciplina", "codigo", String.valueOf(curriculo.getDisciplina().getCodigo()), false).get(0)));
            }

            PersistenceHelper.Persist(curr); 
        }
        return Response.status(200).build();
    }
    
    @Path("/getCurriculos/nomeCurso={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurriculosByName(@PathParam("nome") String nome) throws Exception {
        Gson gson = new Gson();
        List cursos = PersistenceHelper.queryCustom("Curso", "nome", nome, true);
        List curriculo = PersistenceHelper.queryCustom("Curriculo", "curso", String.valueOf(((Curso)cursos.get(0)).getId()) ,false);
         return curriculo.size() > 0 ? ClientUtils.sendResponse(curriculo) :
                ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }
    
    @Path("/getCurriculos/codigoCurso={codigo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public Response getCurriculosByCodigo(@PathParam("codigo") int codigo) throws Exception {
        List cursos = PersistenceHelper.queryCustom("Curso", "codigo", String.valueOf(codigo), false);
        List curriculo = PersistenceHelper.queryCustom("Curriculo", "curso", String.valueOf(((Curso)cursos.get(0)).getId()) ,false);
         return curriculo.size() > 0 ? ClientUtils.sendResponse(curriculo) : 
                ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }  
}
