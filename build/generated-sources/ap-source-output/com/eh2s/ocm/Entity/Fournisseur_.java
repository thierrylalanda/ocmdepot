package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tarticles;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Fournisseur.class)
public class Fournisseur_ { 

    public static volatile SingularAttribute<Fournisseur, String> code;
    public static volatile SingularAttribute<Fournisseur, String> mail;
    public static volatile SingularAttribute<Fournisseur, String> adresse;
    public static volatile ListAttribute<Fournisseur, Tarticles> tarticlesList;
    public static volatile SingularAttribute<Fournisseur, String> telephone;
    public static volatile SingularAttribute<Fournisseur, Integer> id;
    public static volatile SingularAttribute<Fournisseur, String> nom;
    public static volatile ListAttribute<Fournisseur, LigneSortie> ligneSortieList;
    public static volatile SingularAttribute<Fournisseur, Societe> societe;
    public static volatile ListAttribute<Fournisseur, LigneCommandeFournisseur> ligneCommandeFournisseurList;

}