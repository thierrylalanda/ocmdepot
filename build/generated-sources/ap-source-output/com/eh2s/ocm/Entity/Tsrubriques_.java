package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Trubriques;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tsrubriques.class)
public class Tsrubriques_ { 

    public static volatile SingularAttribute<Tsrubriques, Integer> delais;
    public static volatile SingularAttribute<Tsrubriques, Trubriques> rubriqueid;
    public static volatile ListAttribute<Tsrubriques, Tincidents> tincidentsList;
    public static volatile SingularAttribute<Tsrubriques, String> name;
    public static volatile SingularAttribute<Tsrubriques, Integer> id;

}