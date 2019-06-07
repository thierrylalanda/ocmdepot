package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Tclients;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(CompteEmballage.class)
public class CompteEmballage_ { 

    public static volatile SingularAttribute<CompteEmballage, Emballage> emballage;
    public static volatile SingularAttribute<CompteEmballage, Tclients> client;
    public static volatile SingularAttribute<CompteEmballage, Double> montant;
    public static volatile SingularAttribute<CompteEmballage, Integer> id;
    public static volatile SingularAttribute<CompteEmballage, Double> quantite;

}