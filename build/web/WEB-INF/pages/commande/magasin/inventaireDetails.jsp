<%-- 
    Document   : inventaireDetails
    Created on : 27 mars 2019, 15:08:55
    Author     : Administrateur
--%>

<%-- 
    Document   : historiqueInventaire
    Created on : 25 mars 2019, 09:03:14
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestmagasin() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien144}">
        <c:set var="id" value="&r=7tyui895qze412"></c:set>
        <c:if test="${stock != null}">
            <c:set var="isnew" value="${5}"></c:set>
            <c:set var="id" value="&stock=${stock.getArticle().getId()}"></c:set>
        </c:if>
        <c:if test="${stock == null}">
            <c:set var="isnew" value="${0}"></c:set>
        </c:if>
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <section>
            <div class="row autoloader" autoloader="true">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Historique Détaillé Inventaire</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                        </div>
                        <div class="card-content">
                            <div class="card-body">
                                <div class="card-text">
                                    <form class="form form_find_magasin" method="post" action="inventaire?action=model&model=inventaire&q=${q}&isNew=0">
                                        <fieldset>
                                            <div class="row">
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <label for="code">
                                                            Magasin
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%"   id="find_magasin_to_stock" class="select2-size-xs block form-control round " name="id">
                                                            <option value=""></option>
                                                            <c:forEach items="${magasins}" var="mag">
                                                                <option <c:if test="${sessionScope.magasinEncour != null}">disabled</c:if> <c:if test="${mag.getId()==sessionScope.magasinEncour.getId()}">selected</c:if> value="${mag.getId()}"> ${mag.getNom()}</option>
                                                            </c:forEach>
                                                        </select>    
                                                    </div>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </form>
                                    <c:if test="${not empty magasinEncour }">
                                        <form class="form" method="post" action="inventaire?action=model&model=historiqueInventaire&q=${q}&isNew=3">
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

                                                            </label>

                                                            <input style="margin-top: 25px" type="submit" class="btn btn-outline-primary  round btn-sm"
                                                                   value="Rechercher">
                                                        </div>
                                                    </div>


                                                </div>
                                            </fieldset>

                                        </form>
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->
                                        <c:if test="${not empty inventaires }">

                                            <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                    data-toggle='tooltips' title="tout Imprimer "
                                                    name="id"><i class="fa fa-print"></i></button>
                                            </c:if>
                                            <c:if test="${not empty magasinEncour }">
                                            <a title="Fermer ce magasin" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                               data-toggle="tooltips"  href="inventaire?q=${q}&action=model&model=inventaire&isNew=0"><i class="fa fa-eject"></i></a>
                                            </c:if>
                                    </div>
                                </div>
                                <div class="text-center bg-dark title_table" style="color:white">${messageTable}</div>
                                <br>
                                <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>Categorie</th>
                                            <th>Code Art</th>
                                            <th>Article</th>
                                            <th>Qte init</th>
                                            <th>Qte Final</th>
                                            <th>Ecart</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total" value="0"></c:set>
                                        <c:set var="qte" value="0"></c:set>
                                        <c:set var="qtev" value="0"></c:set>
                                        <c:forEach items="${inventaires}" var="cmd">
                                            <c:set var="qte" value="${qte+cmd.getQtAvant()}"></c:set>
                                            <c:set var="qtev" value="${qtev+cmd.getQtApres()}"></c:set>

                                            <c:set var="total" value="${total + cmd.getEcartQt()}"></c:set>
                                                <tr>

                                                    <td>${cmd.getArticle().getCategorie().getNom()}</td>
                                                <td>${cmd.getArticle().getCode()}</td>
                                                <td>${cmd.getArticle().getNom()}</td>
                                                <td  style="cursor:pointer">${cmd.getQtAvant()}</td>
                                                <td >${cmd.getQtApres()}</td>
                                                <td>${cmd.getEcartQt()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr style="font-weight: bold; color: #00b5b8">
                                            <td></td>
                                            <td></td>
                                            <td>TOTAL</td>
                                            <td>${qte}</td>
                                            <td>${qtev}</td>
                                            <td>${total}</td>

                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </c:if>
</c:if>

