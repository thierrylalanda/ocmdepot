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
@Table(name = "tservices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tservices.findAll", query = "SELECT t FROM Tservices t WHERE t.site.regionid.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tservices.findById", query = "SELECT t FROM Tservices t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tservices.findByRegion", query = "SELECT t FROM Tservices t WHERE t.site.regionid.id = :reg")
    ,
    @NamedQuery(name = "Tservices.findByName", query = "SELECT t FROM Tservices t WHERE t.name = :name")})
public class Tservices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "site", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tsites site;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceid", fetch = FetchType.LAZY)
    private List<Tusers> tusersList;

    public Tservices() {
    }

    public Tservices(Integer id) {
        this.id = id;
    }

    public Tservices(Integer id, String name) {
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

    public Tsites getSite() {
        return site;
    }

    public void setSite(Tsites site) {
        this.site = site;
    }

    @XmlTransient
    public List<Tusers> getTusersList() {
        return tusersList;
    }

    public void setTusersList(List<Tusers> tusersList) {
        this.tusersList = tusersList;
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
        if (!(object instanceof Tservices)) {
            return false;
        }
        Tservices other = (Tservices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.Tservices[ id=" + id + " ]";
    }

}
