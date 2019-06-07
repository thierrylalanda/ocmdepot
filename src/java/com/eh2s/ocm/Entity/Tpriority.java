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
@Table(name = "tpriority")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpriority.findAll", query = "SELECT t FROM Tpriority t WHERE t.societe.immatriculation = :imma")
    ,
    @NamedQuery(name = "Tpriority.findById", query = "SELECT t FROM Tpriority t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tpriority.findByNumber", query = "SELECT t FROM Tpriority t WHERE t.number = :number")
    ,
    @NamedQuery(name = "Tpriority.findByName", query = "SELECT t FROM Tpriority t WHERE t.name = :name")})
public class Tpriority implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number")
    private int number;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "prioriteid", fetch = FetchType.LAZY)
    private List<Tincidents> tincidentsList;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Societe societe;

    public Tpriority() {
    }

    public Tpriority(Integer id) {
        this.id = id;
    }

    public Tpriority(Integer id, int number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpriority)) {
            return false;
        }
        Tpriority other = (Tpriority) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.Tpriority[ id=" + id + " ]";
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

}
