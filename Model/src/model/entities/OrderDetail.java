package model.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement(name="orderDetail")
@NamedQueries({ @NamedQuery(name = "OrderDetail.findAll", query = "select o from OrderDetail o") })
@Table(name = "ORDER_DETAIL")
public class OrderDetail implements Serializable {
    
    private static final long serialVersionUID = -8371210684830013107L;
    
    @Id
    @Column(nullable = false)
    @SequenceGenerator( name = "SEQ_ORDER_DETAIL_ID", sequenceName = "SEQ_ORDER_DETAIL_ID", allocationSize = 1, initialValue = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ORDER_DETAIL_ID")
    private Integer id;
    
    @Column(nullable = false)
    private Long quantity;
    
    @ManyToOne
    @JoinColumn(name = "ORDERT_ID")
    private Ordert ordert;
    
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, Ordert ordert, Product product, Long quantity) {
        this.id = id;
        this.ordert = ordert;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Ordert getOrdert() {
        return ordert;
    }

    public void setOrdert(Ordert ordert) {
        this.ordert = ordert;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
