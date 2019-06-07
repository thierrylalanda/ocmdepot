<%-- 
    Document   : historiqueAccount
    Created on : 7 avr. 2019, 13:36:14
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestcaisse() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">

        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <section>
            <div class="row autoloader" autoloader="true">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Historique Caisse</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">
                            <div class="card-body">
                                <div class="card-text">
                                    <form class="form" method="post" action="gestioncaisse?action=model&model=historiqueAccount&q=${q}&isNew=1">
                                        <fieldset>
                                            <div class="row">
                                                <div class="col-md-2">
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
                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="articles_inventaire">Etat
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required   class="select2-size-xs block form-control round required" name="etat">
                                                        <option  value="-1">Tous</option>
                                                        <option  value="0">Non Achevé</option>
                                                        <option  value="1">Payer</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="articles_inventaire">Clients
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required   class="select2-size-xs block form-control round required" name="client">
                                                        <option  value="-1">Tous</option>
                                                        <c:forEach items="${clients}" var="cl">
                                                            <option  value="${cl.getId()}">${cl.getNom()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-search"></i> Recherche
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
                                                name="id" action="gestioncaisse?q=${q}&action=model&model=historiqueAccount&isNew=2">
                                            <i class="fa fa-eye"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="gestioncaisse?q=${q}&action=model&model=addAccount&isNew=2"><i class="fa fa-edit"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="gestioncaisse?q=${q}&action=model&model=addAccount&isNew=3"><i class="fa fa-trash"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                data-toggle='tooltips' title="tout Imprimer "
                                                name="id"><i class="fa fa-print"></i></button>

                                    </div>
                                </div>
                                <c:if test="${not empty messageTable}">
                                    <div class="text-center bg-dark title_table" style="color:white">${messageTable}</div>
                                </c:if>
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
                                            <th>Etat</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${not empty sessionScope.allAccount }">
                                            <c:set var="totalMT" value="${0}"></c:set>
                                            <c:set var="totalMR" value="${0}"></c:set>
                                            <c:set var="totalAV" value="${0}"></c:set>
                                            <c:forEach items="${sessionScope.allAccount}" var="cmd">
                                                <c:set var="totalMR" value="${totalMR+cmd.getMontantRestant()}"></c:set>
                                                <c:set var="totalMT" value="${totalMT+cmd.getMontantNet()}"></c:set>
                                                <c:set var="totalAV" value="${totalAV+(cmd.getMontantNet()-cmd.getMontantRestant())}"></c:set>
                                                    <tr>
                                                        <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                            <td>${cmd.getId()}</td>
                                            <td>${cmd.getClient().getNom()}</td>
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
                                                        Non Achevé
                                                    </span>
                                                </c:if>
                                            </td>
                                            <td><f:formatDate value="${cmd.getDateCreate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                    <tfoot>
                                        <tr >
                                            <td></td>
                                            <td></td>
                                            <td>Total</td>
                                            <td><f:formatNumber value="${totalMT}"type="CURRENCY" currencySymbol=""/></td>
                                            <td><f:formatNumber value="${totalAV}"type="CURRENCY" currencySymbol=""/></td>
                                            <td style="color:#ea4335"><f:formatNumber value="${totalMR}"type="CURRENCY" currencySymbol=""/></td>
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
