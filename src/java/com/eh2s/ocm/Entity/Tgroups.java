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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "tgroups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tgroups.findAll", query = "SELECT t FROM Tgroups t WHERE t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tgroups.findById", query = "SELECT t FROM Tgroups t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tgroups.findByName", query = "SELECT t FROM Tgroups t WHERE t.name = :name")
    ,
    @NamedQuery(name = "Tgroups.findByType", query = "SELECT t FROM Tgroups t WHERE t.type = :type")})
public class Tgroups implements Serializable {

    @Column(name = "type")
    private Integer type;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupeid", fetch = FetchType.LAZY)
    private List<Tusers> tusersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgroup", fetch = FetchType.LAZY)
    private List<TgroupsAssoc> tgroupsAssocList;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Societe societe;

    public Tgroups() {
    }

    public Tgroups(Integer id) {
        this.id = id;
    }

    public Tgroups(Integer id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @XmlTransient
    public List<Tusers> getTusersList() {
        return tusersList;
    }

    public void setTusersList(List<Tusers> tusersList) {
        this.tusersList = tusersList;
    }

    @XmlTransient
    public List<TgroupsAssoc> getTgroupsAssocList() {
        return tgroupsAssocList;
    }

    public void setTgroupsAssocList(List<TgroupsAssoc> tgroupsAssocList) {
        this.tgroupsAssocList = tgroupsAssocList;
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
        if (!(object instanceof Tgroups)) {
            return false;
        }
        Tgroups other = (Tgroups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.Tgroups[ id=" + id + " ]";
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
