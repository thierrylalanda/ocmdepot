<%-- 
    Document   : margeCommande
    Created on : 15 janv. 2019, 17:53:44
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien129}">
    <%
        Date now = new Date();
        request.setAttribute("day", now);
    %>
    <c:if test="${sessionScope.societe.getGestmarge()==1}">
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

        <div class="row autoloader" autoloader="true">

            <div class="col-md-12">
                <!-- Restore & show all table -->
                <section id="restore">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Marge des commandes</h4>
                                    <div class="heading-elements ">
                                        <ul class="list-inline mb-0">
                                            <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                                            <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                                            <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="card-content collapse show">
                                    <div class="card-body card-dashboard">
                                        <p class="card-text">
                                        <form method="post" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=1" class="" novalidate>


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
                                                    <div class="col-md-3">
                                                        <div class="form-group">
                                                            <label for="lastName">
                                                                Statut
                                                                <span class="danger">*</span>
                                                            </label>
                                                            <select style="width: 100%" id="statut" class="select2-size-xs block form-control round" name="statut">

                                                                <c:forEach items="${statutCommandes}" var="sta">
                                                                    <c:if test="${sta.getCode() == 201 or sta.getCode() == 200 or sta.getCode() == 300}">
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




                                                <!--  <a class="btn btn-float btn-outline-success btn-round hidden"
                                                      data-toggle='tooltips' title="Mettre à jour les ristournes" href="gestionCommandes?q=${q}&action=model&model=PasserCommande&isNew=8">
                                                   <i class="fa fa-eye"></i>
                                                  </a>-->
                                                <c:if test="${commandes != null}">
                                                    <c:if test="${commandes.size() != 0}">
                                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                                data-toggle='tooltips' title="Imprimer"
                                                                name="id"><i class="fa fa-print"></i></button>
                                                        </c:if>
                                                    </c:if>

                                            </div>
                                        </div>
                                        </p>
                                        <div class="text-center bg-dark title_table" style="color:white">${messageTable}</div>
                                        <br>
                                        <div style="overflow-x: scroll">
                                            <table style="width: 100%" class="table table-dark table-striped table-bordered marge_table">
                                                <thead>
                                                    <tr>
                                                        <th class="datec" pos="0">N°</th>
                                                        <th>Client</th>
                                                        <th>Collis</th>
                                                        <th>Produits</th>
                                                        <th>Marge client</th>
                                                        <th>Marge directe</th>
                                                        <th>Date</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:set var="total" value="0"></c:set>
                                                    <c:set var="totalC" value="0"></c:set>
                                                    <c:set var="qte" value="0"></c:set>
                                                    <c:set var="qteL" value="0"></c:set>
                                                    <c:forEach items="${commandes}" var="all">
                                                        <c:if test="${all.getEtatc().getCode()== 200 or all.getEtatc().getCode()== 201 or all.getEtatc().getCode()== 300}">
                                                            <c:set var="qteP" value="0"></c:set>
                                                            <c:forEach items="${all.getTcommandesList()}" var="allA">
                                                                <c:set var="totalC" value="${totalC +allA.getQuantite()}"></c:set>
                                                                <c:set var="qteP" value="${qteP + allA.getQuantite()}"></c:set>

                                                            </c:forEach>
                                                            <c:set var="total" value="${total + all.getMargeFournisseur()}"></c:set>
                                                            <c:set var="qte" value="${qte+all.getMargeClient()}"></c:set>
                                                            <c:set var="qteL" value="${qteL + all.getQtotal()}"></c:set>
                                                                <tr >

                                                                    <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${all.getId()}</td>    
                                                                <td>${all.getClient().getNom()}</td> 
                                                                <td>${all.getQtotal()}</td> 
                                                                <td>${qteP}</td> 
                                                                <td>${all.getMargeClient()}</td> 
                                                                <td><f:formatNumber value="${all.getMargeFournisseur()}"type="CURRENCY" currencySymbol=""/></td> 
                                                                <td>${all.getDatecc()}</td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>

                                                </tbody>
                                                <tfoot>
                                                    <tr style="font-weight: bold; color: #00b5b8">

                                                        <td></td>
                                                        <td>TOTAL</td>
                                                        <td>${qteL}</td>
                                                        <td><f:formatNumber value="${totalC}"type="CURRENCY" currencySymbol=""/></td>
                                                        <td>${qte}</td>
                                                        <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                        <td></td>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                        <div class="form-group">
                                            <div class="btn-group" role="group" aria-label="Basic example">
                                                <!-- Outline Round Floating button -->

                                                <c:if test="${commandes != null}">
                                                    <c:if test="${commandes.size() != 0}">
                                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                                data-toggle='tooltips' title="Imprimer"
                                                                name="id"><i class="fa fa-print"></i></button>
                                                        </c:if>
                                                    </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!--/ Restore & show all table -->
            </div>
        </div>
        <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
            <c:import url="/WEB-INF/composants/modal/voirMarge.jsp"/>
        </c:if>
    </c:if>
</c:if>
