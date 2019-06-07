package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tsrubriques;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Trubriques.class)
public class Trubriques_ { 

    public static volatile SingularAttribute<Trubriques, String> name;
    public static volatile SingularAttribute<Trubriques, Integer> id;
    public static volatile ListAttribute<Trubriques, Tsrubriques> tsrubriquesList;
    public static volatile SingularAttribute<Trubriques, Societe> societe;

}