<%-- 
    Document   : resolveAccount
    Created on : 7 avr. 2019, 13:35:45
    Author     : Administrateur
--%>

<%-- 
    Document   : traitement
    Created on : 26 nov. 2018, 17:25:15
    Author     : Administrateur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or 1==1}">
    <c:set var="id" value="&r=7tyui895qze412"></c:set>
    <c:if test="${account != null}">
        <c:set var="isnew" value="${1}"></c:set>
        <c:set var="id" value="&account=${account.getId()}"></c:set>
    </c:if>
    <c:if test="${account == null}">
        <c:set var="isnew" value="${0}"></c:set>
    </c:if>
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <div class="row">
        <c:if test="${1==2}">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header card-head-inverse bg-primary">
                        <h4 class="card-title">Règlement encours</h4>
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
                            <c:forEach items="${lignesAccount}" var="all">
                                <c:if test="${all.getEtat()==0}">
                                    <a href="gestioncaisse?action=model&model=addAccount&q=${q}&isNew=2&id=${all.getId()}" title="Total  emprunt ${all.getMontantNet()}" >
                                        <li class="list-group-item <c:if test="${all.getId()==sessionScope.ligneEncour.getId()}">active</c:if>">
                                                <span class="badge badge-default badge-pill float-right bg-danger">
                                                <f:formatNumber value="${all.getMontantRestant()}"type="CURRENCY" currencySymbol=""/> 
                                            </span>
                                            ${all.getClient().getNom()} <f:formatDate value="${all.getDateCreate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/>
                                        </li>
                                    </a>
                                </c:if>
                            </c:forEach>

                        </ul>

                    </div>
                </div>
            </div>
        </c:if>

        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title" id="from-actions-center-dropdown">Emprunt de ${ligne.getClient().getNom()}</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                    <p class="card-text">
                        <strong for="firstName">Net à Payer :</strong>
                        <span class="badge bg-success">
                            <f:formatNumber value="${ligne.getMontantNet()}"type="CURRENCY" currencySymbol=""/>
                        </span>

                    </p>
                    <p class="card-text">
                        <strong for="firstName">Reste :</strong>
                        <span class="badge bg-warning">
                            <f:formatNumber value="${ligne.getMontantRestant()}"type="CURRENCY" currencySymbol=""/>
                        </span>

                    </p>
                </div>
                <div class="card-content">
                    <div class="card-body">
                        <div class="card-text">
                            <c:if test="${ligne.getEtat() == 0}">
                                <form class="form" novalidate method="post" action="gestioncaisse?action=model&model=reglementAccount&q=${q}&isNew=${isnew}${id}&ligne=${ligne.getId()}">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="form-group col-lg-2 col-sm-12">
                                                <label for="quantite">Avance
                                                    <span class="danger">*</span></label>
                                                <input type="number" required id="quantite" value="${account.getAvance()}" min="0" class="form-control-sm form-control round" name="avance"
                                                       pattern="^\d+(?:\.\d{1,2})?$" onblur="
                                                               this.parentNode.parentNode.style.backgroundColor = /^\d+(?:\.\d{1,2})?$/.test(this.value) ? 'inherit' : 'red'" />
                                            </div>
                                            <div class="form-group col-lg-2 col-sm-12">
                                                <label for="quantite"><span class="danger">*</span>Commentaire</label>
                                                <textarea name="commentaire" class="form-control-sm form-control round" required></textarea>
                                            </div>
                                            <div class="form-control-sm form-group col-lg-3 col-sm-12">
                                                <button type="submit" class="btn btn-primary round btn-sm btn_caisse" style="margin-top: 25px">
                                                    <i class="fa fa-check-square-o"></i> Enregistrer
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <div class="btn-group btn-sm" role="group" aria-label="Basic example">
                                <!-- Outline Round Floating button -->

<!--                                    <button type="button" title="Modifier" class="btn-sm btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="gestioncaisse?action=model&model=reglementAccount&q=${q}&isNew=2"><i class="fa fa-edit"></i></button>-->
                                <c:if test="${1==2}">
                                    <button type="button" title="supprimer" class="btn-sm btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="gestioncaisse?action=model&model=reglementAccount&q=${q}&isNew=3"><i class="fa fa-trash"></i></button>

                                </c:if>
                                <button  class="btn t btn-outline-dark round pull-right print_marge" 
                                         data-toggle='tooltips' title="Imprimer la Liste des versement"
                                         ><i class="fa fa-print"></i></button>
                            </div>
                        </div>
                        <div class="text-center bg-dark title_table" style="color:white">Liste des versements concernant le client ${ligne.getClient().getNom()}</div>
                        <br>
                        <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>N°</th>
                                    <th>Montant</th>
                                    <th>Avance</th>
                                    <th>Reste</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:set var="total" value="0"></c:set>
                                <c:set var="avance" value="0"></c:set>
                                <c:set var="reste" value="0"></c:set>
                                <c:forEach items="${ligne.getAccountList()}" var="cmd">
                                    <c:set var="total" value="${total + cmd.getMontantInit()}"></c:set>
                                    <c:set var="avance" value="${avance + cmd.getAvance()}"></c:set>
                                    <c:set var="reste" value="${reste + cmd.getMontantRestant()}"></c:set>
                                        <tr>
                                            <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                <td>${cmd.getId()} </td>
                                <td>${cmd.getMontantInit()}</td>
                                <td>${cmd.getAvance()}</td>
                                <td>${cmd.getMontantRestant()}</td>
                                <td><f:formatDate value="${cmd.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                            <tfoot>
                                <tr style="font-weight: bold; color: #00b5b8">
                                    <td></td>
                                    <td>TOTAL</td>
                                    <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                    <td><f:formatNumber value="${avance}"type="CURRENCY" currencySymbol=""/></td>
                                    <td><f:formatNumber value="${reste}"type="CURRENCY" currencySymbol=""/></td>
                                    <td></td>
                                </tr>
                            </tfoot>
                        </table>
                        <a class="btn btn-outline-secondary btn-sm round" href="gestioncaisse?action=model&model=init&q=commande/caisse/allDetteClient&isNew=0"><icon class="fa fa-arrow-left"></icon></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>

