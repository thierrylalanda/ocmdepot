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
@Table(name = "affect_tourner_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AffectTournerUser.findAll", query = "SELECT a FROM AffectTournerUser a")
    , @NamedQuery(name = "AffectTournerUser.findById", query = "SELECT a FROM AffectTournerUser a WHERE a.id = :id")
    ,@NamedQuery(name = "AffectTournerUser.findByTourner", query = "SELECT a FROM AffectTournerUser a WHERE a.tourner.id = :id")
    ,@NamedQuery(name = "AffectTournerUser.findByUser", query = "SELECT a FROM AffectTournerUser a WHERE a.user.id = :id")})
public class AffectTournerUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tusers user;
    @JoinColumn(name = "tourner", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tourner tourner;

    public AffectTournerUser() {
    }

    public AffectTournerUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tusers getUser() {
        return user;
    }

    public void setUser(Tusers user) {
        this.user = user;
    }

    public Tourner getTourner() {
        return tourner;
    }

    public void setTourner(Tourner tourner) {
        this.tourner = tourner;
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
        if (!(object instanceof AffectTournerUser)) {
            return false;
        }
        AffectTournerUser other = (AffectTournerUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ocm.eh2s.entity.AffectTournerUser[ id=" + id + " ]";
    }

}
