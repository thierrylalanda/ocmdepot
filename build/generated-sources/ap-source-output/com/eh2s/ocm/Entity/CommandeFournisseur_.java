package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.Tarticles;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(CommandeFournisseur.class)
public class CommandeFournisseur_ { 

    public static volatile SingularAttribute<CommandeFournisseur, Double> prix;
    public static volatile SingularAttribute<CommandeFournisseur, Double> quantiteRecu;
    public static volatile SingularAttribute<CommandeFournisseur, LigneCommandeFournisseur> ligneCommandeFournisseur;
    public static volatile SingularAttribute<CommandeFournisseur, Integer> id;
    public static volatile SingularAttribute<CommandeFournisseur, Double> prixTotal;
    public static volatile SingularAttribute<CommandeFournisseur, Integer> etat;
    public static volatile SingularAttribute<CommandeFournisseur, Tarticles> article;
    public static volatile SingularAttribute<CommandeFournisseur, Double> quantite;

}