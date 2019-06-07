<%-- 
    Document   : caisse
    Created on : 16 avr. 2019, 13:17:59
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestcaisse() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
        <c:set var="id" value="&r=7tyui895qze412"></c:set>
        <c:if test="${ligneToUpdate != null}">
            <c:set var="isnew" value="${1}"></c:set>
            <c:set var="id" value="&ligne=${ligneToUpdate.getId()}"></c:set>
        </c:if>
        <c:if test="${ligneToUpdate == null}">
            <c:set var="isnew" value="${0}"></c:set>
        </c:if>
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <section>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Gestion Caisse</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">

                            <div class="card-body">
                                <div class="card-text">

                                    <div class="row">
                                        <c:if test="${empty caisse and empty caisses}">
                                            <div class="col-lg-6 col-md-12 border-success">
                                                <div class="card ">
                                                    <div class="card-content">
                                                        <div class="card-body">
                                                            <h4 class="card-title info">Créer un compte Caisse</h4>
                                                            <form class="form" method="post" action="gestioncaisse?action=model&model=caisse&q=${q}&isNew=3">
                                                                <div class="form-group col-lg-12 col-sm-12">
                                                                    <label for="quantite">Montant<span class="danger">*</span></label>
                                                                    <input type="number" min="0"  class="form-control-sm form-control round" required name="montant"/>
                                                                </div>
                                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                                    <button type="submit" class="btn btn-primary round btn-sm btn_caisse" style="margin-top: 25px">
                                                                        <i class="fa fa-save"></i> Enregistrer
                                                                    </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${empty caisse and not empty caisses}">
                                            <div class="col-lg-6 col-md-12 border-success">
                                                <div class="card ">
                                                    <div class="card-content">
                                                        <div class="card-body">
                                                            <h4 class="card-title info">Ouverture de la caisse</h4>
                                                            <p class="card-text">
                                                                <a href="gestioncaisse?action=model&model=caisse&q=${q}&isNew=1" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                                    <i class="fa fa-save"></i> Ouverture de la Caisse
                                                                </a>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${caisse.getId()!=0}">
                                            <div class="col-lg-6 col-md-12 border-success">
                                                <div class="card ">
                                                    <div class="card-content">
                                                        <div class="card-body">
                                                            <h4 class="card-title info">Caisse Encours</h4>
                                                            <p class="card-text">
                                                                <strong for="firstName">Montant Ouverture :</strong>
                                                                <f:formatNumber value="${caisse.getMontant()}"type="CURRENCY" currencySymbol=" FCFA"/>
                                                            </p>
                                                            <p class="card-text">
                                                                <strong for="firstName">Reste en Caisse :</strong>
                                                                <f:formatNumber value="${caisse.getMontantRestant()}"type="CURRENCY" currencySymbol=" FCFA"/>
                                                            </p>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="row">
                                        <div class="card ">
                                            <div class="card-content">
                                                <div class="card-body">
                                                    <h4 class="card-title info">Evolution de la caisse</h4>
                                                    <div class="form-group">
                                                        <div class="btn-group" role="group" aria-label="Basic example">

                                                            <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                                    data-toggle='tooltips' title="tout Imprimer "
                                                                    name="id"><i class="fa fa-print"></i></button> 
                                                        </div>
                                                    </div>
                                                    <div style="overflow-x: scroll;">
                                                        <table style="width: 100%"  class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                                            <thead>
                                                                <tr>
                                                                    <th>#</th>
                                                                    <th>N°</th>
                                                                    <th>Montant</th>
                                                                    <th>Date Ouv</th>
                                                                    <th>Montant Rest</th>
                                                                    <th>Montant Vers</th>
                                                                    <th>Ecart</th>
                                                                    <th>Date Fer</th>
                                                                    <th>Etat</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:set var="total" value="0"></c:set>
                                                                <c:set var="totalR" value="0"></c:set>
                                                                <c:set var="totalV" value="0"></c:set>
                                                                <c:set var="totalE" value="0"></c:set>
                                                                <c:forEach items="${caisses}" var="cmd">


                                                                    <c:set var="total" value="${total + cmd.getMontant()}"></c:set>
                                                                    <c:set var="totalR" value="${totalR + cmd.getMontantRestant()}"></c:set>
                                                                    <c:set var="totalV" value="${totalV + cmd.getMontantVerser()}"></c:set>
                                                                    <c:set var="totalE" value="${totalE + cmd.getEcart()}"></c:set>
                                                                        <tr>
                                                                            <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                                                <td>${cmd.getId()}</td>
                                                                <td><f:formatNumber value="${cmd.getMontant()}"type="CURRENCY" currencySymbol=""/></td>
                                                                <td><f:formatDate value="${cmd.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                                                <td><f:formatNumber value="${cmd.getMontantRestant()}"type="CURRENCY" currencySymbol=""/></td>
                                                                <td><f:formatNumber value="${cmd.getMontantVerser()}"type="CURRENCY" currencySymbol=""/></td>
                                                                <td><f:formatNumber value="${cmd.getEcart()}"type="CURRENCY" currencySymbol=""/></td>
                                                                <td><f:formatDate value="${cmd.getDateFermeture()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                                                <td>
                                                                    <c:if test="${cmd.getEtatCaisse()==0}">
                                                                        <span class="badge badge-warning">
                                                                            Fermée
                                                                        </span>
                                                                    </c:if>
                                                                    <c:if test="${cmd.getEtatCaisse()==1}">
                                                                        <span class="badge badge-success">
                                                                            Ouverte
                                                                        </span>
                                                                    </c:if>
                                                                </td>
                                                                </tr>
                                                            </c:forEach>

                                                            </tbody>
                                                            <tfoot>
                                                                <tr style="font-weight: bold; color: #00b5b8">
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td><f:formatNumber value="${totalR}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${totalV}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${totalE}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>
                                                            </tfoot>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </c:if>
</c:if>

