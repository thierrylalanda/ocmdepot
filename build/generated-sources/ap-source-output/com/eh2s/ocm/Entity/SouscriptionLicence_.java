package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.TypeLicence;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(SouscriptionLicence.class)
public class SouscriptionLicence_ { 

    public static volatile SingularAttribute<SouscriptionLicence, Date> dateSous;
    public static volatile SingularAttribute<SouscriptionLicence, Date> dateFinTest;
    public static volatile SingularAttribute<SouscriptionLicence, Integer> id;
    public static volatile SingularAttribute<SouscriptionLicence, TypeLicence> type;
    public static volatile SingularAttribute<SouscriptionLicence, Societe> societe;
    public static volatile SingularAttribute<SouscriptionLicence, Integer> upUser;

}