<%-- 
    Document   : detailsTransfert
    Created on : 31 mars 2019, 23:30:07
    Author     : Administrateur
--%>

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
<c:if test="${sessionScope.societe.getGestmagasin() == 1}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <section>
        <div class="row autoloader" autoloader="false">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Détails Transfert N° ${ligneTransfert.getId()} Du Magasin : ${ligneTransfert.getMg2().getNom()} Du : <f:formatDate value="${ligneTransfert.getDateTransf()}" type="BOTH" dateStyle="MEDIUM"/></h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-lg-4 col-md-12 col-xs-12 border-success">
                                    <div class="card ">
                                        <div class="card-content">
                                            <div class="card-body">
                                                <h4 class="info">Magasin</h4>
                                                <p class="card-text">
                                                    <strong for="firstName">Nom :</strong>
                                                    ${ligneTransfert.getMg1().getNom()}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-12 col-xs-12 border-success">
                                    <div class="card ">
                                        <div class="card-content">
                                            <div class="card-body">
                                                <h4 class="info">Details Inventaire</h4>
                                                <p class="card-text">
                                                    <strong for="firstName">Operateur :</strong>
                                                    <span class="badge bg-blue-grey">
                                                        ${ligneTransfert.getOperateur()}
                                                    </span>

                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <button title="Receptionner le transfert" type="button" class="btn btn-float btn-outline-success btn-round btn-link-update" name="id" action="aoyurdfe148jg?q=commande/magasin/ReceptionStock&action=model&model=inventaire&isNew=6&id=${ligneTransfert.getId()}"><i class="fa fa-save"></i></button>

                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien16}">
                                    <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="aoyurdfe148jg?q=${q}&action=model&model=transfertStock&isNew=5"><i class="fa fa-trash"></i></button>
                                </c:if>
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <!-- Outline Round Floating button -->
                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                        data-toggle='tooltips' title="tout Imprimer "
                                        name="id"><i class="fa fa-print"></i></button>
                            </div>
                        </div>
                        <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Code Art</th>
                                    <th>Article</th>
                                    <th>P.U</th>
                                    <th>Qte</th>
                                    <th>P.T</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="total" value="0"></c:set>
                                <c:set var="qte" value="0"></c:set>
                                <c:forEach items="${ligneTransfert.getTransfertStockList()}" var="cmd">
                                    <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>

                                    <c:set var="total" value="${total + (cmd.getQuantite()*cmd.getArticle().getPrix())}"></c:set>
                                        <tr>

                                            <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                <td>${cmd.getArticle().getCode()}</td>
                                <td>${cmd.getArticle().getNom()}</td>
                                <td >${cmd.getArticle().getPrix()}</td>
                                <td  style="cursor:pointer">${cmd.getQuantite()}</td>

                                <td>${cmd.getQuantite()*cmd.getArticle().getPrix()}</td>
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
                                    <td>${total}</td>

                                </tr>
                            </tfoot>
                        </table>
                        <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</c:if>

