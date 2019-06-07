package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tsites;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tregions.class)
public class Tregions_ { 

    public static volatile SingularAttribute<Tregions, String> tel1;
    public static volatile SingularAttribute<Tregions, String> tel2;
    public static volatile ListAttribute<Tregions, Magasin> magasinList;
    public static volatile ListAttribute<Tregions, Tsites> tsitesList;
    public static volatile SingularAttribute<Tregions, String> mail;
    public static volatile SingularAttribute<Tregions, String> name;
    public static volatile ListAttribute<Tregions, Tdistricts> tdistrictsList;
    public static volatile SingularAttribute<Tregions, Integer> id;
    public static volatile SingularAttribute<Tregions, Societe> societe;

}