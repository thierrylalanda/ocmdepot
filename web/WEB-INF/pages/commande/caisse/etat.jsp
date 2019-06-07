<%-- 
    Document   : etat
    Created on : 26 avr. 2019, 10:29:20
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
        <c:if test="${ligneToUpdate != null}">
            <c:set var="isnew" value="${1}"></c:set>
            <c:set var="id" value="&ligne=${ligneToUpdate.getId()}"></c:set>
        </c:if>
        <c:if test="${ligneToUpdate == null}">
            <c:set var="isnew" value="${0}"></c:set>
        </c:if>
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <section>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Etat Caisse Du jour</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">

                            <div class="card-body">
                                <div class="card-text">

                                    <div class="row">
                                        <div class="card ">
                                            <div class="card-content">
                                                <div class="card-body">
                                                    <h4 class="card-title info">Etat Caisse Du jour</h4>
                                                    
                                                        <form class="form" method="post" action="historiques?action=model&model=historiqueCaisse&q=${q}&isNew=3">
                                                            <fieldset>
                                                                <div class="row">
                                                                    <div class="form-group col-lg-2 col-sm-12">
                                                                        <label for="categorie_article">Date
                                                                            <span class="danger">*</span></label>
                                                                        <input type="text" class="form-control-sm form-control round singledate" name="date"/>
                                                                    </div>
                                                                    <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                                        <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                                            <i class="fa fa-search"></i> Rechercher
                                                                        </button>
                                                                    </div>
                                                            </fieldset>    


                                                        </form>
                                                    <div class="form-group">
                                                        <div class="btn-group" role="group" aria-label="Basic example">

                                                            <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                                    data-toggle='tooltips' title="tout Imprimer "
                                                                    name="id"><i class="fa fa-print"></i></button> 
                                                        </div>
                                                    </div>
                                                    <div class="text-center bg-dark title_table" style="color:white">${messageTable}</div>
                                                    <br>
                                                    <div style="overflow-x: scroll;">
                                                        <table style="width: 100%"  class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                                            <thead>

                                                                <tr>
                                                                    <th class="datec" pos="0">N°</th>
                                                                    <th>Operation</th>
                                                                    <th>Quantite</th>
                                                                    <th>Net Facture</th>
                                                                    <th>Montant Encaissé</th>
                                                                    <th>Montant décaissé</th>
                                                                    <th>Dette Client</th>
                                                                    <th>Remise</th>
                                                                    <th>Marge</th>
                                                                    <th>Dette Fournisseur</th>

                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:set var="totalQ" value="0"></c:set>
                                                                <c:set var="totalM" value="0"></c:set>
                                                                <c:set var="totalN" value="0"></c:set>
                                                                <c:set var="totalME" value="0"></c:set>
                                                                <c:set var="totalMD" value="0"></c:set>
                                                                <c:set var="totalDC" value="0"></c:set>
                                                                <c:set var="totalDF" value="0"></c:set>
                                                                <c:set var="totalR" value="0"></c:set>

                                                                <c:set var="totalN" value="${totalN+factureclient.getNetfacture()+factureboncommande.getNetfacture()}"></c:set>
                                                                <c:set var="totalR" value="${totalR+factureclient.getRemise()}"></c:set>
                                                                <c:set var="totalM" value="${totalM+factureclient.getMargeFournisseur()}"></c:set>
                                                                <c:set var="totalME" value="${totalME+factureclient.getMontantencaisse()+autreentree.getMontantencaisse()+recouvrement.getMontantencaisse()}"></c:set>
                                                                <c:set var="totalMD" value="${totalMD+factureboncommande.getMontantdecaisse()+autredepense.getMontantdecaisse()+recouvrementBon.getMontantdecaisse()}"></c:set>
                                                                <c:set var="totalDC" value="${totalDC +factureclient.getDettclient()}"></c:set>
                                                                <c:set var="totalDF" value="${totalDF +factureboncommande.getDettFournisseur()}"></c:set>
                                                                    <tr>
                                                                        <td>6</td>
                                                                        <td>Factures Client</td>
                                                                        <td>${factureclient.getQuantite()}</td>
                                                                    <td><f:formatNumber value="${factureclient.getNetfacture()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${factureclient.getMontantencaisse()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td><f:formatNumber value="${factureclient.getDettclient()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${factureclient.getRemise()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${factureclient.getMargeFournisseur()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>5</td>
                                                                    <td>Bons de commande</td>
                                                                    <td>${factureboncommande.getQuantite()}</td>
                                                                    <td><f:formatNumber value="${factureboncommande.getNetfacture()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td><f:formatNumber value="${factureboncommande.getMontantdecaisse()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td><f:formatNumber value="${factureboncommande.getDettFournisseur()}"type="NUMBER" currencySymbol=""/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>4</td>
                                                                    <td>Dépenses Divers</td>
                                                                    <td>${autredepense.getQuantite()}</td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td><f:formatNumber value="${autredepense.getMontantdecaisse()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>3</td>
                                                                    <td>Entrées Divers</td>
                                                                    <td>${autreentree.getQuantite()}</td>
                                                                    <td></td>
                                                                    <td><f:formatNumber value="${autreentree.getMontantencaisse()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>2</td>
                                                                    <td>Recouvrement Client</td>
                                                                    <td>${recouvrement.getQuantite()}</td>
                                                                    <td></td>
                                                                    <td><f:formatNumber value="${recouvrement.getMontantencaisse()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>1</td>
                                                                    <td>Recouvrement Fournisseur</td>
                                                                    <td>${recouvrementBon.getQuantite()}</td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td><f:formatNumber value="${recouvrementBon.getMontantdecaisse()}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>


                                                            </tbody>
                                                            <tfoot>
                                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td>Total</td>
                                                                    <td><f:formatNumber value="${totalN}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${totalME}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${totalMD}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${totalDC}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${totalR}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${totalM}"type="NUMBER" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${totalDF}"type="NUMBER" currencySymbol=""/></td>
                                                                </tr>
                                                                

                                                            </tfoot>
                                                        </table>
                                                    </div>
                                                </div>
                                                <c:if test="${1==2}">
                                                    <div style="overflow-x: scroll;">
                                                        <table style="width: 100%"  class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                                            <thead>
                                                                <tr>

                                                                </tr>
                                                                <tr>
                                                                    <th>Date</th>
                                                                    <th>Operation</th>
                                                                    <th>Libelle</th>
                                                                    <th>Net Facture</th>
                                                                    <th>Entrées</th>
                                                                    <th>Sorties</th>
                                                                    <th>Solde encaisse</th>
                                                                    <th>Operateur</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                                <c:set var="totalS" value="0"></c:set>
                                                                <c:set var="totalE" value="0"></c:set>
                                                                <c:set var="totalFF" value="0"></c:set>
                                                                <c:forEach items="${sorties}" var="cmd">
                                                                    <c:set var="totalS" value="${totalS + cmd.getMontant()}"></c:set>
                                                                        <tr>
                                                                            <td><f:formatDate value="${cmd.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                                                        <td>${cmd.getSource().getNom()}</td>
                                                                        <td>${cmd.getCommentaire()}</td>
                                                                        <td>
                                                                            <c:if test="${cmd.getLigneSortie() != null}">
                                                                                <f:formatNumber value="${cmd.getLigneSortie().getMontantNet()}"type="CURRENCY" currencySymbol=""/>
                                                                            </c:if>
                                                                        </td>
                                                                        <td></td>

                                                                        <td>
                                                                            <c:if test="${cmd.getLigneSortie() == null}">
                                                                                <f:formatNumber value="${cmd.getMontant()}"type="CURRENCY" currencySymbol=""/>
                                                                            </c:if>
                                                                            <c:if test="${cmd.getLigneSortie() != null}">
                                                                                <f:formatNumber value="${cmd.getAvance()}"type="CURRENCY" currencySymbol=""/>
                                                                            </c:if>
                                                                        </td>

                                                                        <td><f:formatNumber value="${cmd.getSoldeCaisse()}"type="CURRENCY" currencySymbol=""/></td>
                                                                        <td>${cmd.getOperateur().getFirstname()}</td>
                                                                    </tr>
                                                                </c:forEach>
                                                                <c:forEach items="${entrees}" var="cmd">
                                                                    <c:set var="totalE" value="${totalE + cmd.getAvance()}"></c:set>
                                                                        <tr>
                                                                            <td><f:formatDate value="${cmd.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                                                        <td>${cmd.getSource().getNom()}</td>
                                                                        <td>${cmd.getCommentaire()}</td>
                                                                        <td>
                                                                            <c:if test="${cmd.getLigneAccount() != null}">
                                                                                <f:formatNumber value="${cmd.getLigneAccount().getMontantNet()}"type="CURRENCY" currencySymbol=""/>
                                                                            </c:if>
                                                                        </td>
                                                                        <td><f:formatNumber value="${cmd.getAvance()}"type="CURRENCY" currencySymbol=""/></td>
                                                                        <td></td>
                                                                        <td><f:formatNumber value="${cmd.getSoldeCaisse()}"type="CURRENCY" currencySymbol=""/></td>
                                                                        <td>${cmd.getOperateur().getFirstname()}</td>

                                                                    </tr>
                                                                </c:forEach>

                                                            </tbody>
                                                            <tfoot>
                                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td>Total</td>
                                                                    <td><f:formatNumber value="${totalE}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td><f:formatNumber value="${totalS}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td></td>
                                                                    <td style="color: red"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td>Solde prévisionnel</td>
                                                                    <td ><f:formatNumber value="${caisse.getMontantRestant()}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td>Solde Réel</td>
                                                                    <td><f:formatNumber value="${caisse.getMontantVerser()}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td></td>
                                                                    <td>Différence</td>
                                                                    <td><f:formatNumber value="${caisse.getEcart()}"type="CURRENCY" currencySymbol=""/></td>
                                                                    <td></td>
                                                                </tr>

                                                            </tfoot>
                                                        </table>
                                                    </div>
                                                </c:if>
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


