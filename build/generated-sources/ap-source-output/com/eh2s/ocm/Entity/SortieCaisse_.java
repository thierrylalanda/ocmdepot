package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.SourceMouvementCaisse;
import com.eh2s.ocm.Entity.Tusers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(SortieCaisse.class)
public class SortieCaisse_ { 

    public static volatile SingularAttribute<SortieCaisse, Date> date;
    public static volatile SingularAttribute<SortieCaisse, Double> soldeCaisse;
    public static volatile SingularAttribute<SortieCaisse, Caisse> caisse;
    public static volatile SingularAttribute<SortieCaisse, LigneSortie> ligneSortie;
    public static volatile SingularAttribute<SortieCaisse, Double> avance;
    public static volatile SingularAttribute<SortieCaisse, LigneCommandeFournisseur> facture;
    public static volatile SingularAttribute<SortieCaisse, Double> montant;
    public static volatile SingularAttribute<SortieCaisse, SourceMouvementCaisse> source;
    public static volatile SingularAttribute<SortieCaisse, Integer> id;
    public static volatile SingularAttribute<SortieCaisse, Tusers> operateur;
    public static volatile SingularAttribute<SortieCaisse, String> commentaire;
    public static volatile SingularAttribute<SortieCaisse, Double> montantRestant;

}