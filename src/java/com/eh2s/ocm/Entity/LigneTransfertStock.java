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
 * @author messi01
 */
@Entity
@Table(name = "ligneTransfertStock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneTransfertStock.findAll", query = "SELECT l FROM LigneTransfertStock l")
    , @NamedQuery(name = "LigneTransfertStock.findById", query = "SELECT l FROM LigneTransfertStock l WHERE l.id = :id")
    , @NamedQuery(name = "LigneTransfertStock.findByDateTransf", query = "SELECT l FROM LigneTransfertStock l WHERE l.dateTransf = :dateTransf")
    , @NamedQuery(name = "LigneTransfertStock.findByOperateur", query = "SELECT l FROM LigneTransfertStock l WHERE l.operateur = :operateur")})
public class LigneTransfertStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_transf")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransf;
    @Size(max = 100)
    @Column(name = "operateur")
    private String operateur;
    @JoinColumn(name = "mg1", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Magasin mg1;
    @JoinColumn(name = "mg2", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Magasin mg2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ligneTransfert", fetch = FetchType.LAZY)
    private List<TransfertStock> transfertStockList;

    public LigneTransfertStock() {
    }

    public LigneTransfertStock(Integer id) {
        this.id = id;
    }

    public LigneTransfertStock(Integer id, Date dateTransf) {
        this.id = id;
        this.dateTransf = dateTransf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTransf() {
        return dateTransf;
    }

    public void setDateTransf(Date dateTransf) {
        this.dateTransf = dateTransf;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public Magasin getMg1() {
        return mg1;
    }

    public void setMg1(Magasin mg1) {
        this.mg1 = mg1;
    }

    public Magasin getMg2() {
        return mg2;
    }

    public void setMg2(Magasin mg2) {
        this.mg2 = mg2;
    }

    public List<TransfertStock> getTransfertStockList() {
        return transfertStockList;
    }

    public void setTransfertStockList(List<TransfertStock> transfertStockList) {
        this.transfertStockList = transfertStockList;
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
        if (!(object instanceof LigneTransfertStock)) {
            return false;
        }
        LigneTransfertStock other = (LigneTransfertStock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.test.entity.Lignetransfertstock[ id=" + id + " ]";
    }
    
    
}
