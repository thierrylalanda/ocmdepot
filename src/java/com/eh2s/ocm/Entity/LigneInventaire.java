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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author messi01
 */
@Entity
@Table(name = "ligne_inventaire")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "LigneInventaire.findAll", query = "SELECT l FROM LigneInventaire l")
    , @NamedQuery(name = "LigneInventaire.findById", query = "SELECT l FROM LigneInventaire l WHERE l.id = :id")
    , @NamedQuery(name = "LigneInventaire.findLigneMagasinByPeriode", query = "SELECT l FROM LigneInventaire l WHERE l.magasin.id = :magasin AND FUNCTION('DATE',l.dateInv) >= :d AND FUNCTION('DATE',l.dateInv) <= :d1")
    , @NamedQuery(name = "LigneInventaire.findAllLigneSociete", query = "SELECT l FROM LigneInventaire l WHERE l.societe.id = :societe")
    , @NamedQuery(name = "LigneInventaire.findLigneSocieteByPeriode", query = "SELECT l FROM LigneInventaire l WHERE l.societe.id = :societe AND FUNCTION('DATE',l.dateInv) >= :d AND FUNCTION('DATE',l.dateInv) <= :d1")
    , @NamedQuery(name = "LigneInventaire.findByDateInv", query = "SELECT l FROM LigneInventaire l WHERE FUNCTION('DATE',l.dateInv) = :dateInv")})
public class LigneInventaire implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_inv")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInv;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private Integer type=0;
    @Column(name = "is_inv")
    private Integer isInv=0;
    @JoinColumn(name = "operateur", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tusers operateur;
    @JoinColumn(name = "magasin", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Magasin magasin;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Societe societe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ligneInv", fetch = FetchType.LAZY)
    private List<Inventaire> inventaireList;

    public LigneInventaire() {
    }

    public LigneInventaire(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateInv() {
        return dateInv;
    }

    public void setDateInv(Date dateInv) {
        this.dateInv = dateInv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Tusers getOperateur() {
        return operateur;
    }

    public void setOperateur(Tusers operateur) {
        this.operateur = operateur;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public List<Inventaire> getInventaireList() {
        return inventaireList;
    }

    public void setInventaireList(List<Inventaire> inventaireList) {
        this.inventaireList = inventaireList;
    }

    public Integer getIsInv() {
        return isInv;
    }

    public void setIsInv(Integer isInv) {
        this.isInv = isInv;
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
        if (!(object instanceof LigneInventaire)) {
            return false;
        }
        LigneInventaire other = (LigneInventaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.LigneInventaire[ id=" + id + " ]";
    }
}
