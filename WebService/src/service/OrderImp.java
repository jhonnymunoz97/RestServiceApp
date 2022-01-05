package service;

import java.util.List;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.entities.Ordert;

import model.fascade.SessionEJBLocal;

@Stateless
@Path("entities/order")
public class OrderImp {
    
    private static final Logger logger = Logger.getLogger(OrderImp.class.getName());
    
    public OrderImp() {
        super();
    }
    
    @EJB(beanName="SessionEJB")
    SessionEJBLocal mySessionBean;

    @GET
    @Produces("application/json")
    @Path("/list")
    public List<Ordert> getAllOrdert(){
        return this.mySessionBean.getAllOrdert();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Ordert findOrdertById(@PathParam("id") Integer id){
        return this.mySessionBean.findOrdertById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/document/{documentNumber}")
    public Ordert findOrdertByDocumentNumber(@PathParam("documentNumber") Integer documentNumber){
        return this.mySessionBean.findOrdertByDocumentNumber(documentNumber);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/")
    public Ordert addOrdert(Ordert order){
        return this.mySessionBean.addOrdert(order);
    }
}
