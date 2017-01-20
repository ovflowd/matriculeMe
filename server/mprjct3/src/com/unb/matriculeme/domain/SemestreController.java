package com.unb.matriculeme.domain;

import com.unb.matriculeme.dao.Semestre;
import com.unb.matriculeme.helpers.ClientUtils;
import com.unb.matriculeme.helpers.PersistenceHelper;
import com.unb.matriculeme.messages.AllRightMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Comentarios da classe: alguem vai dar get DIRETAMENTE e SO PARA PEGAR (n)o semestre? Responsta: NO

@Path("/semestre")
public class SemestreController {
    @Path("/setSemestre")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setSemestre(List<Semestre> semestre) {
        for (Semestre aSemestre : semestre) {
            PersistenceHelper.Persist(aSemestre);
        }
        return ClientUtils.sendMessage(new AllRightMessage("Semestre set successfully."));
    }
}
