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
@Table(name = "role_apk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleApk.findAll", query = "SELECT r FROM RoleApk r")
    , @NamedQuery(name = "RoleApk.findById", query = "SELECT r FROM RoleApk r WHERE r.id = :id")})
public class RoleApk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code")
    private Integer code;
    @JoinColumn(name = "utilisateur", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tusers utilisateur;
    @JoinColumn(name = "role", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ActionAPK role;

    public RoleApk() {
    }

    public RoleApk(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tusers getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Tusers utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ActionAPK getRole() {
        return role;
    }

    public void setRole(ActionAPK role) {
        this.role = role;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
        if (!(object instanceof RoleApk)) {
            return false;
        }
        RoleApk other = (RoleApk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ocm.eh2s.entity.RoleApk[ id=" + id + " ]";
    }
    
}
