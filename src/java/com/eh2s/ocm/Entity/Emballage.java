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
@Table(name = "emballage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emballage.findAll", query = "SELECT e FROM Emballage e WHERE e.societe.id = :societe ")
    , @NamedQuery(name = "Emballage.findById", query = "SELECT e FROM Emballage e WHERE e.id = :id")
    , @NamedQuery(name = "Emballage.findByCode", query = "SELECT e FROM Emballage e WHERE e.code = :code")
    , @NamedQuery(name = "Emballage.findByNom", query = "SELECT e FROM Emballage e WHERE e.nom = :nom")
    , @NamedQuery(name = "Emballage.findByPrix", query = "SELECT e FROM Emballage e WHERE e.prix = :prix")
    , @NamedQuery(name = "Emballage.findByStock", query = "SELECT e FROM Emballage e WHERE e.stock = :stock")
    , @NamedQuery(name = "Emballage.findBySeuil", query = "SELECT e FROM Emballage e WHERE e.seuil = :seuil")})
public class Emballage implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private Double prix;
    @Column(name = "stock")
    private Double stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seuil")
    private int seuil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_total")
    private double prixTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emballage", fetch = FetchType.LAZY)
    private List<CommandeEmballage> commandeEmballageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emballage", fetch = FetchType.LAZY)
    private List<CompteEmballage> compteEmballageList;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Societe societe;
    @OneToMany(mappedBy = "emballage", fetch = FetchType.LAZY)
    private List<Inventaire> inventaireList;
    @OneToMany(mappedBy = "emballage", fetch = FetchType.LAZY)
    private List<Tarticles> tarticlesList;

    public Emballage() {
    }

    public Emballage(Integer id) {
        this.id = id;
    }

    public Emballage(Integer id, int seuil, double prixTotal) {
        this.id = id;
        this.seuil = seuil;
        this.prixTotal = prixTotal;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public int getSeuil() {
        return seuil;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public List<CommandeEmballage> getCommandeEmballageList() {
        return commandeEmballageList;
    }

    public void setCommandeEmballageList(List<CommandeEmballage> commandeEmballageList) {
        this.commandeEmballageList = commandeEmballageList;
    }

    public List<CompteEmballage> getCompteEmballageList() {
        return compteEmballageList;
    }

    public void setCompteEmballageList(List<CompteEmballage> compteEmballageList) {
        this.compteEmballageList = compteEmballageList;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public List<Inventaire> getInventaireList() {
        return inventaireList;
    }

    public void setInventaireList(List<Inventaire> inventaireList) {
        this.inventaireList = inventaireList;
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
        if (!(object instanceof Emballage)) {
            return false;
        }
        Emballage other = (Emballage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Emballage[ id=" + id + " ]";
    }
}
