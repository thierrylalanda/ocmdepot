/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.temporaire;

import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Trubriques;
import com.eh2s.ocm.Entity.Tsecteurs;
import com.eh2s.ocm.Entity.Tsources;
import com.eh2s.ocm.Entity.Tsrubriques;
import com.eh2s.ocm.Entity.Tusers;

/**
 *
 * @author messi
 */
public class reportingresponsable {

    private Tusers responsable;
    private Tregions region;
    private Tsecteurs secteur;
    private Trubriques rubrique;
    private Tsrubriques srubrique;
    private Tclients client;
    private Tdistricts district;
    private Tsources source;
    private int plainteDansDelais;
    private int plainteHorsDelais;
    private int totalPlainteRecus;
    private int totalPlainteTraiter;
    private double pourcentagePlainteDansDelais;
    private double pourcentagePlainteTraiter;
    private String titre;
    private String entite;
    private int statut;
    private int statut1;
    private int statut2;
    private int statut3;
    private int statut4;

    public int getPlainteDansDelais() {
        return plainteDansDelais;
    }

    public void setPlainteDansDelais(int plainteDansDelais) {
        this.plainteDansDelais = plainteDansDelais;
    }

    public int getPlainteHorsDelais() {
        return plainteHorsDelais;
    }

    public void setPlainteHorsDelais(int plainteHorsDelais) {
        this.plainteHorsDelais = plainteHorsDelais;
    }

    public int getTotalPlainteRecus() {
        return totalPlainteRecus;
    }

    public void setTotalPlainteRecus(int totalPlainteRecus) {
        this.totalPlainteRecus = totalPlainteRecus;
    }

    public int getTotalPlainteTraiter() {
        return totalPlainteTraiter;
    }

    public void setTotalPlainteTraiter(int totalPlainteTraiter) {
        this.totalPlainteTraiter = totalPlainteTraiter;
    }

    public double getPourcentagePlainteDansDelais() {
        return pourcentagePlainteDansDelais;
    }

    public void setPourcentagePlainteDansDelais(double pourcentagePlainteDansDelais) {
        this.pourcentagePlainteDansDelais = pourcentagePlainteDansDelais;
    }

    public double getPourcentagePlainteTraiter() {
        return pourcentagePlainteTraiter;
    }

    public void setPourcentagePlainteTraiter(double pourcentagePlainteTraiter) {
        this.pourcentagePlainteTraiter = pourcentagePlainteTraiter;
    }

    public Tusers getResponsable() {
        return responsable;
    }

    public void setResponsable(Tusers responsable) {
        this.responsable = responsable;
    }

    public Tregions getRegion() {
        return region;
    }

    public void setRegion(Tregions region) {
        this.region = region;
    }

    public Tsecteurs getSecteur() {
        return secteur;
    }

    public void setSecteur(Tsecteurs secteur) {
        this.secteur = secteur;
    }

    public Trubriques getRubrique() {
        return rubrique;
    }

    public void setRubrique(Trubriques rubrique) {
        this.rubrique = rubrique;
    }

    public Tsrubriques getSrubrique() {
        return srubrique;
    }

    public void setSrubrique(Tsrubriques srubrique) {
        this.srubrique = srubrique;
    }

    public Tclients getClient() {
        return client;
    }

    public void setClient(Tclients client) {
        this.client = client;
    }

    public Tdistricts getDistrict() {
        return district;
    }

    public void setDistrict(Tdistricts district) {
        this.district = district;
    }

    public Tsources getSource() {
        return source;
    }

    public void setSource(Tsources source) {
        this.source = source;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEntite() {
        return entite;
    }

    public void setEntite(String entite) {
        this.entite = entite;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getStatut1() {
        return statut1;
    }

    public void setStatut1(int statut1) {
        this.statut1 = statut1;
    }

    public int getStatut2() {
        return statut2;
    }

    public void setStatut2(int statut2) {
        this.statut2 = statut2;
    }

    public int getStatut3() {
        return statut3;
    }

    public void setStatut3(int statut3) {
        this.statut3 = statut3;
    }

    public int getStatut4() {
        return statut4;
    }

    public void setStatut4(int statut4) {
        this.statut4 = statut4;
    }

}
