<%-- 
    Document   : commandesAcheve
    Created on : 10 déc. 2018, 14:27:24
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or 1==1}">
    <%
        Date now = new Date();
        request.setAttribute("day", now);
    %>
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

    <div class="row autoloader" autoloader="true">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Toutes les commandes</h4>
                                <div class="heading-elements ">
                                    <ul class="list-inline mb-0">
                                        <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                                        <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body card-dashboard">
                                    <p class="card-text">

                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir les détails de la commande"
                                                        name="numc" action="administration?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien106}">
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element_commande" 
                                                        data-toggle='tooltips' title="Imprimer une commande"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                </c:if>
                                        </div>
                                    </div>
                                    </p>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>N°</th>
                                                <th>Societe</th>
                                                <th>Client</th>
                                                <th>Statut</th>
                                                <th>Qte</th>
                                                <th>P.T</th>
                                                <th class="datec" pos="7">Date commande</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="total" value="0"></c:set>
                                            <c:set var="qte" value="0"></c:set>
                                            <c:forEach items="${commandesAcheve}" var="all">
                                                <c:set var="total" value="${total + all.getPrixtotal()}"></c:set>
                                                    <c:set var="qte" value="${qte+all.getQt()}"></c:set>
                                                        <tr >
                                                            <td><option selected  class="hidden">${all.getNumc()}</option> </td>
                                                <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${all.getNumc()}</td>    
                                                <td>${all.getClient().getSociete().getNom()}</td> 
                                                <td>${all.getClient().getNom()}</td> 
                                                <td>
                                                    <c:if test="${all.getStatus()==200}">
                                                        <span class=" <c:if test="${all.getStatus()==200}">badge badge-success</c:if>">
                                                                Livrée
                                                            </span>
                                                    </c:if>
                                                    <c:if test="${all.getStatus()==201}">
                                                        <span class=" <c:if test="${all.getStatus()==201}">badge badge-primary</c:if>">
                                                                Encours de livraison
                                                            </span>
                                                    </c:if>
                                                    <c:if test="${all.getStatus()==501}">
                                                        <span class="<c:if test="${all.getStatus()==501}">badge badge-danger</c:if>">
                                                                Non traité
                                                            </span>
                                                    </c:if>
                                                    <c:if test="${all.getStatus()==502}">
                                                        <span class=" <c:if test="${all.getStatus()==502}">badge badge-warning</c:if>">
                                                                Encours de traitement
                                                            </span>
                                                    </c:if>
                                                    <c:if test="${all.getStatus()==401}">
                                                        <span class=" <c:if test="${all.getStatus()==401}">badge badge-info</c:if>">
                                                                Attente de facturation
                                                            </span>
                                                    </c:if>
                                                </td> 
                                                <td>${all.getQt()}</td> 
                                                <td><f:formatNumber value="${all.getPrixtotal()}"type="CURRENCY" currencySymbol=""/></td> 
                                                <td>${all.getDate()}</td>
                                                </tr>
                                        </c:forEach>

                                        </tbody>
                                        <tfoot>
                                            <tr style="font-weight: bold; color: #00b5b8">
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td>TOTAL</td>
                                                <td>${qte}</td>
                                                <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                <td></td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--/ Restore & show all table -->
        </div>
    </div>
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
        <c:import url="/WEB-INF/composants/modal/voirCommande.jsp"/>
    </c:if>
</c:if>
