<%-- 
    Document   : listeVente
    Created on : 20 avr. 2019, 17:34:35
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien107}">
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
                                <h4 class="card-title">Liste des Ventes</h4>
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
                                            <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir les détails de la commande"
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien106}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round print_one_element_commande petit_format" 
                                                        data-toggle='tooltips' title="Imprimer une commande sous petit format"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element_commande" 
                                                        data-toggle='tooltips' title="Imprimer une commande"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                        data-toggle='tooltips' title="tout Imprimer "
                                                        name="id"><i class="fa fa-print"></i></button>
                                                </c:if>
                                        </div>
                                    </div>
                                    </p>
                                    <div style="overflow-x: scroll;">
                                        <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th class="datec" pos="1">N°</th>
                                                    <th>Client</th>
                                                    <th>Statut</th>
                                                    <th>Qte</th>
                                                    <th>P.T</th>
                                                    <th>Remise</th>
                                                    <th>Echeance</th>
                                                    <th>Date commande</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="total" value="0"></c:set>
                                                <c:set var="qte" value="0"></c:set>
                                                <c:set var="remise" value="0"></c:set>
                                                <c:forEach items="${commandes}" var="all">
                                                    <c:if test="${all.getEtatc().getCode()==200 or all.getEtatc().getCode()==300 or all.getEtatc().getCode()==201}">
                                                        <c:set var="total" value="${total + all.getPrixtotal()}"></c:set>
                                                        <c:set var="qte" value="${qte+all.getQtotal()}"></c:set>
                                                        <c:set var="remise" value="${remise+all.getRemise()}"></c:set>
                                                            <tr >
                                                                <td><option selected  class="hidden">${all.getId()}</option> </td>
                                                    <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${all.getId()}</td>    
                                                    <td>${all.getClient().getNom()}</td> 
                                                    <td>
                                                        <c:if test="${all.getEtatc().getCode()==200}">
                                                            <span class=" <c:if test="${all.getEtatc().getCode()==200}">badge badge-success</c:if>">
                                                                    Livrée
                                                                </span>
                                                        </c:if>
                                                        <c:if test="${all.getEtatc().getCode()==201}">
                                                            <span class=" <c:if test="${all.getEtatc().getCode()==201}">badge badge-primary</c:if>">
                                                                    Encours de livraison
                                                                </span>
                                                        </c:if>
                                                        <c:if test="${all.getEtatc().getCode()==300}">
                                                            <c:if test="${not empty all.getLigneAccountList()}">
                                                                <c:if test="${all.getLigneAccountList().get(0).getMontantRestant()!=0.0}">

                                                                    <span class=" badge badge-warning">
                                                                        Encours Paiement
                                                                    </span>
                                                                </c:if>
                                                                <c:if test="${all.getLigneAccountList().get(0).getMontantRestant() == 0.0}">

                                                                    <span class=" badge badge-success">
                                                                        Payer 
                                                                    </span>
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${empty all.getLigneAccountList()}">
                                                                <span class=" badge badge-danger">
                                                                    Non Payée
                                                                </span>
                                                            </c:if>
                                                        </c:if>


                                                    </td> 
                                                    <td>${all.getQtotal()}</td> 
                                                    <td><f:formatNumber value="${all.getPrixtotal()}"type="CURRENCY" currencySymbol=""/></td> 
                                                    <td><f:formatNumber value="${all.getRemise()}"type="CURRENCY" currencySymbol=""/></td> 
                                                    <td>${all.getDateliv()}</td>
                                                    <td>${all.getDatecc()}</td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>

                                            </tbody>
                                            <tfoot>
                                                <tr style="font-weight: bold; color: #00b5b8">
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td>TOTAL</td>
                                                    <td><f:formatNumber value="${qte}"type="CURRENCY" currencySymbol=""/></td>
                                                    <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                    <td><f:formatNumber value="${remise}"type="CURRENCY" currencySymbol=""/></td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir les détails de la commande"
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien106}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round print_one_element_commande petit_format" 
                                                        data-toggle='tooltips' title="Imprimer une commande sous petit format"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element_commande" 
                                                        data-toggle='tooltips' title="Imprimer une commande"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                        data-toggle='tooltips' title="tout Imprimer "
                                                        name="id"><i class="fa fa-print"></i></button>
                                                </c:if>
                                        </div>
                                    </div>
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

