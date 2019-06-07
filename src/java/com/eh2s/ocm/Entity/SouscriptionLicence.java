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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "souscription_licence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SouscriptionLicence.findAll", query = "SELECT s FROM SouscriptionLicence s WHERE s.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "SouscriptionLicence.findById", query = "SELECT s FROM SouscriptionLicence s WHERE s.id = :id")
    ,
    @NamedQuery(name = "SouscriptionLicence.findByUpUser", query = "SELECT s FROM SouscriptionLicence s WHERE s.upUser = :upUser")
    ,
    @NamedQuery(name = "SouscriptionLicence.findByDateSous", query = "SELECT s FROM SouscriptionLicence s WHERE s.dateSous = :dateSous")
    ,
    @NamedQuery(name = "SouscriptionLicence.findByDateFinTest", query = "SELECT s FROM SouscriptionLicence s WHERE s.dateFinTest = :dateFinTest")})
public class SouscriptionLicence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "up_user")
    private Integer upUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_sous")
    @Temporal(TemporalType.DATE)
    private Date dateSous;
    @Column(name = "date_fin_test")
    @Temporal(TemporalType.DATE)
    private Date dateFinTest;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Societe societe;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TypeLicence type;

    public SouscriptionLicence() {
    }

    public SouscriptionLicence(Integer id) {
        this.id = id;
    }

    public SouscriptionLicence(Integer id, Date dateSous) {
        this.id = id;
        this.dateSous = dateSous;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUpUser() {
        return upUser;
    }

    public void setUpUser(Integer upUser) {
        this.upUser = upUser;
    }

    public Date getDateSous() {
        return dateSous;
    }

    public void setDateSous(Date dateSous) {
        this.dateSous = dateSous;
    }

    public Date getDateFinTest() {
        return dateFinTest;
    }

    public void setDateFinTest(Date dateFinTest) {
        this.dateFinTest = dateFinTest;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public TypeLicence getType() {
        return type;
    }

    public void setType(TypeLicence type) {
        this.type = type;
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
        if (!(object instanceof SouscriptionLicence)) {
            return false;
        }
        SouscriptionLicence other = (SouscriptionLicence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.Entity.SouscriptionLicence[ id=" + id + " ]";
    }

}
