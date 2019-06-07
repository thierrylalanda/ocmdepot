package com.eh2s.ocm.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-04T12:33:46")
@StaticMetamodel(Notification.class)
public class Notification_ { 

    public static volatile SingularAttribute<Notification, String> titel;
    public static volatile SingularAttribute<Notification, Integer> client;
    public static volatile SingularAttribute<Notification, Integer> lut;
    public static volatile SingularAttribute<Notification, Integer> id;
    public static volatile SingularAttribute<Notification, Date> dateNotif;
    public static volatile SingularAttribute<Notification, String> message;
    public static volatile SingularAttribute<Notification, Integer> users;

}