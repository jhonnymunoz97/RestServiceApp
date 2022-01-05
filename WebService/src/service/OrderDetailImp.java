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

import model.entities.OrderDetail;

import model.fascade.SessionEJBLocal;

@Stateless
@Path("entities/order-detail")
public class OrderDetailImp {
    
    public OrderDetailImp() {
        super();
    }
    
    @EJB(beanName="SessionEJB")
    SessionEJBLocal mySessionBean;
    
    private static final Logger logger = Logger.getLogger(OrderDetailImp.class.getName());

    @GET
    @Produces("application/json")
    @Path("/list")
    public List<OrderDetail> getAllOrderDetail(){
        return this.mySessionBean.getAllOrderDetail();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public OrderDetail findOrderDetailById(@PathParam("id") Integer id){
        return this.mySessionBean.findOrderDetailById(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/")
    public OrderDetail addOrderDetail(OrderDetail orderDetail){
        return this.mySessionBean.addOrderDetail(orderDetail);
    }
    
}
