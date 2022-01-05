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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement(name="ordert")
@NamedQueries({ @NamedQuery(name = "Ordert.findAll", query = "select o from Ordert o") })
public class Ordert implements Serializable {
    
    private static final long serialVersionUID = -6927311228919517864L;
    
    @Column(name = "DOCUMENT_NUMBER", unique = true)
    private Integer documentNumber;
    
    @Id
    @Column(nullable = false)
    @SequenceGenerator( name = "SEQ_ORDERT_ID", sequenceName = "SEQ_ORDERT_ID", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ORDERT_ID")   
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "ORDERR_DATE", nullable = false)
    private Date orderrDate;
    
    //@OneToMany(mappedBy = "ordert", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @XmlTransient
    @OneToMany(mappedBy = "ordert", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "ordert", cascade = { CascadeType.PERSIST, CascadeType.ALL })
    private List<OrderDetail> orderDetailList;
    
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;


    public Ordert() {
    }

    public Ordert(Customer customer, Integer documentNumber, Integer id, Date orderrDate) {
        this.customer = customer;
        this.documentNumber = documentNumber;
        this.id = id;
        this.orderrDate = orderrDate;
    }


    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderrDate() {
        return orderrDate;
    }

    public void setOrderrDate(Date orderrDate) {
        this.orderrDate = orderrDate;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }
    
    @XmlTransient
    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        getOrderDetailList().add(orderDetail);
        orderDetail.setOrdert(this);
        return orderDetail;
    }

    public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
        getOrderDetailList().remove(orderDetail);
        orderDetail.setOrdert(null);
        return orderDetail;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
