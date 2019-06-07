package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.CompteEmballage;
import com.eh2s.ocm.Entity.CompteRistourne;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tsecteurs;
import com.eh2s.ocm.Entity.Ttypeclients;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tclients.class)
public class Tclients_ { 

    public static volatile SingularAttribute<Tclients, String> psd;
    public static volatile SingularAttribute<Tclients, Tourner> tourner;
    public static volatile SingularAttribute<Tclients, Integer> chgpwd;
    public static volatile SingularAttribute<Tclients, String> mail;
    public static volatile ListAttribute<Tclients, LigneAccount> ligneAccountList;
    public static volatile ListAttribute<Tclients, Tincidents> tincidentsList;
    public static volatile SingularAttribute<Tclients, String> codeclient;
    public static volatile SingularAttribute<Tclients, String> telephone2;
    public static volatile SingularAttribute<Tclients, Date> datemodification;
    public static volatile SingularAttribute<Tclients, String> telephone;
    public static volatile SingularAttribute<Tclients, String> nom;
    public static volatile ListAttribute<Tclients, CompteEmballage> compteEmballageList;
    public static volatile SingularAttribute<Tclients, Societe> societe;
    public static volatile SingularAttribute<Tclients, Tsecteurs> secteurid;
    public static volatile SingularAttribute<Tclients, String> password;
    public static volatile SingularAttribute<Tclients, String> adresse;
    public static volatile SingularAttribute<Tclients, Date> datecreation;
    public static volatile ListAttribute<Tclients, CompteRistourne> compteRistourneList;
    public static volatile SingularAttribute<Tclients, Integer> id;
    public static volatile SingularAttribute<Tclients, String> prenom;
    public static volatile SingularAttribute<Tclients, Ttypeclients> typeclientid;
    public static volatile ListAttribute<Tclients, Tlignecommande> tlignecommandeList;

}