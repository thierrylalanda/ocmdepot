package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.TgroupsAssoc;
import com.eh2s.ocm.Entity.Tusers;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tgroups.class)
public class Tgroups_ { 

    public static volatile SingularAttribute<Tgroups, String> name;
    public static volatile ListAttribute<Tgroups, Tusers> tusersList;
    public static volatile SingularAttribute<Tgroups, Integer> id;
    public static volatile SingularAttribute<Tgroups, Integer> type;
    public static volatile ListAttribute<Tgroups, TgroupsAssoc> tgroupsAssocList;
    public static volatile SingularAttribute<Tgroups, Societe> societe;

}