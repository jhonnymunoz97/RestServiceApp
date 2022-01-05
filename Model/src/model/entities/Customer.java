package model.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement(name="customer")
@NamedQueries({ @NamedQuery(name = "Customer.findAll", query = "select o from Customer o") })
public class Customer implements Serializable {
    
    private static final long serialVersionUID = 2984110798475128359L;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    
    @Column(name = "CUSTOMER_CODE", unique = true, length = 50)
    private String customerCode;
    
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    
    @Id
    @Column(nullable = false)
    @SequenceGenerator( name = "SEQ_CUSTOMER_ID", sequenceName = "SEQ_CUSTOMER_ID", allocationSize = 1, initialValue = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CUSTOMER_ID")
    private Integer id;
    
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    
    @Column(name = "TAX_IDENTIFICATION_NUMBER", unique = true, length = 50)
    private String taxIdentificationNumber;
    
    //@OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @XmlTransient
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ordert> ordertList;

    public Customer() {
    }

    public Customer(Date birthDate, String customerCode, String firstName, Integer id, String lastName,
                    String taxIdentificationNumber) {
        this.birthDate = birthDate;
        this.customerCode = customerCode;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    @XmlTransient
    public List<Ordert> getOrdertList() {
        return ordertList;
    }

    @XmlTransient
    public void setOrdertList(List<Ordert> ordertList) {
        this.ordertList = ordertList;
    }

    public Ordert addOrdert(Ordert ordert) {
        getOrdertList().add(ordert);
        ordert.setCustomer(this);
        return ordert;
    }

    public Ordert removeOrdert(Ordert ordert) {
        getOrdertList().remove(ordert);
        ordert.setCustomer(null);
        return ordert;
    }
}
