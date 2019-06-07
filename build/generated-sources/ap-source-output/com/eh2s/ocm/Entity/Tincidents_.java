package com.eh2s.ocm.Entity;

import com.eh2s.ocm.Entity.PieceJointe;
import com.eh2s.ocm.Entity.StatutIncident;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tpriority;
import com.eh2s.ocm.Entity.Tsources;
import com.eh2s.ocm.Entity.Tsrubriques;
import com.eh2s.ocm.Entity.TtraitementTicket;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.Entity.Userplainte;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Tincidents.class)
public class Tincidents_ { 

    public static volatile SingularAttribute<Tincidents, Tsources> sourceid;
    public static volatile SingularAttribute<Tincidents, Tusers> creator;
    public static volatile SingularAttribute<Tincidents, Date> dateFer;
    public static volatile SingularAttribute<Tincidents, Tclients> clientid;
    public static volatile ListAttribute<Tincidents, TtraitementTicket> ttraitementTicketList;
    public static volatile SingularAttribute<Tincidents, Integer> isaffect;
    public static volatile SingularAttribute<Tincidents, String> description;
    public static volatile SingularAttribute<Tincidents, String> photo;
    public static volatile SingularAttribute<Tincidents, String> title;
    public static volatile SingularAttribute<Tincidents, Date> dateCreate;
    public static volatile ListAttribute<Tincidents, PieceJointe> pieceJointeList;
    public static volatile SingularAttribute<Tincidents, String> descriptionFermeture;
    public static volatile SingularAttribute<Tincidents, Integer> IsInDelais;
    public static volatile ListAttribute<Tincidents, Userplainte> userplainteList;
    public static volatile SingularAttribute<Tincidents, Tsrubriques> srubriqueid;
    public static volatile SingularAttribute<Tincidents, Integer> id;
    public static volatile SingularAttribute<Tincidents, StatutIncident> state;
    public static volatile SingularAttribute<Tincidents, Date> dateModif;
    public static volatile SingularAttribute<Tincidents, Tpriority> prioriteid;
    public static volatile SingularAttribute<Tincidents, Integer> diffday;
    public static volatile SingularAttribute<Tincidents, Date> dateHope;

}