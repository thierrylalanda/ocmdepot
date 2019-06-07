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
@Table(name = "compte_emballage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompteEmballage.findAll", query = "SELECT c FROM CompteEmballage c")
    , @NamedQuery(name = "CompteEmballage.findByClient", query = "SELECT c FROM CompteEmballage c WHERE c.client.id = :client")
    , @NamedQuery(name = "CompteEmballage.findByEmballage", query = "SELECT c FROM CompteEmballage c WHERE c.emballage.id = :emballage")
        , @NamedQuery(name = "CompteEmballage.findBySociete", query = "SELECT c FROM CompteEmballage c WHERE c.client.societe.id = :societe")
        , @NamedQuery(name = "CompteEmballage.findByClientAndEmballage", query = "SELECT c FROM CompteEmballage c WHERE c.client.id = :client AND c.emballage.id = :emballage")
    , @NamedQuery(name = "CompteEmballage.findByMontant", query = "SELECT c FROM CompteEmballage c WHERE c.montant = :montant")
    , @NamedQuery(name = "CompteEmballage.findByQuantite", query = "SELECT c FROM CompteEmballage c WHERE c.quantite = :quantite")})
public class CompteEmballage implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant")
    private Double montant;
    @Column(name = "quantite")
    private Double quantite;
    @JoinColumn(name = "emballage", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Emballage emballage;
    @JoinColumn(name = "client", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tclients client;

    public CompteEmballage() {
    }

    public CompteEmballage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Emballage getEmballage() {
        return emballage;
    }

    public void setEmballage(Emballage emballage) {
        this.emballage = emballage;
    }

    public Tclients getClient() {
        return client;
    }

    public void setClient(Tclients client) {
        this.client = client;
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
        if (!(object instanceof CompteEmballage)) {
            return false;
        }
        CompteEmballage other = (CompteEmballage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.CompteEmballage[ id=" + id + " ]";
    }

}
