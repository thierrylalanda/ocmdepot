<%-- 
    Document   : sideMenu
    Created on : 10 déc. 2018, 16:02:28
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
 <c:if test="${sessionScope.root=='EH2S'}">
                        <div class="col-md-2">
                            <div id="accordionWrapa1" role="tablist" aria-multiselectable="true">
                                <div class="card">

                                    <div id="heading2" class="card-header">
                                        <a data-toggle="collapse" data-parent="#accordionWrapa1" href="#accordion2" aria-expanded="false"
                                           aria-controls="accordion2" class="card-title lead collapsed">Tous les Tickets</a>
                                    </div>
                                    <div id="accordion2" role="tabpanel" aria-labelledby="heading2" class="collapse <c:if test="${q=='admin/incident/allIncident' or q=='admin/incident/encourIncident' or q=='admin/incident/resoluIncident' or q=='admin/incident/IncidentNonResolu' or q=='admin/incident/InsolvableIncident'}">show</c:if>"
                                         aria-expanded="false">
                                            <div class="card-content">
                                                <div class="card-body">
                                                    <div class="list-group">
                                                        <a href="administration?action=nbygruyr458puggc125&q=admin/incident/allIncident" class="list-group-item list-group-item-action <c:if test="${q=='admin/incident/allIncident'}">active</c:if>">Tous Les incident (${totalticket.size()})</a>
                                                    <a href="administration?action=nbygruyr458puggc125&q=admin/incident/encourIncident" class="list-group-item list-group-item-action <c:if test="${q=='admin/incident/encourIncident'}">active</c:if>">Encours (${ticketEncours.size()})</a>
                                                    <a href="administration?action=nbygruyr458puggc125&q=admin/incident/resoluIncident" class="list-group-item list-group-item-action <c:if test="${q=='admin/incident/resoluIncident'}">active</c:if>">Resolu (${ticketresolut.size()})</a>
                                                    <a href="administration?action=nbygruyr458puggc125&q=admin/incident/IncidentNonResolu" class="list-group-item list-group-item-action <c:if test="${q=='admin/incident/IncidentNonResolu'}">active</c:if>">Non Resolu (${ticketNonResolut.size()})</a>
                                                    <a href="administration?action=nbygruyr458puggc125&q=admin/incident/InsolvableIncident" class="list-group-item list-group-item-action <c:if test="${q=='admin/incident/InsolvableIncident'}">active</c:if>">Insolvable (${ticketInsolvable.size()})</a>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div id="heading3" class="card-header">
                                            <a data-toggle="collapse" data-parent="#accordionWrapa1" href="#accordion3" aria-expanded="false"
                                               aria-controls="accordion2" class="card-title lead collapsed">Toutes les Commandes</a>
                                        </div>
                                        <div id="accordion3" role="tabpanel" aria-labelledby="heading3" class="collapse <c:if test="${q=='admin/commande/allCommande' or q=='admin/commande/commandesEncours' or q=='admin/commande/commandesLivraison' or q=='admin/commande/commandesAcheve'}">show</c:if>"
                                             aria-expanded="false">
                                            <div class="card-content">
                                                <div class="card-body">
                                                    <div class="list-group">
                                                        <a  href="administration?action=nbygruyr458puggc125&q=admin/commande/allCommande" class="list-group-item list-group-item-action <c:if test="${q=='admin/commande/allCommande'}">active</c:if>">
                                                        Toutes les commandes (${commandesAll.size()})
                                                    </a>
                                                    <a href="administration?action=nbygruyr458puggc125&q=admin/commande/commandesEncours" class="list-group-item list-group-item-action <c:if test="${q=='admin/commande/commandesEncours'}">active</c:if>">Encours (${commandesEncours.size()})</a>
                                                    <a href="administration?action=nbygruyr458puggc125&q=admin/commande/commandesLivraison" class="list-group-item list-group-item-action <c:if test="${q=='admin/commande/commandesLivraison'}">active</c:if>">Encours de livraison (${commandesLivraison.size()})</a>
                                                    <a href="administration?action=nbygruyr458puggc125&q=admin/commande/commandesAcheve" class="list-group-item list-group-item-action <c:if test="${q=='admin/commande/commandesAcheve'}">active</c:if>">Livrées (${commandesAcheve.size()})</a>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                    </c:if>
                    <c:if test="${sessionScope.root!='EH2S' and (sessionScope.client ==  null and q.split('/')[0] != 'commande') 
                                  or (q.split('/')[0] == 'commande' and q.split('/')[1] == 'societe')}">
                          <div class="col-md-2">
                              <div id="accordionWrapa1" role="tablist" aria-multiselectable="true">
                                  <div class="card">
                                      <c:if test="${not empty sessionScope.lien79 or not empty sessionScope.lien80 or not empty sessionScope.lien81
                                                    or not empty sessionScope.lien82 or not empty sessionScope.lien83}">
                                            <div id="heading1" class="card-header" role="tab">
                                                <a data-toggle="collapse" data-parent="#accordionWrapa1" href="#accordion1" aria-expanded="false"
                                                   aria-controls="accordion1" class="card-title lead">Vos Tickets</a>
                                            </div>
                                            <div id="accordion1" role="tabpanel" aria-labelledby="heading1" class="collapse <c:if test="${(q=='incident/user/InsolvableIncident' or q=='incident/user/resoluIncident' or q=='incident/user/IncidentNonResolu' or q=='incident/user/allIncident' or q=='incident/user/encourIncident') or not (q=='incident/newIncident' or q=='incident/InsolvableIncident' or q=='incident/resoluIncident' or q=='incident/IncidentNonResolu' or q=='incident/allIncident' or q=='incident/encourIncident') and not (q=='commande/societe/allCommande' or q=='commande/societe/newCommande' or q=='commande/societe/commandesEncours' or q=='commande/societe/commandesLivraison' or q=='commande/societe/commandesAcheve')}">show</c:if>">
                                                    <div class="card-content">
                                                        <div class="card-body">
                                                            <div class="list-group">
                                                            <c:if test="${not empty sessionScope.lien79}">
                                                                <a href="ticket?q=incident/user/allIncident&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/user/allIncident'}">active</c:if>">Tous vos incident (${totalticketcli.size()})</a>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.lien80}">
                                                                <a href="ticket?q=incident/user/encourIncident&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/user/encourIncident'}">active</c:if>">Encours (${newticketcli.size()})</a>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.lien81}">
                                                                <a href="ticket?q=incident/user/resoluIncident&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/user/resoluIncident'}">active</c:if>">Resolu (${ticketresolutcli.size()})</a>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.lien82}">
                                                                <a href="ticket?q=incident/user/IncidentNonResolu&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/user/IncidentNonResolu'}">active</c:if>">Non Resolu (${ticketNonResolutcli.size()})</a>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.lien83}">
                                                                <a href="ticket?q=incident/user/InsolvableIncident&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/user/InsolvableIncident'}">active</c:if>">Insolvable (${ticketInsolvablecli.size()})</a>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                      </c:if>
                                      <c:if test="${not empty sessionScope.lien123}">
                                          <c:import url="/WEB-INF/menu/sideMenuSociete.jsp"/>
                                      </c:if>
                                      <c:if test="${not empty sessionScope.lien124 and empty sessionScope.lien123}">
                                          <c:import url="/WEB-INF/menu/sideMenuRegion.jsp"/>
                                      </c:if>
                                     <c:if test="${not empty sessionScope.lien125 and empty sessionScope.lien123 and empty sessionScope.lien124}">
                                          <c:import url="/WEB-INF/menu/sideMenuDistrict.jsp"/>
                                      </c:if>
                                      <c:if test="${not empty sessionScope.lien126 and empty sessionScope.lien125 and empty sessionScope.lien123 and empty sessionScope.lien124}">
                                          <c:import url="/WEB-INF/menu/sideMenuSecteur.jsp"/>
                                      </c:if>

                                  </div>
                              </div>

                          </div>
                    </c:if>

