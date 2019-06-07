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
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * @author messi01
 */
@Entity
@Table(name = "tlignecommande")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Tlignecommande.findAll", query = "SELECT t FROM Tlignecommande t WHERE t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tlignecommande.findAllBySociete", query = "SELECT t FROM Tlignecommande t WHERE t.societe.id = :id")
    ,
    @NamedQuery(name = "Tlignecommande.findById", query = "SELECT t FROM Tlignecommande t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tlignecommande.findByTourner", query = "SELECT t FROM Tlignecommande t WHERE t.tourner.numc = :numt")
    ,
    @NamedQuery(name = "Tlignecommande.findByTournerByPeriodeByStatut", query = "SELECT t FROM Tlignecommande t WHERE t.datelivraison >= :d1 AND t.datelivraison <= :d2 AND t.tourner.numc = :numt AND t.etatc.code = :code")
    ,
    @NamedQuery(name = "Tlignecommande.findByTournerByPeriode", query = "SELECT t FROM Tlignecommande t WHERE t.datelivraison >= :d1 AND t.datelivraison <= :d2 AND t.tourner.numc = :numt")
    ,
    @NamedQuery(name = "Tlignecommande.findByNumc", query = "SELECT t FROM Tlignecommande t WHERE t.numc = :numc")
    ,
    @NamedQuery(name = "Tlignecommande.findByClient", query = "SELECT t FROM Tlignecommande t WHERE t.client.id = :clit")
    ,
    @NamedQuery(name = "Tlignecommande.findByDatec", query = "SELECT t FROM Tlignecommande t WHERE FUNCTION('DATE',t.datec) >= :d1 AND FUNCTION('DATE',t.datec) <= :d2 AND t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tlignecommande.findByDatec01", query = "SELECT t FROM Tlignecommande t WHERE t.datelivraison >= :d1 AND t.datelivraison <= :d2 AND t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tlignecommande.findByPeriode", query = "SELECT t FROM Tlignecommande t WHERE t.datelivraison >= :d1 AND t.datelivraison <= :d2 AND t.societe.id = :imma AND t.etatc.code = :code")
    ,
    @NamedQuery(name = "Tlignecommande.findByDateByClient", query = "SELECT t FROM Tlignecommande t WHERE FUNCTION('DATE',t.datec) >= :d1 AND FUNCTION('DATE',t.datec) <= :d2 AND t.client.id = :cli")
    ,
    @NamedQuery(name = "Tlignecommande.findByDateByClient01", query = "SELECT t FROM Tlignecommande t WHERE t.datelivraison >= :d1 AND t.datelivraison <= :d2 AND t.client.id = :cli")
    ,
    @NamedQuery(name = "Tlignecommande.findForFeuilleRoute", query = "SELECT t FROM Tlignecommande t WHERE t.datelivraison >= :d1 AND t.datelivraison <= :d2 AND t.client.id = :cli AND t.etatc.code = :code")
    ,
    @NamedQuery(name = "Tlignecommande.findByDatelivraison", query = "SELECT t FROM Tlignecommande t WHERE t.datelivraison = :datelivraison")
    ,
    @NamedQuery(name = "Tlignecommande.findByEtatc", query = "SELECT t FROM Tlignecommande t WHERE t.etatc = :etatc")
    ,
    @NamedQuery(name = "Tlignecommande.findByQtotal", query = "SELECT t FROM Tlignecommande t WHERE t.qtotal = :qtotal")
    ,
    @NamedQuery(name = "Tlignecommande.findByPrixtotal", query = "SELECT t FROM Tlignecommande t WHERE t.prixtotal = :prixtotal")
    ,
    @NamedQuery(name = "Tlignecommande.findByIsModif", query = "SELECT t FROM Tlignecommande t WHERE t.isModif = :isModif")
    ,
    @NamedQuery(name = "Tlignecommande.findByDatemodif", query = "SELECT t FROM Tlignecommande t WHERE t.datemodif = :datemodif")
    ,
    @NamedQuery(name = "Tlignecommande.findByVenteAnnuel", query = "SELECT t FROM Tlignecommande t WHERE FUNCTION('YEAR',t.datelivraison) = :annee AND t.societe.id = :id AND t.etatc.code = :code")
    ,
    @NamedQuery(name = "Tlignecommande.findByCommentaire", query = "SELECT t FROM Tlignecommande t WHERE t.commentaire = :commentaire")})
public class Tlignecommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "datec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datec;
    @Column(name = "datelivraison")
    @Temporal(TemporalType.DATE)
    private Date datelivraison;
    @Column(name = "datemodif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datemodif;
    @Column(name = "is_modif")
    private Integer isModif;
    @Column(name = "numc")
    private Integer numc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prixtotal")
    private Double prixtotal;
    @Column(name = "remise")
    private Double remise = 0.0;
    @Column(name = "qtotal")
    private Double qtotal;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "signature")
    private String signature;
    @Column(name = "margefournisseur")
    private Double margefournisseur = 0.0;
    @Column(name = "margeclient")
    private Double margeclient = 0.0;
    @Column(name = "cassier")
    private Double cassier = 0.0;
    @Size(max = 100)
    @Column(name = "operateur")
    private String operateur;
    @Column(name = "transport")
    private Integer transport;
    @Column(name = "statut")
    private Integer statut=0;
    @JoinColumn(name = "etatc", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tetatc etatc;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Societe societe;
    @JoinColumn(name = "client", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tclients client;
    @JoinColumn(name = "tourner", referencedColumnName = "societe")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tourner tourner;
    @JoinColumn(name = "preselleur", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tusers preselleur;
    @OneToMany(mappedBy = "ligne", fetch = FetchType.LAZY)
    private List<CommandeEmballage> commandeEmballageList;
    @OneToMany(mappedBy = "ligneCommande", fetch = FetchType.LAZY)
    private List<LigneAccount> ligneAccountList;
    @OneToMany(mappedBy = "ligne", fetch = FetchType.LAZY)
    private List<Tcommandes> tcommandesList;
    @Transient
    private String datecc;
    @Transient
    private String dateliv;

    public Tlignecommande() {
    }

    public Tlignecommande(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getDatecc() {
        datecc = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(datec);
        return datecc;
    }

    public String getDateliv() {
        dateliv = new SimpleDateFormat("dd/MM/yyyy").format(datelivraison);
        return dateliv;
    }

    public Integer getStatut() {
        return statut;
    }

    public void setStatut(Integer statut) {
        this.statut = statut;
    }

    
    
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public Date getDatelivraison() {
        return datelivraison;
    }

    public void setDatelivraison(Date datelivraison) {
        this.datelivraison = datelivraison;
    }

    public Date getDatemodif() {
        return datemodif;
    }

    public void setDatemodif(Date datemodif) {
        this.datemodif = datemodif;
    }

    public Integer getIsModif() {
        return isModif;
    }

    public void setIsModif(Integer isModif) {
        this.isModif = isModif;
    }

    public Integer getNumc() {
        return numc;
    }

    public void setNumc(Integer numc) {
        this.numc = numc;
    }

    public Double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(Double prixtotal) {
        this.prixtotal = prixtotal;
    }

    public Double getRemise() {
        return remise;
    }

    public void setRemise(Double remise) {
        this.remise = remise;
    }

    public Double getQtotal() {
        return qtotal;
    }

    public void setQtotal(Double qtotal) {
        this.qtotal = qtotal;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Double getMargeFournisseur() {
        return margefournisseur;
    }

    public void setMargeFournisseur(Double margefournisseur) {
        this.margefournisseur = margefournisseur;
    }

    public Double getMargeClient() {
        return margeclient;
    }

    public void setMargeClient(Double margeclient) {
        this.margeclient = margeclient;
    }

    public Double getCassier() {
        return cassier;
    }

    public void setCassier(Double cassier) {
        this.cassier = cassier;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public Integer getTransport() {
        return transport;
    }

    public void setTransport(Integer transport) {
        this.transport = transport;
    }

    public Tetatc getEtatc() {
        return etatc;
    }

    public void setEtatc(Tetatc etatc) {
        this.etatc = etatc;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Tclients getClient() {
        return client;
    }

    public void setClient(Tclients client) {
        this.client = client;
    }

    public Tourner getTourner() {
        return tourner;
    }

    public void setTourner(Tourner tourner) {
        this.tourner = tourner;
    }

    public Tusers getPreselleur() {
        return preselleur;
    }

    public void setPreselleur(Tusers preselleur) {
        this.preselleur = preselleur;
    }

    public List<CommandeEmballage> getCommandeEmballageList() {
        return commandeEmballageList;
    }

    public void setCommandeEmballageList(List<CommandeEmballage> commandeEmballageList) {
        this.commandeEmballageList = commandeEmballageList;
    }

    public List<LigneAccount> getLigneAccountList() {
        return ligneAccountList;
    }

    public void setLigneAccountList(List<LigneAccount> ligneAccountList) {
        this.ligneAccountList = ligneAccountList;
    }

    public List<Tcommandes> getTcommandesList() {
        return tcommandesList;
    }

    public void setTcommandesList(List<Tcommandes> tcommandesList) {
        this.tcommandesList = tcommandesList;
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
        if (!(object instanceof Tlignecommande)) {
            return false;
        }
        Tlignecommande other = (Tlignecommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Tlignecommande[ id=" + id + " ]";
    }

}
