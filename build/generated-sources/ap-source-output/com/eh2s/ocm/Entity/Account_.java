package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.SourceMouvementCaisse;
import com.eh2s.ocm.Entity.Tusers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, Double> montantInit;
    public static volatile SingularAttribute<Account, Double> soldeCaisse;
    public static volatile SingularAttribute<Account, Date> date;
    public static volatile SingularAttribute<Account, Caisse> caisse;
    public static volatile SingularAttribute<Account, Double> avance;
    public static volatile SingularAttribute<Account, LigneAccount> ligneAccount;
    public static volatile SingularAttribute<Account, Integer> id;
    public static volatile SingularAttribute<Account, Tusers> operateur;
    public static volatile SingularAttribute<Account, SourceMouvementCaisse> source;
    public static volatile SingularAttribute<Account, String> commentaire;
    public static volatile SingularAttribute<Account, Double> montantRestant;

}