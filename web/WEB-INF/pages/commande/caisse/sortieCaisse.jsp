<%-- 
    Document   : sortieCaisse
    Created on : 16 avr. 2019, 13:06:35
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
                            <h4 class="card-title">Sortie de Caisse</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">
                            <div class="card-body">
                                <div class="card-text">
                                    <form class="form" method="post" action="gestioncaisse?action=model&model=sortieAccount&q=${q}&isNew=1">
                                        <fieldset>
                                            <div class="row">
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <label for="code">
                                                            Source
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" required="" class="select2-size-xs block form-control round " name="source">
                                                            <c:forEach items="${sourcesMouvement}" var="tr">
                                                                <option value="${tr.getId()}"> ${tr.getNom()}</option>
                                                            </c:forEach>
                                                        </select>    
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="quantite">Montant<span class="danger">*</span></label>
                                                    <input type="number" min="0" value="" class="form-control-sm form-control round" required name="montant"/>
                                                </div>

                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="quantite"><span class="danger">*</span>Commentaire</label>
                                                    <textarea name="commentaire" class="form-control-sm form-control round" required></textarea>
                                                </div>
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm btn_caisse" style="margin-top: 25px">
                                                        <i class="fa fa-upload"></i> Retrait
                                                    </button>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                                <div class="form-group">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->
                                        <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                data-toggle='tooltips' title="voir les détails"
                                                name="id" action="gestioncaisse?q=${q}&action=model&model=sortieAccount&isNew=2">
                                            <i class="fa fa-eye"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                data-toggle='tooltips' title="tout Imprimer "
                                                name="id"><i class="fa fa-print"></i></button>

                                    </div>
                                </div>
                                <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>N°</th>
                                            <th>Source</th>
                                            <th>Operateur</th>
                                            <th>Montant</th>
                                            <th>Raison</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${not empty sorties }">
                                            <c:set var="total" value="${0}"></c:set>
                                            <c:forEach items="${sorties}" var="cmd">
                                                <c:if test="${empty cmd.getLigneSortie()}">
                                                    <c:set var="total" value="${total+cmd.getMontant()}"></c:set>
                                                        <tr>
                                                            <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                                <td>${cmd.getId()}</td>
                                                <td>${cmd.getSource().getNom()}</td>
                                                <td>${cmd.getOperateur().getFirstname()}</td>
                                                <td>
                                                    <f:formatNumber value="${cmd.getMontant()}"type="CURRENCY" currencySymbol=""/>

                                                </td>

                                                <td>${cmd.getCommentaire()}</td>
                                                <td><f:formatDate value="${cmd.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>Total</td>
                                            <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
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
        <c:import url="/WEB-INF/composants/modal/voirSortie.jsp"/>
    </c:if>
</c:if>

