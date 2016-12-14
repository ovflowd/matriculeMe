package com.unb.matriculeme.domain;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.unb.matriculeme.dao.Semestre;
import com.unb.matriculeme.helpers.PersistenceHelper;

//Comentarios da classe: alguem vai dar get DIRETAMENTE e SO PARA PEGAR (n)o semestre? Responsta: NO

@Path("/semestre")
public class SemestreController {
	@Path("/setSemestre")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSemestre(List<Semestre> semestre)
	{
		for (int i = 0; i < semestre.size(); i++)
		{
			PersistenceHelper.Persist(semestre.get(i));
		}
		return Response.status(200).build();
	}
}
