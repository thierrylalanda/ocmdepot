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
 * @author Administrateur
 */
@Entity
@Table(name = "commande_emballage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommandeEmballage.findAll", query = "SELECT c FROM CommandeEmballage c")
    , @NamedQuery(name = "CommandeEmballage.findById", query = "SELECT c FROM CommandeEmballage c WHERE c.id = :id")
    , @NamedQuery(name = "CommandeEmballage.findByPrix", query = "SELECT c FROM CommandeEmballage c WHERE c.prix = :prix")
    , @NamedQuery(name = "CommandeEmballage.findByPrixTotal", query = "SELECT c FROM CommandeEmballage c WHERE c.prixTotal = :prixTotal")
    , @NamedQuery(name = "CommandeEmballage.findByQt", query = "SELECT c FROM CommandeEmballage c WHERE c.qt = :qt")
    , @NamedQuery(name = "CommandeEmballage.findByQuantite", query = "SELECT c FROM CommandeEmballage c WHERE c.quantite = :quantite")})
public class CommandeEmballage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private Double prix;
    @Column(name = "prix_total")
    private Double prixTotal;
    @Column(name = "remise")
    private Double remise=0.0;
    @Column(name = "qt")
    private Double qt;
    @Column(name = "quantite")
    private Double quantite;
    @JoinColumn(name = "ligne", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tlignecommande ligne;
    @JoinColumn(name = "emballage", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Emballage emballage;

    public CommandeEmballage() {
    }

    public CommandeEmballage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    
    
    public Double getQt() {
        return qt;
    }

    public void setQt(Double qt) {
        this.qt = qt;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Tlignecommande getLigne() {
        return ligne;
    }

    public void setLigne(Tlignecommande ligne) {
        this.ligne = ligne;
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
        if (!(object instanceof CommandeEmballage)) {
            return false;
        }
        CommandeEmballage other = (CommandeEmballage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.CommandeEmballage[ id=" + id + " ]";
    }

}
