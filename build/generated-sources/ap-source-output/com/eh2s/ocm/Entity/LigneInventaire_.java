package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tusers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:45")
@StaticMetamodel(LigneInventaire.class)
public class LigneInventaire_ { 

    public static volatile SingularAttribute<LigneInventaire, Date> dateInv;
    public static volatile SingularAttribute<LigneInventaire, String> description;
    public static volatile SingularAttribute<LigneInventaire, Integer> isInv;
    public static volatile SingularAttribute<LigneInventaire, Integer> id;
    public static volatile SingularAttribute<LigneInventaire, Tusers> operateur;
    public static volatile SingularAttribute<LigneInventaire, Integer> type;
    public static volatile SingularAttribute<LigneInventaire, Magasin> magasin;
    public static volatile ListAttribute<LigneInventaire, Inventaire> inventaireList;
    public static volatile SingularAttribute<LigneInventaire, Societe> societe;

}