<%-- 
    Document   : historiqueVenteArticle
    Created on : 26 avr. 2019, 14:46:38
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien107}">
    <%
        Date now = new Date();
        request.setAttribute("day", now);
    %>
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

    <div class="row autoloader" autoloader="true">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Ventes Par Article</h4>
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
                                    <form method="post" action="historiques?q=${q}&action=model&model=historiqueVente&isNew=2" class="" novalidate>


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
                                                            Articles
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" id="statut" class="select2-size-xs block form-control round " name="article">
                                                            <option value="0">Tous</option>
                                                            <c:forEach items="${articles}" var="art">
                                                                <option value="${art.getId()}">${art.getNom()}=>${art.getCode()}</option>
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
                                                            <c:forEach items="${statutCommandes}" var="sta">
                                                                <c:if test="${sta.getCode()==200 or sta.getCode()==300}">
                                                                    <option <c:if test="${sta.getCode()==200}">selected</c:if> value="${sta.getCode()}"> ${sta.getNom()}</option>
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

                                            <c:if test="${sessionScope.root=='EH2S' or 1==1}">


                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                        data-toggle='tooltips' title="tout Imprimer "
                                                        name="id"><i class="fa fa-print"></i></button>
                                                </c:if>
                                        </div>
                                    </div>
                                    </p>
                                    <div class="text-center bg-dark title_table" style="color:white">${messageTable}</div>
                                    <br>
                                    <div style="overflow-x: scroll">
                                        <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-select-checkbox">
                                            <thead>
                                                <tr>
                                                    <th class="datec" pos="1">N°</th>
                                                    <th>Article</th>
                                                    <th>Qte</th>
                                                    <th>Prix</th>
                                                    <th>P.T</th>
                                                    <th>Remise</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="total" value="0"></c:set>
                                                <c:set var="qte" value="0"></c:set>
                                                <c:set var="remise" value="0"></c:set>
                                                <c:forEach items="${commandes}" var="all">
                                                    <c:set var="total" value="${total + all.getPrixTotal()}"></c:set>
                                                    <c:set var="qte" value="${qte+all.getQuantite()}"></c:set>
                                                    <c:set var="remise" value="${remise+all.getRemise()}"></c:set>
                                                        <tr >
                                                            <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${all.getId()}</td>    
                                                        <td>${all.getArticle().getNom()}</td> 

                                                        <td>${all.getQuantite()}</td> 
                                                        <td><f:formatNumber value="${all.getPrix()}"type="CURRENCY" currencySymbol=""/></td> 
                                                        <td><f:formatNumber value="${all.getPrixTotal()}"type="CURRENCY" currencySymbol=""/></td> 
                                                        <td><f:formatNumber value="${all.getRemise()}"type="CURRENCY" currencySymbol=""/></td> 

                                                    </tr>
                                                </c:forEach>

                                            </tbody>
                                            <tfoot>
                                                <tr style="font-weight: bold; color: #00b5b8">
                                                    <td></td>
                                                    <td>TOTAL</td>
                                                    <td><f:formatNumber value="${qte}"type="CURRENCY" currencySymbol=""/></td>
                                                    <td></td>
                                                    <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                    <td><f:formatNumber value="${remise}"type="CURRENCY" currencySymbol=""/></td>

                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->

                                            <c:if test="${sessionScope.root=='EH2S' or 1==1}">

                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                        data-toggle='tooltips' title="tout Imprimer "
                                                        name="id"><i class="fa fa-print"></i></button>
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
</c:if>
