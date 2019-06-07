package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SortieCaisse;
import com.eh2s.ocm.Entity.Tusers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(LigneSortie.class)
public class LigneSortie_ { 

    public static volatile SingularAttribute<LigneSortie, Double> montantNet;
    public static volatile SingularAttribute<LigneSortie, Date> dateUpdate;
    public static volatile SingularAttribute<LigneSortie, Fournisseur> fournisseur;
    public static volatile SingularAttribute<LigneSortie, Integer> id;
    public static volatile SingularAttribute<LigneSortie, Tusers> operateur;
    public static volatile SingularAttribute<LigneSortie, Date> dateCreate;
    public static volatile ListAttribute<LigneSortie, SortieCaisse> sortietList;
    public static volatile SingularAttribute<LigneSortie, Integer> etat;
    public static volatile SingularAttribute<LigneSortie, String> commentaire;
    public static volatile SingularAttribute<LigneSortie, Societe> societe;
    public static volatile SingularAttribute<LigneSortie, LigneCommandeFournisseur> numeroBon;
    public static volatile SingularAttribute<LigneSortie, Double> montantRestant;

}