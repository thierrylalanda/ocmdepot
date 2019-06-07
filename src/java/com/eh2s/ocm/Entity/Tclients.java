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
import javax.persistence.Cacheable;
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
 * @author messi
 */
@Entity
@Table(name = "tclients")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Tclients.findAll", query = "SELECT t FROM Tclients t WHERE t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tclients.findById", query = "SELECT t FROM Tclients t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tclients.findByNom", query = "SELECT t FROM Tclients t WHERE t.nom = :nom")
    ,
    @NamedQuery(name = "Tclients.findByTourner", query = "SELECT t FROM Tclients t WHERE t.tourner.numc = :numt")
    ,
    @NamedQuery(name = "Tclients.findByPrenom", query = "SELECT t FROM Tclients t WHERE t.prenom = :prenom")
    ,
    @NamedQuery(name = "Tclients.findByAdresse", query = "SELECT t FROM Tclients t WHERE t.adresse = :adresse")
    ,
    @NamedQuery(name = "Tclients.findByTelephone", query = "SELECT t FROM Tclients t WHERE t.telephone = :telephone")
    ,
    @NamedQuery(name = "Tclients.findByTelephone2", query = "SELECT t FROM Tclients t WHERE t.telephone2 = :telephone2")
    ,
    @NamedQuery(name = "Tclients.findByMail", query = "SELECT t FROM Tclients t WHERE t.mail = :mail")
    ,
    @NamedQuery(name = "Tclients.findByRegion", query = "SELECT t FROM Tclients t WHERE t.secteurid.districtid.regionid.id = :region AND t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tclients.findByDistrict", query = "SELECT t FROM Tclients t WHERE t.secteurid.districtid.id = :region AND t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tclients.findBySecteur", query = "SELECT t FROM Tclients t WHERE t.secteurid.id = :region AND t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tclients.findByDatecreation", query = "SELECT t FROM Tclients t WHERE t.datecreation = :datecreation")
    ,
    @NamedQuery(name = "Tclients.findByDatemodification", query = "SELECT t FROM Tclients t WHERE t.datemodification = :datemodification")
    ,
    @NamedQuery(name = "Tclients.findByCodeclientAndPassword", query = "SELECT t FROM Tclients t WHERE t.codeclient = :codeclient AND t.password = :password")
    ,
    @NamedQuery(name = "Tclients.findByCodeclient", query = "SELECT t FROM Tclients t WHERE t.codeclient = :codeclient")})
public class Tclients implements Serializable {

     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "chgpwd")
    private Integer chgpwd;
    @Size(max = 255)
    @Column(name = "codeclient")
    private String codeclient;
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "datemodification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datemodification;
    @Size(max = 255)
    @Column(name = "mail")
    private String mail;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 255)
    @Column(name = "psd")
    private String psd;
    @Size(max = 255)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 255)
    @Column(name = "telephone2")
    private String telephone2;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Tlignecommande> tlignecommandeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
    private List<CompteRistourne> compteRistourneList;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<LigneAccount> ligneAccountList;
    @JoinColumn(name = "typeclientid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ttypeclients typeclientid;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Societe societe;
    @JoinColumn(name = "secteurid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tsecteurs secteurid;
    @JoinColumn(name = "tourner", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tourner tourner;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
    private List<CompteEmballage> compteEmballageList;
    @OneToMany(mappedBy = "clientid", fetch = FetchType.LAZY)
    private List<Tincidents> tincidentsList;

    public Tclients() {
    }

    public Tclients(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getChgpwd() {
        return chgpwd;
    }

    public void setChgpwd(Integer chgpwd) {
        this.chgpwd = chgpwd;
    }

    public String getCodeclient() {
        return codeclient;
    }

    public void setCodeclient(String codeclient) {
        this.codeclient = codeclient;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDatemodification() {
        return datemodification;
    }

    public void setDatemodification(Date datemodification) {
        this.datemodification = datemodification;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public List<Tlignecommande> getTlignecommandeList() {
        return tlignecommandeList;
    }

    public void setTlignecommandeList(List<Tlignecommande> tlignecommandeList) {
        this.tlignecommandeList = tlignecommandeList;
    }

    public List<CompteRistourne> getCompteRistourneList() {
        return compteRistourneList;
    }

    public void setCompteRistourneList(List<CompteRistourne> compteRistourneList) {
        this.compteRistourneList = compteRistourneList;
    }

    public List<LigneAccount> getLigneAccountList() {
        return ligneAccountList;
    }

    public void setLigneAccountList(List<LigneAccount> ligneAccountList) {
        this.ligneAccountList = ligneAccountList;
    }

    public Ttypeclients getTypeclientid() {
        return typeclientid;
    }

    public void setTypeclientid(Ttypeclients typeclientid) {
        this.typeclientid = typeclientid;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Tsecteurs getSecteurid() {
        return secteurid;
    }

    public void setSecteurid(Tsecteurs secteurid) {
        this.secteurid = secteurid;
    }

    public Tourner getTourner() {
        return tourner;
    }

    public void setTourner(Tourner tourner) {
        this.tourner = tourner;
    }

    public List<CompteEmballage> getCompteEmballageList() {
        return compteEmballageList;
    }

    public void setCompteEmballageList(List<CompteEmballage> compteEmballageList) {
        this.compteEmballageList = compteEmballageList;
    }

    public List<Tincidents> getTincidentsList() {
        return tincidentsList;
    }

    public void setTincidentsList(List<Tincidents> tincidentsList) {
        this.tincidentsList = tincidentsList;
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
        if (!(object instanceof Tclients)) {
            return false;
        }
        Tclients other = (Tclients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Tclients[ id=" + id + " ]";
    }

}
