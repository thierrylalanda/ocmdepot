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
 * @author messi
 */
@Entity
@Table(name = "userplainte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userplainte.findAll", query = "SELECT u FROM Userplainte u")
    ,
    @NamedQuery(name = "Userplainte.findByPeriode", query = "SELECT u FROM Userplainte u WHERE u.iduser.id = :id AND FUNCTION('DATE',u.idplainte.dateCreate) BETWEEN :d1 AND :d2 ORDER BY u.idplainte.id ASC")
    ,
    @NamedQuery(name = "Userplainte.findByIdPlainte", query = "SELECT u FROM Userplainte u WHERE u.idplainte.id = :id")
    ,
    @NamedQuery(name = "Userplainte.findById", query = "SELECT u FROM Userplainte u WHERE u.id = :id")})
public class Userplainte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tusers iduser;
    @JoinColumn(name = "idplainte", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tincidents idplainte;

    public Userplainte() {
    }

    public Userplainte(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tusers getIduser() {
        return iduser;
    }

    public void setIduser(Tusers iduser) {
        this.iduser = iduser;
    }

    public Tincidents getIdplainte() {
        return idplainte;
    }

    public void setIdplainte(Tincidents idplainte) {
        this.idplainte = idplainte;
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
        if (!(object instanceof Userplainte)) {
            return false;
        }
        Userplainte other = (Userplainte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.Userplainte[ id=" + id + " ]";
    }

}
