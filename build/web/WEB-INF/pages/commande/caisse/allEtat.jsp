<%-- 
    Document   : allEtat
    Created on : 26 avr. 2019, 10:29:32
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestcaisse() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">

        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <section>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Etat Périodique des Mouvements</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">

                            <div class="card-body">
                                <div class="card-text">

                                    <div class="row">
                                        <div class="card ">
                                            <div class="card-content">
                                                <div class="card-body">
                                                    <h4 class="card-title info">Etat Périodique des Mouvements</h4>
                                                    <form class="form" method="post" action="historiques?action=model&model=historiqueCaisse&q=${q}&isNew=2">
                                                        <fieldset>
                                                            <div class="row">
                                                                <div class="col-md-3">
                                                                    <div class="form-group">
                                                                        <label for="firstName">
                                                                            Periode
                                                                            <span class="danger">*</span>
                                                                        </label>
                                                                        <div class='input-group input-group-xs'>
                                                                            <input type='text' class="form-control-xs form-control localeRange" name="interval" />
                                                                            <div class="input-group-append">
                                                                                <span class="input-group-text">
                                                                                    <span class="fa fa-calendar"></span>
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
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
                                                                <c:forEach items="${sorties}" var="cmd">
                                                                    
                                                                    <c:if test="${cmd.getLigneSortie() == null}">
                                                                        <c:set var="totalS" value="${totalS + cmd.getMontant()}"></c:set>
                                                                            </c:if>
                                                                            <c:if test="${cmd.getLigneSortie() != null}">
                                                                                <c:set var="totalS" value="${totalS + cmd.getAvance()}"></c:set>
                                                                            </c:if>
                                                                        <tr>
                                                                            <td><f:formatDate value="${cmd.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                                                        <td>${cmd.getSource().getNom()}</td>
                                                                        <td>${cmd.getCommentaire()}</td>
                                                                        <td>
                                                                            <c:if test="${cmd.getLigneSortie() != nul}">
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
                                                            </tfoot>
                                                        </table>
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

