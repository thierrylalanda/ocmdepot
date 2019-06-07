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
 * @author messi01
 */
@Entity
@Table(name = "tourner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tourner.findAll", query = "SELECT t FROM Tourner t")
    , @NamedQuery(name = "Tourner.findAllBySociete", query = "SELECT t FROM Tourner t WHERE t.societe.id = :id")
    , @NamedQuery(name = "Tourner.findById", query = "SELECT t FROM Tourner t WHERE t.id = :id")
    , @NamedQuery(name = "Tourner.findByNumc", query = "SELECT t FROM Tourner t WHERE t.numc = :numc")})
public class Tourner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "numc")
    private String numc;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Societe societe;
    @OneToMany(mappedBy = "tourner", fetch = FetchType.LAZY)
    private List<Tlignecommande> tlignecommandeList;
    @OneToMany(mappedBy = "tourner", fetch = FetchType.LAZY)
    private List<Tclients> tclientsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourner", fetch = FetchType.LAZY)
    private List<AffectTournerUser> affectTournerUserList;
    @JoinColumn(name = "magasin", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Magasin magasin;

    public Tourner() {
    }

    public Tourner(Integer id) {
        this.id = id;
    }

    public Tourner(Integer id, String numc) {
        this.id = id;
        this.numc = numc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumc() {
        return numc;
    }

    public void setNumc(String numc) {
        this.numc = numc;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    @XmlTransient
    public List<Tlignecommande> getTlignecommandeList() {
        return tlignecommandeList;
    }

    public void setTlignecommandeList(List<Tlignecommande> tlignecommandeList) {
        this.tlignecommandeList = tlignecommandeList;
    }

    public List<Tclients> getTclientsList() {
        return tclientsList;
    }

    public void setTclientsList(List<Tclients> tclientsList) {
        this.tclientsList = tclientsList;
    }

    @XmlTransient
    public List<AffectTournerUser> getAffectTournerUserList() {
        return affectTournerUserList;
    }

    public void setAffectTournerUserList(List<AffectTournerUser> affectTournerUserList) {
        this.affectTournerUserList = affectTournerUserList;
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
        if (!(object instanceof Tourner)) {
            return false;
        }
        Tourner other = (Tourner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ocm.eh2s.entity.Tourner[ id=" + id + " ]";
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

}
