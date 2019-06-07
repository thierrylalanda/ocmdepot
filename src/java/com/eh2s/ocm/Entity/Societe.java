/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "societe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Societe.findAll", query = "SELECT s FROM Societe s")
    ,
    @NamedQuery(name = "Societe.findById", query = "SELECT s FROM Societe s WHERE s.id = :id")
    ,
    @NamedQuery(name = "Societe.findByAllByAutoSaveClient", query = "SELECT s FROM Societe s WHERE s.autosaveclient = :auto")
    ,
    @NamedQuery(name = "Societe.findByImma", query = "SELECT s FROM Societe s WHERE s.immatriculation = :imma")
    ,
    @NamedQuery(name = "Societe.findByNom", query = "SELECT s FROM Societe s WHERE s.nom = :nom")
    ,
    @NamedQuery(name = "Societe.findByLogo", query = "SELECT s FROM Societe s WHERE s.logo = :logo")
    ,
    @NamedQuery(name = "Societe.findByAdresse", query = "SELECT s FROM Societe s WHERE s.adresse = :adresse")
    ,
    @NamedQuery(name = "Societe.findByEmail", query = "SELECT s FROM Societe s WHERE s.email = :email")
    , @NamedQuery(name = "Societe.findByTel", query = "SELECT s FROM Societe s WHERE s.tel = :tel")
    , @NamedQuery(name = "Societe.findByAutosaveclient", query = "SELECT s FROM Societe s WHERE s.autosaveclient = :autosaveclient")
    , @NamedQuery(name = "Societe.findByGeststock", query = "SELECT s FROM Societe s WHERE s.geststock = :geststock")
    , @NamedQuery(name = "Societe.findByGestmarge", query = "SELECT s FROM Societe s WHERE s.gestmarge = :gestmarge")
    , @NamedQuery(name = "Societe.findByGestcassier", query = "SELECT s FROM Societe s WHERE s.gestcassier = :gestcassier")
    , @NamedQuery(name = "Societe.findByGesttourner", query = "SELECT s FROM Societe s WHERE s.gesttourner = :gesttourner")
    , @NamedQuery(name = "Societe.findByGestmagasin", query = "SELECT s FROM Societe s WHERE s.gestmagasin = :gestmagasin")
    , @NamedQuery(name = "Societe.findByGestcaisse", query = "SELECT s FROM Societe s WHERE s.gestcaisse = :gestcaisse")
    , @NamedQuery(name = "Societe.findByGestemballage", query = "SELECT s FROM Societe s WHERE s.gestemballage = :gestemballage")
    , @NamedQuery(name = "Societe.findByGestfournisseur", query = "SELECT s FROM Societe s WHERE s.gestfournisseur = :gestfournisseur")})
public class Societe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "activite")
    private String activite;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 255)
    @Column(name = "centre_impot")
    private String centreImpot;
    @Size(max = 255)
    @Column(name = "cni_rc")
    private String cniRc;
    @Size(max = 255)
    @Column(name = "code_postal")
    private String codePostal;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 20)
    @Column(name = "immatriculation")
    private String immatriculation;
    @Size(max = 255)
    @Column(name = "logo")
    private String logo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "logo_base64")
    private String logoBase64;
    @Column(name = "maintenance")
    private Integer maintenance;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "regime_fiscal")
    private String regimeFiscal;
    @Size(max = 255)
    @Column(name = "sigle")
    private String sigle;
    @Size(max = 255)
    @Column(name = "tel")
    private String tel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autosaveclient")
    private int autosaveclient;
    @Basic(optional = false)
    @NotNull
    @Column(name = "geststock")
    private int geststock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestmarge")
    private int gestmarge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestcassier")
    private int gestcassier;
    @Column(name = "gesttourner")
    private Integer gesttourner;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestmagasin")
    private int gestmagasin;
    @Column(name = "gestcaisse")
    private Integer gestcaisse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestemballage")
    private int gestemballage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestfournisseur")
    private int gestfournisseur;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<LigneInventaire> ligneInventaireList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Trubriques> trubriquesList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Fournisseur> fournisseurList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Tlignecommande> tlignecommandeList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Tusers> tusersList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Tpriority> tpriorityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Tourner> tournerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Caisse> caisseList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<LigneAccount> ligneAccountList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Tsources> tsourcesList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Tclients> tclientsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Magasin> magasinList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Tregions> tregionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Emballage> emballageList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Tgroups> tgroupsList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Tcategorie> tcategorieList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<Ttypeclients> ttypeclientsList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<SouscriptionLicence> souscriptionLicenceList;
    @OneToMany(mappedBy = "societe", fetch = FetchType.LAZY)
    private List<LigneSortie> ligneSortieList;

    public Societe() {
    }

    public Societe(Integer id) {
        this.id = id;
    }

    public Societe(Integer id, int autosaveclient, int geststock, int gestmarge, int gestcassier, int gestmagasin, int gestemballage, int gestfournisseur) {
        this.id = id;
        this.autosaveclient = autosaveclient;
        this.geststock = geststock;
        this.gestmarge = gestmarge;
        this.gestcassier = gestcassier;
        this.gestmagasin = gestmagasin;
        this.gestemballage = gestemballage;
        this.gestfournisseur = gestfournisseur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCentreImpot() {
        return centreImpot;
    }

    public void setCentreImpot(String centreImpot) {
        this.centreImpot = centreImpot;
    }

    public String getCniRc() {
        return cniRc;
    }

    public void setCniRc(String cniRc) {
        this.cniRc = cniRc;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogoBase64() {
        return logoBase64;
    }

    public void setLogoBase64(String logoBase64) {
        this.logoBase64 = logoBase64;
    }

    public Integer getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Integer maintenance) {
        this.maintenance = maintenance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRegimeFiscal() {
        return regimeFiscal;
    }

    public void setRegimeFiscal(String regimeFiscal) {
        this.regimeFiscal = regimeFiscal;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAutoSaveClient() {
        return autosaveclient;
    }

    public void setAutoSaveClient(int autosaveclient) {
        this.autosaveclient = autosaveclient;
    }

    public int getGestStock() {
        return geststock;
    }

    public void setGestStock(int geststock) {
        this.geststock = geststock;
    }

    public int getGestmarge() {
        return gestmarge;
    }

    public void setGestmarge(int gestmarge) {
        this.gestmarge = gestmarge;
    }

    public int getGestcassier() {
        return gestcassier;
    }

    public void setGestcassier(int gestcassier) {
        this.gestcassier = gestcassier;
    }

    public Integer getGesttourner() {
        return gesttourner;
    }

    public void setGesttourner(Integer gesttourner) {
        this.gesttourner = gesttourner;
    }

    public int getGestmagasin() {
        return gestmagasin;
    }

    public void setGestmagasin(int gestmagasin) {
        this.gestmagasin = gestmagasin;
    }

    public Integer getGestcaisse() {
        return gestcaisse;
    }

    public void setGestcaisse(Integer gestcaisse) {
        this.gestcaisse = gestcaisse;
    }

    public int getGestemballage() {
        return gestemballage;
    }

    public void setGestemballage(int gestemballage) {
        this.gestemballage = gestemballage;
    }

    public int getGestfournisseur() {
        return gestfournisseur;
    }

    public void setGestfournisseur(int gestfournisseur) {
        this.gestfournisseur = gestfournisseur;
    }

    public List<LigneInventaire> getLigneInventaireList() {
        return ligneInventaireList;
    }

    public void setLigneInventaireList(List<LigneInventaire> ligneInventaireList) {
        this.ligneInventaireList = ligneInventaireList;
    }

    public List<Trubriques> getTrubriquesList() {
        return trubriquesList;
    }

    public void setTrubriquesList(List<Trubriques> trubriquesList) {
        this.trubriquesList = trubriquesList;
    }

    public List<Fournisseur> getFournisseurList() {
        return fournisseurList;
    }

    public void setFournisseurList(List<Fournisseur> fournisseurList) {
        this.fournisseurList = fournisseurList;
    }

    public List<Tlignecommande> getTlignecommandeList() {
        return tlignecommandeList;
    }

    public void setTlignecommandeList(List<Tlignecommande> tlignecommandeList) {
        this.tlignecommandeList = tlignecommandeList;
    }

    public List<Tusers> getTusersList() {
        return tusersList;
    }

    public void setTusersList(List<Tusers> tusersList) {
        this.tusersList = tusersList;
    }

    public List<Tpriority> getTpriorityList() {
        return tpriorityList;
    }

    public void setTpriorityList(List<Tpriority> tpriorityList) {
        this.tpriorityList = tpriorityList;
    }

    public List<Tourner> getTournerList() {
        return tournerList;
    }

    public void setTournerList(List<Tourner> tournerList) {
        this.tournerList = tournerList;
    }

    public List<Caisse> getCaisseList() {
        return caisseList;
    }

    public void setCaisseList(List<Caisse> caisseList) {
        this.caisseList = caisseList;
    }

    public List<LigneAccount> getLigneAccountList() {
        return ligneAccountList;
    }

    public void setLigneAccountList(List<LigneAccount> ligneAccountList) {
        this.ligneAccountList = ligneAccountList;
    }

    public List<Tsources> getTsourcesList() {
        return tsourcesList;
    }

    public void setTsourcesList(List<Tsources> tsourcesList) {
        this.tsourcesList = tsourcesList;
    }

    public List<Tclients> getTclientsList() {
        return tclientsList;
    }

    public void setTclientsList(List<Tclients> tclientsList) {
        this.tclientsList = tclientsList;
    }

    public List<Magasin> getMagasinList() {
        return magasinList;
    }

    public void setMagasinList(List<Magasin> magasinList) {
        this.magasinList = magasinList;
    }

    public List<Tregions> getTregionsList() {
        return tregionsList;
    }

    public void setTregionsList(List<Tregions> tregionsList) {
        this.tregionsList = tregionsList;
    }

    public List<Emballage> getEmballageList() {
        return emballageList;
    }

    public void setEmballageList(List<Emballage> emballageList) {
        this.emballageList = emballageList;
    }

    public List<Tgroups> getTgroupsList() {
        return tgroupsList;
    }

    public void setTgroupsList(List<Tgroups> tgroupsList) {
        this.tgroupsList = tgroupsList;
    }

    public List<Tcategorie> getTcategorieList() {
        return tcategorieList;
    }

    public void setTcategorieList(List<Tcategorie> tcategorieList) {
        this.tcategorieList = tcategorieList;
    }

    public List<Ttypeclients> getTtypeclientsList() {
        return ttypeclientsList;
    }

    public void setTtypeclientsList(List<Ttypeclients> ttypeclientsList) {
        this.ttypeclientsList = ttypeclientsList;
    }

    public List<SouscriptionLicence> getSouscriptionLicenceList() {
        return souscriptionLicenceList;
    }

    public void setSouscriptionLicenceList(List<SouscriptionLicence> souscriptionLicenceList) {
        this.souscriptionLicenceList = souscriptionLicenceList;
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
        if (!(object instanceof Societe)) {
            return false;
        }
        Societe other = (Societe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Societe[ id=" + id + " ]";
    }

}
