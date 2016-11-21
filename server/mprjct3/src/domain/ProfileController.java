package domain;
//TODO: esse ta liso, refazer o domain. tb tirar as alteracoes que deram problema no fk  perfil->departamento
//@TODO Concordo, colocou query direto aqui.

import dao.Profile;
import helpers.PersistenceHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/perfil")
public class ProfileController {

    public Integer queryGetPerfilAluno(int alunoId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("SELECT  FROM perfil WHERE alunoId='" + alunoId + "'");

        List perfil = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return alunoId;
    }

    public Integer queryGetCursosDepartamento(int departamento) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("SELECT departamento FROM Perfil WHERE nome='" + departamento + "'");

        // Variável não é usada..
        List departamentoId = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return departamento;
    }

    public void Update(Profile profile, Profile newProfile) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Profile profile2 = em.merge(profile);

        //profile2.set(profile.getName());
        //profile2.setCode(newProfile.getCode());

        profile2.setStudent(newProfile.getStudent());
        em.getTransaction().commit();
        em.close();
    }

    @Path("/setPerfil")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sayPlainTextHello(Profile profile) throws Exception {
        Profile profile1 = new Profile();
        profile1.setStudent(profile.getStudent());
        profile1.setDepartment(profile.getDepartment());
        profile1.setMetrics(profile.getMetrics());
        PersistenceHelper.persist(profile1);
        return Response.status(200).build();
    }

    @Path("/getPerfilInfo/Id={id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertFeetToInch(@PathParam("id") int id) {

        List perfis = PersistenceHelper.queryCustom("Profile", "perfil_id", String.valueOf(id), false);

        Profile profile = (Profile) perfis.get(0);
        //DataMining  firstThread = new DataMining();
        if (perfis.size() > 0) {
            //firstThread.start();
        }

        return perfis.size() > 0 ? Response.ok("{\n\t\"departamento:\":" + profile.getDepartment()
                + ",\n\t\"aluno_id\":\"" + profile.getStudent() + "\""
                + ",\n\t\"metrica\":\"" + profile.getMetrics() + "\""
                + ",\n\t\"Id\":" + profile.getId() + "\n}", MediaType.APPLICATION_JSON).build() : Response.status(404).build();
    }
}
