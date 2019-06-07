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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "commande_fournisseur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommandeFournisseur.findAll", query = "SELECT c FROM CommandeFournisseur c")
    , @NamedQuery(name = "CommandeFournisseur.findById", query = "SELECT c FROM CommandeFournisseur c WHERE c.id = :id")
    , @NamedQuery(name = "CommandeFournisseur.findByPrix", query = "SELECT c FROM CommandeFournisseur c WHERE c.prix = :prix")
    , @NamedQuery(name = "CommandeFournisseur.findByPrixTotal", query = "SELECT c FROM CommandeFournisseur c WHERE c.prixTotal = :prixTotal")
    , @NamedQuery(name = "CommandeFournisseur.findByQuantite", query = "SELECT c FROM CommandeFournisseur c WHERE c.quantite = :quantite")
    ,@NamedQuery(name = "CommandeFournisseur.findForCategorie", query = "SELECT c FROM CommandeFournisseur c WHERE c.article.categorie.id = :id AND c.ligneCommandeFournisseur.dateLivraison >= :d AND c.ligneCommandeFournisseur.etat.code = :etat AND c.ligneCommandeFournisseur.dateLivraison <= :d2")
    ,@NamedQuery(name = "CommandeFournisseur.findForArticlePeriode", query = "SELECT c FROM CommandeFournisseur c WHERE c.article.id = :article AND c.ligneCommandeFournisseur.dateLivraison >= :d AND c.ligneCommandeFournisseur.etat.code = :etat AND c.ligneCommandeFournisseur.dateLivraison <= :d2")
    ,@NamedQuery(name = "CommandeFournisseur.findForSocietePeriode", query = "SELECT c FROM CommandeFournisseur c WHERE c.ligneCommandeFournisseur.operateur.societe.id = :societe AND c.ligneCommandeFournisseur.dateLivraison >= :d AND c.ligneCommandeFournisseur.etat.code = :etat AND c.ligneCommandeFournisseur.dateLivraison <= :d2")
 , @NamedQuery(name = "CommandeFournisseur.findByLigneCommandeFournisseur", query = "SELECT c FROM CommandeFournisseur c WHERE c.ligneCommandeFournisseur.id = :ligne")})
public class CommandeFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix")
    private double prix;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_total")
    private double prixTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private Double quantite;
    @Column(name = "quantite_recu")
    private Double quantiteRecu;
    @Column(name = "etat")
    private Integer etat;
    @JoinColumn(name = "ligne_commande_fournisseur", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LigneCommandeFournisseur ligneCommandeFournisseur;
    @JoinColumn(name = "article", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tarticles article;

    public CommandeFournisseur() {
    }

    public CommandeFournisseur(Integer id) {
        this.id = id;
    }

    public CommandeFournisseur(Integer id, double prix, double prixTotal, Double quantite) {
        this.id = id;
        this.prix = prix;
        this.prixTotal = prixTotal;
        this.quantite = quantite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getQuantiteRecu() {
        return quantiteRecu;
    }

    public void setQuantiteRecu(Double quantiteRecu) {
        this.quantiteRecu = quantiteRecu;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public LigneCommandeFournisseur getLigneCommandeFournisseur() {
        return ligneCommandeFournisseur;
    }

    public void setLigneCommandeFournisseur(LigneCommandeFournisseur ligneCommandeFournisseur) {
        this.ligneCommandeFournisseur = ligneCommandeFournisseur;
    }

    public Tarticles getArticle() {
        return article;
    }

    public void setArticle(Tarticles article) {
        this.article = article;
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
        if (!(object instanceof CommandeFournisseur)) {
            return false;
        }
        CommandeFournisseur other = (CommandeFournisseur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.CommandeFournisseur[ id=" + id + " ]";
    }

}
