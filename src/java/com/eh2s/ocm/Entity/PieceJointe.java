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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "piece_jointe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PieceJointe.findAll", query = "SELECT p FROM PieceJointe p")
    ,
    @NamedQuery(name = "PieceJointe.findByIdPj", query = "SELECT p FROM PieceJointe p WHERE p.idPj = :idPj")
    ,
    @NamedQuery(name = "PieceJointe.findAllByTickect", query = "SELECT p FROM PieceJointe p WHERE p.incident.id = :tikect")
    ,
    @NamedQuery(name = "PieceJointe.findByElementJoint", query = "SELECT p FROM PieceJointe p WHERE p.elementJoint = :elementJoint")})
public class PieceJointe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pj")
    private Integer idPj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "element_joint")
    private String elementJoint;
    @Column(name = "iswho")
    private Integer iswho;
    @JoinColumn(name = "incident", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tincidents incident;

    public PieceJointe() {
    }

    public PieceJointe(Integer idPj) {
        this.idPj = idPj;
    }

    public PieceJointe(Integer idPj, String elementJoint) {
        this.idPj = idPj;
        this.elementJoint = elementJoint;
    }

    public Integer getIdPj() {
        return idPj;
    }

    public void setIdPj(Integer idPj) {
        this.idPj = idPj;
    }

    public String getElementJoint() {
        return elementJoint;
    }

    public void setElementJoint(String elementJoint) {
        this.elementJoint = elementJoint;
    }

    public Tincidents getIncident() {
        return incident;
    }

    public void setIncident(Tincidents incident) {
        this.incident = incident;
    }

    public Integer getIswho() {
        return iswho;
    }

    public void setIswho(Integer iswho) {
        this.iswho = iswho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPj != null ? idPj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PieceJointe)) {
            return false;
        }
        PieceJointe other = (PieceJointe) object;
        if ((this.idPj == null && other.idPj != null) || (this.idPj != null && !this.idPj.equals(other.idPj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.PieceJointe[ idPj=" + idPj + " ]";
    }

}
