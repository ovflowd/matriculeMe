package com.unb.matriculeme.domain;

//TODO: fazer o get do perfil; alem disso, nao esquecer das perguntas salvas no notepad
//R: nao aparentemente

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.unb.matriculeme.dao.Aluno;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Departamento;
import com.unb.matriculeme.dao.Perfil;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.BaseMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import java.util.List;


@Path("/perfil")
public class PerfilController {


    @Path("/setPerfil/matricula={matricula}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sayPlainTextHello(@PathParam ("matricula") int matricula, Perfil profile) throws Exception {
    	List alunos = PersistenceHelper.queryCustom("Aluno", "matricula", String.valueOf(matricula), false);
    	if (alunos.size() > 0){
    		profile.setAluno((Aluno)alunos.get(0));
    		profile.setDepartamento(((Departamento)PersistenceHelper.queryCustom("Departamento", "codigo", String.valueOf(profile.getDepartamento().getCodigo()), false).get(0)));
            PersistenceHelper.Persist(profile);
    	}
        return Response.status(200).build();
    }
    
    
    @Path("/getPerfil/Aluno={matricula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterPerfil(@PathParam("matricula") int matricula) throws IllegalAccessException {
    	List Alunos = PersistenceHelper.queryCustom("Aluno", "matricula", String.valueOf(matricula), false);
    	if (Alunos.size() > 0){
            List perfis = PersistenceHelper.queryCustom("Perfil", "aluno_id", String.valueOf(((Aluno)Alunos.get(0)).getId()), false);
            return perfis.size() > 0 ? ClientUtils.sendResponse(perfis.get(0)) :
                ClientUtils.sendMessage(new NotFoundMessage("This Profile wasn't found on our system."));
        }
    	return ClientUtils.sendMessage(new NotFoundMessage("This Profile wasn't found on our system."));
    }
}
