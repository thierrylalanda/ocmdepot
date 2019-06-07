package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tincidents;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tsources.class)
public class Tsources_ { 

    public static volatile ListAttribute<Tsources, Tincidents> tincidentsList;
    public static volatile SingularAttribute<Tsources, String> name;
    public static volatile SingularAttribute<Tsources, Integer> id;
    public static volatile SingularAttribute<Tsources, Societe> societe;

}