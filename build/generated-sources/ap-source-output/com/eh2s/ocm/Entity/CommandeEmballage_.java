package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Tlignecommande;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(CommandeEmballage.class)
public class CommandeEmballage_ { 

    public static volatile SingularAttribute<CommandeEmballage, Tlignecommande> ligne;
    public static volatile SingularAttribute<CommandeEmballage, Double> qt;
    public static volatile SingularAttribute<CommandeEmballage, Double> prix;
    public static volatile SingularAttribute<CommandeEmballage, Emballage> emballage;
    public static volatile SingularAttribute<CommandeEmballage, Integer> id;
    public static volatile SingularAttribute<CommandeEmballage, Double> remise;
    public static volatile SingularAttribute<CommandeEmballage, Double> prixTotal;
    public static volatile SingularAttribute<CommandeEmballage, Double> quantite;

}