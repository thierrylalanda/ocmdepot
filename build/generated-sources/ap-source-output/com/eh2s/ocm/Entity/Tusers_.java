package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.AffectTournerUser;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.RoleApk;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SortieCaisse;
import com.eh2s.ocm.Entity.Taffectzone;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tservices;
import com.eh2s.ocm.Entity.TtraitementTicket;
import com.eh2s.ocm.Entity.Userplainte;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tusers.class)
public class Tusers_ { 

    public static volatile SingularAttribute<Tusers, String> psd;
    public static volatile SingularAttribute<Tusers, Date> lastLogin;
    public static volatile SingularAttribute<Tusers, Integer> chgpwd;
    public static volatile SingularAttribute<Tusers, String> firstname;
    public static volatile SingularAttribute<Tusers, Integer> fi;
    public static volatile ListAttribute<Tusers, TtraitementTicket> ttraitementTicketList;
    public static volatile SingularAttribute<Tusers, String> mail;
    public static volatile ListAttribute<Tusers, RoleApk> roleApkList;
    public static volatile ListAttribute<Tusers, LigneAccount> ligneAccountList;
    public static volatile SingularAttribute<Tusers, Date> datemodification;
    public static volatile SingularAttribute<Tusers, String> login;
    public static volatile SingularAttribute<Tusers, Magasin> magasin;
    public static volatile SingularAttribute<Tusers, Tgroups> groupeid;
    public static volatile SingularAttribute<Tusers, String> password;
    public static volatile ListAttribute<Tusers, Userplainte> userplainteList;
    public static volatile ListAttribute<Tusers, Taffectzone> taffectzoneList;
    public static volatile ListAttribute<Tusers, LigneInventaire> ligneInventaireList;
    public static volatile SingularAttribute<Tusers, Date> datecreation;
    public static volatile SingularAttribute<Tusers, Integer> id;
    public static volatile ListAttribute<Tusers, LigneSortie> ligneSortieList;
    public static volatile SingularAttribute<Tusers, String> address1;
    public static volatile ListAttribute<Tusers, Tincidents> tincidentsList;
    public static volatile ListAttribute<Tusers, AffectTournerUser> affectTournerUserList;
    public static volatile SingularAttribute<Tusers, Societe> societe;
    public static volatile SingularAttribute<Tusers, String> lastname;
    public static volatile ListAttribute<Tusers, SortieCaisse> sortieCaisseList;
    public static volatile SingularAttribute<Tusers, Boolean> deleted;
    public static volatile SingularAttribute<Tusers, String> phone;
    public static volatile SingularAttribute<Tusers, Integer> disable;
    public static volatile ListAttribute<Tusers, Account> accountList;
    public static volatile SingularAttribute<Tusers, String> fonction;
    public static volatile SingularAttribute<Tusers, Tservices> serviceid;
    public static volatile ListAttribute<Tusers, LigneCommandeFournisseur> ligneCommandeFournisseurList;
    public static volatile ListAttribute<Tusers, Tlignecommande> tlignecommandeList;

}