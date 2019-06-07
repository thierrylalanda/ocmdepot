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
 * @author Administrateur
 */
@Entity
@Table(name = "fournisseur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fournisseur.findAll", query = "SELECT f FROM Fournisseur f")
    , @NamedQuery(name = "Fournisseur.findById", query = "SELECT f FROM Fournisseur f WHERE f.id = :id")
    , @NamedQuery(name = "Fournisseur.findByCode", query = "SELECT f FROM Fournisseur f WHERE f.code = :code")
    , @NamedQuery(name = "Fournisseur.findBySociete", query = "SELECT f FROM Fournisseur f WHERE f.societe.id = :societe")
    , @NamedQuery(name = "Fournisseur.findByAdresse", query = "SELECT f FROM Fournisseur f WHERE f.adresse = :adresse")
    , @NamedQuery(name = "Fournisseur.findByNom", query = "SELECT f FROM Fournisseur f WHERE f.nom = :nom")
    , @NamedQuery(name = "Fournisseur.findByMail", query = "SELECT f FROM Fournisseur f WHERE f.mail = :mail")})
public class Fournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code")
    private String code;
    @Size(max = 50)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom")
    private String nom;
    @Size(max = 50)
    @Column(name = "mail")
    private String mail;
    @Size(max = 50)
    @Column(name = "telephone")
    private String telephone;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Societe societe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fournisseur", fetch = FetchType.LAZY)
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurList;
    @OneToMany(mappedBy = "fournisseur", fetch = FetchType.LAZY)
    private List<Tarticles> tarticlesList;
    @OneToMany(mappedBy = "fournisseur", fetch = FetchType.LAZY)
    private List<LigneSortie> ligneSortieList;

    public Fournisseur() {
    }

    public Fournisseur(Integer id) {
        this.id = id;
    }

    public Fournisseur(Integer id, String code, String nom) {
        this.id = id;
        this.code = code;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public List<LigneSortie> getLigneSortieList() {
        return ligneSortieList;
    }

    public void setLigneSortieList(List<LigneSortie> ligneSortieList) {
        this.ligneSortieList = ligneSortieList;
    }

    
    
    public List<LigneCommandeFournisseur> getLigneCommandeFournisseurList() {
        return ligneCommandeFournisseurList;
    }

    public void setLigneCommandeFournisseurList(List<LigneCommandeFournisseur> ligneCommandeFournisseurList) {
        this.ligneCommandeFournisseurList = ligneCommandeFournisseurList;
    }

    public List<Tarticles> getTarticlesList() {
        return tarticlesList;
    }

    public void setTarticlesList(List<Tarticles> tarticlesList) {
        this.tarticlesList = tarticlesList;
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
        if (!(object instanceof Fournisseur)) {
            return false;
        }
        Fournisseur other = (Fournisseur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Fournisseur[ id=" + id + " ]";
    }
}
