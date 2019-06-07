package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.SouscriptionLicence;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tpriority;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Trubriques;
import com.eh2s.ocm.Entity.Tsources;
import com.eh2s.ocm.Entity.Ttypeclients;
import com.eh2s.ocm.Entity.Tusers;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Societe.class)
public class Societe_ { 

    public static volatile SingularAttribute<Societe, Integer> gesttourner;
    public static volatile SingularAttribute<Societe, Integer> autosaveclient;
    public static volatile ListAttribute<Societe, LigneAccount> ligneAccountList;
    public static volatile SingularAttribute<Societe, String> cniRc;
    public static volatile SingularAttribute<Societe, Integer> gestfournisseur;
    public static volatile SingularAttribute<Societe, String> nom;
    public static volatile SingularAttribute<Societe, String> sigle;
    public static volatile SingularAttribute<Societe, String> regimeFiscal;
    public static volatile ListAttribute<Societe, Caisse> caisseList;
    public static volatile ListAttribute<Societe, LigneInventaire> ligneInventaireList;
    public static volatile SingularAttribute<Societe, String> logo;
    public static volatile ListAttribute<Societe, Trubriques> trubriquesList;
    public static volatile ListAttribute<Societe, Tusers> tusersList;
    public static volatile SingularAttribute<Societe, String> tel;
    public static volatile SingularAttribute<Societe, Integer> id;
    public static volatile SingularAttribute<Societe, String> logoBase64;
    public static volatile ListAttribute<Societe, Fournisseur> fournisseurList;
    public static volatile ListAttribute<Societe, LigneSortie> ligneSortieList;
    public static volatile SingularAttribute<Societe, String> email;
    public static volatile ListAttribute<Societe, Tgroups> tgroupsList;
    public static volatile SingularAttribute<Societe, Integer> gestmagasin;
    public static volatile SingularAttribute<Societe, String> centreImpot;
    public static volatile SingularAttribute<Societe, Integer> gestcassier;
    public static volatile SingularAttribute<Societe, Integer> gestcaisse;
    public static volatile SingularAttribute<Societe, Integer> gestemballage;
    public static volatile ListAttribute<Societe, Tcategorie> tcategorieList;
    public static volatile ListAttribute<Societe, SouscriptionLicence> souscriptionLicenceList;
    public static volatile SingularAttribute<Societe, String> codePostal;
    public static volatile SingularAttribute<Societe, String> activite;
    public static volatile ListAttribute<Societe, Emballage> emballageList;
    public static volatile ListAttribute<Societe, Magasin> magasinList;
    public static volatile SingularAttribute<Societe, Integer> gestmarge;
    public static volatile ListAttribute<Societe, Tourner> tournerList;
    public static volatile ListAttribute<Societe, Ttypeclients> ttypeclientsList;
    public static volatile SingularAttribute<Societe, String> adresse;
    public static volatile ListAttribute<Societe, Tpriority> tpriorityList;
    public static volatile ListAttribute<Societe, Tsources> tsourcesList;
    public static volatile ListAttribute<Societe, Tregions> tregionsList;
    public static volatile SingularAttribute<Societe, String> immatriculation;
    public static volatile SingularAttribute<Societe, Integer> maintenance;
    public static volatile SingularAttribute<Societe, Integer> geststock;
    public static volatile ListAttribute<Societe, Tclients> tclientsList;
    public static volatile ListAttribute<Societe, Tlignecommande> tlignecommandeList;

}