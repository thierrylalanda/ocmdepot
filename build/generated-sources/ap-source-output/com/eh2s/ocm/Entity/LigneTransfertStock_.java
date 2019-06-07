package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.TransfertStock;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(LigneTransfertStock.class)
public class LigneTransfertStock_ { 

    public static volatile ListAttribute<LigneTransfertStock, TransfertStock> transfertStockList;
    public static volatile SingularAttribute<LigneTransfertStock, Magasin> mg1;
    public static volatile SingularAttribute<LigneTransfertStock, Magasin> mg2;
    public static volatile SingularAttribute<LigneTransfertStock, Integer> id;
    public static volatile SingularAttribute<LigneTransfertStock, Date> dateTransf;
    public static volatile SingularAttribute<LigneTransfertStock, String> operateur;

}