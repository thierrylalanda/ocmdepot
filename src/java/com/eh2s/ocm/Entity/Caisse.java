/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "caisse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caisse.findAll", query = "SELECT c FROM Caisse c")
    , @NamedQuery(name = "Caisse.findById", query = "SELECT c FROM Caisse c WHERE c.id = :id")
    , @NamedQuery(name = "Caisse.findByMontant", query = "SELECT c FROM Caisse c WHERE c.montant = :montant")
    , @NamedQuery(name = "Caisse.findBySociete", query = "SELECT c FROM Caisse c WHERE c.societe.id = :societe")
    , @NamedQuery(name = "Caisse.findByToDay", query = "SELECT c FROM Caisse c WHERE c.societe.id = :societe AND FUNCTION('DATE',c.date) = CURRENT_DATE")
        , @NamedQuery(name = "Caisse.findBySocieteAndDate", query = "SELECT c FROM Caisse c WHERE c.societe.id = :societe AND FUNCTION('DATE',c.date) = :d")
    , @NamedQuery(name = "Caisse.findByEtatCaisse", query = "SELECT c FROM Caisse c WHERE c.etatCaisse = :etatCaisse")
    , @NamedQuery(name = "Caisse.findByDate", query = "SELECT c FROM Caisse c WHERE c.date = :date")})
public class Caisse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant")
    private double montant;
    @Column(name = "montant_verser")
    private double montantVerser = 0.0;
    @Column(name = "ecart")
    private double ecart = 0.0;
    @Size(max = 255)
    @Column(name = "commentaire")
    private String commentaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "etat_caisse")
    private int etatCaisse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant_restant")
    private Double montantRestant;
    @Column(name = "date_fermeture")
    @Temporal(TemporalType.DATE)
    private Date dateFermeture;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Societe societe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caisse", fetch = FetchType.LAZY)
    private List<SortieCaisse> sortieCaisseList;
    @OneToMany(mappedBy = "caisse", fetch = FetchType.LAZY)
    private List<Account> accountList;

    public Caisse() {
    }

    public Caisse(Integer id) {
        this.id = id;
    }

    public Caisse(Integer id, double montant, int etatCaisse, Date date) {
        this.id = id;
        this.montant = montant;
        this.etatCaisse = etatCaisse;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getMontantVerser() {
        return montantVerser;
    }

    public void setMontantVerser(double montantVerser) {
        this.montantVerser = montantVerser;
    }

    public double getEcart() {
        return ecart;
    }

    public void setEcart(double ecart) {
        this.ecart = ecart;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    
    
    public int getEtatCaisse() {
        return etatCaisse;
    }

    public void setEtatCaisse(int etatCaisse) {
        this.etatCaisse = etatCaisse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Double montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public List<SortieCaisse> getSortieCaisseList() {
        return sortieCaisseList;
    }

    public void setSortieCaisseList(List<SortieCaisse> sortieCaisseList) {
        this.sortieCaisseList = sortieCaisseList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
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
        if (!(object instanceof Caisse)) {
            return false;
        }
        Caisse other = (Caisse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Caisse[ id=" + id + " ]";
    }
}
