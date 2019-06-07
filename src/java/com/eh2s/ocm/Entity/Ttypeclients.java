/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "ttypeclients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ttypeclients.findAll", query = "SELECT t FROM Ttypeclients t WHERE t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Ttypeclients.findById", query = "SELECT t FROM Ttypeclients t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Ttypeclients.findByName", query = "SELECT t FROM Ttypeclients t WHERE t.name = :name")})
public class Ttypeclients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeclientid", fetch = FetchType.LAZY)
    private List<Tclients> tclientsList;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Societe societe;

    public Ttypeclients() {
    }

    public Ttypeclients(Integer id) {
        this.id = id;
    }

    public Ttypeclients(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Tclients> getTclientsList() {
        return tclientsList;
    }

    public void setTclientsList(List<Tclients> tclientsList) {
        this.tclientsList = tclientsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ttypeclients)) {
            return false;
        }
        Ttypeclients other = (Ttypeclients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.Ttypeclients[ id=" + id + " ]";
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

}
