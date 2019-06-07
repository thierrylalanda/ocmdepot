<%-- 
    Document   : voirMarge
    Created on : 15 janv. 2019, 18:46:26
    Author     : messi
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="modal fade in text-left <c:if test="${commande != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Commande N°${commande.get(0).getLigne().getId()} de ${commande.get(0).getLigne().getClient().getNom()}</h3>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">
                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien106 or sessionScope.client != null}">
                    <button  class="btn t btn-outline-dark btn-sm round pull-right" 
                             data-toggle='tooltips' title="Imprimer la fiche de cette incident"
                             onclick="getDataToPrintCommande(${commande.get(0).getLigne().getId()})"><i class="fa fa-print"></i></button>
                    </c:if>
                <form method="post" action="administration?q=${q}&action=model&model=secteur&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos Commande
                    </h6>

                    <fieldset>
                        <div class="row">
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Client</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Nom :</strong>
                                                ${commande.get(0).getLigne().getClient().getNom()} ${commande.get(0).getLigne().getClient().getPrenom()}
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Tel :</strong> 
                                                ${commande.get(0).getLigne().getClient().getTelephone()}
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Email :</strong> 
                                                ${commande.get(0).getLigne().getClient().getMail()}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Details Commande</h4>

                                            <p class="card-text">
                                                <strong for="firstName">Statut :</strong>
                                                <span class=" <c:if test="${commande.get(0).getLigne().getEtatc().getCode()==200}">badge badge-success</c:if>
                                                      <c:if test="${commande.get(0).getLigne().getEtatc().getCode()==201}">badge badge-primary</c:if>
                                                      <c:if test="${commande.get(0).getLigne().getEtatc().getCode()==502}">badge badge-warning</c:if>
                                                      <c:if test="${commande.get(0).getLigne().getEtatc().getCode()==501}">badge badge-danger</c:if>
                                                      <c:if test="${commande.get(0).getLigne().getEtatc().getCode()==401}">badge badge-info</c:if>">
                                                    ${commande.get(0).getLigne().getEtatc().getNom()}
                                                </span>

                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Date commande :</strong>
                                                <span class="badge bg-blue-grey">
                                                    ${commande.get(0).getLigne().getDatecc()}
                                                </span>

                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Echeance :</strong>
                                                <span class="badge bg-blue-grey">
                                                    ${commande.get(0).getLigne().getDateliv()}
                                                </span>

                                            </p>
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
                                            <h4 class="card-title info">Commande</h4>

                                            <div style="overflow-x: scroll;">
                                                <table style="width: 100%"  class="table table-dark table-striped table-bordered ">
                                                    <thead>
                                                        <tr>
                                                            <th>Article</th>
                                                            <th>P.U</th>
                                                            <th>Qte</th>
                                                            <th>P.T</th>
                                                            <th>Marge client</th>
                                                            <th>Marge Direct</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:set var="total" value="0"></c:set>
                                                        <c:set var="pt" value="0"></c:set>
                                                        <c:set var="mc" value="0"></c:set>
                                                        <c:set var="md" value="0"></c:set>
                                                        <c:forEach items="${commande}" var="cmd">
                                                            <c:set var="total" value="${total + cmd.getQuantite()}"></c:set>
                                                            <c:set var="pt" value="${pt + cmd.getPrixTotal()}"></c:set>
                                                            <c:set var="mc" value="${mc + cmd.getMargeClient()}"></c:set>
                                                            <c:set var="md" value="${md + cmd.getMargeFournisseur()}"></c:set>
                                                                <tr>
                                                                    <td>${cmd.getArticle().getCode()}</td>
                                                                <td>${cmd.getPrix()}</td>
                                                                <td>${cmd.getQuantite()}</td>
                                                                <td>${cmd.getPrixTotal()}</td>
                                                                <td>${cmd.getMargeClient()}</td>
                                                                <td>${cmd.getMargeFournisseur()}</td>
                                                            </tr>
                                                        </c:forEach>

                                                    </tbody>
                                                    <tfoot>
                                                        <tr style="font-weight: bold; color: #00b5b8">
                                                            <td></td>
                                                            <td>TOTAL</td>
                                                            <td>${total}</td>
                                                            <td><f:formatNumber value="${pt}"type="CURRENCY" currencySymbol=""/></td>
                                                           <td><f:formatNumber value="${mc}"type="CURRENCY" currencySymbol=""/></td>
                                                            <td><f:formatNumber value="${md}"type="CURRENCY" currencySymbol=""/></td>
                                                        
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
                       value="Annuler">

            </div>


        </div>
    </div>
</div>

