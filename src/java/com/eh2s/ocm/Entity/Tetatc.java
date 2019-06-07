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
@Table(name = "tetatc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tetatc.findAll", query = "SELECT t FROM Tetatc t")
    ,
    @NamedQuery(name = "Tetatc.findById", query = "SELECT t FROM Tetatc t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tetatc.findByCode", query = "SELECT t FROM Tetatc t WHERE t.code = :code")
    ,
    @NamedQuery(name = "Tetatc.findByNom", query = "SELECT t FROM Tetatc t WHERE t.nom = :nom")})
public class Tetatc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private Integer code;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "etatc", fetch = FetchType.LAZY)
    private List<Tlignecommande> tlignecommandeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etat", fetch = FetchType.LAZY)
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurList;

    public Tetatc() {
    }

    public Tetatc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Tlignecommande> getTlignecommandeList() {
        return tlignecommandeList;
    }

    public void setTlignecommandeList(List<Tlignecommande> tlignecommandeList) {
        this.tlignecommandeList = tlignecommandeList;
    }

    public List<LigneCommandeFournisseur> getLigneCommandeFournisseurList() {
        return ligneCommandeFournisseurList;
    }

    public void setLigneCommandeFournisseurList(List<LigneCommandeFournisseur> ligneCommandeFournisseurList) {
        this.ligneCommandeFournisseurList = ligneCommandeFournisseurList;
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
        if (!(object instanceof Tetatc)) {
            return false;
        }
        Tetatc other = (Tetatc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Tetatc[ id=" + id + " ]";
    }
}
