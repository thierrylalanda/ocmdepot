<%-- 
    Document   : retourEmballage
    Created on : 17 avr. 2019, 18:26:54
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestemballage() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
        <c:set var="id" value="&r=7tyui895qze412"></c:set>
        <c:if test="${compte != null}">
            <c:set var="isnew" value="${1}"></c:set>
            <c:set var="id" value="&id=${compte.getId()}"></c:set>
        </c:if>
        <c:if test="${compte == null}">
            <c:set var="isnew" value="${0}"></c:set>
        </c:if>
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title" id="from-actions-center-dropdown">Retour Emballage</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="card-text">
                                <form class="form form_find_magasin" method="post" action="gestionCommandes?action=model&model=retour&q=${q}&isNew=1">
                                    <fieldset>
                                        <div class="row">
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label for="code">
                                                        Client
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <select style="width: 100%"   id="find_magasin_to_stock" class="select2-size-xs block form-control round " name="client">
                                                        <option value=""></option>
                                                        <c:forEach items="${clients}" var="cl">
                                                            <option <c:if test="${sessionScope.clientEncour != null}">disabled <c:if test="${cl.getId()==sessionScope.clientEncour.getId()}">selected</c:if></c:if>  value="${cl.getId()}"> ${cl.getNom()}</option>
                                                        </c:forEach>
                                                    </select>    
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                                <c:if test="${not empty clientEncour }">
                                    <form class="form" method="post" action="gestionCommandes?action=model&model=retour&q=${q}&isNew=2&client=${clientEncour.getId()}">
                                        <fieldset>
                                            <div class="row">

                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="articles_stock_magasin">Emballages
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="articles_inventaire_societe"  class="select2-size-xs block form-control round required" name="emballage">
                                                        <option></option>
                                                        <c:forEach items="${sessionScope.clientEncour.getCompteEmballageList()}" var="emb">
                                                            <option stock="${emb.getQuantite()}" value="${emb.getEmballage().getId()}">${emb.getEmballage().getCode()}=> ${emb.getEmballage().getNom()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Qte</label>
                                                    <input type="number" id="quantite_societe" disabled class="form-control-sm form-control round"/>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Retour</label>
                                                    <input type="number" value="${compte.getQuantite()}" class="form-control-sm form-control round" required name="quantite"/>
                                                </div>
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-download"></i> Ajouter
                                                    </button>
                                                </div>
                                            </div>
                                        </fieldset>

                                    </form>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <c:if test="${not empty clientEncour }">
                                        <a title="Fermer ce client" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                           data-toggle="tooltips"  href="gestionCommandes?q=${q}&action=model&model=retour&isNew=3"><i class="fa fa-eject"></i></a>
                                        </c:if>
                                    <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                            data-toggle='tooltips' title="tout Imprimer "
                                            name="id"><i class="fa fa-print"></i></button> 
                                </div>
                            </div>
                            <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Client</th>
                                        <th>Code</th>
                                        <th>Nom</th>
                                        <th>Qte</th>
                                        <th>Montant</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="total" value="0"></c:set>
                                    <c:set var="qte" value="0"></c:set>
                                    <c:forEach items="${comptes}" var="cmd">
                                        <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>

                                        <c:set var="total" value="${total + cmd.getMontant()}"></c:set>
                                            <tr>
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                    <td>${cmd.getClient().getNom()}</td>
                                    <td>${cmd.getEmballage().getCode()}</td>
                                    <td>${cmd.getEmballage().getNom()}</td>
                                    <td>${cmd.getQuantite()}</td>
                                    <td><f:formatNumber value="${cmd.getMontant()}"type="CURRENCY" currencySymbol=""/></td>
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
        </div>
    </c:if>
</c:if>

