package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.entities.Persona;

import model.fascade.SessionEJBLocal;

@Stateless
@Path("entities/persona")
public class PersonaImp {
    
    public PersonaImp() {
        super();
    }
    
    @EJB(beanName="SessionEJB")
    SessionEJBLocal mySessionBean;

    @GET
    @Produces("application/json")
    @Path("/list")
    public List<Persona> getAllPersona(){
        return this.mySessionBean.getAllPersonas();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Persona getPersonaById(@PathParam("id") Integer id){
        return this.mySessionBean.getPersonaById(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/")
    public Persona addPersona(Persona persona){
        return this.mySessionBean.addPersona(persona);
    }
}
