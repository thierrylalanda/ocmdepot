<%-- 
    Document   : listeAchat
    Created on : 20 avr. 2019, 17:33:30
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestfournisseur() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">

        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <section>
            <div class="row autoloader" autoloader="true">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Liste des Achats</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">
                            <div class="card-body">

                                <div class="form-group">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->
                                        <button title="voir" class="btn-sm btn btn-float btn-outline-success btn-round btn-link-update"
                                                data-toggle="tooltips" name="id" action="gestionachat?action=model&model=bonAchat&q=${q}&isNew=1">
                                            <i class="fa fa-eye"></i>
                                        </button>
                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                data-toggle='tooltips' title="tout Imprimer "
                                                name="id"><i class="fa fa-print"></i></button>

                                    </div>
                                </div>
                                <div style="overflow-x: scroll;">
                                    <table style="width:100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>N°</th>
                                                <th>Fournisseur</th>
                                                <th>Operateur</th>
                                                <th>Total</th>
                                                <th>Etat</th>
                                                <th>Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${not empty achats}">
                                                <c:set var="total" value="${0}"></c:set>
                                                <c:forEach items="${achats}" var="cmd">

                                                    <c:set var="total" value="${total + cmd.getPrixTotal()}"></c:set>
                                                        <tr>
                                                            <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                                <td>${cmd.getId()}</td>
                                                <td>${cmd.getFournisseur().getNom()}</td>
                                                <td>${cmd.getOperateur().getFirstname()}</td>
                                                <td><f:formatNumber value="${cmd.getPrixTotal()}"type="CURRENCY" currencySymbol=""/></td>
                                                <td>
                                                    <c:if test="${cmd.getStatut()==0}">
                                                        Non Réceptionnée
                                                    </c:if>
                                                    <c:if test="${cmd.getStatut()==1}">
                                                        Réceptionnée
                                                    </c:if>
                                                </td>
                                                <td><f:formatDate value="${cmd.getDateCommande()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
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
        </section>
    </c:if>
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
        <c:import url="/WEB-INF/composants/modal/voirAchat.jsp"/>
    </c:if>
</c:if>

