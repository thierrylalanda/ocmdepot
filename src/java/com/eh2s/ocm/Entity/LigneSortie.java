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
@Table(name = "ligne_sortie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneSortie.findAll", query = "SELECT l FROM LigneSortie l")
    , @NamedQuery(name = "LigneSortie.findById", query = "SELECT l FROM LigneSortie l WHERE l.id = :id")
    , @NamedQuery(name = "LigneSortie.findBySociete", query = "SELECT l FROM LigneSortie l WHERE l.societe.id = :societe")
    , @NamedQuery(name = "LigneSortie.findByEtat", query = "SELECT l FROM LigneSortie l WHERE l.etat = :etat")
    , @NamedQuery(name = "LigneSortie.findByDateCreate", query = "SELECT l FROM LigneSortie l WHERE l.dateCreate = :dateCreate")
    , @NamedQuery(name = "LigneSortie.findByDateUpdate", query = "SELECT l FROM LigneSortie l WHERE l.dateUpdate = :dateUpdate")
    , @NamedQuery(name = "LigneSortie.findByMontantNet", query = "SELECT l FROM LigneSortie l WHERE l.montantNet = :montantNet")
    , @NamedQuery(name = "LigneSortie.findByMontantRestant", query = "SELECT l FROM LigneSortie l WHERE l.montantRestant = :montantRestant")
    , @NamedQuery(name = "LigneSortie.findByCommentaire", query = "SELECT l FROM LigneSortie l WHERE l.commentaire = :commentaire")
    , @NamedQuery(name = "LigneSortie.findByFournisseur", query = "SELECT l FROM LigneSortie l WHERE l.numeroBon.fournisseur.id = :fournisseur")
    , @NamedQuery(name = "LigneSortie.findByPeriodeSociete", query = "SELECT l FROM LigneSortie l WHERE l.societe.id = :societe AND FUNCTION('DATE',l.dateCreate) >= :d AND FUNCTION('DATE',l.dateCreate) <= :d1")
    , @NamedQuery(name = "LigneSortie.findByPeriodeFournisseur", query = "SELECT l FROM LigneSortie l WHERE l.numeroBon.fournisseur.id = :fournisseur AND FUNCTION('DATE',l.dateCreate) >= :d AND FUNCTION('DATE',l.dateCreate) <= :d1")
    , @NamedQuery(name = "LigneSortie.findByPeriodeSocieteAndEtat", query = "SELECT l FROM LigneSortie l WHERE l.societe.id = :societe AND l.etat = :etat AND FUNCTION('DATE',l.dateCreate) >= :d AND FUNCTION('DATE',l.dateCreate) <= :d1")
    , @NamedQuery(name = "LigneSortie.findByPeriodeFournisseurAndEtat", query = "SELECT l FROM LigneSortie l WHERE l.numeroBon.fournisseur.id = :fournisseur AND l.etat = :etat AND FUNCTION('DATE',l.dateCreate) >= :d AND FUNCTION('DATE',l.dateCreate) <= :d1")})
public class LigneSortie implements Serializable {

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
    @JoinColumn(name = "operateur", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tusers operateur;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Societe societe;
    @JoinColumn(name = "numero_bon", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LigneCommandeFournisseur numeroBon;
    @OneToMany(mappedBy = "ligneSortie", fetch = FetchType.LAZY)
    private List<SortieCaisse> sortietList;
    @JoinColumn(name = "fournisseur", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fournisseur fournisseur;

    public LigneSortie() {
    }

    public LigneSortie(Integer id) {
        this.id = id;
    }

    public LigneSortie(Integer id, double montantNet, double montantRestant) {
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

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    
    
    public Tusers getOperateur() {
        return operateur;
    }

    public void setOperateur(Tusers operateur) {
        this.operateur = operateur;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public LigneCommandeFournisseur getNumeroBon() {
        return numeroBon;
    }

    public void setNumeroBon(LigneCommandeFournisseur numeroBon) {
        this.numeroBon = numeroBon;
    }

    public List<SortieCaisse> getSortietList() {
        return sortietList;
    }

    public void setSortietList(List<SortieCaisse> sortietList) {
        this.sortietList = sortietList;
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
        if (!(object instanceof LigneSortie)) {
            return false;
        }
        LigneSortie other = (LigneSortie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.LigneSortie[ id=" + id + " ]";
    }

}
