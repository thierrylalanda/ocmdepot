<%-- 
    Document   : reception
    Created on : 29 nov. 2018, 09:42:36
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
        <c:set var="isnew" value="${0}"></c:set>
        <c:set var="id" value="&cc=${commande.getId()}"></c:set>
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
                    <h4 class="card-title">Commande à réceptionner</h4>
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
                            <c:if test="${all.getEtatc().getCode()==201 and all.getClient().getId()==sessionScope.client.getId()}">
                                <a href="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=3&id=${all.getId()}" title="${all.getQtotal()} produits commandé(s)" >
                                    <li class="list-group-item <c:if test="${all.getId()==commandes.get(0).getLigne().getId()}">active</c:if>">
                                            <span class="badge badge-default badge-pill float-right 
                                            <c:if test="${all.getQtotal()<5}">bg-primary</c:if>
                                            <c:if test="${all.getQtotal()>5}">bg-danger</c:if>">
                                            ${all.getQtotal()}
                                        </span>
                                        Commande du :
                                        ${all.getDatecc()}
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
                            <c:if test="${commande!=null}">
                                <form class="form" novalidate method="post" action="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=2&id=${commande.getLigne().getId()}&ismodif=ok&idC=${commande.getId()}">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="rubriqueIncident">Categorie
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required id="categorie_article"  class="select2-size-xs block form-control round" name="cat">
                                                    <option value=""></option>
                                                    <c:forEach items="${categories}" var="cat">
                                                        <option <c:if test="${cat.getId()==commande.getArticle().getCategorie().getId()}">selected</c:if> value="${cat.getId()}"> ${cat.getNom()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="srubriqueIncident">Article
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required id="articles_categorie" class="select2-size-xs block form-control round" name="article">
                                                    <c:if test="${commande != null}">
                                                        <option selected value="${commande.getArticle().getId()}"> ${commande.getArticle().getNom()}</option>
                                                    </c:if>
                                                </select>
                                            </div>
                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="quantite">Qte
                                                    <span class="danger">*</span></label>
                                                <input type="number" required id="quantite" value="${commande.getQuantite()}" class="form-control-sm form-control round" name="quantite"/>
                                            </div>
                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="description">Commentaire</label>
                                                <textarea id="description" class="form-control-sm form-control round" name="commantaire">${commande.getCommantaire()}</textarea>
                                            </div>
                                            <div class="form-control-sm form-group col-lg-3 col-sm-12">
                                                <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                    <i class="fa fa-check-square-o"></i> Enregistrer
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:if>

                            <div class="form-group">
                                <div class="btn-group btn-sm" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <button  class="btn btn-float btn-outline-success btn-round"
                                             data-toggle='tooltips' title="voir les détails de la commande" id="seedetailscommande">
                                        <i class="fa fa-eye"></i></button>
                                    <a data-toggle="tooltips" title="Receptionner la commande" class="btn-sm btn btn-float btn-outline-success btn-round add_commande" href="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=5&id=${commandes.get(0).getLigne().getId()}">
                                        <i class="fa fa-save"></i>
                                    </a>


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
                                    <td>${cmd.getArticle().getPrix()}</td>
                                    <td>${cmd.getQuantite()}</td>
                                    <td>${cmd.getArticle().getPrix() * cmd.getQuantite()}</td>
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
                                        <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                   
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
</c:if>

