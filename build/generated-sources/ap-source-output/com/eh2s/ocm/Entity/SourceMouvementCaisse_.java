package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.SortieCaisse;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(SourceMouvementCaisse.class)
public class SourceMouvementCaisse_ { 

    public static volatile ListAttribute<SourceMouvementCaisse, SortieCaisse> sortieCaisseList;
    public static volatile ListAttribute<SourceMouvementCaisse, Account> accountList;
    public static volatile SingularAttribute<SourceMouvementCaisse, Integer> id;
    public static volatile SingularAttribute<SourceMouvementCaisse, String> nom;

}