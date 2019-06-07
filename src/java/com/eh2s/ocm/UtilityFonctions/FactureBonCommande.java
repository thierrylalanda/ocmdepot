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
public class FactureBonCommande {
    double netfacture =0;
    double montantdecaisse = 0;
    double dettFournisseur = 0;
    int quantite = 0;

    public double getNetfacture() {
        return netfacture;
    }

    public void setNetfacture(double netfacture) {
        this.netfacture = netfacture;
    }

    public double getMontantdecaisse() {
        return montantdecaisse;
    }

    public void setMontantdecaisse(double montantdecaisse) {
        this.montantdecaisse = montantdecaisse;
    }

    public double getDettFournisseur() {
        return dettFournisseur;
    }

    public void setDettFournisseur(double dettFournisseur) {
        this.dettFournisseur = dettFournisseur;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    
}
