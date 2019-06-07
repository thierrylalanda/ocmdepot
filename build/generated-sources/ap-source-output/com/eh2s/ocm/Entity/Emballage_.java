package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.CommandeEmballage;
import com.eh2s.ocm.Entity.CompteEmballage;
import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tarticles;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Emballage.class)
public class Emballage_ { 

    public static volatile SingularAttribute<Emballage, Integer> seuil;
    public static volatile SingularAttribute<Emballage, String> code;
    public static volatile SingularAttribute<Emballage, Double> prix;
    public static volatile ListAttribute<Emballage, Tarticles> tarticlesList;
    public static volatile SingularAttribute<Emballage, Integer> id;
    public static volatile SingularAttribute<Emballage, Double> stock;
    public static volatile SingularAttribute<Emballage, String> nom;
    public static volatile SingularAttribute<Emballage, Double> prixTotal;
    public static volatile ListAttribute<Emballage, CompteEmballage> compteEmballageList;
    public static volatile ListAttribute<Emballage, CommandeEmballage> commandeEmballageList;
    public static volatile ListAttribute<Emballage, Inventaire> inventaireList;
    public static volatile SingularAttribute<Emballage, Societe> societe;

}