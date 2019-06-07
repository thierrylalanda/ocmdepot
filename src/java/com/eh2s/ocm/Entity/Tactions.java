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
@Table(name = "tactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tactions.findAll", query = "SELECT t FROM Tactions t"),
    @NamedQuery(name = "Tactions.findByIdAction", query = "SELECT t FROM Tactions t WHERE t.idAction = :idAction"),
    @NamedQuery(name = "Tactions.findByCodeAction", query = "SELECT t FROM Tactions t WHERE t.codeAction = :codeAction"),
    @NamedQuery(name = "Tactions.findByNomAction", query = "SELECT t FROM Tactions t WHERE t.nomAction = :nomAction")})
public class Tactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_action")
    private Integer idAction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "code_action")
    private int codeAction;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_action")
    private String nomAction;
    @JoinColumn(name = "smenu", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tsmenu smenu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "action", fetch = FetchType.LAZY)
    private List<TgroupsAssoc> tgroupsAssocList;

    public Tactions() {
    }

    public Tactions(Integer idAction) {
        this.idAction = idAction;
    }

    public Tactions(Integer idAction, int codeAction, String nomAction) {
        this.idAction = idAction;
        this.codeAction = codeAction;
        this.nomAction = nomAction;
    }

    public Integer getIdAction() {
        return idAction;
    }

    public void setIdAction(Integer idAction) {
        this.idAction = idAction;
    }

    public int getCodeAction() {
        return codeAction;
    }

    public void setCodeAction(int codeAction) {
        this.codeAction = codeAction;
    }

    public String getNomAction() {
        return nomAction;
    }

    public void setNomAction(String nomAction) {
        this.nomAction = nomAction;
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
        hash += (idAction != null ? idAction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tactions)) {
            return false;
        }
        Tactions other = (Tactions) object;
        if ((this.idAction == null && other.idAction != null) || (this.idAction != null && !this.idAction.equals(other.idAction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.Tactions[ idAction=" + idAction + " ]";
    }

    public Tsmenu getSmenu() {
        return smenu;
    }

    public void setSmenu(Tsmenu smenu) {
        this.smenu = smenu;
    }
    
}
