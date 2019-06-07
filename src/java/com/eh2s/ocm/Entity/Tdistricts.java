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
@Table(name = "tdistricts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tdistricts.findAll", query = "SELECT t FROM Tdistricts t WHERE t.regionid.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tdistricts.findById", query = "SELECT t FROM Tdistricts t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tdistricts.findByName", query = "SELECT t FROM Tdistricts t WHERE t.name = :name")})
public class Tdistricts implements Serializable {

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
    @JoinColumn(name = "regionid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tregions regionid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "districtid", fetch = FetchType.LAZY)
    private List<Tsecteurs> tsecteursList;

    public Tdistricts() {
    }

    public Tdistricts(Integer id) {
        this.id = id;
    }

    public Tdistricts(Integer id, String name) {
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

    public Tregions getRegionid() {
        return regionid;
    }

    public void setRegionid(Tregions regionid) {
        this.regionid = regionid;
    }

    @XmlTransient
    public List<Tsecteurs> getTsecteursList() {
        return tsecteursList;
    }

    public void setTsecteursList(List<Tsecteurs> tsecteursList) {
        this.tsecteursList = tsecteursList;
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
        if (!(object instanceof Tdistricts)) {
            return false;
        }
        Tdistricts other = (Tdistricts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.Tdistricts[ id=" + id + " ]";
    }

}
