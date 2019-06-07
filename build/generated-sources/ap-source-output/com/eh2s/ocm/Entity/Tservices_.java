package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tsites;
import com.eh2s.ocm.Entity.Tusers;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tservices.class)
public class Tservices_ { 

    public static volatile SingularAttribute<Tservices, Tsites> site;
    public static volatile SingularAttribute<Tservices, String> name;
    public static volatile ListAttribute<Tservices, Tusers> tusersList;
    public static volatile SingularAttribute<Tservices, Integer> id;

}