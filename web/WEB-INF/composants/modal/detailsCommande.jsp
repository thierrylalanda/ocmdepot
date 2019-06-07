<%-- 
    Document   : detailsCommande
    Created on : 1 déc. 2018, 20:20:26
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="dcommande" value="${commandes}"></c:set>
    <div class="modal fade in text-left" data-backdrop="static" id="detailCommande" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Commande N°${dcommande.get(0).getLigne().getId()} de ${dcommande.get(0).getLigne().getClient().getNom()}</h3>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">
                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien106 or sessionScope.client != null}">
                    <button  class="btn t btn-outline-dark btn-sm round pull-right" 
                             data-toggle='tooltips' title="Imprimer la commande en A4"
                             onclick="getDataToPrintCommande(${dcommande.get(0).getLigne().getId()},'A4')"><i class="fa fa-print"></i></button>
                             <button  class="btn t btn-outline-dark btn-sm round pull-right" 
                             data-toggle='tooltips' title="Imprimer la commande sous petit format"
                             onclick="getDataToPrintCommande(${dcommande.get(0).getLigne().getId()},'A6')"><i class="fa fa-print"></i></button>
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
                                                ${dcommande.get(0).getLigne().getClient().getNom()} ${dcommande.get(0).getLigne().getClient().getPrenom()}
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Tel :</strong> 
                                                ${dcommande.get(0).getLigne().getClient().getTelephone()}
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Email :</strong> 
                                                ${dcommande.get(0).getLigne().getClient().getMail()}
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
                                                <span class=" <c:if test="${dcommande.get(0).getLigne().getEtatc().getCode()==200}">badge badge-success</c:if>
                                                      <c:if test="${dcommande.get(0).getLigne().getEtatc().getCode()==201}">badge badge-primary</c:if>
                                                      <c:if test="${dcommande.get(0).getLigne().getEtatc().getCode()==502}">badge badge-warning</c:if>
                                                      <c:if test="${dcommande.get(0).getLigne().getEtatc().getCode()==501}">badge badge-danger</c:if>
                                                      <c:if test="${dcommande.get(0).getLigne().getEtatc().getCode()==401}">badge badge-info</c:if>">
                                                    ${dcommande.get(0).getLigne().getEtatc().getNom()}
                                                </span>

                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Date commande :</strong>
                                                <span class="badge bg-blue-grey">
                                                   ${dcommande.get(0).getLigne().getDatecc()}
                                                </span>

                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Echeance :</strong>
                                                <span class="badge bg-blue-grey">
                                                    ${dcommande.get(0).getLigne().getDateliv()}
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
                                        <div class="card-body responsive" >
                                            <h4 class="card-title info">Commande</h4>
                                            <div style="overflow-x: scroll;">
                                                <table style="width: 100%"  class="table table-dark table-striped table-bordered ">
                                                    <thead>
                                                        <tr>
                                                            <th>Article</th>
                                                            <th>P.U</th>
                                                            <th>Qte Cmdé</th>
                                                            <th>P.T</th>
                                                            <th>Qte Prop</th>
                                                            <th>Commentaire</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${dcommande}" var="cmd">
                                                            <tr>
                                                                <td>${cmd.getArticle().getNom()}</td>
                                                                <td>${cmd.getArticle().getPrix()}</td>
                                                                <td>${cmd.getQuantite()}</td>
                                                                <td>${cmd.getPrixTotal()}</td>
                                                                <td>${cmd.getQt()}</td>
                                                                <td>${cmd.getCommantaire1()}</td>
                                                            </tr>
                                                        </c:forEach>

                                                    </tbody>
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

