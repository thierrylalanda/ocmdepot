<%-- 
    Document   : MargeCategorie
    Created on : 17 janv. 2019, 10:08:56
    Author     : messi
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
                                        <form method="post" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=3" class="" novalidate>


                                            <fieldset>
                                                <div class="row">
                                                    <div class="col-md-2">
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
                                                                Type
                                                                <span class="danger">*</span>
                                                            </label>
                                                            <select style="width: 100%" id="marge_type" class="select2-size-xs block form-control round" name="type">
                                                                <option value="0"> Categorie</option>
                                                                <option value="1"> Article</option>

                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 marge_categorie">
                                                        <div class="form-group">
                                                            <label for="lastName">
                                                                Catégorie
                                                                <span class="danger">*</span>
                                                            </label>
                                                            <select style="width: 100%" id="" class="select2-size-xs block form-control round" name="categorie">

                                                                <c:forEach items="${categories}" var="cat">
                                                                    <option value="${cat.getId()}"> ${cat.getNom()}</option>

                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2 hidden marge_article">
                                                        <div class="form-group">
                                                            <label for="lastName">
                                                                Articles
                                                                <span class="danger">*</span>
                                                            </label>
                                                            <select style="width: 100%" id="" class="select2-size-xs block form-control round" name="article">

                                                                <c:forEach items="${articles}" var="art">
                                                                    <option value="${art.getId()}">${art.getCode()}-${art.getNom()}</option>

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
                                                            <select style="width: 100%" id="statut" class="select2-size-xs block form-control round" name="statut">

                                                                <c:forEach items="${statutCommandes}" var="sta">
                                                                    <c:if test="${sta.getCode() == 201 or sta.getCode() == 200 or sta.getCode() == 300}">
                                                                        <option value="${sta.getCode()}"> ${sta.getNom()}</option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
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
                                        </p>
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
                                        <div class="text-center bg-dark title_table" style="color:white">${messageTable}</div>
                                        <br>
                                        <div style="overflow-x: scroll">
                                            <table style="width: 100%" class="table table-dark table-striped table-bordered marge_table">
                                                <thead>
                                                    <tr>
                                                        <th class="datec" pos="0">N°</th>
                                                        <th>Client</th>
                                                        <th>Article</th>
                                                        <th>Prix</th>
                                                        <th>Qte</th>
                                                        <th>Prix.T</th>
                                                        <th>Marge client</th>
                                                        <th>Marge directe</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:set var="total" value="0"></c:set>
                                                    <c:set var="totalC" value="0"></c:set>
                                                    <c:set var="qte" value="0"></c:set>
                                                    <c:set var="qteL" value="0"></c:set>
                                                    <c:forEach items="${commandes}" var="all">

                                                        <c:set var="totalC" value="${totalC + (all.getMargeClient())}"></c:set>
                                                        <c:set var="total" value="${total + all.getPrixTotal()}"></c:set>
                                                        <c:set var="qte" value="${qte+all.getQuantite()}"></c:set>
                                                        <c:set var="qteL" value="${qteL + (all.getMargeFournisseur())}"></c:set>
                                                            <tr >

                                                                <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${all.getId()}</td>    
                                                            <td >${all.getLigne().getClient().getNom()}</td> 
                                                            <td style="cursor: pointer" data-toogle="tooltip" title="${all.getArticle().getNom()}">${all.getArticle().getCode()}</td> 
                                                            <td>${all.getArticle().getPrix()}</td> 
                                                            <td>${all.getQuantite()}</td> 
                                                            <td><f:formatNumber value="${all.getPrixTotal()}"type="CURRENCY" currencySymbol=""/></td> 
                                                            <td><f:formatNumber value="${all.getMargeClient()}"type="CURRENCY" currencySymbol=""/></td>
                                                            <td><f:formatNumber value="${all.getMargeFournisseur()}"type="CURRENCY" currencySymbol=""/></td> 
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>
                                                <tfoot>
                                                    <tr style="font-weight: bold; color: #00b5b8">
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td>TOTAL</td>
                                                        <td>${qte}</td>
                                                        <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                        <td><f:formatNumber value="${totalC}"type="CURRENCY" currencySymbol=""/></td>
                                                        <td><f:formatNumber value="${qteL}"type="CURRENCY" currencySymbol=""/></td>
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
    </c:if>
</c:if>

