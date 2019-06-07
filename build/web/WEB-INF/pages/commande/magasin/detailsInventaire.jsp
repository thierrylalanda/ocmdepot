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
                        <h4 class="card-title">Détails Inventaire N° ${ligneInventaire.getId()} Du Magasin : ${ligneInventaire.getMagasin().getNom()} Du : <f:formatDate value="${ligneInventaire.getDateInv()}" type="BOTH" dateStyle="MEDIUM"/></h4>
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
                                                    ${ligneInventaire.getMagasin().getNom()}
                                                </p>
                                                <p class="card-text">
                                                    <strong for="firstName">Magasignier :</strong> 
                                                    ${ligneInventaire.getMagasin().getMagasigner().getFirstname()}
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
                                                    <strong for="firstName">Date Inv :</strong>
                                                    <span class="badge bg-blue-grey">
                                                        <f:formatDate value="${ligneInventaire.getDateInv()}" type="BOTH" dateStyle="MEDIUM"/>
                                                    </span>

                                                </p>
                                                <p class="card-text">
                                                    <strong for="firstName">Operateur :</strong>
                                                    <span class="badge bg-blue-grey">
                                                        ${ligneInventaire.getOperateur().getFirstname()}
                                                    </span>

                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
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
                                        <th>Categorie</th>
                                        <th>Code Art</th>
                                        <th>Article</th>
                                        <th>Qte init</th>
                                        <th>Qte Final</th>
                                        <th>Ecart</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="total" value="0"></c:set>
                                    <c:set var="qte" value="0"></c:set>
                                    <c:set var="qtev" value="0"></c:set>
                                    <c:forEach items="${ligneInventaire.getInventaireList()}" var="cmd">
                                        <c:set var="qte" value="${qte+cmd.getQtAvant()}"></c:set>
                                        <c:set var="qtev" value="${qtev+cmd.getQtApres()}"></c:set>

                                        <c:set var="total" value="${total + cmd.getEcartQt()}"></c:set>
                                            <tr>

                                                <td>${cmd.getArticle().getCategorie().getNom()}</td>
                                            <td>${cmd.getArticle().getCode()}</td>
                                            <td>${cmd.getArticle().getNom()}</td>
                                            <td  style="cursor:pointer">${cmd.getQtAvant()}</td>
                                            <td >${cmd.getQtApres()}</td>
                                            <td>${cmd.getEcartQt()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr style="font-weight: bold; color: #00b5b8">
                                        <td></td>
                                        <td></td>
                                        <td>TOTAL</td>
                                        <td>${qte}</td>
                                        <td>${qtev}</td>
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
