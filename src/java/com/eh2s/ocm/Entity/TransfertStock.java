/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "transfert_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransfertStock.findAll", query = "SELECT t FROM TransfertStock t")
    , @NamedQuery(name = "TransfertStock.findById", query = "SELECT t FROM TransfertStock t WHERE t.id = :id")
    , @NamedQuery(name = "TransfertStock.findByLigne", query = "SELECT t FROM TransfertStock t WHERE t.ligneTransfert.id = :id")
    , @NamedQuery(name = "TransfertStock.findByArticle", query = "SELECT t FROM TransfertStock t WHERE t.article = :article")
    , @NamedQuery(name = "TransfertStock.findByQuantite", query = "SELECT t FROM TransfertStock t WHERE t.quantite = :quantite")})
public class TransfertStock implements Serializable {

     private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private int quantite;
    @JoinColumn(name = "ligne_transfert", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LigneTransfertStock ligneTransfert;
    @JoinColumn(name = "article", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tarticles article;

    public TransfertStock() {
    }

    public TransfertStock(Integer id) {
        this.id = id;
    }

    public TransfertStock(Integer id, int quantite) {
        this.id = id;
        this.quantite = quantite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public LigneTransfertStock getLigneTransfert() {
        return ligneTransfert;
    }

    public void setLigneTransfert(LigneTransfertStock ligneTransfert) {
        this.ligneTransfert = ligneTransfert;
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
        if (!(object instanceof TransfertStock)) {
            return false;
        }
        TransfertStock other = (TransfertStock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.TransfertStock[ id=" + id + " ]";
    }

}
