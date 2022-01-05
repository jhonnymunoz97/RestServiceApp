package model.fascade;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.criteria.Order;

import model.entities.Customer;
import model.entities.OrderDetail;
import model.entities.Ordert;
import model.entities.Persona;
import model.entities.Product;

@Stateless(name = "SessionEJB", mappedName = "RestServiceApp-Model-SessionEJB")
public class SessionEJBBean implements SessionEJBLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public SessionEJBBean() {
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        return em.merge(entity);
    }

    public void removeOrdert(Ordert ordert) {
        ordert = em.find(Ordert.class, ordert.getId());
        em.remove(ordert);
    }

    public void removeProduct(Product product) {
        product = em.find(Product.class, product.getId());
        em.remove(product);
    }

    public void removePersona(Persona persona) {
        persona = em.find(Persona.class, persona.getId());
        em.remove(persona);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetail = em.find(OrderDetail.class, orderDetail.getId());
        em.remove(orderDetail);
    }

    public void removeCustomer(Customer customer) {
        customer = em.find(Customer.class, customer.getId());
        em.remove(customer);
    }
    
    /************************* PERSONA ***********************/
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Persona> getAllPersonas(){
        return this.em.createNamedQuery("Persona.findAll",Persona.class).getResultList();
    }
    
    public Persona getPersonaById(Integer id){
        return (Persona) this.em.createQuery("select o from Persona o where o.id=:id").setParameter("id", id).getSingleResult();
    }
    
    public Persona addPersona(Persona persona){
        this.em.persist(persona);
        return persona;
    }
    
    /************************* CUSTOMER ***********************/
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Customer> getAllCustomers(){
        return this.em.createNamedQuery("Customer.findAll",Customer.class).getResultList();
    }
    
    public Customer findCustomerByTax(String taxIdentificationNumber){
        return (Customer) em.createQuery("SELECT o FROM Customer o WHERE o.taxIdentificationNumber=:taxIdentificationNumber")
                            .setParameter("taxIdentificationNumber", taxIdentificationNumber)
                            .getSingleResult();
    }
    
    public Customer addCustomer(Customer customer){
        this.em.persist(customer);
        return (Customer) this.em.createQuery("SELECT o FROM Customer o WHERE o.id=(SELECT MAX(o.id) FROM Customer o )").getSingleResult();
        //return customer;
    }
    
    /************************* PRODUCT ***********************/
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Product> getAllProducts(){
        return this.em.createNamedQuery("Product.findAll",Product.class).getResultList();
    }
    
    public Product findProductByProductCode(String productCode){
        return (Product) this.em.createQuery("SELECT o FROM Product o WHERE o.productCode=:productCode")
                            .setParameter("productCode", productCode)
                            .getSingleResult();
    }
    
    public Product addProduct(Product product){
        this.em.persist(product);
        return (Product) this.em.createQuery("SELECT o FROM Product o WHERE o.id=(SELECT MAX(o.id) FROM Product o )").getSingleResult();
        //return product;
    }
    
    /************************* ORDERT ***********************/
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Ordert> getAllOrdert(){
        return this.em.createNamedQuery("Ordert.findAll",Ordert.class).getResultList();
    }
    
    public Ordert findOrdertById(Integer id){
        return (Ordert) this.em.createQuery("select o from Ordert o where o.id=:id").setParameter("id", id).getSingleResult();
                           
    }
    
    public Ordert findOrdertByDocumentNumber(Integer documentNumber){
        return (Ordert) this.em.createQuery("SELECT o FROM Ordert o WHERE o.documentNumber=:documentNumber")
                            .setParameter("documentNumber", documentNumber)
                            .getSingleResult();
    }
    
    public Ordert addOrdert(Ordert order){
        this.em.persist(order);
        //return order;
        Ordert order_response = (Ordert) this.em.createQuery("SELECT o FROM Ordert o WHERE o.id=(SELECT MAX(o.id) FROM Ordert o )").getSingleResult();
        Ordert response = new Ordert();
        response.setId(order_response.getId());
        response.setDocumentNumber(order_response.getDocumentNumber());
        return response;
    }
    
    /************************** ORDER_DETAIL ********************/
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<OrderDetail> getAllOrderDetail(){
        return this.em.createNamedQuery("OrderDetail.findAll",OrderDetail.class).getResultList();    
    }
    
    public OrderDetail findOrderDetailById(Integer id){
        return (OrderDetail) this.em.createQuery("select o from OrderDetail o where o.id=:id").setParameter("id", id).getSingleResult();  
    }
    
    public OrderDetail addOrderDetail(OrderDetail orderDetail){
        this.em.persist(orderDetail);
        OrderDetail order_detail_response = (OrderDetail) this.em.createQuery("SELECT o FROM OrderDetail o WHERE o.id=(SELECT MAX(o.id) FROM OrderDetail o )").getSingleResult();
        OrderDetail response = new OrderDetail();
        response.setId(order_detail_response.getId());
        return response;
        //return orderDetail;
    }
    
}
