package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tincidents;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tpriority.class)
public class Tpriority_ { 

    public static volatile SingularAttribute<Tpriority, Integer> number;
    public static volatile ListAttribute<Tpriority, Tincidents> tincidentsList;
    public static volatile SingularAttribute<Tpriority, String> name;
    public static volatile SingularAttribute<Tpriority, Integer> id;
    public static volatile SingularAttribute<Tpriority, Societe> societe;

}