package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.AffectTournerUser;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tlignecommande;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tourner.class)
public class Tourner_ { 

    public static volatile ListAttribute<Tourner, AffectTournerUser> affectTournerUserList;
    public static volatile SingularAttribute<Tourner, Integer> id;
    public static volatile SingularAttribute<Tourner, Magasin> magasin;
    public static volatile SingularAttribute<Tourner, String> numc;
    public static volatile SingularAttribute<Tourner, Societe> societe;
    public static volatile ListAttribute<Tourner, Tclients> tclientsList;
    public static volatile ListAttribute<Tourner, Tlignecommande> tlignecommandeList;

}