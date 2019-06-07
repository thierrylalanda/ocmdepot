package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.LigneTransfertStock;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.StockMg;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tusers;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Magasin.class)
public class Magasin_ { 

    public static volatile ListAttribute<Magasin, LigneTransfertStock> lignetransfertstockList;
    public static volatile ListAttribute<Magasin, LigneTransfertStock> lignetransfertstockList1;
    public static volatile SingularAttribute<Magasin, Tusers> magasigner;
    public static volatile ListAttribute<Magasin, LigneInventaire> ligneInventaireList;
    public static volatile ListAttribute<Magasin, Tourner> tournerList;
    public static volatile ListAttribute<Magasin, Tusers> tusersList;
    public static volatile SingularAttribute<Magasin, Integer> id;
    public static volatile SingularAttribute<Magasin, Tregions> region;
    public static volatile SingularAttribute<Magasin, String> nom;
    public static volatile ListAttribute<Magasin, StockMg> stockMgList;
    public static volatile SingularAttribute<Magasin, Societe> societe;

}