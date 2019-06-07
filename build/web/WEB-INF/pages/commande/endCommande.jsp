<%-- 
    Document   : endCommande
    Created on : 13 févr. 2019, 17:32:47
    Author     : messi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien130}">
    <c:set var="id" value="&r=7tyui895qze412"></c:set>
    <c:if test="${commande != null}">
        <c:set var="isnew" value="${0}"></c:set>
        <c:set var="id" value="&cc=${commande.getId()}"></c:set>
    </c:if>
    <c:if test="${commande == null}">
        <c:set var="isnew" value="${7}"></c:set>
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
                        <c:forEach items="${commandesLivraison}" var="all">

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
                                <form class="form" novalidate method="post" action="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=${isnew}${id}&id=${numc}">
                                    <div class="form-body">
                                        <div class="row">

                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="srubriqueIncident">Article
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required id="articles_inventaire_societe" class="select2-size-xs block form-control round" name="article">
                                                    <c:if test="${commande != null}">
                                                        <option selected value="${commande.getArticle().getId()}">${commande.getArticle().getCode()}=> ${commande.getArticle().getNom()}</option>
                                                    </c:if>
                                                    <c:forEach items="${articles}" var="art">
                                                        <option stock="${art.getStock()}" <c:if test="${commande.getArticle().getId() == art.getId()}">selected</c:if>  value="${art.getId()}"> ${art.getCode()}=>${art.getNom()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <c:if test="${commande != null}">
                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="quantite">Proposition
                                                        <span class="danger">*</span></label>
                                                    <input type="number" required id="quantite" <c:if test="${commande.getQt() != 0}">value="${commande.getQt()}"</c:if> <c:if test="${commande.getQt() == 0}">value="${commande.getQuantite()}"</c:if>  class="form-control-sm form-control round" name="qt"/>
                                                    </div>
                                            </c:if>

                                            <c:if test="${commande == null}">
                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="quantite">Qte
                                                        <span class="danger">*</span></label>
                                                    <input type="number" required id="quantite" value=""  class="form-control-sm form-control round" name="quantite"/>
                                                </div>

                                            </c:if>
                                            <c:if test="${sessionScope.societe.getGestStock()==1}">
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite"><span class="danger"></span>Stock</label>
                                                    <input type="number" id="quantite_societe" disabled class="form-control-sm form-control round"/>
                                                </div>
                                            </c:if>
<!--                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="description">Commentaire</label>
                                                <textarea id="description" class="form-control-sm form-control round" name="commantaire1">${commande.getCommantaire()}</textarea>
                                            </div>-->
                                            <div class="form-control-sm form-group col-lg-3 col-sm-12">
                                                <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                    <i class="fa fa-check-square-o"></i> Enregistrer
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                            <c:set var="propos" value="${0}"></c:set>
                            <c:forEach items="${commandes}" var="cmde">
                                <c:set var="propos" value="${propos + cmde.getQt()}"></c:set>
                            </c:forEach>

                            <div class="form-group">
                                <div class="btn-group btn-sm" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
                                        <button  class="btn btn-float btn-outline-success btn-round"
                                                 data-toggle='tooltips' title="voir les détails de la commande" id="seedetailscommande">
                                            <i class="fa fa-eye"></i></button>
                                        </c:if>
                                        <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien100}">
                                        <button type="button" title="Effectuer une proposition de commande" class="btn-sm btn btn-float btn-outline-primary btn-round btn-link-update" name="idC" action="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=3&id=${numc}"><i class="fa fa-edit"></i></button>
                                        </c:if>
                                        <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien101}">
                                        <button type="button" title="supprimer le produit" class="btn-sm btn btn-float btn-outline-danger btn-round btn-link-delete" name="ida" action="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=4&id=${numc}"><i class="fa fa-trash"></i></button>
                                        </c:if>   
                                        <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien108}">
                                            <c:if test="${propos !=0}">
                                            <a title="Enregistrer la Proposition de commande" class="btn-sm btn btn-float btn-outline-primary btn-round "
                                               data-toggle="tooltips" href="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=6&id=${numc}">
                                                <i class="fa fa-save"></i>
                                            </a>
                                        </c:if>
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
                                        <th>Code</th>
                                        <th>Article</th>
                                        <th>P.U</th>
                                        <th>Qte</th>
                                        <th class="hidden">Qte</th>
                                        <th>P.T</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:set var="total" value="0"></c:set>
                                    <c:set var="qte" value="0"></c:set>
                                    <c:set var="prop" value="0"></c:set>
                                    <c:forEach items="${commandes}" var="cmd">
                                        <c:set var="total" value="${total + (cmd.getPrix() * cmd.getQuantite())}"></c:set>
                                        <c:if test="${cmd.getArticle().getCode().substring(0,5) != 'VRACC' && cmd.getArticle().getCode().substring(0,3)!= 'VIP' }">

                                            <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>
                                        </c:if>
                                        <c:set var="prop" value="${prop+cmd.getQt()}"></c:set>
                                            <tr>
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                    <td>${cmd.getArticle().getCode()}</td>
                                    <td>${cmd.getArticle().getNom()}</td>
                                    <td>${cmd.getPrix()}</td>
                                    <td class="td_updateP" style="cursor:pointer">${cmd.getQuantite()}</td>
                                    <td class="hidden td_to_updateP"><input idC="${cmd.getId()}" class="qt_updateP  round q" q="${q}" type="number" value="${cmd.getQuantite()}"></td>
                                    <td><f:formatNumber value="${cmd.getPrixTotal()}"type="CURRENCY" currencySymbol=""/></td>

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
                                        <td class="hidden"></td>
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

