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

import model.entities.Product;

import model.fascade.SessionEJBLocal;

@Stateless
@Path("entities/product")
public class ProductImp {
    
    public ProductImp() {
        super();
    }
    
    @EJB(beanName="SessionEJB")
    SessionEJBLocal mySessionBean;

    @GET
    @Produces("application/json")
    @Path("/list")
    public List<Product> getAllProducts(){
        return this.mySessionBean.getAllProducts();
    }

    @GET
    @Produces("application/json")
    @Path("/{productCode}")
    public Product findProductByProductCode(@PathParam("productCode") String productCode){
        return this.mySessionBean.findProductByProductCode(productCode);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/")
    public Product addProduct(Product product){
        return this.mySessionBean.addProduct(product);
    }
    
}
