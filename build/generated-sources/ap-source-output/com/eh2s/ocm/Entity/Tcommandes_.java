package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tlignecommande;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tcommandes.class)
public class Tcommandes_ { 

    public static volatile SingularAttribute<Tcommandes, Tlignecommande> ligne;
    public static volatile SingularAttribute<Tcommandes, Integer> qt;
    public static volatile SingularAttribute<Tcommandes, String> commantaire1;
    public static volatile SingularAttribute<Tcommandes, Double> prix;
    public static volatile SingularAttribute<Tcommandes, String> commantaire;
    public static volatile SingularAttribute<Tcommandes, Double> margeclient;
    public static volatile SingularAttribute<Tcommandes, Double> remise;
    public static volatile SingularAttribute<Tcommandes, Integer> id;
    public static volatile SingularAttribute<Tcommandes, Double> margefournisseur;
    public static volatile SingularAttribute<Tcommandes, Double> prixTotal;
    public static volatile SingularAttribute<Tcommandes, Tarticles> article;
    public static volatile SingularAttribute<Tcommandes, Double> quantite;

}