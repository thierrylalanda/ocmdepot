<%-- 
    Document   : voirSortie
    Created on : 16 avr. 2019, 16:19:59
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal fade in text-left <c:if test="${sortie != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">SORTIE N° ${sortie.getId()}</h3>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">

                <form method="post" action="administration?q=${q}&action=model&model=secteur&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>INFOS SORTIE
                    </h6>

                    <fieldset>
                        <div class="row">
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Sortie</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Total :</strong>
                                                <f:formatNumber value="${sortie.getMontant()}"type="CURRENCY" currencySymbol=""/>
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Reste :</strong> 
                                                <span class="badge bg-warning">
                                                    <f:formatNumber value="${ligne.getMontantRestant()}"type="CURRENCY" currencySymbol=""/>
                                                </span>

                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Details Sortie</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Operateur :</strong>
                                                <span class="badge bg-blue-grey">
                                                    ${sortie.getOperateur().getFirstname()}
                                                </span>

                                            </p>
                                            <c:if test="${not empty sortie.getFacture()}">
                                                <p class="card-text">
                                                    <strong for="firstName">N° Facture :</strong>
                                                    ${sortie.getFacture().getId()}

                                                </p>
                                            </c:if>
                                            <p class="card-text">
                                                <strong for="firstName">Date Sortie :</strong>
                                                <span class="badge bg-blue-grey">
                                                    <f:formatDate value="${sortie.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/>
                                                </span>

                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${not empty sortie.getFacture()}">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 border-success">
                                    <div class="card ">
                                        <div class="card-content">
                                            <div class="card-body responsive">
                                                <h4 class="card-title info">SORTIE</h4>

                                                <div style="overflow-x: scroll;">
                                                    <table style="width: 100%" class="table table-dark table-striped table-bordered responsive">
                                                        <thead>
                                                            <tr>
                                                                <th>Code</th>
                                                                <th>Prix</th>
                                                                <th>Qte</th>
                                                                <th>Prix Total</th>
                                                                <th>Qte Reçue</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:set var="total" value="0"></c:set>
                                                            <c:set var="qte" value="0"></c:set>
                                                            <c:set var="qter" value="0"></c:set>
                                                            <c:forEach items="${sortie.getFacture().getCommandeFournisseurList()}" var="cmd">
                                                                <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>
                                                                <c:set var="qter" value="${qter+cmd.getQuantiteRecu()}"></c:set>
                                                                <c:set var="total" value="${total + cmd.getPrixTotal()}"></c:set>

                                                                    <tr> 
                                                                        <td>${cmd.getArticle().getCode()}</td>
                                                                    <td>${cmd.getPrix()}</td>
                                                                    <td >${cmd.getQuantite()}</td>
                                                                    <td >${cmd.getPrixTotal()}</td>
                                                                    <td>${cmd.getQuantiteRecu()}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                        <tfoot>
                                                            <tr style="font-weight: bold; color: #00b5b8">
                                                                <td></td>
                                                                <td>TOTAL</td>
                                                                <td>${qte}</td>
                                                                <td>${total}</td>
                                                                <td>${qter}</td>
                                                            </tr>
                                                        </tfoot>
                                                    </table>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </c:if>
                    </fieldset>


                </form>

            </div>
            <div class="modal-footer">
                <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                       value="Fermer">

            </div>


        </div>
    </div>
</div>
