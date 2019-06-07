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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id")
    , @NamedQuery(name = "Account.findByMontantInit", query = "SELECT a FROM Account a WHERE a.montantInit = :montantInit")
    , @NamedQuery(name = "Account.findByAvance", query = "SELECT a FROM Account a WHERE a.avance = :avance")
    , @NamedQuery(name = "Account.findAccountSocieteToDay", query = "SELECT a FROM Account a WHERE a.operateur.societe.id = :societe AND FUNCTION('DATE',a.date) = CURRENT_DATE")
    , @NamedQuery(name = "Account.findAccountSocieteByPeriode", query = "SELECT a FROM Account a WHERE a.operateur.societe.id = :societe AND FUNCTION('DATE',a.date) >= :d AND FUNCTION('DATE',a.date) <= :d1")
    , @NamedQuery(name = "Account.findByOperateur", query = "SELECT a FROM Account a WHERE a.operateur = :operateur")
    , @NamedQuery(name = "Account.findBySociete", query = "SELECT a FROM Account a WHERE a.operateur.societe.id = :societe")
    , @NamedQuery(name = "Account.findByMontantRestant", query = "SELECT a FROM Account a WHERE a.montantRestant = :montantRestant")
    , @NamedQuery(name = "Account.findByDate", query = "SELECT a FROM Account a WHERE a.date = :date")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant_init")
    private double montantInit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "avance")
    private double avance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant_restant")
    private double montantRestant;
    @Column(name = "solde_caisse")
    private double soldeCaisse = 0.0;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 255)
    @Column(name = "commentaire")
    private String commentaire;
    @JoinColumn(name = "ligne_account", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LigneAccount ligneAccount;
    @JoinColumn(name = "operateur", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tusers operateur;
    @JoinColumn(name = "caisse", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Caisse caisse;
    @JoinColumn(name = "source", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SourceMouvementCaisse source;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Account(Integer id, double montantInit, double avance, double montantRestant, Date date) {
        this.id = id;
        this.montantInit = montantInit;
        this.avance = avance;
        this.montantRestant = montantRestant;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMontantInit() {
        return montantInit;
    }

    public void setMontantInit(double montantInit) {
        this.montantInit = montantInit;
    }

    public double getAvance() {
        return avance;
    }

    public void setAvance(double avance) {
        this.avance = avance;
    }

    public double getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(double montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSoldeCaisse() {
        return soldeCaisse;
    }

    public void setSoldeCaisse(double soldeCaisse) {
        this.soldeCaisse = soldeCaisse;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LigneAccount getLigneAccount() {
        return ligneAccount;
    }

    public void setLigneAccount(LigneAccount ligneAccount) {
        this.ligneAccount = ligneAccount;
    }

    public Tusers getOperateur() {
        return operateur;
    }

    public void setOperateur(Tusers operateur) {
        this.operateur = operateur;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public SourceMouvementCaisse getSource() {
        return source;
    }

    public void setSource(SourceMouvementCaisse source) {
        this.source = source;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Account[ id=" + id + " ]";
    }

}
