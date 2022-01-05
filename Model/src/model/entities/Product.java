package model.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.OneToMany;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement(name="product")
@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "select o from Product o") })
public class Product implements Serializable {
    
    private static final long serialVersionUID = 7320264270596272956L;
    
    @Column(nullable = false, length = 100)
    private String description;
    
    @Id
    @Column(nullable = false)
    @SequenceGenerator( name = "SEQ_PRODUCT_ID", sequenceName = "SEQ_PRODUCT_ID", allocationSize = 1, initialValue = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PRODUCT_ID")
    private Integer id;
    
    @Column(name = "PRODUCT_CODE", nullable = false, length = 50)
    private String productCode;
    
    
    //@OneToMany(mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @XmlTransient
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList1;

    public Product() {
    }

    public Product(String description, Integer id, String productCode) {
        this.description = description;
        this.id = id;
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList1() {
        return orderDetailList1;
    }

    @XmlTransient
    public void setOrderDetailList1(List<OrderDetail> orderDetailList1) {
        this.orderDetailList1 = orderDetailList1;
    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        getOrderDetailList1().add(orderDetail);
        orderDetail.setProduct(this);
        return orderDetail;
    }

    public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
        getOrderDetailList1().remove(orderDetail);
        orderDetail.setProduct(null);
        return orderDetail;
    }
}
