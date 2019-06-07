/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "tarticles")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Tarticles.findAll", query = "SELECT t FROM Tarticles t WHERE t.categorie.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tarticles.findAllBySociete", query = "SELECT t FROM Tarticles t WHERE t.categorie.societe.id = :id")
    ,
    @NamedQuery(name = "Tarticles.findALLByCat", query = "SELECT t FROM Tarticles t WHERE t.categorie.id = :cat")
    ,
    @NamedQuery(name = "Tarticles.findById", query = "SELECT t FROM Tarticles t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tarticles.findByNom", query = "SELECT t FROM Tarticles t WHERE t.nom = :nom")
    ,
    @NamedQuery(name = "Tarticles.findByUnite", query = "SELECT t FROM Tarticles t WHERE t.unite = :unite")
    ,
    @NamedQuery(name = "Tarticles.findByPrix", query = "SELECT t FROM Tarticles t WHERE t.prix = :prix")})
public class Tarticles implements Serializable {

     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "chemin")
    private String chemin;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "photo")
    private String photo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private Double prix;
    @Column(name = "stock")
    private Double stock=0.0;
    @Size(max = 255)
    @Column(name = "unite")
    private String unite;
    @Column(name = "seuil")
    private int seuil=0;
    @Column(name = "margeclient")
    private Double margeclient=0.0;
    @Column(name = "margefournisseur")
    private Double margefournisseur=0.0;
    @Column(name = "prix_achat")
    private Double prixAchat=0.0;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY)
    private List<CommandeFournisseur> commandeFournisseurList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY)
    private List<TransfertStock> transfertStockList;
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Tcommandes> tcommandesList;
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Inventaire> inventaireList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY)
    private List<StockMg> stockMgList;
    @JoinColumn(name = "categorie", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tcategorie categorie;
    @JoinColumn(name = "fournisseur", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fournisseur fournisseur;
    @JoinColumn(name = "emballage", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Emballage emballage;

    public Tarticles() {
    }

    public Tarticles(Integer id) {
        this.id = id;
    }

    public Tarticles(Integer id, int seuil, Double margeclient, Double margefournisseur) {
        this.id = id;
        this.seuil = seuil;
        this.margeclient = margeclient;
        this.margefournisseur = margefournisseur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getSeuil() {
        return seuil;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }

    public Double getMargeClient() {
        return margeclient;
    }

    public void setMargeClient(Double margeclient) {
        this.margeclient = margeclient;
    }

    public Double getMargeFournisseur() {
        return margefournisseur;
    }

    public void setMargeFournisseur(Double margefournisseur) {
        this.margefournisseur = margefournisseur;
    }

    public Double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public List<CommandeFournisseur> getCommandeFournisseurList() {
        return commandeFournisseurList;
    }

    public void setCommandeFournisseurList(List<CommandeFournisseur> commandeFournisseurList) {
        this.commandeFournisseurList = commandeFournisseurList;
    }

    public List<TransfertStock> getTransfertStockList() {
        return transfertStockList;
    }

    public void setTransfertStockList(List<TransfertStock> transfertStockList) {
        this.transfertStockList = transfertStockList;
    }

    public List<Tcommandes> getTcommandesList() {
        return tcommandesList;
    }

    public void setTcommandesList(List<Tcommandes> tcommandesList) {
        this.tcommandesList = tcommandesList;
    }

    public List<Inventaire> getInventaireList() {
        return inventaireList;
    }

    public void setInventaireList(List<Inventaire> inventaireList) {
        this.inventaireList = inventaireList;
    }

    public List<StockMg> getStockMgList() {
        return stockMgList;
    }

    public void setStockMgList(List<StockMg> stockMgList) {
        this.stockMgList = stockMgList;
    }

    public Tcategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Tcategorie categorie) {
        this.categorie = categorie;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Emballage getEmballage() {
        return emballage;
    }

    public void setEmballage(Emballage emballage) {
        this.emballage = emballage;
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
        if (!(object instanceof Tarticles)) {
            return false;
        }
        Tarticles other = (Tarticles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Tarticles[ id=" + id + " ]";
    }
    
}
