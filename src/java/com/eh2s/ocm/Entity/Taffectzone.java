/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi01
 */
@Entity
@Table(name = "taffectzone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taffectzone.findAll", query = "SELECT t FROM Taffectzone t")
    , @NamedQuery(name = "Taffectzone.findById", query = "SELECT t FROM Taffectzone t WHERE t.id = :id")
    , @NamedQuery(name = "Taffectzone.findByRegion", query = "SELECT t FROM Taffectzone t WHERE t.region = :region")
    , @NamedQuery(name = "Taffectzone.findByusers", query = "SELECT t FROM Taffectzone t WHERE t.users.id = :user")
    , @NamedQuery(name = "Taffectzone.findByDistrict", query = "SELECT t FROM Taffectzone t WHERE t.district = :district")
    , @NamedQuery(name = "Taffectzone.findBySecteur", query = "SELECT t FROM Taffectzone t WHERE t.secteur = :secteur")})
public class Taffectzone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "region")
    private Integer region;
    @Column(name = "district")
    private Integer district;
    @Column(name = "secteur")
    private Integer secteur;
    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tusers users;

    public Taffectzone() {
    }

    public Taffectzone(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getSecteur() {
        return secteur;
    }

    public void setSecteur(Integer secteur) {
        this.secteur = secteur;
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
        if (!(object instanceof Taffectzone)) {
            return false;
        }
        Taffectzone other = (Taffectzone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ocm.Entity.Taffectzone[ id=" + id + " ]";
    }

    public Tusers getUsers() {
        return users;
    }

    public void setUsers(Tusers users) {
        this.users = users;
    }

}
