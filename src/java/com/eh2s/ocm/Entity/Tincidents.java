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
import javax.persistence.CascadeType;
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
 * @author messi
 */
@Entity
//@Cacheable(false)
@Table(name = "tincidents")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Tincidents.findAll", query = "SELECT t FROM Tincidents t WHERE t.clientid.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tincidents.findById", query = "SELECT t FROM Tincidents t WHERE t.id = :id")
    ,
     @NamedQuery(name = "Tincidents.findByClient", query = "SELECT t FROM Tincidents t WHERE t.clientid.id = :cli")
    ,
    @NamedQuery(name = "Tincidents.findByAnnee", query = "SELECT t FROM Tincidents t WHERE FUNCTION('YEAR',t.dateCreate) = :annee AND t.clientid.societe.immatriculation = :imma ORDER BY t.id ASC")
    ,
    @NamedQuery(name = "Tincidents.findByDescription", query = "SELECT t FROM Tincidents t WHERE t.description = :description")
    ,
    @NamedQuery(name = "Tincidents.findByDateCreate", query = "SELECT t FROM Tincidents t WHERE t.dateCreate = :dateCreate")
    ,
    @NamedQuery(name = "Tincidents.findByPeriode", query = "SELECT t FROM Tincidents t WHERE FUNCTION('DATE',t.dateCreate) >= :d1 AND FUNCTION('DATE',t.dateCreate) <= :d2 AND t.clientid.societe.immatriculation = :imma ORDER BY t.id ASC")
    ,
    @NamedQuery(name = "Tincidents.findByDateHope", query = "SELECT t FROM Tincidents t WHERE t.dateHope < :d1 AND t.isaffect = :aff AND t.state.code = :status AND t.clientid.societe.immatriculation = :imma ORDER BY t.id ASC")
    ,
    @NamedQuery(name = "Tincidents.findByDelais", query = "SELECT t FROM Tincidents t WHERE t.dateHope = :dateModif")
    ,
    @NamedQuery(name = "Tincidents.findByState", query = "SELECT t FROM Tincidents t WHERE t.state = :state")})
public class Tincidents implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "isaffect")
    private int isaffect;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Basic(optional = false)
    @Column(name = "date_hope")
    @Temporal(TemporalType.DATE)
    private Date dateHope;
    @Basic(optional = false)
    @Column(name = "date_fer")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFer;
    @Basic(optional = false)
    @Column(name = "date_modif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModif;
    @Basic(optional = false)
    @Column(name = "is_in_delais")
    private Integer IsInDelais;
    @Basic(optional = false)
    @Column(name = "diffday")
    private Integer diffday;
    @Size(min = 1, max = 255)
    @Column(name = "description_fermeture")
    private String descriptionFermeture;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "photo")
    private String photo;
    @JoinColumn(name = "state", referencedColumnName = "code")
    @ManyToOne(fetch = FetchType.LAZY)
    private StatutIncident state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incident", fetch = FetchType.LAZY)
    private List<PieceJointe> pieceJointeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idplainte", fetch = FetchType.LAZY)
    private List<Userplainte> userplainteList;
    @JoinColumn(name = "prioriteid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tpriority prioriteid;
    @JoinColumn(name = "srubriqueid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tsrubriques srubriqueid;
    @JoinColumn(name = "sourceid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tsources sourceid;
    @JoinColumn(name = "clientid", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tclients clientid;
    @JoinColumn(name = "creator", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tusers creator;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incident", fetch = FetchType.LAZY)
    private List<TtraitementTicket> ttraitementTicketList;
    @Transient
    private String datecreation;
    @Transient
    private String datecloture;
    @Transient
    private String datemodification;
    @Transient
    private String datefermeture;

    public String getDatecc() {
        //DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        datecreation = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dateCreate);
        return datecreation;
    }

    public String getDateliv() {
        datecloture = new SimpleDateFormat("dd/MM/yyyy").format(dateHope);
        return datecloture;
    }

    public String Datemodification() {
        datemodification = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dateModif);
        return datemodification;
    }

    public String Fatefermeture() {
        datefermeture = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dateFer);
        return datefermeture;
    }

    public Tincidents() {
    }

    public Tincidents(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateHope() {
        return dateHope;
    }

    public void setDateHope(Date dateHope) {
        this.dateHope = dateHope;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public Integer getIsInDelais() {
        return IsInDelais;
    }

    public void setIsInDelais(Integer IsInDelais) {
        this.IsInDelais = IsInDelais;
    }

    public Integer getDiffday() {
        return diffday;
    }

    public void setDiffday(Integer diffday) {
        this.diffday = diffday;
    }

    public String getDescriptionFermeture() {
        return descriptionFermeture;
    }

    public void setDescriptionFermeture(String descriptionFermeture) {
        this.descriptionFermeture = descriptionFermeture;
    }

    public Date getDateFer() {
        return dateFer;
    }

    public void setDateFer(Date dateFer) {
        this.dateFer = dateFer;
    }

    @XmlTransient
    public List<PieceJointe> getPieceJointeList() {
        return pieceJointeList;
    }

    public void setPieceJointeList(List<PieceJointe> pieceJointeList) {
        this.pieceJointeList = pieceJointeList;
    }

    @XmlTransient
    public List<Userplainte> getUserplainteList() {
        return userplainteList;
    }

    public void setUserplainteList(List<Userplainte> userplainteList) {
        this.userplainteList = userplainteList;
    }

    public Tpriority getPrioriteid() {
        return prioriteid;
    }

    public void setPrioriteid(Tpriority prioriteid) {
        this.prioriteid = prioriteid;
    }

    public Tsrubriques getSrubriqueid() {
        return srubriqueid;
    }

    public void setSrubriqueid(Tsrubriques srubriqueid) {
        this.srubriqueid = srubriqueid;
    }

    public Tsources getSourceid() {
        return sourceid;
    }

    public void setSourceid(Tsources sourceid) {
        this.sourceid = sourceid;
    }

    public Tclients getClientid() {
        return clientid;
    }

    public void setClientid(Tclients clientid) {
        this.clientid = clientid;
    }

    public Tusers getCreator() {
        return creator;
    }

    public void setCreator(Tusers creator) {
        this.creator = creator;
    }

    public List<TtraitementTicket> getTtraitementTicketList() {
        return ttraitementTicketList;
    }

    public void setTtraitementTicketList(List<TtraitementTicket> ttraitementTicketList) {
        this.ttraitementTicketList = ttraitementTicketList;
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
        if (!(object instanceof Tincidents)) {
            return false;
        }
        Tincidents other = (Tincidents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.Tincidents[ id=" + id + " ]";
    }

    public StatutIncident getState() {
        return state;
    }

    public void setState(StatutIncident state) {
        this.state = state;
    }

    public int getIsaffect() {
        return isaffect;
    }

    public void setIsaffect(int isaffect) {
        this.isaffect = isaffect;
    }

}
