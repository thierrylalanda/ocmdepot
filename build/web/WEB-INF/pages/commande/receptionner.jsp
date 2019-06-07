<%-- 
    Document   : receptionner
    Created on : 16 janv. 2019, 09:38:09
    Author     : messi
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
                                <h4 class="card-title">Receptionner les commandes</h4>
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
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien128}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-reception" 
                                                        data-toggle='tooltips' title="Recetionner une commande"
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=PropositionCommande&isNew=8"><i class="fa fa-check-circle"></i></button>
                                                </c:if>
                                        </div>
                                    </div>
                                    </p>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th class="datec" pos="1">N°</th>
                                                <th>Client</th>
                                                <th>Statut</th>
                                                <th>Qte</th>
                                                <th>P.T</th>
                                                <th>Date commande</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="total" value="0"></c:set>
                                            <c:set var="qte" value="0"></c:set>
                                            <c:forEach items="${commandes}" var="all">
                                                <c:if test="${all.getEtatc().getCode()==201}">
                                                    <c:set var="total" value="${total + all.getPrixtotal()}"></c:set>
                                                    <c:set var="qte" value="${qte+all.getQtotal()}"></c:set>
                                                        <tr >
                                                            <td><option selected  class="hidden">${all.getId()}</option> </td>
                                                <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${all.getId()}</td>    
                                                <td>${all.getClient().getNom()}</td> 
                                                <td>

                                                    <c:if test="${all.getEtatc().getCode()==201}">
                                                        <span class=" <c:if test="${all.getEtatc().getCode()==201}">badge badge-primary</c:if>">
                                                                Encours de livraison
                                                            </span>
                                                    </c:if>
                                                </td> 
                                                <td>${all.getQtotal()}</td> 
                                                <td><f:formatNumber value="${all.getPrixtotal()}"type="CURRENCY" currencySymbol=""/></td> 
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
                                                <td>${qte}</td>
                                                <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                <td></td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir les détails de la commande"
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien128}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-reception" 
                                                        data-toggle='tooltips' title="Recetionner une commande"
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=PropositionCommande&isNew=8"><i class="fa fa-check-circle"></i></button>
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
