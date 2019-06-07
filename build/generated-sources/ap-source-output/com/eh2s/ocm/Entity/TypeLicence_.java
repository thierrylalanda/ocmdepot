package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.SouscriptionLicence;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(TypeLicence.class)
public class TypeLicence_ { 

    public static volatile SingularAttribute<TypeLicence, Integer> code;
    public static volatile SingularAttribute<TypeLicence, Integer> testMode;
    public static volatile ListAttribute<TypeLicence, SouscriptionLicence> souscriptionLicenceList;
    public static volatile SingularAttribute<TypeLicence, Integer> id;
    public static volatile SingularAttribute<TypeLicence, Integer> initUser;
    public static volatile SingularAttribute<TypeLicence, String> type;
    public static volatile SingularAttribute<TypeLicence, Integer> maxUser;

}