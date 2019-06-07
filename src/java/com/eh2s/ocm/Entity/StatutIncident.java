/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "statut_incident")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatutIncident.findAll", query = "SELECT s FROM StatutIncident s")
    ,
    @NamedQuery(name = "StatutIncident.findByCode", query = "SELECT s FROM StatutIncident s WHERE s.code = :code")
    ,
    @NamedQuery(name = "StatutIncident.findByNom", query = "SELECT s FROM StatutIncident s WHERE s.nom = :nom")})
public class StatutIncident implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private Integer code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private List<Tincidents> tincidentsList;

    public StatutIncident() {
    }

    public StatutIncident(Integer code) {
        this.code = code;
    }

    public StatutIncident(Integer code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Tincidents> getTincidentsList() {
        return tincidentsList;
    }

    public void setTincidentsList(List<Tincidents> tincidentsList) {
        this.tincidentsList = tincidentsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatutIncident)) {
            return false;
        }
        StatutIncident other = (StatutIncident) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.Entity.StatutIncident[ code=" + code + " ]";
    }

}
