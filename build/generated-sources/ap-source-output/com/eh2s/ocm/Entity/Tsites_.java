package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tservices;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tsites.class)
public class Tsites_ { 

    public static volatile SingularAttribute<Tsites, Tregions> regionid;
    public static volatile SingularAttribute<Tsites, String> name;
    public static volatile SingularAttribute<Tsites, Integer> id;
    public static volatile ListAttribute<Tsites, Tservices> tservicesList;

}