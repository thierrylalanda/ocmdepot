package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tactions;
import com.eh2s.ocm.Entity.Tmenu;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tsmenu.class)
public class Tsmenu_ { 

    public static volatile SingularAttribute<Tsmenu, Integer> id;
    public static volatile ListAttribute<Tsmenu, Tactions> tactionsList;
    public static volatile SingularAttribute<Tsmenu, Tmenu> menu;
    public static volatile SingularAttribute<Tsmenu, String> nom;

}