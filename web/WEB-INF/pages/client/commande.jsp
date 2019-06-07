<%-- 
    Document   : commande
    Created on : 28 nov. 2018, 11:36:10
    Author     : messi01
--%>

<%-- 
    Document   : validation
    Created on : 28 nov. 2018, 11:11:46
    Author     : messi01
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or 1==1}">
    <c:set var="id" value="&r=7tyui895qze412"></c:set>
    <c:if test="${commande != null}">
        <c:set var="isnew" value="${2}"></c:set>
        <c:set var="id" value="&idC=${commande.getId()}"></c:set>
    </c:if>
    <c:if test="${commande == null}">
        <c:set var="isnew" value="${1}"></c:set>
    </c:if>
    <c:if test="${commandes != null}">
        <c:set var="numc" value="${commandes.get(0).getLigne().getId()}"></c:set>
    </c:if>
    <c:if test="${commandes == null}">
        <c:set var="numc" value="${0}"></c:set>
    </c:if>
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
   <div class="row <c:if test="${commande == null}">autoloader</c:if>" <c:if test="${commande == null}">autoloader="true"</c:if>>
        <div class="col-md-4">
            <div class="card">
                <div class="card-header card-head-inverse bg-primary">
                    <h4 class="card-title">Commande encours</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                    <div class="heading-elements">
                        <ul class="list-inline mb-0">
                            <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card-content">
                    <div class="card-body">
                        <input type="text" ng-model="search" class="round form-control-sm form-control" placeholder="rechercher"/>
                    </div>
                    <ul class="list-group list-group-flush" style="max-height: 600px;overflow-y: scroll">
                        <c:forEach items="${commandesclients}" var="all">
                            <c:if test="${all.getEtatc().getCode()==501 and all.getClient().getId()==sessionScope.client.getId()}">
                                <a href="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=3&id=${all.getId()}" title="${all.getQtotal()} produits commandé(s)" >
                                    <li class="list-group-item <c:if test="${all.getId()==commandes.get(0).getLigne().getId()}">active</c:if>">
                                            <span class="badge badge-default badge-pill float-right 
                                            <c:if test="${all.getQtotal()<5}">bg-primary</c:if>
                                            <c:if test="${all.getQtotal()>5}">bg-danger</c:if>">
                                            ${all.getQtotal()}
                                        </span>
                                        Commande du :
                                        ${all.getDatec()}
                                    </li>
                                </a>
                            </c:if>
                        </c:forEach>

                    </ul>

                </div>
            </div>
        </div>
        <c:if test="${commandes != null}">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title" id="from-actions-center-dropdown">Commande de ${commandes.get(0).getLigne().getClient().getNom()}</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>

                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="card-text">

                            </div>
                            

                            <div class="form-group">
                                <div class="btn-group btn-sm" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <button  class="btn btn-float btn-outline-success btn-round"
                                             data-toggle='tooltips' title="voir les détails de la commande" id="seedetailscommande">
                                        <i class="fa fa-eye"></i></button>
                                    <button type="button" title="Ajouter un produit" class="btn btn-float btn-outline-success btn-round"
                                            data-toggle="modal"
                                            data-target="#bootstrap">
                                        <i class="fa fa-plus-circle"></i></button>
                                    <button type="button" title="Modifier le produit" class="btn-sm btn btn-float btn-outline-primary btn-round btn-link-update" name="idC" action="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=3&id=${numc}"><i class="fa fa-edit"></i></button>
                                    <button type="button" title="supprimer le produit" class="btn-sm btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=4&id=${numc}"><i class="fa fa-trash"></i></button>



                                </div>
                            </div>
                            <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Categorie</th>
                                        <th>Article</th>
                                        <th>P.U</th>
                                        <th>Qte</th>
                                        <th>P.T</th>
                                        <th class="datec" pos="6">Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="total" value="0"></c:set>
                                    <c:set var="qte" value="0"></c:set>
                                    <c:forEach items="${commandes}" var="cmd">
                                        <c:set var="total" value="${total + (cmd.getArticle().getPrix() * cmd.getQuantite())}"></c:set>
                                        <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>
                                            <tr>
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                    <td>${cmd.getArticle().getCategorie().getNom()}</td>
                                    <td>${cmd.getArticle().getNom()}</td>
                                    <td>
                                        <f:formatNumber value="${cmd.getArticle().getPrix()}"type="CURRENCY" currencySymbol=""/>
                                    </td>
                                    <td>${cmd.getQuantite()} </td>
                                    <td>
                                        <f:formatNumber value="${cmd.getArticle().getPrix() * cmd.getQuantite()}"type="CURRENCY" currencySymbol=""/>
                                    </td>
                                    <td>${cmd.getLigne().getDatecc()}</td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                                <tfoot>
                                    <tr style="font-weight: bold; color: #00b5b8">
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>TOTAL</td>
                                        <td>${qte}</td>
                                    <td ><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                        <td></td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <c:import url="/WEB-INF/composants/modal/detailsCommande.jsp"/>
        </c:if>
    </div>
    <c:import url="/WEB-INF/composants/modal/updateCommandeClient.jsp"/>

</c:if>


