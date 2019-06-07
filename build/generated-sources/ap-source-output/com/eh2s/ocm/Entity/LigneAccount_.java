package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tusers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(LigneAccount.class)
public class LigneAccount_ { 

    public static volatile SingularAttribute<LigneAccount, Double> montantNet;
    public static volatile SingularAttribute<LigneAccount, Date> dateUpdate;
    public static volatile ListAttribute<LigneAccount, Account> accountList;
    public static volatile SingularAttribute<LigneAccount, Tclients> client;
    public static volatile SingularAttribute<LigneAccount, Integer> id;
    public static volatile SingularAttribute<LigneAccount, Tusers> operateur;
    public static volatile SingularAttribute<LigneAccount, Date> dateCreate;
    public static volatile SingularAttribute<LigneAccount, Integer> etat;
    public static volatile SingularAttribute<LigneAccount, String> commentaire;
    public static volatile SingularAttribute<LigneAccount, Tlignecommande> ligneCommande;
    public static volatile SingularAttribute<LigneAccount, Societe> societe;
    public static volatile SingularAttribute<LigneAccount, Double> montantRestant;

}