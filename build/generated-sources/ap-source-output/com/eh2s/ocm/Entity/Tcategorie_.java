package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tarticles;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tcategorie.class)
public class Tcategorie_ { 

    public static volatile ListAttribute<Tcategorie, Tarticles> tarticlesList;
    public static volatile SingularAttribute<Tcategorie, Integer> id;
    public static volatile SingularAttribute<Tcategorie, String> nom;
    public static volatile SingularAttribute<Tcategorie, Societe> societe;

}