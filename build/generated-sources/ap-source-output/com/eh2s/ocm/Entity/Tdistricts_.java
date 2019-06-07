package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tsecteurs;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tdistricts.class)
public class Tdistricts_ { 

    public static volatile SingularAttribute<Tdistricts, Tregions> regionid;
    public static volatile ListAttribute<Tdistricts, Tsecteurs> tsecteursList;
    public static volatile SingularAttribute<Tdistricts, String> name;
    public static volatile SingularAttribute<Tdistricts, Integer> id;

}