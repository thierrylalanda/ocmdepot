package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.CommandeFournisseur;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.SortieCaisse;
import com.eh2s.ocm.Entity.Tetatc;
import com.eh2s.ocm.Entity.Tusers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(LigneCommandeFournisseur.class)
public class LigneCommandeFournisseur_ { 

    public static volatile SingularAttribute<LigneCommandeFournisseur, Fournisseur> fournisseur;
    public static volatile ListAttribute<LigneCommandeFournisseur, CommandeFournisseur> commandeFournisseurList;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Date> dateCommande;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Integer> transport;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Double> prixTotal;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Tetatc> etat;
    public static volatile ListAttribute<LigneCommandeFournisseur, SortieCaisse> sortieCaisseList;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Date> dateLivraison;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Double> quantiteTotal;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Integer> id;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Date> dateModif;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Tusers> operateur;
    public static volatile SingularAttribute<LigneCommandeFournisseur, String> commentaire;
    public static volatile ListAttribute<LigneCommandeFournisseur, LigneSortie> ligneSortieList;
    public static volatile SingularAttribute<LigneCommandeFournisseur, Integer> statut;

}