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
import com.unb.matriculeme.dao.Departamento;
import com.unb.matriculeme.dao.Perfil;
import com.unb.matriculeme.helpers.PersistenceHelper;

import java.util.List;

@Path("/perfil")
public class PerfilController {
	
	@Path("/setPerfil")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setPerfil(Perfil perfilRecebido){
		Perfil perfil = new Perfil();
		//perfil.setAluno((Aluno)(Queries.queryCustom("Aluno", "matricula", String.valueOf(perfilRecebido.getAluno().getMatricula()), false).get(0)));
		//perfil.setDepartamentos((Departamento)(Queries.queryCustom("Departamento", "codigo", String.valueOf(perfilRecebido.getDepartamento().getCodigo()), false)).get(0));
		perfil.setMetrica(perfilRecebido.getMetrica());
		PersistenceHelper.Persist(perfil);
		return Response.status(200).build();
	}
}
