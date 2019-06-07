package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.LigneTransfertStock;
import com.eh2s.ocm.Entity.Tarticles;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(TransfertStock.class)
public class TransfertStock_ { 

    public static volatile SingularAttribute<TransfertStock, Integer> id;
    public static volatile SingularAttribute<TransfertStock, Tarticles> article;
    public static volatile SingularAttribute<TransfertStock, Integer> quantite;
    public static volatile SingularAttribute<TransfertStock, LigneTransfertStock> ligneTransfert;

}