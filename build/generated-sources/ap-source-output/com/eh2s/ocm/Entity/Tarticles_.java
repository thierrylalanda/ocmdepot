package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.CommandeFournisseur;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.StockMg;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tcommandes;
import com.eh2s.ocm.Entity.TransfertStock;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tarticles.class)
public class Tarticles_ { 

    public static volatile SingularAttribute<Tarticles, Integer> seuil;
    public static volatile SingularAttribute<Tarticles, String> code;
    public static volatile SingularAttribute<Tarticles, Tcategorie> categorie;
    public static volatile SingularAttribute<Tarticles, Double> prixAchat;
    public static volatile ListAttribute<Tarticles, Tcommandes> tcommandesList;
    public static volatile SingularAttribute<Tarticles, Double> prix;
    public static volatile SingularAttribute<Tarticles, String> unite;
    public static volatile SingularAttribute<Tarticles, String> photo;
    public static volatile SingularAttribute<Tarticles, Double> margeclient;
    public static volatile SingularAttribute<Tarticles, Fournisseur> fournisseur;
    public static volatile SingularAttribute<Tarticles, String> chemin;
    public static volatile ListAttribute<Tarticles, CommandeFournisseur> commandeFournisseurList;
    public static volatile SingularAttribute<Tarticles, String> nom;
    public static volatile ListAttribute<Tarticles, TransfertStock> transfertStockList;
    public static volatile SingularAttribute<Tarticles, Emballage> emballage;
    public static volatile SingularAttribute<Tarticles, Integer> id;
    public static volatile SingularAttribute<Tarticles, Double> stock;
    public static volatile SingularAttribute<Tarticles, Double> margefournisseur;
    public static volatile ListAttribute<Tarticles, Inventaire> inventaireList;
    public static volatile ListAttribute<Tarticles, StockMg> stockMgList;

}