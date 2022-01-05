package model.fascade;

import java.util.List;

import javax.ejb.Local;

import model.entities.Customer;
import model.entities.OrderDetail;
import model.entities.Ordert;
import model.entities.Persona;
import model.entities.Product;

@Local
public interface SessionEJBLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    <T> T persistEntity(T entity);

    <T> T mergeEntity(T entity);

    void removeOrdert(Ordert ordert);

    void removeProduct(Product product);

    void removePersona(Persona persona);

    void removeOrderDetail(OrderDetail orderDetail);

    void removeCustomer(Customer customer);
    
    // Persona
    public List<Persona> getAllPersonas();
    public Persona getPersonaById(Integer id);
    public Persona addPersona(Persona persona);
    
    //Customer
    public List<Customer> getAllCustomers();
    public Customer findCustomerByTax(String taxIdentificationNumber);
    public Customer addCustomer(Customer customer);
    
    //Product
    public List<Product> getAllProducts();
    public Product findProductByProductCode(String productCode);
    public Product addProduct(Product product);
    
    //Order
    public List<Ordert> getAllOrdert();
    public Ordert findOrdertById(Integer id);
    public Ordert findOrdertByDocumentNumber(Integer documentNumber);
    public Ordert addOrdert(Ordert order);
    
    //OrderDetail
    public List<OrderDetail> getAllOrderDetail();
    public OrderDetail findOrderDetailById(Integer id);
    public OrderDetail addOrderDetail(OrderDetail orderDetail);
    
}
