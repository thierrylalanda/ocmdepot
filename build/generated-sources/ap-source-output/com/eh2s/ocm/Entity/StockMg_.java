package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Tarticles;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(StockMg.class)
public class StockMg_ { 

    public static volatile SingularAttribute<StockMg, Double> stockV;
    public static volatile SingularAttribute<StockMg, Double> prix;
    public static volatile SingularAttribute<StockMg, Integer> id;
    public static volatile SingularAttribute<StockMg, Double> stock;
    public static volatile SingularAttribute<StockMg, Magasin> magasin;
    public static volatile SingularAttribute<StockMg, Double> prixTotal;
    public static volatile SingularAttribute<StockMg, Tarticles> article;

}