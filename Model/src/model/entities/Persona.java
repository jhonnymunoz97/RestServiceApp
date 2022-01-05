package model.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name="persona")
@NamedQueries({ @NamedQuery(name = "Persona.findAll", query = "select o from Persona o") })
public class Persona implements Serializable {
    private static final long serialVersionUID = 8007401927980262112L;
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    @Id
    @Column(nullable = false)
    private Integer id;
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    @Column(length = 50)
    private String phone;

    public Persona() {
    }

    public Persona(String firstName, Integer id, String lastName, String phone) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
