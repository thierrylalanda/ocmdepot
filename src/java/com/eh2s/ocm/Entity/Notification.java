/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    ,
    @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id")
    ,
    @NamedQuery(name = "Notification.findByLut", query = "SELECT n FROM Notification n WHERE n.lut = :lut")
    ,
    @NamedQuery(name = "Notification.findByUserAndStatus", query = "SELECT n FROM Notification n WHERE n.users = :user AND n.lut = :lut")
    ,
    @NamedQuery(name = "Notification.findByUser", query = "SELECT n FROM Notification n WHERE n.users = :user")
    ,
    @NamedQuery(name = "Notification.findByClientAndStatus", query = "SELECT n FROM Notification n WHERE n.client = :client AND n.lut = :lut")
    ,
    @NamedQuery(name = "Notification.findByClient", query = "SELECT n FROM Notification n WHERE n.client = :client")
    ,
    @NamedQuery(name = "Notification.findByDateNotif", query = "SELECT n FROM Notification n WHERE n.dateNotif = :dateNotif")})
public class Notification implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "lut")
    private int lut;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "titel")
    private String titel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "message")
    private String message;
    @Column(name = "users")
    private Integer users;
    @Column(name = "client")
    private Integer client;
    @Column(name = "date_notif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNotif;

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }

    public Notification(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUser() {
        return users;
    }

    public void setUser(Integer user) {
        this.users = user;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Date getDateNotif() {
        return dateNotif;
    }

    public void setDateNotif(Date dateNotif) {
        this.dateNotif = dateNotif;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
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
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eh2s.ccmanager.Entity.Notification[ id=" + id + " ]";
    }

    public int getLut() {
        return lut;
    }

    public void setLut(int lut) {
        this.lut = lut;
    }

}
