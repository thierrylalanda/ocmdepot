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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi01
 */
@Entity
@Table(name = "magasin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Magasin.findAll", query = "SELECT m FROM Magasin m")
    , @NamedQuery(name = "Magasin.findById", query = "SELECT m FROM Magasin m WHERE m.id = :id")
    , @NamedQuery(name = "Magasin.findBySociete", query = "SELECT m FROM Magasin m WHERE m.societe.id = :id")
    , @NamedQuery(name = "Magasin.findByRegion", query = "SELECT m FROM Magasin m WHERE m.region.id = :id")
    , @NamedQuery(name = "Magasin.findByNom", query = "SELECT m FROM Magasin m WHERE m.nom = :nom")
    , @NamedQuery(name = "Magasin.findByMagasigner", query = "SELECT m FROM Magasin m WHERE m.magasigner.id = :magasigner")})
public class Magasin implements Serializable {

   
   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom")
    private String nom;
    @JoinColumn(name = "magasigner", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tusers magasigner;
    @OneToMany(mappedBy = "magasin", fetch = FetchType.LAZY)
    private List<LigneInventaire> ligneInventaireList;
    @OneToMany(mappedBy = "magasin", fetch = FetchType.LAZY)
    private List<Tusers> tusersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mg1", fetch = FetchType.LAZY)
    private List<LigneTransfertStock> lignetransfertstockList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mg2", fetch = FetchType.LAZY)
    private List<LigneTransfertStock> lignetransfertstockList1;
    @OneToMany(mappedBy = "magasin", fetch = FetchType.LAZY)
    private List<Tourner> tournerList;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Societe societe;
    @JoinColumn(name = "region", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tregions region;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasin", fetch = FetchType.LAZY)
    private List<StockMg> stockMgList;

    public Magasin() {
    }

    public Magasin(Integer id) {
        this.id = id;
    }

    public Magasin(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Tusers getMagasigner() {
        return magasigner;
    }

    public void setMagasigner(Tusers magasigner) {
        this.magasigner = magasigner;
    }

    public List<LigneInventaire> getLigneInventaireList() {
        return ligneInventaireList;
    }

    public void setLigneInventaireList(List<LigneInventaire> ligneInventaireList) {
        this.ligneInventaireList = ligneInventaireList;
    }

    public List<Tusers> getTusersList() {
        return tusersList;
    }

    public void setTusersList(List<Tusers> tusersList) {
        this.tusersList = tusersList;
    }

    public List<LigneTransfertStock> getLignetransfertstockList() {
        return lignetransfertstockList;
    }

    public void setLignetransfertstockList(List<LigneTransfertStock> lignetransfertstockList) {
        this.lignetransfertstockList = lignetransfertstockList;
    }

    public List<LigneTransfertStock> getLignetransfertstockList1() {
        return lignetransfertstockList1;
    }

    public void setLignetransfertstockList1(List<LigneTransfertStock> lignetransfertstockList1) {
        this.lignetransfertstockList1 = lignetransfertstockList1;
    }

    public List<Tourner> getTournerList() {
        return tournerList;
    }

    public void setTournerList(List<Tourner> tournerList) {
        this.tournerList = tournerList;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Tregions getRegion() {
        return region;
    }

    public void setRegion(Tregions region) {
        this.region = region;
    }

    public List<StockMg> getStockMgList() {
        return stockMgList;
    }

    public void setStockMgList(List<StockMg> stockMgList) {
        this.stockMgList = stockMgList;
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
        if (!(object instanceof Magasin)) {
            return false;
        }
        Magasin other = (Magasin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Magasin[ id=" + id + " ]";
    }


}
