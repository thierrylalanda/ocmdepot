package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.Tlignecommande;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tetatc.class)
public class Tetatc_ { 

    public static volatile SingularAttribute<Tetatc, Integer> code;
    public static volatile SingularAttribute<Tetatc, Integer> id;
    public static volatile SingularAttribute<Tetatc, String> nom;
    public static volatile ListAttribute<Tetatc, LigneCommandeFournisseur> ligneCommandeFournisseurList;
    public static volatile ListAttribute<Tetatc, Tlignecommande> tlignecommandeList;

}