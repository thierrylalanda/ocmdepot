package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tdistricts;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tsecteurs.class)
public class Tsecteurs_ { 

    public static volatile SingularAttribute<Tsecteurs, Tdistricts> districtid;
    public static volatile SingularAttribute<Tsecteurs, String> name;
    public static volatile SingularAttribute<Tsecteurs, Integer> id;
    public static volatile ListAttribute<Tsecteurs, Tclients> tclientsList;

}