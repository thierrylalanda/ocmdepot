<%-- 
    Document   : decaisse
    Created on : 25 avr. 2019, 15:07:52
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestcaisse() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
        <c:set var="id" value="&r=7tyui895qze412"></c:set>
        <c:if test="${ligne != null}">
            <c:set var="isnew" value="${3}"></c:set>
            <c:set var="id" value="&ligne=${ligne.getId()}"></c:set>
        </c:if>
        <c:if test="${ligne == null}">
            <c:set var="isnew" value="${1}"></c:set>
        </c:if>

        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <section>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">SORTIE POUR BON DE COMMANDE N° ${ligne.getId()}</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                            <button  class="btn t btn-outline-dark btn-sm round pull-right" 
                                     data-toggle='tooltips' title="Imprimer la facture"
                                     onclick="getDataToPrintBordereauCommande(${ligne.getId()})"><i class="fa fa-print"></i></button>

                        </div>
                        <div class="card-content">

                            <div class="card-body">
                                <div class="card-text">

                                    <div class="row">
                                        <div class="col-lg-6 col-md-12 border-success">
                                            <div class="card ">
                                                <div class="card-content">
                                                    <div class="card-body">
                                                        <h4 class="card-title info">Facturation</h4>
                                                        <c:if test="${ligne.getEtat().getCode() != 300}">
                                                            <form class="form" method="post" action="gestioncaisse?action=model&model=facturationAccount&q=${q}&isNew=${isnew}${id}&ligne_commande=${ligne.getId()}&forpaiement=0">
                                                                <div class="row">
                                                                    <div class="form-group col-lg-6 col-sm-12">
                                                                        <label for="quantite">Avance<span class="danger">*</span></label>
                                                                        <input type="number"  value="" min="0" required  class="form-control-sm form-control round" name="avance"/>
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="form-group col-lg-6 col-sm-12">
                                                                        <label for="quantite">Commentaire<span class="danger">*</span></label>
                                                                        <textarea name="commentaire" class="form-control-sm form-control round" required></textarea>
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                                        <button type="submit" class="btn btn-primary round btn-sm btn_caisse" style="margin-top: 25px">
                                                                            <i class="fa fa-money"></i> Decaisser
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                                        <a href="gestioncaisse?action=model&model=facturationAccount&q=${q}&isNew=${isnew}${id}&ligne_commande=${ligne.getId()}&allencaissement=0&forpaiement=0" class="btn btn-success round btn-sm" style="margin-top: 25px">
                                                                            <i class="fa fa-money"></i> Tout Decaisser
                                                                        </a>
                                                                    </div>
                                                                </div>

                                                            </form>
                                                        </c:if>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6 col-md-12 border-success">
                                            <div class="card ">
                                                <div class="card-content">
                                                    <div class="card-body">
                                                        <h4 class="card-title info">Détails</h4>
                                                        <p class="card-text">
                                                            <strong for="firstName">Net à payer :</strong>
                                                            <f:formatNumber value="${ligne.getPrixTotal()}"type="CURRENCY" currencySymbol=" FCFA"/>
                                                        </p>
                                                        <p class="card-text">
                                                            <strong for="firstName">Fournisseur :</strong>
                                                            ${ligne.getFournisseur().getNom()}
                                                        </p>

                                                        <table style="width: 100%"  class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                                            <thead>
                                                                <tr>
                                                                    <th>Article</th>
                                                                    <th>P.U</th>
                                                                    <th>Qte</th>
                                                                    <th>P.T</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:set var="total" value="0"></c:set>
                                                                <c:set var="pt" value="0"></c:set>
                                                                <c:set var="pp" value="0"></c:set>
                                                                <c:forEach items="${ligne.getCommandeFournisseurList()}" var="cmd">

                                                                    <c:set var="pt" value="${pt + cmd.getPrixTotal()}"></c:set>
                                                                    <c:set var="total" value="${total + cmd.getQuantite()}"></c:set>
                                                                        <tr>
                                                                            <td>${cmd.getArticle().getCode()}</td>
                                                                        <td>${cmd.getPrix()}</td>
                                                                        <td>${cmd.getQuantite()}</td>
                                                                        <td>${cmd.getPrixTotal()}</td>
                                                                    </tr>
                                                                </c:forEach>

                                                            </tbody>
                                                            <tfoot>
                                                                <tr style="font-weight: bold; color: #00b5b8">
                                                                    <td></td>
                                                                    <td>TOTAL</td>
                                                                    <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${pt}"type="CURRENCY" currencySymbol=""/></td>

                                                                </tr>
                                                            </tfoot>
                                                        </table>
                                                                    <a class="btn btn-outline-secondary btn-sm round" href="gestioncaisse?action=model&model=init&q=commande/caisse/bonCommande&isNew=0&societe=yes"><icon class="fa fa-arrow-left"></icon></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </c:if>
</c:if>
