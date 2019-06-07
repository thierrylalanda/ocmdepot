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
@Table(name = "sortie_caisse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SortieCaisse.findAll", query = "SELECT s FROM SortieCaisse s")
    , @NamedQuery(name = "SortieCaisse.findSortieSocieteToDay", query = "SELECT s FROM SortieCaisse s WHERE s.operateur.societe.id = :societe AND FUNCTION('DATE',s.date) = CURRENT_DATE")
    , @NamedQuery(name = "SortieCaisse.findSortieSocieteByPeriode", query = "SELECT s FROM SortieCaisse s WHERE s.operateur.societe.id = :societe AND FUNCTION('DATE',s.date) >= :d AND FUNCTION('DATE',s.date) <= :d1")
    , @NamedQuery(name = "SortieCaisse.findById", query = "SELECT s FROM SortieCaisse s WHERE s.id = :id")
    , @NamedQuery(name = "SortieCaisse.findByDate", query = "SELECT s FROM SortieCaisse s WHERE s.date = :date")
    , @NamedQuery(name = "SortieCaisse.findByMontant", query = "SELECT s FROM SortieCaisse s WHERE s.montant = :montant")
    , @NamedQuery(name = "SortieCaisse.findByOperateur", query = "SELECT s FROM SortieCaisse s WHERE s.operateur = :operateur")
    , @NamedQuery(name = "SortieCaisse.findBySociete", query = "SELECT s FROM SortieCaisse s WHERE s.operateur.societe.id = :societe")
    , @NamedQuery(name = "SortieCaisse.findByFacture", query = "SELECT s FROM SortieCaisse s WHERE s.facture.id = :facture")
    , @NamedQuery(name = "SortieCaisse.findByCommentaire", query = "SELECT s FROM SortieCaisse s WHERE s.commentaire = :commentaire")})
public class SortieCaisse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant")
    private double montant;
    @Column(name = "avance")
    private double avance = 0.0;
    @Column(name = "montant_restant")
    private double montantRestant = 0.0;
    @Column(name = "solde_caisse")
    private double soldeCaisse;
    @Size(max = 255)
    @Column(name = "commentaire")
    private String commentaire;
    @JoinColumn(name = "caisse", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caisse caisse;
    @JoinColumn(name = "facture", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LigneCommandeFournisseur facture;
    @JoinColumn(name = "operateur", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tusers operateur;
    @JoinColumn(name = "source", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SourceMouvementCaisse source;
    @JoinColumn(name = "ligne_sortie", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LigneSortie ligneSortie;

    public SortieCaisse() {
    }

    public SortieCaisse(Integer id) {
        this.id = id;
    }

    public SortieCaisse(Integer id, Date date, double montant) {
        this.id = id;
        this.date = date;
        this.montant = montant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Caisse getCaisse() {
        return caisse;
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

    public LigneSortie getLigneSortie() {
        return ligneSortie;
    }

    public void setLigneSortie(LigneSortie ligneSortie) {
        this.ligneSortie = ligneSortie;
    }

    public void setMontantRestant(double montantRestant) {
        this.montantRestant = montantRestant;
    }

    public double getSoldeCaisse() {
        return soldeCaisse;
    }

    public void setSoldeCaisse(double soldeCaisse) {
        this.soldeCaisse = soldeCaisse;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public LigneCommandeFournisseur getFacture() {
        return facture;
    }

    public void setFacture(LigneCommandeFournisseur facture) {
        this.facture = facture;
    }

    public Tusers getOperateur() {
        return operateur;
    }

    public void setOperateur(Tusers operateur) {
        this.operateur = operateur;
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
        if (!(object instanceof SortieCaisse)) {
            return false;
        }
        SortieCaisse other = (SortieCaisse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.SortieCaisse[ id=" + id + " ]";
    }
}
