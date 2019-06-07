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
@Table(name = "tcategorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tcategorie.findAll", query = "SELECT t FROM Tcategorie t WHERE t.societe.immatriculation = :imma"),
     @NamedQuery(name = "Tcategorie.findAllBySociete", query = "SELECT t FROM Tcategorie t WHERE t.societe.id = :id"),
    @NamedQuery(name = "Tcategorie.findById", query = "SELECT t FROM Tcategorie t WHERE t.id = :id"),
    @NamedQuery(name = "Tcategorie.findByNom", query = "SELECT t FROM Tcategorie t WHERE t.nom = :nom")})
public class Tcategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom")
    private String nom;
    @JoinColumn(name = "societe", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Societe societe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie", fetch = FetchType.LAZY)
    private List<Tarticles> tarticlesList;

    public Tcategorie() {
    }

    public Tcategorie(Integer id) {
        this.id = id;
    }

    public Tcategorie(Integer id, String nom) {
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
    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public List<Tarticles> getTarticlesList() {
        return tarticlesList;
    }

    public void setTarticlesList(List<Tarticles> tarticlesList) {
        this.tarticlesList = tarticlesList;
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
        if (!(object instanceof Tcategorie)) {
            return false;
        }
        Tcategorie other = (Tcategorie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.Entity.Tcategorie_1[ id=" + id + " ]";
    }

}
