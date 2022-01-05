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

import model.entities.Customer;

import model.fascade.SessionEJBLocal;

@Stateless
@Path("entities/customer")
public class CustomerImp {
    
    public CustomerImp() {
        super();
    }
    
    @EJB(beanName="SessionEJB")
    SessionEJBLocal mySessionBean;

    @GET
    @Produces("application/json")
    @Path("/list")
    public List<Customer> getAllCustomers(){
        return this.mySessionBean.getAllCustomers();
    }

    @GET
    @Produces("application/json")
    @Path("/{taxIdentificationNumber}")
    public Customer findCustomerByTaxIdentification(@PathParam("taxIdentificationNumber")
                                                    String taxIdentificationNumber){
        return this.mySessionBean.findCustomerByTax(taxIdentificationNumber);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/")
    public Customer addCustomer(Customer customer){
        return this.mySessionBean.addCustomer(customer);
    }
}
