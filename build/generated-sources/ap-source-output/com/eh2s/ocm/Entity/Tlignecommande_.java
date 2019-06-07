package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.CommandeEmballage;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tcommandes;
import com.eh2s.ocm.Entity.Tetatc;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tusers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tlignecommande.class)
public class Tlignecommande_ { 

    public static volatile SingularAttribute<Tlignecommande, Double> cassier;
    public static volatile SingularAttribute<Tlignecommande, Tetatc> etatc;
    public static volatile ListAttribute<Tlignecommande, Tcommandes> tcommandesList;
    public static volatile SingularAttribute<Tlignecommande, String> signature;
    public static volatile ListAttribute<Tlignecommande, LigneAccount> ligneAccountList;
    public static volatile SingularAttribute<Tlignecommande, Integer> isModif;
    public static volatile SingularAttribute<Tlignecommande, Double> qtotal;
    public static volatile SingularAttribute<Tlignecommande, Tclients> client;
    public static volatile SingularAttribute<Tlignecommande, Date> datec;
    public static volatile SingularAttribute<Tlignecommande, Integer> id;
    public static volatile SingularAttribute<Tlignecommande, Date> datemodif;
    public static volatile SingularAttribute<Tlignecommande, Double> margefournisseur;
    public static volatile SingularAttribute<Tlignecommande, String> commentaire;
    public static volatile SingularAttribute<Tlignecommande, Date> datelivraison;
    public static volatile SingularAttribute<Tlignecommande, Tourner> tourner;
    public static volatile SingularAttribute<Tlignecommande, Double> margeclient;
    public static volatile SingularAttribute<Tlignecommande, Double> remise;
    public static volatile SingularAttribute<Tlignecommande, Integer> transport;
    public static volatile SingularAttribute<Tlignecommande, Integer> numc;
    public static volatile SingularAttribute<Tlignecommande, Societe> societe;
    public static volatile SingularAttribute<Tlignecommande, Tusers> preselleur;
    public static volatile SingularAttribute<Tlignecommande, Double> prixtotal;
    public static volatile SingularAttribute<Tlignecommande, String> operateur;
    public static volatile ListAttribute<Tlignecommande, CommandeEmballage> commandeEmballageList;
    public static volatile SingularAttribute<Tlignecommande, Integer> statut;

}