<%-- 
    Document   : voirInventaire
    Created on : 25 mars 2019, 09:09:49
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="modal fade in text-left <c:if test="${ligne != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">inventaire N°${ligne.getId()}</h3>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">
                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien106 or sessionScope.client != null}">
                    <button  class="btn t btn-outline-dark btn-sm round pull-right" 
                             data-toggle='tooltips' title="Imprimer la fiche de cet inventaire"
                             onclick="getDataToPrintCommande(${ligne.getId()})"><i class="fa fa-print"></i></button>
                    </c:if>
                <form method="post" action="administration?q=${q}&action=model&model=secteur&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos Inventaire
                    </h6>

                    <fieldset>
                        <div class="row">
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Magasin</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Nom :</strong>
                                                ${ligne.getMagasin().getNom()}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Details Inventaire</h4>

                                            <p class="card-text">
                                                <strong for="firstName">Responsable :</strong>
                                                <span >
                                                    ${ligne.getOperateur().getFirstname()}
                                                </span>

                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Date Inventaire :</strong>
                                                <span class="badge bg-blue-grey">
                                                    ${ligne.getDateInv()}
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
                                            <h4 class="card-title info">Inventaire</h4>

                                            <div style="overflow-x: scroll;">
                                                <table style="width: 100%"  class="table table-dark table-striped table-bordered ">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Article</th>
                                                            <th>QT init</th>
                                                            <th>QT final</th>
                                                            <th>Ecart</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${ligne.getInventaireList()}" var="cmd">
                                                            <tr>
                                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                                        <td>${cmd.getArticle().getNom()}</td>
                                                        <td>${cmd.getQtAvant()}</td>
                                                        <td>${cmd.getQtApres()}</td>
                                                        <td>${cmd.getEcartQt()}</td>
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
                       value="Annuler">

            </div>


        </div>
    </div>
</div>

