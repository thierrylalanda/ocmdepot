package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.Tarticles;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Inventaire.class)
public class Inventaire_ { 

    public static volatile SingularAttribute<Inventaire, Double> qtAvant;
    public static volatile SingularAttribute<Inventaire, Double> qtApres;
    public static volatile SingularAttribute<Inventaire, Emballage> emballage;
    public static volatile SingularAttribute<Inventaire, Double> ecartQt;
    public static volatile SingularAttribute<Inventaire, Integer> id;
    public static volatile SingularAttribute<Inventaire, Integer> type;
    public static volatile SingularAttribute<Inventaire, LigneInventaire> ligneInv;
    public static volatile SingularAttribute<Inventaire, Tarticles> article;
    public static volatile SingularAttribute<Inventaire, Double> quantite;

}