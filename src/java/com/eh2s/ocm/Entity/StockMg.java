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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi01
 */
@Entity
@Table(name = "stock_mg")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "StockMg.findAll", query = "SELECT s FROM StockMg s")
    , @NamedQuery(name = "StockMg.findById", query = "SELECT s FROM StockMg s WHERE s.id = :id")
    , @NamedQuery(name = "StockMg.findByMagasin", query = "SELECT s FROM StockMg s WHERE s.magasin.id = :id")
    , @NamedQuery(name = "StockMg.findByStock", query = "SELECT s FROM StockMg s WHERE s.stock = :stock")
    , @NamedQuery(name = "StockMg.findByPrix", query = "SELECT s FROM StockMg s WHERE s.prix = :prix")
    , @NamedQuery(name = "StockMg.findByPrixTotal", query = "SELECT s FROM StockMg s WHERE s.prixTotal = :prixTotal")
        , @NamedQuery(name = "StockMg.findByArticleMagasin", query = "SELECT s FROM StockMg s WHERE s.article.id = :article AND s.magasin.id = :magasin")
    , @NamedQuery(name = "StockMg.findByStockV", query = "SELECT s FROM StockMg s WHERE s.stockV = :stockV")})
public class StockMg implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "stock")
    private Double stock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private Double prix;
    @Column(name = "prix_total")
    private Double prixTotal;
    @Column(name = "stock_v")
    private Double stockV;
    @JoinColumn(name = "magasin", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Magasin magasin;
    @JoinColumn(name = "article", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tarticles article;

    public StockMg() {
    }

    public StockMg(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
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

    public Double getStockV() {
        return stockV;
    }

    public void setStockV(Double stockV) {
        this.stockV = stockV;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
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
        if (!(object instanceof StockMg)) {
            return false;
        }
        StockMg other = (StockMg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.StockMg[ id=" + id + " ]";
    }
}
