<%-- 
    Document   : secondNavBar
    Created on : 16 oct. 2018, 11:02:55
    Author     : eh2s001
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- Horizontal navigation-->
<div class="header-navbar navbar-expand-sm navbar navbar-horizontal navbar-fixed navbar-light navbar-without-dd-arrow navbar-shadow menu-border"
     role="navigation" data-menu="menu-wrapper">
    <!-- Horizontal menu content-->
    <div class="navbar-container main-menu-content" data-menu="menu-container">
        <!-- include ../../../includes/mixins-->
        <ul class="nav navbar-nav" id="main-menu-navigation" data-menu="menu-navigation">

            <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="ft-grid"></i><span>Plainte</span></a>
                <ul class="dropdown-menu">
                    <li data-menu="" class="<c:if test="${q=='client/nouvelIncident'}">active</c:if>"><a class="dropdown-item" href="ticketClient?q=client/nouvelIncident&action=jkdhfoldg458dgbjdg478962" data-toggle="dropdown">Ouvrir un Ticket<i class="icon-table"></i>
                                <submenu class="name"></submenu></a>
                        </li>
                        <li data-menu="" class="<c:if test="${q=='client/mestickets'}">active</c:if>"><a class="dropdown-item" href="ticketClient?q=client/mestickets&action=jkdhfoldg458dgbjdg478962" data-toggle="dropdown">Mes Tickets<i class="icon-table"></i>
                                <submenu class="name"></submenu></a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="ft-grid"></i><span>Commande</span></a>
                    <ul class="dropdown-menu">
                        <li data-menu="" class="<c:if test="${q=='client/addcommande'}">active</c:if>"><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=client/addcommande&isNew=8" data-toggle="dropdown">Nouvelle Commande<i class="icon-table"></i>
                                <submenu class="name"></submenu></a>
                        </li>
                        <li data-menu="" class="<c:if test="${q=='client/commande'}">active</c:if>"><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=client/commande&isNew=8" data-toggle="dropdown">Mes Commandes<i class="icon-table"></i>
                                <submenu class="name"></submenu></a>
                        </li>
                        <li data-menu="" class="<c:if test="${q=='client/validation'}">active</c:if>"><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=client/validation&isNew=8" data-toggle="dropdown">Validation<i class="icon-table"></i>
                                <submenu class="name"></submenu></a>
                        </li>
                        <li data-menu="" class="<c:if test="${q=='client/reception'}">active</c:if>"><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=client/reception&isNew=8" data-toggle="dropdown">Reception<i class="icon-table"></i>
                                <submenu class="name"></submenu></a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="ft-monitor"></i><span>Historique</span></a>
                    <ul class="dropdown-menu">
                       <li data-menu="" class="<c:if test="${q=='client/historiqueIncident'}">active</c:if>"><a class="dropdown-item" href="ticketClient?q=client/historiqueIncident&action=jkdhfoldg458dgbjdg478962" data-toggle="dropdown">Ticket<i class="icon-table"></i>
                                <submenu class="name"></submenu></a>
                        </li>
                        <li data-menu="" class="<c:if test="${q=='client/historiqueCommande'}">active</c:if>"><a class="dropdown-item" href="gestionCommandes?action=model&model=HistoriqueCommande&q=client/historiqueCommande&isNew=0&client=${sessionScope.client.getId()}" data-toggle="dropdown">Commande<i class="icon-table"></i>
                                <submenu class="name"></submenu></a>
                        </li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- /horizontal menu content-->
</div>
<!-- Horizontal navigation-->