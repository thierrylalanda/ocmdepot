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
@Table(name = "tgroups_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TgroupsAssoc.findAll", query = "SELECT t FROM TgroupsAssoc t")
    ,
    @NamedQuery(name = "TgroupsAssoc.DeletteBygroup", query = "DELETE FROM TgroupsAssoc t WHERE t.idgroup.id = :group")
    ,
     @NamedQuery(name = "TgroupsAssoc.findByGroupe", query = "SELECT t FROM TgroupsAssoc t WHERE t.idgroup.id = :id")
    ,
    @NamedQuery(name = "TgroupsAssoc.findById", query = "SELECT t FROM TgroupsAssoc t WHERE t.id = :id")})
public class TgroupsAssoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idgroup", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tgroups idgroup;
    @JoinColumn(name = "action", referencedColumnName = "id_action")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tactions action;

    public TgroupsAssoc() {
    }

    public TgroupsAssoc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tgroups getGroup1() {
        return idgroup;
    }

    public void setGroup1(Tgroups group1) {
        this.idgroup = group1;
    }

    public Tactions getAction() {
        return action;
    }

    public void setAction(Tactions action) {
        this.action = action;
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
        if (!(object instanceof TgroupsAssoc)) {
            return false;
        }
        TgroupsAssoc other = (TgroupsAssoc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.TgroupsAssoc[ id=" + id + " ]";
    }

}
