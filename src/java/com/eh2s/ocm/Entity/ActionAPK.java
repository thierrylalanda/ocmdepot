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
 * @author messi01
 */
@Entity
@Table(name = "action_APK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionAPK.findAll", query = "SELECT a FROM ActionAPK a")
    , @NamedQuery(name = "ActionAPK.findById", query = "SELECT a FROM ActionAPK a WHERE a.id = :id")
    , @NamedQuery(name = "ActionAPK.findByCode", query = "SELECT a FROM ActionAPK a WHERE a.code = :code")
    , @NamedQuery(name = "ActionAPK.findByNom", query = "SELECT a FROM ActionAPK a WHERE a.nom = :nom")})
public class ActionAPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private int code;
    @Size(max = 50)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.LAZY)
    private List<RoleApk> roleApkList;

    public ActionAPK() {
    }

    public ActionAPK(Integer id) {
        this.id = id;
    }

    public ActionAPK(Integer id, int code) {
        this.id = id;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<RoleApk> getRoleApkList() {
        return roleApkList;
    }

    public void setRoleApkList(List<RoleApk> roleApkList) {
        this.roleApkList = roleApkList;
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
        if (!(object instanceof ActionAPK)) {
            return false;
        }
        ActionAPK other = (ActionAPK) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ocm.eh2s.entity.ActionAPK[ id=" + id + " ]";
    }
    
}
