package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SortieCaisse;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Caisse.class)
public class Caisse_ { 

    public static volatile SingularAttribute<Caisse, Date> date;
    public static volatile SingularAttribute<Caisse, Integer> etatCaisse;
    public static volatile ListAttribute<Caisse, SortieCaisse> sortieCaisseList;
    public static volatile SingularAttribute<Caisse, Date> dateFermeture;
    public static volatile ListAttribute<Caisse, Account> accountList;
    public static volatile SingularAttribute<Caisse, Double> montantVerser;
    public static volatile SingularAttribute<Caisse, Double> ecart;
    public static volatile SingularAttribute<Caisse, Double> montant;
    public static volatile SingularAttribute<Caisse, Integer> id;
    public static volatile SingularAttribute<Caisse, String> commentaire;
    public static volatile SingularAttribute<Caisse, Societe> societe;
    public static volatile SingularAttribute<Caisse, Double> montantRestant;

}