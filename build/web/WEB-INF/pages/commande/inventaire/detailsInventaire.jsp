<%-- 
    Document   : detailsInventaire
    Created on : 27 mars 2019, 14:04:26
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestmagasin() == 0}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <section>
        <div class="row autoloader" autoloader="false">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Détails Inventaire N° ${ligneInventaire.getId()} Du : <f:formatDate value="${ligneInventaire.getDateInv()}" type="BOTH" dateStyle="MEDIUM"/> fait par : ${ligneInventaire.getOperateur().getFirstname()}</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="form-group">
                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                            data-toggle='tooltips' title="tout Imprimer "
                                            name="id"><i class="fa fa-print"></i></button>
                                </div>
                            </div>
                            <div style="overflow-x: scroll">
                                <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>Code</th>
                                            <th>Produit</th>
                                            <th>Qte init</th>
                                            <th>Qte Entrée</th>
                                            <th>Qte Final</th>
                                            <th>Ecart</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total" value="0"></c:set>
                                        <c:set var="qte" value="0"></c:set>
                                        <c:set var="qtev" value="0"></c:set>
                                        <c:set var="quantite" value="0"></c:set>
                                        <c:forEach items="${ligneInventaire.getInventaireList()}" var="cmd">
                                            <c:set var="qte" value="${qte+cmd.getQtAvant()}"></c:set>
                                            <c:set var="qtev" value="${qtev+cmd.getQtApres()}"></c:set>
                                            <c:set var="quantite" value="${quantite+cmd.getQuantite()}"></c:set>
                                            <c:set var="total" value="${total + cmd.getEcartQt()}"></c:set>
                                                <tr>
                                                <c:if test="${type==0}">
                                                    <td>${cmd.getArticle().getCode()}</td>
                                                    <td>${cmd.getArticle().getNom()}</td>
                                                </c:if>
                                                <c:if test="${type==1}">
                                                    <td>${cmd.getEmballage().getCode()}</td>
                                                    <td>${cmd.getEmballage().getNom()}</td>
                                                </c:if>
                                                <td  style="cursor:pointer">${cmd.getQtAvant()}</td>
                                                <td >${cmd.getQuantite()}</td>
                                                <td >${cmd.getQtApres()}</td>
                                                <td>${cmd.getEcartQt()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr style="font-weight: bold; color: #00b5b8">
                                            <td></td>
                                            <td>TOTAL</td>
                                            <td>${qte}</td>
                                            <td>${quantite}</td>
                                            <td>${qtev}</td>
                                            <td>${total}</td>

                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>
