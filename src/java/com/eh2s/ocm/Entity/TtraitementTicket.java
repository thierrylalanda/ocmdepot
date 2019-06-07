/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "ttraitement_ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TtraitementTicket.findAll", query = "SELECT t FROM TtraitementTicket t")
    ,
    @NamedQuery(name = "TtraitementTicket.findAllByTicket", query = "SELECT t FROM TtraitementTicket t WHERE t.incident.id = :ticket")
    ,
    @NamedQuery(name = "TtraitementTicket.findById", query = "SELECT t FROM TtraitementTicket t WHERE t.id = :id")
    ,
    @NamedQuery(name = "TtraitementTicket.findByDateComment", query = "SELECT t FROM TtraitementTicket t WHERE t.dateComment = :dateComment")})
public class TtraitementTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "date_comment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateComment;
    @JoinColumn(name = "incident", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tincidents incident;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tusers user;
    @Transient
    private String datecommentaire;

    public String Datecommentaire() {
        //DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        datecommentaire = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dateComment);
        return datecommentaire;
    }

    public TtraitementTicket() {
    }

    public TtraitementTicket(Integer id) {
        this.id = id;
    }

    public TtraitementTicket(Integer id, String commentaire) {
        this.id = id;
        this.commentaire = commentaire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public Tincidents getIncident() {
        return incident;
    }

    public void setIncident(Tincidents incident) {
        this.incident = incident;
    }

    public Tusers getUser() {
        return user;
    }

    public void setUser(Tusers user) {
        this.user = user;
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
        if (!(object instanceof TtraitementTicket)) {
            return false;
        }
        TtraitementTicket other = (TtraitementTicket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.entity.TtraitementTicket[ id=" + id + " ]";
    }

}
