package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tclients;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(CompteRistourne.class)
public class CompteRistourne_ { 

    public static volatile SingularAttribute<CompteRistourne, Date> dateDebut;
    public static volatile SingularAttribute<CompteRistourne, Tclients> client;
    public static volatile SingularAttribute<CompteRistourne, Double> montant;
    public static volatile SingularAttribute<CompteRistourne, Integer> id;
    public static volatile SingularAttribute<CompteRistourne, Date> dateFin;
    public static volatile SingularAttribute<CompteRistourne, Integer> etat;

}