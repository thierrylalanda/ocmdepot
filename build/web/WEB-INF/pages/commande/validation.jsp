<%-- 
    Document   : validation
    Created on : 28 nov. 2018, 10:57:42
    Author     : messi01
--%>
<%-- 
    Document   : traitement
    Created on : 26 nov. 2018, 17:25:15
    Author     : Administrateur
--%>

<%-- 
    Document   : addcommande
    Created on : 26 nov. 2018, 16:49:23
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien103}">
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
   <div  class="row <c:if test="${commande == null}">autoloader</c:if>" <c:if test="${commande == null}">autoloader="true"</c:if>>
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
                        <c:forEach items="${commandesValideClient}" var="all">
                           <a href="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=3&id=${all.getId()}" title="${all.getQtotal()} produits commandé(s)" >
                                    <li class="list-group-item <c:if test="${all.getId()==commandes.get(0).getLigne().getId()}">active</c:if>">
                                            <span class="badge badge-default badge-pill float-right 
                                            <c:if test="${all.getQtotal()<5}">bg-primary</c:if>
                                            <c:if test="${all.getQtotal()>5}">bg-danger</c:if>">
                                            ${all.getQtotal()}
                                        </span>
                                        ${all.getClient().getNom()} (${all.getDatecc()})
                                    </li>
                                </a>
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
                                <form class="form" novalidate method="post" action="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=${isnew}${id}&id=${commande.getLigne().getId()}">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="rubriqueIncident">Categorie
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" <c:if test="${commande != null}">disabled</c:if> required id="categorie_article"  class="select2-size-xs block form-control round" name="cat">
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
                                                <input type="number" required value="${commande.getQt()}" id="quantite" class="form-control-sm form-control round" name="qt"/>
                                            </div>
                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="description">Commentaire</label>
                                                <textarea id="description" class="form-control-sm form-control round" name="commantaire1">${commande.getCommantaire()}</textarea>
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
                                    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
                                        <button  class="btn btn-float btn-outline-success btn-round"
                                                 data-toggle='tooltips' title="voir les détails de la commande" id="seedetailscommande">
                                            <i class="fa fa-eye"></i></button>
                                        </c:if>
                                        <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien100}">
                                        <button type="button" title="Modifier le produit" class="btn-sm btn btn-float btn-outline-primary btn-round btn-link-update" name="idC" action="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=3&id=${numc}"><i class="fa fa-edit"></i></button>
                                        </c:if>
                                        <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien101}">
                                        <button type="button" title="supprimer le produit" class="btn-sm btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=4&id=${numc}"><i class="fa fa-trash"></i></button>
                                        </c:if>


                                </div>
                            </div>
                            <c:if test="${ sessionScope.societe.getGestcassier() == 1}">
                                <div class="text-center bg-dark title_table" style="color:white">Total casier : ${commandes.get(0).getLigne().getCassier()}</div><br>

                            </c:if>
                            <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                <thead>
                                    <tr>
                                        <th>#</th>
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
                                        <c:set var="total" value="${total + (cmd.getPrix() * cmd.getQuantite())}"></c:set>
                                        <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>
                                            <tr>
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                <td>${cmd.getArticle().getNom()}</td>
                                <td>${cmd.getPrix()}</td>
                                <td>${cmd.getQuantite()}</td>
                                <td>${cmd.getPrixTotal()}</td>
                            </tr>
                                </c:forEach>

                                </tbody>
                                <tfoot>
                                    <tr style="font-weight: bold; color: #00b5b8">
                                        <td></td>
                                        <td></td>
                                        <td>TOTAL</td>
                                        <td>${qte}</td>
                                        <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                      
                                    </tr>
                                </tfoot>
                            </table>
                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien102}">
                                <div class="form-group col-md-4">
                                    <div class="btn-group btn-sm" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->

                                        <a title="Enregistrer la commande" class="btn-sm btn btn-float btn-outline-warning btn-round "
                                           data-toggle="tooltips" href="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=2&id=${numc}">
                                            <i class="fa fa-save"> Enregistrer pour livraison</i> 
                                        </a>


                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <c:import url="/WEB-INF/composants/modal/detailsCommande.jsp"/>
        </c:if>
    </div>
</c:if>
