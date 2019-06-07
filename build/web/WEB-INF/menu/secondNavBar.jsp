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

            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien1 or not empty sessionScope.lien5 or not empty sessionScope.lien9 or not empty sessionScope.lien13
                          or not empty sessionScope.lien17 or not empty sessionScope.lien21 or not empty sessionScope.lien25 or not empty sessionScope.lien29 or not empty sessionScope.lien34 or not empty sessionScope.lien38
                          or not empty sessionScope.lien42 or not empty sessionScope.lien46 or not empty sessionScope.lien50 or not empty sessionScope.lien54 
                          or not empty sessionScope.lien58 or not empty sessionScope.lien112 or not empty sessionScope.lien116}">
                  <li class="dropdown mega-dropdown nav-item" data-menu="megamenu"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="fa fa-cogs"></i><span>Administration</span></a>
                      <ul class="mega-dropdown-menu dropdown-menu row">
                          <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien1 or not empty sessionScope.lien5 or not empty sessionScope.lien9 or not empty sessionScope.lien13
                                        or not empty sessionScope.lien17 or not empty sessionScope.lien21}">
                                <li class="col-md-3" data-mega-col="col-md-3">
                                    <ul class="drilldown-menu">
                                        <li class="menu-list">
                                            <ul class="mega-menu-sub">
                                                <c:if test="${sessionScope.root=='EH2S'}">
                                                    <li class="<c:if test="${q=='administration/societe'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/societe"><i class="ft-home"></i>Societe</a>
                                                        </li>
                                                </c:if>
                                                <c:if test="${not empty sessionScope.lien1}">
                                                    <li class="<c:if test="${q=='administration/appsociete'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/appsociete"><i class="ft-home"></i>Societe</a>
                                                        </li>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien5 or not empty sessionScope.lien9 or not empty sessionScope.lien13
                                                              or not empty sessionScope.lien17 or not empty sessionScope.lien21}">
                                                      <li><a class="dropdown-item" href="#"><i class="ft-share"></i>Localisation</a>
                                                          <ul class="mega-menu-sub">
                                                              <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien5}">
                                                                  <li class="<c:if test="${q=='administration/region'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/region">Region</a></li>
                                                                  </c:if>
                                                                  <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien9}">
                                                                  <li class="<c:if test="${q=='administration/site'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/site">Site</a></li>
                                                                  </c:if>
                                                                  <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien13}">
                                                                  <li class="<c:if test="${q=='administration/service'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/service">Service</a></li>
                                                                  </c:if>
                                                                  <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien17}">
                                                                  <li class="<c:if test="${q=='administration/district'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/district">Villes</a></li>
                                                                  </c:if>
                                                                  <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien21}">
                                                                  <li class="<c:if test="${q=='administration/secteur'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/secteur">Secteurs</a></li>
                                                                  </c:if>
                                                          </ul>
                                                      </li>
                                                </c:if>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                          </c:if>
                          <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien25 or not empty sessionScope.lien29 or not empty sessionScope.lien34 or not empty sessionScope.lien38}">
                              <li class="col-md-3" data-mega-col="col-md-3">
                                  <ul class="drilldown-menu">
                                      <li class="menu-list">
                                          <ul class="mega-menu-sub">
                                              <c:if test="${sessionScope.root=='EH2S'}">
                                                  <li><a class="dropdown-item" href="#"><i class="ft-folder"></i>Gestion Menu</a>
                                                      <ul class="mega-menu-sub">
                                                          <li class="<c:if test="${q=='administration/menu'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/menu"><i class="fa fa-folder"></i>Menu</a></li>
                                                          <li class="<c:if test="${q=='administration/sousmenu'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/sousmenu"><i class="fa fa-folder-open-o"></i>Sous Menu</a></li>

                                                          </ul>
                                                      </li>
                                              </c:if>
                                              <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien25 or not empty sessionScope.lien29}">
                                                  <li><a class="dropdown-item" href="#"><i class="ft-unlock"></i>Securité</a>
                                                      <ul class="mega-menu-sub">
                                                          <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien25}">
                                                              <li class="<c:if test="${q=='administration/groupe'}">active</c:if>">
                                                                      <a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/groupe">
                                                                          <i class="fa fa-users"></i>Groupe et droits d'accès
                                                                      </a>
                                                                  </li>
                                                          </c:if>
                                                          <c:if test="${sessionScope.root=='EH2S'}">
                                                              <li class="<c:if test="${q=='administration/action'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/action"><i class="fa fa-users"></i>Action</a></li>
                                                              </c:if>
                                                              <c:if test="${sessionScope.root=='EH2S'}">
                                                              <li class="<c:if test="${q=='administration/action'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/updateMarge"><i class="fa fa-users"></i>Mis à jour marge</a></li>
                                                              </c:if>
                                                              <c:if test="${sessionScope.root=='EH2S'}">
                                                              <li class="<c:if test="${q=='administration/sourceMouvement'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/sourceMouvement"><i class="fa fa-users"></i>Source Mouvement Caisse</a></li>
                                                              </c:if>
                                                              <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien29}">
                                                              <li class="<c:if test="${q=='administration/utilisateur'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/utilisateur"><i class="fa fa-user-circle"></i>Utilisateurs et groupe</a></li>
                                                              </c:if>
                                                      </ul>
                                                  </li>
                                              </c:if>
                                              <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien34 or not empty sessionScope.lien38}">
                                                  <li><a class="dropdown-item" href="#"><i class="ft-plus-square"></i>Gestion CLients</a>
                                                      <ul class="mega-menu-sub">
                                                          <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien34}">
                                                              <li class="<c:if test="${q=='administration/typeclient'}">active</c:if>">
                                                                      <a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/typeclient"><i class="ft-users"></i>Type Clients</a>
                                                                  </li>
                                                          </c:if>
                                                          <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien38}">
                                                              <li class="<c:if test="${q=='administration/client'}">active</c:if>">
                                                                      <a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/client"><i class="ft-users"></i>Clients</a>
                                                                  </li>
                                                          </c:if>
                                                      </ul>
                                                  </li>
                                              </c:if>
                                              <c:if test="${sessionScope.societe.getGesttourner() == 1}">
                                                  <li class="<c:if test="${q=='administration/tourner'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/tourner">Gestion des Tournées</a></li>
                                                  </c:if>
                                          </ul>
                                      </li>
                                  </ul>
                              </li>
                          </c:if>
                          <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien42 or not empty sessionScope.lien46 
                                        or not empty sessionScope.lien50 or not empty sessionScope.lien54 or not empty sessionScope.lien58}">
                                <li class="col-md-3" data-mega-col="col-md-3">
                                    <ul class="drilldown-menu">
                                        <li class="menu-list">
                                            <ul class="mega-menu-sub">
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien42 or not empty sessionScope.lien46}">
                                                    <li><a class="dropdown-item" href="#"><i class="ft-search"></i>Rubrique</a>
                                                        <ul class="mega-menu-sub">
                                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien42}">
                                                                <li class="<c:if test="${q=='administration/rubrique'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/rubrique">Rubrique</a></li>
                                                                </c:if>
                                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien46}">
                                                                <li class="<c:if test="${q=='administration/sousrubrique'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/sousrubrique">Sous Rubrique</a></li>
                                                                </c:if>
                                                        </ul>
                                                    </li>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien50}">
                                                    <li class="<c:if test="${q=='administration/source'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/source">Source Incidents</a></li>
                                                    </c:if>
                                                    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien54}">
                                                    <li class="<c:if test="${q=='administration/priorite'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/priorite">Priorite Incident</a></li>
                                                    </c:if>
                                                    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien58}">
                                                    <li class="<c:if test="${q=='administration/statut'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/statut">Statut Incidents</a></li>
                                                    </c:if>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                          </c:if>
                          <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien112 or not empty sessionScope.lien116}">
                              <li class="col-md-3" data-mega-col="col-md-3">
                                  <ul class="drilldown-menu">
                                      <li class="menu-list">
                                          <ul class="mega-menu-sub">
                                              <c:if test="${sessionScope.root=='EH2S'}">
                                                  <li class="<c:if test="${q=='administration/gestionLicence'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=admin/gestionLicence"><i class="ft-user-check"></i>Gestion Licence</a>
                                                      </li>
                                              </c:if>
                                              <c:if test="${sessionScope.societe.getGestfournisseur()==1}">
                                                  <li class="<c:if test="${q=='administration/fournisseur'}">active</c:if>"><a class="dropdown-item" href="administration?action=model&model=Fournisseur&q=administration/fournisseur&isNew=3"><i class="ft-folder"></i>Gestion Fournisseur</a>
                                                      </li>
                                              </c:if>
                                              <c:if test="${not empty sessionScope.lien112 or not empty sessionScope.lien116}">
                                                  <li><a class="dropdown-item" href="#"><i class="ft-search"></i>Gestion commande</a>
                                                      <ul class="mega-menu-sub">
                                                          <c:if test="${not empty sessionScope.lien112}">
                                                              <li class="<c:if test="${q=='administration/categorie'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/categorie">Categories</a></li>
                                                              </c:if>
                                                              <c:if test="${not empty sessionScope.lien116}">
                                                                  <c:if test="${sessionScope.societe.getGestemballage()==1}">
                                                                  <li class="<c:if test="${q=='administration/oderStock'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/oderStock">Emballages</a></li>
                                                                  </c:if>
                                                              <li class="<c:if test="${q=='administration/article'}">active</c:if>"><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=administration/article">Articles</a></li>

                                                          </c:if>

                                                      </ul>
                                                  </li>
                                              </c:if>
                                              <c:if test="${sessionScope.societe.getGestmagasin()==1}">
                                                  <li class="<c:if test="${q=='administration/magasin'}">active</c:if>"><a class="dropdown-item" href="administration?action=model&model=Magasin&q=administration/magasin&isNew=3"><i class="ft-folder"></i>Gestion Magasin</a>
                                                      </li>
                                              </c:if>
                                          </ul>
                                      </li>
                                  </ul>
                              </li>
                          </c:if>
                      </ul>
                  </li>
            </c:if>
            <c:if test="${sessionScope.societe.gestfournisseur==1}">
                <c:if test="${not empty sessionScope.lien147}">
                    <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="ft-folder"></i>Achat
                            <submenu class="name"></submenu></a>
                        <ul class="dropdown-menu">
                            <c:if test="${not empty sessionScope.lien147}">
                                <li class="<c:if test="${q=='commande/achat/achat'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionachat?action=model&model=achat&q=commande/achat/achat&isNew=0" data-toggle="dropdown">Bon de commande<i class="icon-table"></i>
                                            <submenu class="name"></submenu></a>
                                    </li>
                            </c:if>

                            <c:if test="${1==1}">

                                <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Achats Encours
                                        <submenu class="name"></submenu></a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${1==1}">
                                            <li class="<c:if test="${q=='commande/achat/allCommandeUpdate'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionachat?action=model&model=bonAchat&q=commande/achat/allCommandeUpdate&isNew=0&societe=yes" data-toggle="dropdown">Vos Achats<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>

                                        <li class="<c:if test="${q=='commande/achat/allCommande'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionachat?action=model&model=bonAchat&q=commande/achat/allCommande&isNew=0&societe=yes" data-toggle="dropdown">Receptionner Achat<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>

                                        </ul>
                                    </li>
                            </c:if>
                            <c:if test="${1==1}">
                                <li class="<c:if test="${q=='commande/achat/historiqueAchatArticle'}">active</c:if>" data-menu=""><a class="dropdown-item" href="historiques?action=model&model=historiqueVente&q=commande/achat/historiqueAchatArticle&isNew=0" data-toggle="dropdown">Historique Achat par produit<i class="icon-table"></i>
                                            <submenu class="name"></submenu></a>
                                    </li>
                            </c:if>
                            <c:if test="${1==1}">
                                <li class="<c:if test="${q=='commande/achat/listeAchat'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionachat?action=model&model=init&q=commande/achat/listeAchat&isNew=0&societe=yes" data-toggle="dropdown">Liste des Achats<i class="icon-table"></i>
                                            <submenu class="name"></submenu></a>
                                    </li>
                            </c:if>

                            <c:if test="${1==1}">
                                <li class="<c:if test="${q=='commande/achat/historiqueAchat'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionachat?action=model&model=bonAchat&q=commande/achat/historiqueAchat&isNew=0&societe=yes" data-toggle="dropdown">Historique Achats par Fournisseur<i class="icon-table"></i>
                                            <submenu class="name"></submenu></a>
                                    </li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
            </c:if>

            <c:if test="${not empty sessionScope.lien97 or not empty sessionScope.lien98 or not empty sessionScope.lien103 or not empty sessionScope.lien104 or not empty sessionScope.lien128 or not empty sessionScope.lien130 or not empty sessionScope.lien107}">
                <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="fa fa-bar-chart"></i><span>Pré-Vente</span></a>
                    <ul class="dropdown-menu">
                        <c:if test="${not empty sessionScope.lien97}">
                            <li class="<c:if test="${q=='commande/addcommande'}">active</c:if>" data-menu=""><a class="dropdown-item" href="administration?q=commande/addcommande&action=model&model=TicketmodelByPeriode&isNew=8" data-toggle="dropdown">Nouvelle Pré-Vente<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien98}">
                            <li class="<c:if test="${q=='commande/traitement'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=commande/traitement&isNew=9" data-toggle="dropdown">Traiter pre-Vente<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien103}">
                            <li class="<c:if test="${q=='commande/validation'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=commande/validation&isNew=9" data-toggle="dropdown">Valider Pré-Vente<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien130}">
                            <li class="<c:if test="${q=='commande/endCommande'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=commande/endCommande&isNew=9" data-toggle="dropdown">Pré-Vente à Livrer<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien128}">
                            <li class="<c:if test="${q=='commande/receptionner'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=HistoriqueCommande&q=commande/receptionner&isNew=0" data-toggle="dropdown">Receptionner Pré-Vente<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>

                        <c:if test="${not empty sessionScope.lien104}">
                            <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Feuille de route
                                    <submenu class="name"></submenu></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${not empty sessionScope.lien104}">
                                        <li class="<c:if test="${q=='commande/feuilleRoute'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=commande/feuilleRoute&isNew=9" data-toggle="dropdown">Général<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${sessionScope.societe.getGesttourner() == 1}">
                                        <c:if test="${not empty sessionScope.lien131}">
                                            <li class="<c:if test="${q=='commande/feuilleRouteTourner'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=commande/feuilleRouteTourner&isNew=9" data-toggle="dropdown">Tourner<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                    </c:if>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien107}">
                            <li class="<c:if test="${q=='commande/listePreVente'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=init&q=commande/listePreVente&isNew=0" data-toggle="dropdown">Liste Pré-Vente<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien107}">
                            <li class="<c:if test="${q=='commande/historique'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=HistoriqueCommande&q=commande/historique&isNew=0" data-toggle="dropdown">Historique Pré-Vente<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${not empty sessionScope.lien146}">
                <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="fa fa-bar-chart"></i><span>Vente</span></a>
                    <ul class="dropdown-menu">
                        <c:if test="${not empty sessionScope.lien146}">
                            <li class="<c:if test="${q=='commande/vente'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=PasserCommandes&q=commande/vente&isNew=4" data-toggle="dropdown">Nouvelle Vente<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${1==1}">

                            <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Ventes Encours
                                    <submenu class="name"></submenu></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/vente/venteEncours'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=HistoriqueCommande&q=commande/vente/venteEncours&isNew=0" data-toggle="dropdown">Vos Ventes<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>

                                    <li class="<c:if test="${q=='commande/vente/receptionVente'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=HistoriqueCommande&q=commande/vente/receptionVente&isNew=0" data-toggle="dropdown">Receptionner Vente<i class="icon-table"></i>
                                                <submenu class="name"></submenu></a>
                                        </li>

                                    </ul>
                                </li>
                        </c:if>

                        <c:if test="${1==1}">
                            <li class="<c:if test="${q=='commande/historiqueVenteArticle'}">active</c:if>" data-menu=""><a class="dropdown-item" href="historiques?action=model&model=historiqueVente&q=commande/historiqueVenteArticle&isNew=0" data-toggle="dropdown">Historique Vente par Produit<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${1==1}">
                            <li class="<c:if test="${q=='commande/vente/feuilleRoute'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=HistoriqueCommande&q=commande/vente/feuilleRoute&isNew=0" data-toggle="dropdown">Feuille de Route<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${1==1}">
                            <li class="<c:if test="${q=='commande/listeVente'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=init&q=commande/listeVente&isNew=0" data-toggle="dropdown">Liste des Ventes<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${1==1}">
                            <li class="<c:if test="${q=='commande/historiqueVente'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=HistoriqueCommande&q=commande/historiqueVente&isNew=0" data-toggle="dropdown">Historique Vente Par Client<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${not empty sessionScope.lien59 or not empty sessionScope.lien60 or not empty sessionScope.lien61 or not empty sessionScope.lien95 or not empty sessionScope.lien64}">
                <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="fa fa-bug"></i><span>Plainte</span></a>
                    <ul class="dropdown-menu">
                        <c:if test="${not empty sessionScope.lien59}">
                            <li class="<c:if test="${q=='incident/nouvelIncident'}">active</c:if>" data-menu=""><a class="dropdown-item" href="ticket?q=incident/nouvelIncident&action=jkdhfoldg458dgbjdg478962" data-toggle="dropdown">Declarer une plainte<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien60}">
                            <li class="<c:if test="${q=='incident/incidentEngendre'}">active</c:if>" data-menu=""><a class="dropdown-item" href="ticket?q=incident/incidentEngendre&action=jkdhfoldg458dgbjdg478962" data-toggle="dropdown">Plainte Engendrés<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien61}">
                            <li class="<c:if test="${q=='incident/traiterIncident'}">active</c:if>" data-menu=""><a class="dropdown-item" href="ticket?q=incident/traiterIncident&action=jkdhfoldg458dgbjdg478962" data-toggle="dropdown">Traiter Plainte<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien61 or not empty sessionScope.lien63}">
                            <li class="<c:if test="${q=='incident/fermetureIncident'}">active</c:if>" data-menu=""><a class="dropdown-item" href="ticket?q=incident/fermetureIncident&action=jkdhfoldg458dgbjdg478962" data-toggle="dropdown">Fermer Plainte<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien64}">
                            <li class="<c:if test="${q=='historique/listeIncident'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=historique/listeIncident&action=model&model=TicketmodelByPeriode&isNew=8" data-toggle="dropdown">Liste des plaintes<i class="icon-table"></i>
                                        <submenu class="name"></submenu></a>
                                </li>
                        </c:if>
                        <c:if test="${not empty sessionScope.lien95 or not empty sessionScope.lien64}">
                            <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Incidents
                                    <submenu class="name"></submenu></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${not empty sessionScope.lien95}">
                                        <li class="<c:if test="${q=='incident/user/historiqueIncident'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=incident/user/historiqueIncident&action=model&model=TicketmodelByPeriode&isNew=8" data-toggle="dropdown">Mon Historique<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.lien64}">
                                        <li class="<c:if test="${q=='historique/historiqueIncident'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=historique/historiqueIncident&action=model&model=TicketmodelByPeriode&isNew=8" data-toggle="dropdown">Historique General<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${sessionScope.societe.gestmagasin==0}">
                <c:if test="${not empty sessionScope.lien116 or not empty sessionScope.lien136}">
                    <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="ft-folder"></i>Mouvement Stock
                            <submenu class="name"></submenu></a>
                        <ul class="dropdown-menu">
                            <c:if test="${not empty sessionScope.lien116}">
                                <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Article
                                        <submenu class="name"></submenu></a>
                                    <ul class="dropdown-menu">
                                        <li class="<c:if test="${q=='commande/article/stock'}">active</c:if>" data-menu=""><a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=commande/article/stock" data-toggle="dropdown">Stock<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                            <li class="<c:if test="${q=='commande/article/mouvement'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/article/mouvement&isNew=0&societe=yes&type=0" data-toggle="dropdown">Entrée/Sortie<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                            <li class="<c:if test="${q=='commande/article/historiques'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/article/historiques&isNew=7&societe=yes&type=0" data-toggle="dropdown">Historique<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>

                                        </ul>
                                    </li>    
                            </c:if>
                            <c:if test="${sessionScope.societe.getGestemballage()==1 and not empty sessionScope.lien136}">
                                <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Emballage
                                        <submenu class="name"></submenu></a>
                                    <ul class="dropdown-menu">
                                        <li class="<c:if test="${q=='commande/emballage/stock'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/emballage/stock&isNew=0&societe=yes&type=1" data-toggle="dropdown">Stock<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                            <li class="<c:if test="${q=='commande/emballage/mouvement'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/emballage/mouvement&isNew=0&societe=yes&type=1" data-toggle="dropdown">Entrée/Sortie<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                            <li class="<c:if test="${q=='commande/emballage/historiques'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/emballage/historiques&isNew=7&societe=yes&type=1" data-toggle="dropdown">Historique<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                        <c:if test="${sessionScope.societe.getGestemballage()==1 and not empty sessionScope.lien136}">
                                            <li class="<c:if test="${q=='commande/retourEmballage'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=retour&q=commande/retourEmballage&isNew=0" data-toggle="dropdown">Retour Emballage Client<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.societe.gestmagasin==0}">
                <c:if test="${not empty sessionScope.lien136 or not empty sessionScope.lien137 or not empty sessionScope.lien138}">
                    <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="ft-folder"></i>Inventaire
                            <submenu class="name"></submenu></a>
                        <ul class="dropdown-menu">
                            <c:if test="${not empty sessionScope.lien136}">
                                <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Effectuer Inventaire
                                        <submenu class="name"></submenu></a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${not empty sessionScope.lien136}">
                                            <li class="<c:if test="${q=='commande/inventaire/inventaire'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/inventaire/inventaire&isNew=0&societe=yes&type=0" data-toggle="dropdown">Articles<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                        <c:if test="${sessionScope.societe.getGestemballage()==1 and not empty sessionScope.lien136}">
                                            <li class="<c:if test="${q=='commande/inventaire/inventaire'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/inventaire/inventaire&isNew=0&societe=yes&type=1" data-toggle="dropdown">Emballage<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                    </ul>
                                </li>
                            </c:if>

                            <c:if test="${not empty sessionScope.lien137}">
                                <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Historique Inventaire General
                                        <submenu class="name"></submenu></a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${not empty sessionScope.lien137}">
                                            <li class="<c:if test="${q=='commande/inventaire/historiqueInventaire'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/inventaire/historiqueInventaire&isNew=7&societe=yes&type=0&historique=0" data-toggle="dropdown">Articles<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                        <c:if test="${sessionScope.societe.getGestemballage()==1 and not empty sessionScope.lien137}">
                                            <li class="<c:if test="${q=='commande/inventaire/historiqueInventaire'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/inventaire/historiqueInventaire&isNew=7&societe=yes&type=1&historique=0" data-toggle="dropdown">Emballage<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien138}">
                                <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Historique Inventaire Détaillé
                                        <submenu class="name"></submenu></a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${not empty sessionScope.lien138}">
                                            <li class="<c:if test="${q=='commande/inventaire/inventaireDetails'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/inventaire/inventaireDetails&isNew=0&societe=yes&type=0" data-toggle="dropdown">Articles<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                        <c:if test="${sessionScope.societe.getGestemballage()==1 and not empty sessionScope.lien138}">
                                            <li class="<c:if test="${q=='commande/inventaire/inventaireDetails'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/inventaire/inventaireDetails&isNew=0&societe=yes&type=1" data-toggle="dropdown">Emballage<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.societe.gestmagasin==1}">
                <c:if test="${not empty sessionScope.lien142 or not empty sessionScope.lien143 or not empty sessionScope.lien144 or 
                              not empty sessionScope.lien132 or not empty sessionScope.lien140 or not empty sessionScope.lien141}">
                      <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="ft-folder"></i><span>Magasin</span></a>
                          <ul class="dropdown-menu">
                              <c:if test="${not empty sessionScope.lien132 or not empty sessionScope.lien140 or not empty sessionScope.lien141}">
                                  <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Stocks
                                          <submenu class="name"></submenu></a>
                                      <ul class="dropdown-menu">
                                          <c:if test="${not empty sessionScope.lien132}">
                                              <li class="<c:if test="${q=='commande/magasin/inventaireMagasin'}">active</c:if>" data-menu=""><a class="dropdown-item" href="administration?q=commande/magasin/stockMagasin&action=model&model=MagasinStocks&isNew=3" data-toggle="dropdown">Stocks<i class="icon-table"></i>
                                                          <submenu class="name"></submenu></a>
                                                  </li>
                                          </c:if>
                                          <c:if test="${not empty sessionScope.lien140}">
                                              <li class="<c:if test="${q=='commande/magasin/TransfertStock'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?q=commande/magasin/TransfertStock&action=model&model=TransfertStock&isNew=0" data-toggle="dropdown">Transfert Stock<i class="icon-table"></i>
                                                          <submenu class="name"></submenu></a>
                                                  </li>
                                          </c:if>
                                          <c:if test="${not empty sessionScope.lien141}">
                                              <li class="<c:if test="${q=='commande/magasin/ReceptionStock'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?q=commande/magasin/ReceptionStock&action=model&model=TransfertStock&isNew=0" data-toggle="dropdown">Reception Stock<i class="icon-table"></i>
                                                          <submenu class="name"></submenu></a>
                                                  </li>
                                          </c:if>
                                      </ul>
                                  </li>
                              </c:if>
                              <c:if test="${not empty sessionScope.lien142 or not empty sessionScope.lien143 or not empty sessionScope.lien144}">
                                  <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Inventaires
                                          <submenu class="name"></submenu></a>
                                      <ul class="dropdown-menu">
                                          <c:if test="${not empty sessionScope.lien142}">
                                              <li class="<c:if test="${q=='commande/magasin/inventaireMagasin'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=inventaire&q=commande/magasin/inventaireMagasin&isNew=0" data-toggle="dropdown">Effectuer Inventaire<i class="icon-table"></i>
                                                          <submenu class="name"></submenu></a>
                                                  </li>
                                          </c:if>
                                          <c:if test="${not empty sessionScope.lien143}">
                                              <li class="<c:if test="${q=='commande/magasin/historiqueInventaire'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=historiqueInventaire&q=commande/magasin/historiqueInventaire&isNew=0" data-toggle="dropdown">Historique Inventaire General<i class="icon-table"></i>
                                                          <submenu class="name"></submenu></a>
                                                  </li>
                                          </c:if>
                                          <c:if test="${not empty sessionScope.lien144}">
                                              <li class="<c:if test="${q=='commande/magasin/inventaireDetails'}">active</c:if>" data-menu=""><a class="dropdown-item" href="inventaire?action=model&model=historiqueInventaire&q=commande/magasin/inventaireDetails&isNew=0" data-toggle="dropdown">Historique Inventaire Détaillé<i class="icon-table"></i>
                                                          <submenu class="name"></submenu></a>
                                                  </li>
                                          </c:if>
                                      </ul>
                                  </li>
                              </c:if>

                          </ul>
                      </li>
                </c:if>
            </c:if>

            <c:if test="${sessionScope.societe.getGestmarge()==1}">
                <c:if test="${not empty sessionScope.lien129 or not empty sessionScope.lien133 or not empty sessionScope.lien134}">
                    <li class="dropdown  nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown">
                            <i class="fa fa-money"></i><span>Ristourne</span></a>
                        <ul class="dropdown-menu">
                            <c:if test="${not empty sessionScope.lien129}">
                                <li class="<c:if test="${q=='commande/margeCommande'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestionCommandes?action=model&model=HistoriqueCommande&q=commande/margeCommande&isNew=0" data-toggle="dropdown">Général<i class="icon-table"></i>
                                            <submenu class="name"></submenu></a>
                                    </li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien133}">
                                <li class="<c:if test="${q=='commande/MargeCategorie'}">active</c:if>" data-menu=""><a class="dropdown-item" href="administration?q=commande/MargeCategorie&action=model&model=TicketmodelByPeriode&isNew=8" data-toggle="dropdown">Détaillé<i class="icon-table"></i>
                                            <submenu class="name"></submenu></a>
                                    </li>
                            </c:if>
                            <c:if test="${not empty sessionScope.lien134}">
<!--                                <li class="<c:if test="${q=='commande/MargeClient'}">active</c:if>" data-menu=""><a class="dropdown-item" href="administration?q=commande/MargeClient&action=model&model=TicketmodelByPeriode&isNew=8" data-toggle="dropdown">Client<i class="icon-table"></i>
                                            <submenu class="name"></submenu></a>
                                    </li>-->
                            </c:if>
                        </ul>
                    </li>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.societe.getGestcaisse()==1}">
                <c:if test="${not empty sessionScope.lien145}">
                    <li class="dropdown  nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown">
                            <i class="fa fa-money"></i><span>Comptabilité</span></a>
                        <ul class="dropdown-menu">

                            <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Ma Caisse
                                    <submenu class="name"></submenu></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/caisse'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=caisse&q=commande/caisse/caisse&isNew=0" data-toggle="dropdown">Ouverture<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/fermetureCaisse'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=caisse&q=commande/caisse/fermetureCaisse&isNew=0" data-toggle="dropdown">Fermeture<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                </ul>
                            </li>
                            <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Ventes
                                    <submenu class="name"></submenu></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/facturation'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/facturation&isNew=0" data-toggle="dropdown">Factures clients<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==2}">
                                        <li class="<c:if test="${q=='commande/caisse/dette'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/dette&isNew=0" data-toggle="dropdown">Nouvelle Dette Client<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/allDetteClient'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?q=commande/caisse/allDetteClient&action=model&model=init&isNew=0" data-toggle="dropdown">Dettes Clients<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/allAccount'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/allAccount&isNew=0" data-toggle="dropdown">Liste Facturation<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/recouvrement'}">active</c:if>" data-menu=""><a class="dropdown-item" href="historiques?action=model&model=recouvrement&q=commande/caisse/recouvrement&isNew=0" data-toggle="dropdown">Recouvrement<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                </ul>
                            </li>
                            <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Achats
                                    <submenu class="name"></submenu></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/bonCommande'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/bonCommande&isNew=0&societe=yes" data-toggle="dropdown">Factures Fournisseurs<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==2}">
                                        <li class="<c:if test="${q=='commande/caisse/detteFournisseur'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/detteFournisseur&isNew=0&societe=yes" data-toggle="dropdown">Nouvelle Dette Fournisseur<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/allDetteFournisseur'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?q=commande/caisse/allDetteFournisseur&action=model&model=init&isNew=0&forpaiement=0" data-toggle="dropdown">Dettes Fournisseurs <i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/allSortie'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/allSortie&isNew=0" data-toggle="dropdown">Liste Facturation<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/recouvrementFournisseur'}">active</c:if>" data-menu=""><a class="dropdown-item" href="historiques?action=model&model=recouvrement&q=commande/caisse/recouvrementFournisseur&isNew=1" data-toggle="dropdown">Recouvrement<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                </ul>
                            </li>



                            <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Entrée
                                    <submenu class="name"></submenu></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/entreeCaisse'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/entreeCaisse&isNew=0" data-toggle="dropdown">Effectuer une Entree<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/historiqueEntree'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/historiqueEntree&isNew=0" data-toggle="dropdown">Historique Entrée<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                </ul>
                            </li>
                            <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Dépense
                                    <submenu class="name"></submenu></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/sortieCaisse'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/sortieCaisse&isNew=0" data-toggle="dropdown">Effectuer une dépense<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/historiqueSortie'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gestioncaisse?action=model&model=init&q=commande/caisse/historiqueSortie&isNew=0" data-toggle="dropdown">Historique dépense<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                </ul>
                            </li>
                            <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Etat
                                    <submenu class="name"></submenu></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/etat'}">active</c:if>" data-menu=""><a class="dropdown-item" href="historiques?action=model&model=historiqueCaisse&q=commande/caisse/etat&isNew=1" data-toggle="dropdown">Journalier<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                    <c:if test="${1==1}">
                                        <li class="<c:if test="${q=='commande/caisse/allEtat'}">active</c:if>" data-menu=""><a class="dropdown-item" href="historiques?q=commande/caisse/allEtat&action=model&model=historiqueCaisse&isNew=0" data-toggle="dropdown">Périodique<i class="icon-table"></i>
                                                    <submenu class="name"></submenu></a>
                                            </li>
                                    </c:if>
                                </ul>
                            </li>


                        </ul>
                    </li>
                </c:if>
            </c:if>
            <c:if test="${not empty sessionScope.lien65 or not empty sessionScope.lien66 or not empty sessionScope.lien67
                          or not empty sessionScope.lien68 or not empty sessionScope.lien69 or not empty sessionScope.lien70 or not empty sessionScope.lien71}">

                  <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="fa fa-signal"></i><span>Reportings</span></a>
                      <ul class="dropdown-menu">
                          <c:if test="${not empty sessionScope.lien65 or not empty sessionScope.lien66 or not empty sessionScope.lien67
                                        or not empty sessionScope.lien68 or not empty sessionScope.lien69 or not empty sessionScope.lien70 or not empty sessionScope.lien71}">
                                <li class="dropdown dropdown-submenu" data-menu="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#" data-toggle="dropdown">Plaintes
                                        <submenu class="name"></submenu></a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${not empty sessionScope.lien65}">
                                            <li class="<c:if test="${q=='reporting/region'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=reporting/region&action=model&model=TicketmodelByPeriode&isNew=0&periode=0&iq=region" data-toggle="dropdown">Region<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien66}">
                                            <li class="<c:if test="${q=='reporting/personnel'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=reporting/personnel&action=model&model=TicketmodelByPeriode&isNew=3&periode=0&iq=personnel" data-toggle="dropdown">Responsable<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien67}">
                                            <li class="<c:if test="${q=='reporting/client'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=reporting/client&action=model&model=TicketmodelByPeriode&isNew=1&periode=0&iq=client" data-toggle="dropdown">Client<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien68}">
                                            <li class="<c:if test="${q=='reporting/rubrique'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=reporting/rubrique&action=model&model=TicketmodelByPeriode&isNew=4&periode=0&iq=rubrique" data-toggle="dropdown">Rubrique<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien69}">
                                            <li class="<c:if test="${q=='reporting/source'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=reporting/source&action=model&model=TicketmodelByPeriode&isNew=7&periode=0&iq=source" data-toggle="dropdown">Source<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien70}">
                                            <li class="<c:if test="${q=='reporting/district'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=reporting/district&action=model&model=TicketmodelByPeriode&isNew=2&periode=0&iq=district" data-toggle="dropdown">District<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.lien71}">
                                            <li class="<c:if test="${q=='reporting/secteur'}">active</c:if>" data-menu=""><a class="dropdown-item" href="gxwzy14iyf?q=reporting/secteur&action=model&model=TicketmodelByPeriode&isNew=5&periode=0&iq=secteur" data-toggle="dropdown">Secteur<i class="icon-table"></i>
                                                        <submenu class="name"></submenu></a>
                                                </li>
                                        </c:if>
                                    </ul>
                                </li>
                          </c:if>

                      </ul>
                  </li>

            </c:if>
            <c:if test="${not empty sessionScope.lien72 or not empty sessionScope.lien73 or not empty sessionScope.lien74 or not empty sessionScope.lien75
                          or not empty sessionScope.lien76 or not empty sessionScope.lien77 or not empty sessionScope.lien78 or not empty sessionScope.lien135}">
                  <li class="dropdown nav-item" data-menu="dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown"><i class="fa fa-dashboard"></i><span>Tableau de bord</span></a>
                      <ul class="dropdown-menu">
                          <c:if test="${not empty sessionScope.lien72 or not empty sessionScope.lien73 or not empty sessionScope.lien74 or not empty sessionScope.lien75
                                        or not empty sessionScope.lien76 or not empty sessionScope.lien77 or not empty sessionScope.lien78}">
                                <li data-menu="" class="<c:if test="${q=='dashboard/dashboard'}">active</c:if>"><a class="dropdown-item" href="gxwzy14iyf?q=dashboard/dashboard&action=model&model=TicketmodelByPeriode&isNew=5&periode=0&iq=secteur" data-toggle="dropdown">Plaintes<i class="icon-table"></i>
                                            <submenu class="name"></submenu></a>
                                    </li>
                          </c:if>
                          <c:if test="${not empty sessionScope.lien135}">
                              <li data-menu="" class="<c:if test="${q=='dashboard/commande'}">active</c:if>"><a class="dropdown-item" href="gestionStatistiquesCommandes?q=dashboard/commande&action=repport&model=TicketmodelByPeriode&isNew=5&periode=0&iq=secteur" data-toggle="dropdown">Pré-Commandes<i class="icon-table"></i>
                                          <submenu class="name"></submenu></a>
                                  </li>
                          </c:if>

                      </ul>
                  </li>
            </c:if>
        </ul>
    </div>
    <!-- /horizontal menu content-->
</div>
<!-- Horizontal navigation-->