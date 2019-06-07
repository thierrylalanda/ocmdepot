/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.UtilityFonctions;

/**
 *
 * @author messi01
 */
public class FactureClient {

    double netfacture = 0;
    double montantencaisse = 0;
    double dettclient = 0;
    double remise = 0;
    double margeFournisseur = 0;
    int quantite = 0;

    public double getNetfacture() {
        return netfacture;
    }

    public void setNetfacture(double netfacture) {
        this.netfacture = netfacture;
    }

    public double getMontantencaisse() {
        return montantencaisse;
    }

    public void setMontantencaisse(double montantencaisse) {
        this.montantencaisse = montantencaisse;
    }

    public double getDettclient() {
        return dettclient;
    }

    public void setDettclient(double dettclient) {
        this.dettclient = dettclient;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getMargeFournisseur() {
        return margeFournisseur;
    }

    public void setMargeFournisseur(double margeFournisseur) {
        this.margeFournisseur = margeFournisseur;
    }

    
    
}
