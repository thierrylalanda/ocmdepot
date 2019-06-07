<%-- 
    Document   : historiqueAchat
    Created on : 15 avr. 2019, 18:45:31
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
                            <h4 class="card-title">Historique des Achats</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">
                            <div class="card-body">
                                <form method="post" action="historiques?q=${q}&action=model&model=historiqueAchat&isNew=1" class="" novalidate>


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
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label for="lastName">
                                                        Fournisseur
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <select style="width: 100%" id="statut" class="select2-size-xs block form-control round " name="fournisseur">
                                                        <option value="0">Tous</option>
                                                        <c:forEach items="${fournisseurs}" var="four">
                                                            <option value="${four.getId()}"> ${four.getNom()}</option>

                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label for="lastName">
                                                        Statut
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <select style="width: 100%" id="statut" class="select2-size-xs block form-control round " name="statut">
                                                        <option value="0">Tous</option>
                                                        <c:forEach items="${statutCommandes}" var="sta">
                                                            <c:if test="${sta.getCode()==200 or sta.getCode()==300 or sta.getCode()==501}">
                                                                <option value="${sta.getCode()}"> ${sta.getNom()}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label for="lastName">

                                                    </label>

                                                    <input style="margin-top: 25px" type="submit" class="btn btn-outline-primary  round btn-sm"
                                                           value="Enregistrer">
                                                </div>
                                            </div>


                                        </div>
                                    </fieldset>

                                </form>
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
                                <div class="text-center bg-dark title_table" style="color:white">${messageTable}</div>
                                <br>
                                <div style="overflow-x: scroll;">
                                    <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
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
                                                <c:if test="${cmd.getStatut()==0}">
                                                    <td>
                                                        <span class="badge badge-warning">
                                                            Non Receptionné
                                                        </span>
                                                    </td>
                                                </c:if> 
                                                <c:if test="${cmd.getStatut()==1}">
                                                    <td>
                                                        <span class="badge badge-success">Receptionné</span>
                                                    </td>
                                                </c:if> 
                                                <td><f:formatDate value="${cmd.getDateCommande()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/></td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        </tbody>
                                        <tfoot>
                                            <tr style="font-weight: bold; color: #00b5b8">
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td>Total</td>
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
