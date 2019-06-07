<%-- 
    Document   : voirAccount
    Created on : 7 avr. 2019, 13:57:21
    Author     : Administrateur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal fade in text-left <c:if test="${ligne != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Emprunt N° ${ligne.getId()} de ${ligne.getClient().getNom()}</h3>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">

                <form method="post" action="administration?q=${q}&action=model&model=secteur&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos Emprunt
                    </h6>

                    <fieldset>
                        <div class="row">
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Emprunt</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Total :</strong>
                                                <f:formatNumber value="${ligne.getMontantNet()}"type="CURRENCY" currencySymbol=""/>
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Avance :</strong> 
                                                <f:formatNumber value="${ligne.getMontantNet() - ligne.getMontantRestant()}"type="CURRENCY" currencySymbol=""/>
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Reste :</strong> 
                                                <span class="badge bg-warning">
                                                    <f:formatNumber value="${ligne.getMontantRestant()}"type="CURRENCY" currencySymbol=""/>
                                                </span>

                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Statut :</strong>
                                                <c:if test="${ligne.getEtat()==1}">
                                                    <span class="badge badge-success">
                                                        Payer
                                                    </span>
                                                </c:if>
                                                <c:if test="${ligne.getEtat()==0}">
                                                    <span class="badge badge-warning">
                                                        Non Achever
                                                    </span>
                                                </c:if>


                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Details Emprunt</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Operateur :</strong>
                                                <span class="badge bg-blue-grey">
                                                    ${ligne.getOperateur().getFirstname()}
                                                </span>

                                            </p>
                                            <c:if test="${not empty ligne.getLigneCommande()}">
                                                <p class="card-text">
                                                    <strong for="firstName">N° commande :</strong>
                                                    ${ligne.getLigneCommande().getId()}

                                                </p>
                                            </c:if>
                                            <p class="card-text">
                                                <strong for="firstName">Date Emprunt :</strong>
                                                <span class="badge bg-blue-grey">
                                                    <f:formatDate value="${ligne.getDateCreate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/>
                                                </span>

                                            </p>
                                            <c:if test="${not empty ligne.getDateUpdate()}">
                                                <p class="card-text">
                                                    <strong for="firstName">Dernier versement :</strong>
                                                    <span class="badge bg-blue-grey">
                                                        <f:formatDate value="${ligne.getDateUpdate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/>
                                                    </span>

                                                </p>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body responsive">
                                            <h4 class="card-title info">Versement</h4>

                                            <div style="overflow-x: scroll;">
                                                <table style="width: 100%"  class="table table-dark table-striped table-bordered ">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Montant</th>
                                                            <th>Avance</th>
                                                            <th>Reste</th>
                                                            <th>Date</th>
                                                            <th>Operateur</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                        <c:set var="total" value="0"></c:set>
                                                        <c:set var="avance" value="0"></c:set>
                                                        <c:set var="reste" value="0"></c:set>
                                                        <c:forEach items="${ligne.getAccountList()}" var="cmd">
                                                            <c:set var="total" value="${total + cmd.getMontantInit()}"></c:set>
                                                            <c:set var="avance" value="${avance + cmd.getAvance()}"></c:set>
                                                            <c:set var="reste" value="${reste + cmd.getMontantRestant()}"></c:set>
                                                                <tr>
                                                                    <td><option>${cmd.getId()}</option> </td>
                                                        <td>${cmd.getMontantInit()}</td>
                                                        <td>${cmd.getAvance()}</td>
                                                        <td>${cmd.getMontantRestant()}</td>
                                                        <td><f:formatDate value="${cmd.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                                        <td>${cmd.getOperateur().getFirstname()}</td>                                                        
                                                        </tr>
                                                    </c:forEach>

                                                    </tbody>
                                                    <tfoot>
                                                        <tr style="font-weight: bold; color: #00b5b8">

                                                            <td>TOTAL</td>
                                                            <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                            <td><f:formatNumber value="${avance}"type="CURRENCY" currencySymbol=""/></td>
                                                            <td><f:formatNumber value="${reste}"type="CURRENCY" currencySymbol=""/></td>
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
