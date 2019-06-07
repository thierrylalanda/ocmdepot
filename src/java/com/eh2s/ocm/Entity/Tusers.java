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
 * @author messi
 */
@Entity
@Table(name = "tusers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tusers.findAll", query = "SELECT t FROM Tusers t WHERE t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tusers.findById", query = "SELECT t FROM Tusers t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tusers.findByDeleted", query = "SELECT t FROM Tusers t WHERE t.deleted = :deleted")
    ,
    @NamedQuery(name = "Tusers.findByLogin", query = "SELECT t FROM Tusers t WHERE t.login = :login")
    ,
    @NamedQuery(name = "Tusers.findByPassword", query = "SELECT t FROM Tusers t WHERE t.login = :user AND t.password = :password")
    ,
    @NamedQuery(name = "Tusers.findByFirstname", query = "SELECT t FROM Tusers t WHERE t.firstname = :firstname")
    ,
    @NamedQuery(name = "Tusers.findByLastname", query = "SELECT t FROM Tusers t WHERE t.lastname = :lastname")
    ,
    @NamedQuery(name = "Tusers.findByRegion", query = "SELECT t FROM Tusers t WHERE t.serviceid.site.regionid.id = :region AND t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tusers.findByMail", query = "SELECT t FROM Tusers t WHERE t.mail = :mail")
    ,
    @NamedQuery(name = "Tusers.findByPhone", query = "SELECT t FROM Tusers t WHERE t.phone = :tel AND t.mail = :mail")
    ,
    @NamedQuery(name = "Tusers.findByFonction", query = "SELECT t FROM Tusers t WHERE t.fonction = :fonction")
    ,
    @NamedQuery(name = "Tusers.findByAddress1", query = "SELECT t FROM Tusers t WHERE t.address1 = :address1")
    ,
    @NamedQuery(name = "Tusers.findByDisable", query = "SELECT t FROM Tusers t WHERE t.disable = :disable")
    ,
    @NamedQuery(name = "Tusers.findByChgpwd", query = "SELECT t FROM Tusers t WHERE t.chgpwd = :chgpwd")
    ,
    @NamedQuery(name = "Tusers.findByLastLogin", query = "SELECT t FROM Tusers t WHERE t.lastLogin = :lastLogin")
    ,
    @NamedQuery(name = "Tusers.findByDatecreation", query = "SELECT t FROM Tusers t WHERE t.datecreation = :datecreation")
    ,
    @NamedQuery(name = "Tusers.findByDatemodification", query = "SELECT t FROM Tusers t WHERE t.datemodification = :datemodification")})
public class Tusers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "address1")
    private String address1;
    @Column(name = "chgpwd")
    private Integer chgpwd;
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "datemodification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datemodification;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "disable")
    private Integer disable;
    @Column(name = "fi")
    private Integer fi;
    @Size(max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 255)
    @Column(name = "fonction")
    private String fonction;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Size(max = 255)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 255)
    @Column(name = "login")
    private String login;
    @Size(max = 255)
    @Column(name = "mail")
    private String mail;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "phone")
    private String phone;
    @Size(max = 255)
    @Column(name = "psd")
    private String psd;
    @OneToMany(mappedBy = "operateur", fetch = FetchType.LAZY)
    private List<LigneInventaire> ligneInventaireList;
    @OneToMany(mappedBy = "preselleur", fetch = FetchType.LAZY)
    private List<Tlignecommande> tlignecommandeList;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Societe societe;
    @JoinColumn(name = "groupeid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tgroups groupeid;
    @JoinColumn(name = "serviceid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tservices serviceid;
    @JoinColumn(name = "magasin", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Magasin magasin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<AffectTournerUser> affectTournerUserList;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TtraitementTicket> ttraitementTicketList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operateur", fetch = FetchType.LAZY)
    private List<SortieCaisse> sortieCaisseList;
    @OneToMany(mappedBy = "operateur", fetch = FetchType.LAZY)
    private List<LigneAccount> ligneAccountList;
    @OneToMany(mappedBy = "iduser", fetch = FetchType.LAZY)
    private List<Userplainte> userplainteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operateur", fetch = FetchType.LAZY)
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurList;
    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Taffectzone> taffectzoneList;
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Tincidents> tincidentsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private List<RoleApk> roleApkList;
    @OneToMany(mappedBy = "operateur", fetch = FetchType.LAZY)
    private List<Account> accountList;
    @OneToMany(mappedBy = "operateur", fetch = FetchType.LAZY)
    private List<LigneSortie> ligneSortieList;

    public Tusers() {
    }

    public Tusers(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Integer getChgpwd() {
        return chgpwd;
    }

    public void setChgpwd(Integer chgpwd) {
        this.chgpwd = chgpwd;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public Integer getFi() {
        return fi;
    }

    public void setFi(Integer fi) {
        this.fi = fi;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public List<LigneInventaire> getLigneInventaireList() {
        return ligneInventaireList;
    }

    public void setLigneInventaireList(List<LigneInventaire> ligneInventaireList) {
        this.ligneInventaireList = ligneInventaireList;
    }

    public List<Tlignecommande> getTlignecommandeList() {
        return tlignecommandeList;
    }

    public void setTlignecommandeList(List<Tlignecommande> tlignecommandeList) {
        this.tlignecommandeList = tlignecommandeList;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Tgroups getGroupeid() {
        return groupeid;
    }

    public void setGroupeid(Tgroups groupeid) {
        this.groupeid = groupeid;
    }

    public Tservices getServiceid() {
        return serviceid;
    }

    public void setServiceid(Tservices serviceid) {
        this.serviceid = serviceid;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public List<AffectTournerUser> getAffectTournerUserList() {
        return affectTournerUserList;
    }

    public void setAffectTournerUserList(List<AffectTournerUser> affectTournerUserList) {
        this.affectTournerUserList = affectTournerUserList;
    }

    public List<TtraitementTicket> getTtraitementTicketList() {
        return ttraitementTicketList;
    }

    public void setTtraitementTicketList(List<TtraitementTicket> ttraitementTicketList) {
        this.ttraitementTicketList = ttraitementTicketList;
    }

    public List<SortieCaisse> getSortieCaisseList() {
        return sortieCaisseList;
    }

    public void setSortieCaisseList(List<SortieCaisse> sortieCaisseList) {
        this.sortieCaisseList = sortieCaisseList;
    }

    public List<LigneAccount> getLigneAccountList() {
        return ligneAccountList;
    }

    public void setLigneAccountList(List<LigneAccount> ligneAccountList) {
        this.ligneAccountList = ligneAccountList;
    }

    public List<Userplainte> getUserplainteList() {
        return userplainteList;
    }

    public void setUserplainteList(List<Userplainte> userplainteList) {
        this.userplainteList = userplainteList;
    }

    public List<LigneCommandeFournisseur> getLigneCommandeFournisseurList() {
        return ligneCommandeFournisseurList;
    }

    public void setLigneCommandeFournisseurList(List<LigneCommandeFournisseur> ligneCommandeFournisseurList) {
        this.ligneCommandeFournisseurList = ligneCommandeFournisseurList;
    }

    public List<Taffectzone> getTaffectzoneList() {
        return taffectzoneList;
    }

    public void setTaffectzoneList(List<Taffectzone> taffectzoneList) {
        this.taffectzoneList = taffectzoneList;
    }

    public List<Tincidents> getTincidentsList() {
        return tincidentsList;
    }

    public void setTincidentsList(List<Tincidents> tincidentsList) {
        this.tincidentsList = tincidentsList;
    }

    public List<RoleApk> getRoleApkList() {
        return roleApkList;
    }

    public void setRoleApkList(List<RoleApk> roleApkList) {
        this.roleApkList = roleApkList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
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
        if (!(object instanceof Tusers)) {
            return false;
        }
        Tusers other = (Tusers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Tusers[ id=" + id + " ]";
    }
}
