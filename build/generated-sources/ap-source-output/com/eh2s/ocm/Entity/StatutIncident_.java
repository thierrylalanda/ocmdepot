package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tincidents;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(StatutIncident.class)
public class StatutIncident_ { 

    public static volatile SingularAttribute<StatutIncident, Integer> code;
    public static volatile ListAttribute<StatutIncident, Tincidents> tincidentsList;
    public static volatile SingularAttribute<StatutIncident, String> nom;

}