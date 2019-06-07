<%-- 
    Document   : recouvrement
    Created on : 22 mai 2019, 16:17:22
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
                            <h4 class="card-title">Recouvrement Dette Client</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">
                            <div class="card-body">
                                <div class="card-text">
                                    <form method="post" action="historiques?q=${q}&action=model&model=recouvrement&isNew=0" class="" novalidate>


                                        <fieldset>
                                            <div class="row">
                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="categorie_article">Date
                                                        <span class="danger">*</span></label>
                                                    <input type="text" class="form-control-sm form-control round singledate" name="date"/>
                                                </div>
                                                <div class="col-md-2 hidden">
                                                    <div class="form-group">
                                                        <label for="lastName">
                                                            Client
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" id="statut" class="select2-size-xs block form-control round " name="client">
                                                            <option value="0">Tous</option>
                                                            <c:forEach items="${clients}" var="cl">
                                                                <option value="${cl.getId()}"> ${cl.getNom()}</option>

                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="lastName">

                                                        </label>

                                                        <input style="margin-top: 25px" type="submit" class="btn btn-outline-primary  round btn-sm"
                                                               value="Enregistrer">
                                                    </div>
                                                </div>


                                            </div>
                                        </fieldset>

                                    </form>
                                </div>
                                <div class="form-group">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->

                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                data-toggle='tooltips' title="tout Imprimer "
                                                name="id"><i class="fa fa-print"></i></button>

                                    </div>
                                </div>
                                <div class="text-center bg-dark title_table" style="color:white">${messageTable}</div>
                                <br>
                                <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>N°</th>
                                            <th>Client</th>
                                            <th>Montant</th>
                                            <th>Avance</th>
                                            <th>Reste</th>
                                            <th>Date</th>
                                            <th>Commentaire</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${not empty recouvrements }">
                                            <c:set var="totalMT" value="${0}"></c:set>
                                            <c:set var="totalMR" value="${0}"></c:set>
                                            <c:set var="totalAV" value="${0}"></c:set>
                                            <c:forEach items="${recouvrements}" var="cmd">
                                                <c:set var="totalMR" value="${totalMR+cmd.getMontantRestant()}"></c:set>
                                                <c:set var="totalMT" value="${totalMT+cmd.getMontantInit()}"></c:set>
                                                <c:set var="totalAV" value="${totalAV+(cmd.getMontantInit()-cmd.getMontantRestant())}"></c:set>
                                                    <tr>
                                                        <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                            <td>${cmd.getId()}</td>
                                            <td>${cmd.getLigneAccount().getClient().getNom()}</td>
                                            <td><f:formatNumber value="${cmd.getMontantInit()}"type="CURRENCY" currencySymbol=""/></td>
                                            <td><f:formatNumber value="${cmd.getAvance()}"type="CURRENCY" currencySymbol=""/></td>
                                            <td><f:formatNumber value="${cmd.getMontantRestant()}"type="CURRENCY" currencySymbol=""/></td>

                                            <td><f:formatDate value="${cmd.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                            <td>${cmd.getCommentaire()}</td>                                                    
                                            </tr>

                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>Total</td>
                                            <td><f:formatNumber value="${totalMT}"type="CURRENCY" currencySymbol=""/></td>
                                            <td><f:formatNumber value="${totalAV}"type="CURRENCY" currencySymbol=""/></td>
                                            <td><f:formatNumber value="${totalMR}"type="CURRENCY" currencySymbol=""/></td>
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
        </section>
    </c:if>
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
        <c:import url="/WEB-INF/composants/modal/voirAccount.jsp"/>
    </c:if>
</c:if>


