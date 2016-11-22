package com.unb.matriculeme.domain;

import com.google.gson.Gson;
import com.unb.matriculeme.dao.Aluno;
import com.unb.matriculeme.dao.Curso;
import com.unb.matriculeme.dao.Login;
import com.unb.matriculeme.dao.Perfil;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.BaseMessage;
import com.unb.matriculeme.messages.NotFoundMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/alunos") 
public class AlunosController { 

    @Path("/getAluno/nome={nome}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("nome") String nome) {
        List students = PersistenceHelper.queryCustom("Aluno", "nome", nome, true);
        return students.size() > 0 ? ClientUtils.sendResponse(students.get(0)) :
            ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    }
    
    @Path("/updateAlunoCurso/matricula={matricula}") 
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateCurso(Curso curso, @PathParam("matricula") int Matricula )
	{	
    	List alunos = PersistenceHelper.queryCustom("Aluno", "matricula", String.valueOf(Matricula), false);
    	List cursos = PersistenceHelper.queryCustom("Curso", "codigo", String.valueOf(curso.getCodigo()), false);
    	if (alunos.size() > 0 && cursos.size() > 0){
    		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
    		EntityManager em = emf.createEntityManager();
    		em.getTransaction().begin();
    		Aluno a1 = (Aluno)em.merge(alunos.get(0)); 
    		a1.setCurso((Curso)cursos.get(0));
    		em.getTransaction().commit();
    		em.close();
        	return Response.status(200).build();
    	}
    	else 
    		return Response.status(404).build();  
	}
    
    @Path("/getAluno/login={login}&senha={senha}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response example(@PathParam("login") String accessKey, @PathParam("senha") String senha) {
        List students = PersistenceHelper.queryCustom("Login", "accessKey", accessKey, "password", senha);
        List alunos = null;
        if (students.size() > 0)
        {
            alunos  = PersistenceHelper.queryCustom("Aluno", "login_id", String.valueOf(((Login)students.get(0)).getId()), false);
        } 
        return students.size() > 0 ? ClientUtils.sendResponse(alunos.get(0)) :
            ClientUtils.sendMessage(new NotFoundMessage("This User wasn't found on our system."));
    } 

    @Path("/setAluno/")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setHorarios(String mandatory) throws Exception {
        Aluno student = new Gson().fromJson(mandatory, Aluno.class);

        List students = PersistenceHelper.queryCustom("Login", "accessKey", student.getLogin().getAccessKey(), true);

        // User Doesn't Exists
        // Curso e perfil sao setados para default para poder alterar depois
        if (students.size() == 0) {
        	student.setCurso((Curso)(PersistenceHelper.queryCustom("Curso", "codigo", String.valueOf(0), false).get(0)));
            student.setPerfil((Perfil)(PersistenceHelper.queryCustom("Perfil", "metrica", String.valueOf(0), false).get(0)));
        	PersistenceHelper.Persist(student.getLogin());
            PersistenceHelper.Persist(student);
        }

        // If User Exists Return 403. If not, Return 200.
        return ClientUtils.sendMessage(students.size() > 0 ? new BaseMessage(483, "User Already Exists. Creation not Allowed.") :
            new BaseMessage(200, "User Doesn't exists. Creation Allowed."));
    }
}
