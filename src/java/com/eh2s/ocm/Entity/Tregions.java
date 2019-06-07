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
@Table(name = "tregions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tregions.findAll", query = "SELECT t FROM Tregions t WHERE t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tregions.findById", query = "SELECT t FROM Tregions t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tregions.findByName", query = "SELECT t FROM Tregions t WHERE t.name = :name")
    ,
    @NamedQuery(name = "Tregions.findByMail", query = "SELECT t FROM Tregions t WHERE t.mail = :mail")
    ,
    @NamedQuery(name = "Tregions.findByTel1", query = "SELECT t FROM Tregions t WHERE t.tel1 = :tel1")
    ,
    @NamedQuery(name = "Tregions.findByTel2", query = "SELECT t FROM Tregions t WHERE t.tel2 = :tel2")})
public class Tregions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "tel1")
    private String tel1;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "tel2")
    private String tel2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionid", fetch = FetchType.LAZY)
    private List<Tsites> tsitesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionid", fetch = FetchType.LAZY)
    private List<Tdistricts> tdistrictsList;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Societe societe;
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<Magasin> magasinList;

    public Tregions() {
    }

    public Tregions(Integer id) {
        this.id = id;
    }

    public Tregions(String nom) {
        this.name = nom;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    @XmlTransient
    public List<Tsites> getTsitesList() {
        return tsitesList;
    }

    public void setTsitesList(List<Tsites> tsitesList) {
        this.tsitesList = tsitesList;
    }

    @XmlTransient
    public List<Tdistricts> getTdistrictsList() {
        return tdistrictsList;
    }

    public void setTdistrictsList(List<Tdistricts> tdistrictsList) {
        this.tdistrictsList = tdistrictsList;
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
        if (!(object instanceof Tregions)) {
            return false;
        }
        Tregions other = (Tregions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.Tregions[ id=" + id + " ]";
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public List<Magasin> getMagasinList() {
        return magasinList;
    }

    public void setMagasinList(List<Magasin> magasinList) {
        this.magasinList = magasinList;
    }

}
