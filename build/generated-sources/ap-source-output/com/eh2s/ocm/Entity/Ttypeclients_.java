package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Ttypeclients.class)
public class Ttypeclients_ { 

    public static volatile SingularAttribute<Ttypeclients, String> name;
    public static volatile SingularAttribute<Ttypeclients, Integer> id;
    public static volatile ListAttribute<Ttypeclients, Tclients> tclientsList;
    public static volatile SingularAttribute<Ttypeclients, Societe> societe;

}