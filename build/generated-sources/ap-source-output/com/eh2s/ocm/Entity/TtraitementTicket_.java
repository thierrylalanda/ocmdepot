package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tusers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(TtraitementTicket.class)
public class TtraitementTicket_ { 

    public static volatile SingularAttribute<TtraitementTicket, Date> dateComment;
    public static volatile SingularAttribute<TtraitementTicket, Integer> id;
    public static volatile SingularAttribute<TtraitementTicket, String> commentaire;
    public static volatile SingularAttribute<TtraitementTicket, Tincidents> incident;
    public static volatile SingularAttribute<TtraitementTicket, Tusers> user;

}