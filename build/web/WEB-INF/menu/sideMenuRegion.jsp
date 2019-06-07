<%-- 
    Document   : sideMenuRegion
    Created on : 18 déc. 2018, 14:34:09
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
 <c:if test="${not empty sessionScope.lien84 or not empty sessionScope.lien85 or not empty sessionScope.lien86
                                                    or not empty sessionScope.lien87 or not empty sessionScope.lien88 or not empty sessionScope.lien89}">
                                            <div id="heading2" class="card-header">
                                                <a data-toggle="collapse" data-parent="#accordionWrapa1" href="#accordion2" aria-expanded="false"
                                                   aria-controls="accordion2" class="card-title lead collapsed">Tous les Tickets 
                                                    <SUP><span class="badge badge-pill badge-default badge-danger badge-default badge-up">${newticket.size()}</span></SUP></a>
                                            </div>
                                            <div id="accordion2" role="tabpanel" aria-labelledby="heading2" class="collapse <c:if test="${q=='incident/newIncident' or q=='incident/InsolvableIncident' or q=='incident/resoluIncident' or q=='incident/IncidentNonResolu' or q=='incident/allIncident' or q=='incident/encourIncident'}">show</c:if>"
                                                 aria-expanded="false">
                                                    <div class="card-content">
                                                        <div class="card-body">
                                                            <div class="list-group">
                                                            <c:if test="${not empty sessionScope.lien84}">
                                                                <a  href="ticket?q=incident/newIncident&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/newIncident'}">active</c:if>">
                                                                    Nouvel Demande (${newticket.size()})
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.lien85}">
                                                                <a href="ticket?q=incident/allIncident&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/allIncident'}">active</c:if>">Tous Les incident (${totalticket.size()})</a>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.lien86}">
                                                                <a href="ticket?q=incident/encourIncident&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/encourIncident'}">active</c:if>">Encours (${ticketEncours.size()})</a>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.lien87}">
                                                                <a href="ticket?q=incident/resoluIncident&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/resoluIncident'}">active</c:if>">Resolu (${ticketresolut.size()})</a>
                                                            </c:if>
                                                            <c:if test="${not empty sessionScope.lien88}">
                                                                <a href="ticket?q=incident/IncidentNonResolu&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/IncidentNonResolu'}">active</c:if>">Non Resolu (${ticketNonResolut.size()})</a>
                                                            </c:if>
                                                            <c:if test="${ not empty sessionScope.lien89}">
                                                                <a href="ticket?q=incident/InsolvableIncident&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='incident/InsolvableIncident'}">active</c:if>">Insolvable (${ticketInsolvable.size()})</a>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                      </c:if>
                                      <c:if test="${not empty sessionScope.lien118 or not empty sessionScope.lien121 or not empty sessionScope.lien119 or not empty sessionScope.lien120 or not empty sessionScope.lien122}">

                                          <div id="heading3" class="card-header">
                                              <a data-toggle="collapse" data-parent="#accordionWrapa1" href="#accordion3" aria-expanded="false"
                                                 aria-controls="accordion2" class="card-title lead collapsed">Toutes les Commandes
                                                  <SUP> <span class="badge badge-pill badge-default badge-danger badge-default badge-up">${commandesNew.size()}</span></SUP></a>
                                          </div>
                                          <div id="accordion3" role="tabpanel" aria-labelledby="heading3" class="collapse <c:if test="${q=='commande/societe/allCommande' or q=='commande/societe/newCommande' or q=='commande/societe/commandesEncours' or q=='commande/societe/commandesLivraison' or q=='commande/societe/commandesAcheve'}">show</c:if>"
                                               aria-expanded="false">
                                                  <div class="card-content">
                                                      <div class="card-body">
                                                          <div class="list-group">
                                                          <c:if test="${not empty sessionScope.lien118}">
                                                              <a  href="ticket?q=commande/societe/allCommande&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='commande/societe/allCommande'}">active</c:if>">
                                                                  Toutes les commandes (${commandesAll.size()})
                                                              </a>
                                                          </c:if>
                                                          <c:if test="${not empty sessionScope.lien122}">
                                                              <a href="ticket?q=commande/societe/newCommande&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='commande/societe/newCommande'}">active</c:if>">Nouvelles commandes (${commandesNew.size()})</a>
                                                          </c:if>
                                                          <c:if test="${not empty sessionScope.lien119}">
                                                              <a href="ticket?q=commande/societe/commandesEncours&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='commande/societe/commandesEncours'}">active</c:if>">Encours (${commandesEncours.size()})</a>
                                                          </c:if>
                                                          <c:if test="${not empty sessionScope.lien120}">
                                                              <a href="ticket?q=commande/societe/commandesLivraison&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='commande/societe/commandesLivraison'}">active</c:if>">Encours de livraison (${commandesLivraison.size()})</a>
                                                          </c:if>
                                                          <c:if test="${not empty sessionScope.lien121}">
                                                              <a href="ticket?q=commande/societe/commandesAcheve&action=jkdhfoldg458dgbjdg478962" class="list-group-item list-group-item-action <c:if test="${q=='commande/societe/commandesAcheve'}">active</c:if>">Livrées (${commandesAcheve.size()})</a>
                                                          </c:if>
                                                      </div>
                                                  </div>
                                              </div>
                                          </div>
                                      </c:if>
