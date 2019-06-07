package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.TgroupsAssoc;
import com.eh2s.ocm.Entity.Tsmenu;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tactions.class)
public class Tactions_ { 

    public static volatile SingularAttribute<Tactions, Integer> codeAction;
    public static volatile SingularAttribute<Tactions, Tsmenu> smenu;
    public static volatile SingularAttribute<Tactions, String> nomAction;
    public static volatile ListAttribute<Tactions, TgroupsAssoc> tgroupsAssocList;
    public static volatile SingularAttribute<Tactions, Integer> idAction;

}