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
@Table(name = "tsmenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tsmenu.findAll", query = "SELECT t FROM Tsmenu t"),
    @NamedQuery(name = "Tsmenu.findById", query = "SELECT t FROM Tsmenu t WHERE t.id = :id"),
    @NamedQuery(name = "Tsmenu.findByNom", query = "SELECT t FROM Tsmenu t WHERE t.nom = :nom")})
public class Tsmenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "smenu", fetch = FetchType.LAZY)
    private List<Tactions> tactionsList;
    @JoinColumn(name = "menu", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tmenu menu;

    public Tsmenu() {
    }

    public Tsmenu(Integer id) {
        this.id = id;
    }

    public Tsmenu(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Tactions> getTactionsList() {
        return tactionsList;
    }

    public void setTactionsList(List<Tactions> tactionsList) {
        this.tactionsList = tactionsList;
    }

    public Tmenu getMenu() {
        return menu;
    }

    public void setMenu(Tmenu menu) {
        this.menu = menu;
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
        if (!(object instanceof Tsmenu)) {
            return false;
        }
        Tsmenu other = (Tsmenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.Entity.Tsmenu[ id=" + id + " ]";
    }
    
}
