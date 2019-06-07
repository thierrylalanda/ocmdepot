<%-- 
    Document   : allSortie
    Created on : 26 avr. 2019, 12:27:24
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
                            <h4 class="card-title">Liste Factures Fournisseurs</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">
                            <div class="card-body">
                                <div class="card-text">
                                    <form method="post" action="historiques?q=${q}&action=model&model=historiqueCaisse&isNew=5" class="" novalidate>


                                        <fieldset>
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="firstName">
                                                            Periode
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <div class='input-group input-group-xs'>
                                                            <input type='text' class="form-control-xs form-control localeRange" name="interval" />
                                                            <div class="input-group-append">
                                                                <span class="input-group-text">
                                                                    <span class="fa fa-calendar"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <label for="lastName">
                                                            Fournisseur
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" id="statut" class="select2-size-xs block form-control round " name="fournisseur">
                                                            <option value="0">Tous</option>
                                                            <c:forEach items="${fournisseurs}" var="fr">
                                                                <option value="${fr.getId()}"> ${fr.getNom()}</option>

                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <label for="lastName">
                                                            Statut
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" id="statut" class="select2-size-xs block form-control round " name="etat">
                                                            <option value="-1">Tous</option>
                                                            <option value="0">Non payé</option>
                                                            <option value="1">Payé</option>

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
                                        <!--                                        <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                                                        data-toggle='tooltips' title="voir les détails"
                                                                                        name="id" action="gestioncaisse?q=${q}&action=model&model=historiqueAccount&isNew=2">
                                                                                    <i class="fa fa-eye"></i></button>-->
                                        <!--                                        <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="gestioncaisse?q=${q}&action=model&model=addAccount&isNew=2"><i class="fa fa-edit"></i></button>
                                                                                <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="gestioncaisse?q=${q}&action=model&model=addAccount&isNew=3"><i class="fa fa-trash"></i></button>
                                        -->
                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_bordereau_caisse" 
                                                data-toggle='tooltips' title="Imprimer une commande"
                                                name="id" action="gestionachat?q=${q}&action=model&model=bordereau&isNew=2&fromcaisse=yes"><i class="fa fa-print"></i></button>
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
                                            <th>Four</th>
                                            <th>Montant</th>
                                            <th>Avance</th>
                                            <th>Reste</th>
                                            <th>Etat</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${not empty lignesSortie }">
                                            <c:set var="totalMT" value="${0}"></c:set>
                                            <c:set var="totalMR" value="${0}"></c:set>
                                            <c:set var="totalAV" value="${0}"></c:set>
                                            <c:forEach items="${lignesSortie}" var="cmd">
                                                <c:if test="${cmd.getNumeroBon()!= null}">
                                                    <c:set var="totalMR" value="${totalMR+cmd.getMontantRestant()}"></c:set>
                                                    <c:set var="totalMT" value="${totalMT+cmd.getMontantNet()}"></c:set>
                                                    <c:set var="totalAV" value="${totalAV+(cmd.getMontantNet()-cmd.getMontantRestant())}"></c:set>
                                                        <tr>
                                                            <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                                <td>${cmd.getId()}</td>
                                                <td>${cmd.getNumeroBon().getFournisseur().getNom()}</td>
                                                <td><f:formatNumber value="${cmd.getMontantNet()}"type="CURRENCY" currencySymbol=""/></td>
                                                <td><f:formatNumber value="${cmd.getMontantNet()-cmd.getMontantRestant()}"type="CURRENCY" currencySymbol=""/></td>
                                                <td><f:formatNumber value="${cmd.getMontantRestant()}"type="CURRENCY" currencySymbol=""/></td>
                                                <td>
                                                    <c:if test="${cmd.getEtat()==1}">
                                                        <span class="badge badge-success">
                                                            Payé
                                                        </span>
                                                    </c:if>
                                                    <c:if test="${cmd.getEtat()==0}">
                                                        <span class="badge badge-warning">
                                                            Non Payé
                                                        </span>
                                                    </c:if>
                                                </td>
                                                <td><f:formatDate value="${cmd.getDateCreate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
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
</c:if>
