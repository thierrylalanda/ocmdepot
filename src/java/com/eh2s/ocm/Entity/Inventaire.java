/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi01
 */
@Entity
@Table(name = "inventaire")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Inventaire.findAll", query = "SELECT i FROM Inventaire i")
    , @NamedQuery(name = "Inventaire.findById", query = "SELECT i FROM Inventaire i WHERE i.id = :id")
    , @NamedQuery(name = "Inventaire.findByQtAvant", query = "SELECT i FROM Inventaire i WHERE i.qtAvant = :qtAvant")
    , @NamedQuery(name = "Inventaire.findByQtApres", query = "SELECT i FROM Inventaire i WHERE i.qtApres = :qtApres")
    , @NamedQuery(name = "Inventaire.findByEcartQt", query = "SELECT i FROM Inventaire i WHERE i.ecartQt = :ecartQt")
    , @NamedQuery(name = "Inventaire.findByMagasinByPeriodeByArticle", query = "SELECT i FROM Inventaire i WHERE i.article.id = :article AND i.ligneInv.magasin.id = :magasin AND FUNCTION('DATE',i.ligneInv.dateInv) >= :d AND FUNCTION('DATE',i.ligneInv.dateInv) <= :d1")
    , @NamedQuery(name = "Inventaire.findByMagasinByPeriodeByCategorie", query = "SELECT i FROM Inventaire i WHERE i.article.categorie.id = :categorie AND i.ligneInv.magasin.id = :magasin AND FUNCTION('DATE',i.ligneInv.dateInv) >= :d AND FUNCTION('DATE',i.ligneInv.dateInv) <= :d1")
    , @NamedQuery(name = "Inventaire.findBySocieteByPeriodeByArticle", query = "SELECT i FROM Inventaire i WHERE i.article.id = :article AND i.ligneInv.societe.id = :societe AND FUNCTION('DATE',i.ligneInv.dateInv) >= :d AND FUNCTION('DATE',i.ligneInv.dateInv) <= :d1")
        , @NamedQuery(name = "Inventaire.findBySocieteByPeriodeByEmballage", query = "SELECT i FROM Inventaire i WHERE i.emballage.id = :emballage AND i.ligneInv.societe.id = :societe AND FUNCTION('DATE',i.ligneInv.dateInv) >= :d AND FUNCTION('DATE',i.ligneInv.dateInv) <= :d1")
    , @NamedQuery(name = "Inventaire.findBySocieteByPeriodeByCategorie", query = "SELECT i FROM Inventaire i WHERE i.article.categorie.id = :categorie AND i.ligneInv.societe.id = :societe AND FUNCTION('DATE',i.ligneInv.dateInv) >= :d AND FUNCTION('DATE',i.ligneInv.dateInv) <= :d1")})
public class Inventaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qt_avant")
    private Double qtAvant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private Double quantite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qt_apres")
    private Double qtApres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ecart_qt")
    private Double ecartQt;
    @Column(name = "type")
    private Integer type;
    @JoinColumn(name = "article", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tarticles article;
    @JoinColumn(name = "ligne_inv", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LigneInventaire ligneInv;
    @JoinColumn(name = "emballage", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Emballage emballage;

    public Inventaire() {
    }

    public Inventaire(Integer id) {
        this.id = id;
    }

    public Inventaire(Integer id, Double qtAvant, Double quantite, Double qtApres, Double ecartQt) {
        this.id = id;
        this.qtAvant = qtAvant;
        this.quantite = quantite;
        this.qtApres = qtApres;
        this.ecartQt = ecartQt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQtAvant() {
        return qtAvant;
    }

    public void setQtAvant(Double qtAvant) {
        this.qtAvant = qtAvant;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getQtApres() {
        return qtApres;
    }

    public void setQtApres(Double qtApres) {
        this.qtApres = qtApres;
    }

    public Double getEcartQt() {
        return ecartQt;
    }

    public void setEcartQt(Double ecartQt) {
        this.ecartQt = ecartQt;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Tarticles getArticle() {
        return article;
    }

    public void setArticle(Tarticles article) {
        this.article = article;
    }

    public LigneInventaire getLigneInv() {
        return ligneInv;
    }

    public void setLigneInv(LigneInventaire ligneInv) {
        this.ligneInv = ligneInv;
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
        if (!(object instanceof Inventaire)) {
            return false;
        }
        Inventaire other = (Inventaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Inventaire[ id=" + id + " ]";
    }
    
}
