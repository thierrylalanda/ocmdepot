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
 * @author messi
 */
@Entity
@Table(name = "type_licence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeLicence.findAll", query = "SELECT t FROM TypeLicence t"),
    @NamedQuery(name = "TypeLicence.findById", query = "SELECT t FROM TypeLicence t WHERE t.id = :id"),
    @NamedQuery(name = "TypeLicence.findBycode", query = "SELECT t FROM TypeLicence t WHERE t.code = :code"),
    @NamedQuery(name = "TypeLicence.findByType", query = "SELECT t FROM TypeLicence t WHERE t.type = :type"),
    @NamedQuery(name = "TypeLicence.findByMaxUser", query = "SELECT t FROM TypeLicence t WHERE t.maxUser = :maxUser"),
    @NamedQuery(name = "TypeLicence.findByTestMode", query = "SELECT t FROM TypeLicence t WHERE t.testMode = :testMode")})
public class TypeLicence implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private int code;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_user")
    private int maxUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "test_mode")
    private int testMode;
     @Basic(optional = false)
    @Column(name = "init_user")
    private Integer initUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type", fetch = FetchType.LAZY)
    private List<SouscriptionLicence> souscriptionLicenceList;

    public TypeLicence() {
    }

    public TypeLicence(Integer id) {
        this.id = id;
    }

    public TypeLicence(Integer id, String type, int maxUser, int testMode) {
        this.id = id;
        this.type = type;
        this.maxUser = maxUser;
        this.testMode = testMode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(int maxUser) {
        this.maxUser = maxUser;
    }

    public int getTestMode() {
        return testMode;
    }

    public void setTestMode(int testMode) {
        this.testMode = testMode;
    }


    public Integer getInitUser() {
        return initUser;
    }

    public void setInitUser(Integer initUser) {
        this.initUser = initUser;
    }

    @XmlTransient
    public List<SouscriptionLicence> getSouscriptionLicenceList() {
        return souscriptionLicenceList;
    }

    public void setSouscriptionLicenceList(List<SouscriptionLicence> souscriptionLicenceList) {
        this.souscriptionLicenceList = souscriptionLicenceList;
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
        if (!(object instanceof TypeLicence)) {
            return false;
        }
        TypeLicence other = (TypeLicence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.Entity.TypeLicence[ id=" + id + " ]";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
