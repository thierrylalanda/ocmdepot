/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "compte_ristourne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompteRistourne.findAll", query = "SELECT c FROM CompteRistourne c")
    , @NamedQuery(name = "CompteRistourne.findById", query = "SELECT c FROM CompteRistourne c WHERE c.id = :id")
    , @NamedQuery(name = "CompteRistourne.findByDateDebut", query = "SELECT c FROM CompteRistourne c WHERE c.dateDebut = :dateDebut")
        , @NamedQuery(name = "CompteRistourne.findByClient", query = "SELECT c FROM CompteRistourne c WHERE c.client.id = :client")
         , @NamedQuery(name = "CompteRistourne.findBySociete", query = "SELECT c FROM CompteRistourne c WHERE c.client.societe.id = :societe")
    , @NamedQuery(name = "CompteRistourne.findByDateFin", query = "SELECT c FROM CompteRistourne c WHERE c.dateFin = :dateFin")
    , @NamedQuery(name = "CompteRistourne.findByEtat", query = "SELECT c FROM CompteRistourne c WHERE c.etat = :etat")
    , @NamedQuery(name = "CompteRistourne.findByMontant", query = "SELECT c FROM CompteRistourne c WHERE c.montant = :montant")})
public class CompteRistourne implements Serializable {

     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Column(name = "etat")
    private Integer etat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant")
    private Double montant;
    @JoinColumn(name = "client", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tclients client;

    public CompteRistourne() {
    }

    public CompteRistourne(Integer id) {
        this.id = id;
    }

    public CompteRistourne(Integer id, Date dateDebut) {
        this.id = id;
        this.dateDebut = dateDebut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
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
        if (!(object instanceof CompteRistourne)) {
            return false;
        }
        CompteRistourne other = (CompteRistourne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.CompteRistourne[ id=" + id + " ]";
    }
    
}
