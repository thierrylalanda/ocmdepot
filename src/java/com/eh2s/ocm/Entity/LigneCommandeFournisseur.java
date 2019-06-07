/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "ligne_commande_fournisseur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneCommandeFournisseur.findAll", query = "SELECT l FROM LigneCommandeFournisseur l")
    , @NamedQuery(name = "LigneCommandeFournisseur.findBySociete", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.operateur.societe.id = :societe")
    ,@NamedQuery(name = "LigneCommandeFournisseur.findByPeriodeBySociete", query = "SELECT l FROM LigneCommandeFournisseur l WHERE FUNCTION('DATE',l.dateCommande) >= :d1 AND FUNCTION('DATE',l.dateCommande) <= :d2 AND l.operateur.societe.id = :societe")
    ,@NamedQuery(name = "LigneCommandeFournisseur.findByPeriodeByFournisseur", query = "SELECT l FROM LigneCommandeFournisseur l WHERE FUNCTION('DATE',l.dateCommande) >= :d1 AND FUNCTION('DATE',l.dateCommande) <= :d2 AND l.fournisseur.id = :fournisseur")
    , @NamedQuery(name = "LigneCommandeFournisseur.findByOperateur", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.operateur.id = :operateur")
    , @NamedQuery(name = "LigneCommandeFournisseur.findByFournisseur", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.fournisseur.id = :fournisseur")
    , @NamedQuery(name = "LigneCommandeFournisseur.findById", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.id = :id")
    , @NamedQuery(name = "LigneCommandeFournisseur.findByCommentaire", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.commentaire = :commentaire")
    , @NamedQuery(name = "LigneCommandeFournisseur.findByDateCommande", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.dateCommande = :dateCommande")
    , @NamedQuery(name = "LigneCommandeFournisseur.findByDateLivraison", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.dateLivraison = :dateLivraison")
    , @NamedQuery(name = "LigneCommandeFournisseur.findByDateModif", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.dateModif = :dateModif")
    , @NamedQuery(name = "LigneCommandeFournisseur.findByPrixTotal", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.prixTotal = :prixTotal")
    , @NamedQuery(name = "LigneCommandeFournisseur.findByQuantiteTotal", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.quantiteTotal = :quantiteTotal")
    , @NamedQuery(name = "LigneCommandeFournisseur.findByTransport", query = "SELECT l FROM LigneCommandeFournisseur l WHERE l.transport = :transport")})
public class LigneCommandeFournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "commentaire")
    private String commentaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_commande")
    @Temporal(TemporalType.DATE)
    private Date dateCommande;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_livraison")
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    @Column(name = "date_modif")
    @Temporal(TemporalType.DATE)
    private Date dateModif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_total")
    private double prixTotal;
    @Column(name = "quantite_total")
    private Double quantiteTotal;
    @Column(name = "transport")
    private Integer transport = 0;
    @Column(name = "statut")
    private Integer statut = 0;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ligneCommandeFournisseur", fetch = FetchType.LAZY)
    private List<CommandeFournisseur> commandeFournisseurList;
    @OneToMany(mappedBy = "facture", fetch = FetchType.LAZY)
    private List<SortieCaisse> sortieCaisseList;
    @JoinColumn(name = "operateur", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tusers operateur;
    @JoinColumn(name = "etat", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tetatc etat;
    @JoinColumn(name = "fournisseur", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Fournisseur fournisseur;
    @OneToMany(mappedBy = "numeroBon", fetch = FetchType.LAZY)
    private List<LigneSortie> ligneSortieList;
    @Transient
    private String datecc;
    @Transient
    private String dateliv;
    
    public String getDatecc() {
        datecc = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dateCommande);
        return datecc;
    }

    public String getDateliv() {
        dateliv = new SimpleDateFormat("dd/MM/yyyy").format(dateLivraison);
        return dateliv;
    }


    public LigneCommandeFournisseur() {
    }

    public LigneCommandeFournisseur(Integer id) {
        this.id = id;
    }

    public LigneCommandeFournisseur(Integer id, Date dateCommande, Date dateLivraison, double prixTotal) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.prixTotal = prixTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Integer getStatut() {
        return statut;
    }

    public void setStatut(Integer statut) {
        this.statut = statut;
    }

    public Double getQuantiteTotal() {
        return quantiteTotal;
    }

    public void setQuantiteTotal(Double quantiteTotal) {
        this.quantiteTotal = quantiteTotal;
    }

    public Integer getTransport() {
        return transport;
    }

    public void setTransport(Integer transport) {
        this.transport = transport;
    }

    public List<CommandeFournisseur> getCommandeFournisseurList() {
        return commandeFournisseurList;
    }

    public void setCommandeFournisseurList(List<CommandeFournisseur> commandeFournisseurList) {
        this.commandeFournisseurList = commandeFournisseurList;
    }

    public List<SortieCaisse> getSortieCaisseList() {
        return sortieCaisseList;
    }

    public void setSortieCaisseList(List<SortieCaisse> sortieCaisseList) {
        this.sortieCaisseList = sortieCaisseList;
    }

    public Tusers getOperateur() {
        return operateur;
    }

    public void setOperateur(Tusers operateur) {
        this.operateur = operateur;
    }

    public Tetatc getEtat() {
        return etat;
    }

    public void setEtat(Tetatc etat) {
        this.etat = etat;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<LigneSortie> getLigneSortieList() {
        return ligneSortieList;
    }

    public void setLigneSortieList(List<LigneSortie> ligneSortieList) {
        this.ligneSortieList = ligneSortieList;
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
        if (!(object instanceof LigneCommandeFournisseur)) {
            return false;
        }
        LigneCommandeFournisseur other = (LigneCommandeFournisseur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.LigneCommandeFournisseur[ id=" + id + " ]";
    }

}
