/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "tcommandes")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Tcommandes.findAll", query = "SELECT t FROM Tcommandes t WHERE t.ligne.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tcommandes.findByLigne", query = "SELECT t FROM Tcommandes t WHERE t.ligne.id = :ligne")
    ,
    @NamedQuery(name = "Tcommandes.findForArticle", query = "SELECT t FROM Tcommandes t WHERE t.ligne.datelivraison >= :d AND t.ligne.datelivraison <= :d2 AND t.ligne.etatc.code = :etat AND t.ligne.societe.id = :societe")
    ,
    @NamedQuery(name = "Tcommandes.findForCategorie", query = "SELECT t FROM Tcommandes t WHERE t.article.categorie.id = :id AND t.ligne.datelivraison >= :d AND t.ligne.etatc.code = :etat AND t.ligne.datelivraison <= :d2")
    ,
    @NamedQuery(name = "Tcommandes.findForArticlePeriode", query = "SELECT t FROM Tcommandes t WHERE t.article.id = :id AND t.ligne.datelivraison >= :d AND t.ligne.etatc.code = :etat AND t.ligne.datelivraison <= :d2")
    ,
     @NamedQuery(name = "Tcommandes.findForSocietePeriode", query = "SELECT t FROM Tcommandes t WHERE t.ligne.societe.id = :societe AND t.ligne.datelivraison >= :d AND t.ligne.etatc.code = :etat AND t.ligne.datelivraison <= :d2")
    ,
    @NamedQuery(name = "Tcommandes.findById", query = "SELECT t FROM Tcommandes t WHERE t.id = :id")
})
public class Tcommandes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "commantaire")
    private String commantaire;
    @Size(max = 255)
    @Column(name = "commantaire1")
    private String commantaire1;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private Double prix;
    @Column(name = "prix_total")
    private Double prixTotal;
    @Column(name = "remise")
    private Double remise = 0.0;
    @Column(name = "qt")
    private Integer qt = -1;
    @Column(name = "quantite")
    private Double quantite;
    @Column(name = "margefournisseur")
    private Double margefournisseur = 0.0;
    @Column(name = "margeclient")
    private Double margeclient = 0.0;
    @JoinColumn(name = "ligne", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tlignecommande ligne;
    @JoinColumn(name = "article", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tarticles article;

    public Tcommandes() {
    }

    public Tcommandes(Integer id) {
        this.id = id;
    }

    public Tcommandes(Integer id, Double margefournisseur, Double margeclient) {
        this.id = id;
        this.margefournisseur = margefournisseur;
        this.margeclient = margeclient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommantaire() {
        return commantaire;
    }

    public void setCommantaire(String commantaire) {
        this.commantaire = commantaire;
    }

    public String getCommantaire1() {
        return commantaire1;
    }

    public void setCommantaire1(String commantaire1) {
        this.commantaire1 = commantaire1;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getRemise() {
        return remise;
    }

    public void setRemise(Double remise) {
        this.remise = remise;
    }

    public Integer getQt() {
        return qt;
    }

    public void setQt(Integer qt) {
        this.qt = qt;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getMargeFournisseur() {
        return margefournisseur;
    }

    public void setMargeFournisseur(Double margefournisseur) {
        this.margefournisseur = margefournisseur;
    }

    public Double getMargeClient() {
        return margeclient;
    }

    public void setMargeClient(Double margeclient) {
        this.margeclient = margeclient;
    }

    public Tlignecommande getLigne() {
        return ligne;
    }

    public void setLigne(Tlignecommande ligne) {
        this.ligne = ligne;
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
        if (!(object instanceof Tcommandes)) {
            return false;
        }
        Tcommandes other = (Tcommandes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Tcommandes[ id=" + id + " ]";
    }

}
