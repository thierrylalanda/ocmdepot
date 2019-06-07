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

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "ligne_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneAccount.findAll", query = "SELECT l FROM LigneAccount l")
    , @NamedQuery(name = "LigneAccount.findById", query = "SELECT l FROM LigneAccount l WHERE l.id = :id")
    , @NamedQuery(name = "LigneAccount.findByEtat", query = "SELECT l FROM LigneAccount l WHERE l.etat = :etat")
        , @NamedQuery(name = "LigneAccount.findByOperateur", query = "SELECT l FROM LigneAccount l WHERE l.operateur = :operateur")
    , @NamedQuery(name = "LigneAccount.findByDateCreate", query = "SELECT l FROM LigneAccount l WHERE l.dateCreate = :dateCreate")
    , @NamedQuery(name = "LigneAccount.findByDateUpdate", query = "SELECT l FROM LigneAccount l WHERE l.dateUpdate = :dateUpdate")
    , @NamedQuery(name = "LigneAccount.findByMontantNet", query = "SELECT l FROM LigneAccount l WHERE l.montantNet = :montantNet")
    , @NamedQuery(name = "LigneAccount.findBySociete", query = "SELECT l FROM LigneAccount l WHERE l.societe.id = :societe")
    , @NamedQuery(name = "LigneAccount.findByClient", query = "SELECT l FROM LigneAccount l WHERE l.client.id = :client")
    , @NamedQuery(name = "LigneAccount.findByPeriodeSociete", query = "SELECT l FROM LigneAccount l WHERE l.societe.id = :societe AND FUNCTION('DATE',l.dateCreate) >= :d AND FUNCTION('DATE',l.dateCreate) <= :d1")
    , @NamedQuery(name = "LigneAccount.findByPeriodeClient", query = "SELECT l FROM LigneAccount l WHERE l.client.id = :client AND FUNCTION('DATE',l.dateCreate) >= :d AND FUNCTION('DATE',l.dateCreate) <= :d1")
    , @NamedQuery(name = "LigneAccount.findByPeriodeSocieteAndEtat", query = "SELECT l FROM LigneAccount l WHERE l.societe.id = :societe AND l.etat = :etat AND FUNCTION('DATE',l.dateCreate) >= :d AND FUNCTION('DATE',l.dateCreate) <= :d1")
    , @NamedQuery(name = "LigneAccount.findByPeriodeClientAndEtat", query = "SELECT l FROM LigneAccount l WHERE l.client.id = :client AND l.etat = :etat AND FUNCTION('DATE',l.dateCreate) >= :d AND FUNCTION('DATE',l.dateCreate) <= :d1")
        , @NamedQuery(name = "LigneAccount.findByMontantRestant", query = "SELECT l FROM LigneAccount l WHERE l.montantRestant = :montantRestant")})
public class LigneAccount implements Serializable {

     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "etat")
    private Integer etat;
    @Column(name = "date_create")
    @Temporal(TemporalType.DATE)
    private Date dateCreate;
    @Column(name = "date_update")
    @Temporal(TemporalType.DATE)
    private Date dateUpdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant_net")
    private double montantNet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant_restant")
    private double montantRestant;
    @Size(max = 255)
    @Column(name = "commentaire")
    private String commentaire;
    @JoinColumn(name = "client", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tclients client;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Societe societe;
    @JoinColumn(name = "ligne_commande", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tlignecommande ligneCommande;
    @JoinColumn(name = "operateur", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tusers operateur;
    @OneToMany(mappedBy = "ligneAccount", fetch = FetchType.LAZY)
    private List<Account> accountList;

    public LigneAccount() {
    }

    public LigneAccount(Integer id) {
        this.id = id;
    }

    public LigneAccount(Integer id, double montantNet, double montantRestant) {
        this.id = id;
        this.montantNet = montantNet;
        this.montantRestant = montantRestant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public double getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(double montantNet) {
        this.montantNet = montantNet;
    }

    public double getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(double montantRestant) {
        this.montantRestant = montantRestant;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Tclients getClient() {
        return client;
    }

    public void setClient(Tclients client) {
        this.client = client;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Tlignecommande getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(Tlignecommande ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    public Tusers getOperateur() {
        return operateur;
    }

    public void setOperateur(Tusers operateur) {
        this.operateur = operateur;
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
        if (!(object instanceof LigneAccount)) {
            return false;
        }
        LigneAccount other = (LigneAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.LigneAccount[ id=" + id + " ]";
    }
    

}
