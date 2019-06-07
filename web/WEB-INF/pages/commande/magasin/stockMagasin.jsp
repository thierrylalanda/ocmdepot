<%-- 
    Document   : stockMagasin
    Created on : 24 mars 2019, 20:43:07
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestmagasin() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien97}">
        <c:set var="id" value="&r=7tyui895qze412"></c:set>
        <c:if test="${stock != null}">
            <c:set var="isnew" value="${1}"></c:set>
            <c:set var="id" value="&id=${stock.getId()}"></c:set>
        </c:if>
        <c:if test="${stock == null}">
            <c:set var="isnew" value="${0}"></c:set>
        </c:if>
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <div class="row autoloader addCommades" autoloader="true">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title" id="from-actions-center-dropdown">Stocks Magasins</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="card-text">
                                <form class="form form_find_magasin" method="post" action="administration?action=model&model=MagasinStocks&q=${q}&isNew=3">
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
                                                            <option <c:if test="${sessionScope.magasinEncour != null}">disabled <c:if test="${mag.getId()==sessionScope.magasinEncour.getId()}">selected</c:if></c:if>  value="${mag.getId()}"> ${mag.getNom()}</option>
                                                        </c:forEach>
                                                    </select>    
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                                <c:if test="${not empty magasinEncour }">
                                    <form class="form" method="post" action="administration?action=model&model=MagasinStocks&q=${q}&isNew=${isnew}${id}">
                                        <fieldset>
                                            <div class="row">

                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="articles_stock_magasin">Article
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="articles_stock_magasin"  class="select2-size-xs block form-control round required" name="article">
                                                        <c:if test="${stock != null}">
                                                            <option selected value="${stock.getArticle().getId()}"> ${stock.getArticle().getNom()}</option>
                                                        </c:if>
                                                        <c:forEach items="${articles}" var="art">
                                                            <option value="${art.getId()}">${art.getCode()}=> ${art.getNom()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Qte</label>
                                                    <input type="number" id="quantite" value="${stock.getStock()}" class="form-control-sm form-control round" name="stock"/>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Qte V</label>
                                                    <input type="number" id="quantiteV" value="${stock.getStockV()}" class="form-control-sm form-control round" name="stockv"/>
                                                </div>
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-shopping-cart"></i> Ajouter
                                                    </button>
                                                </div>
                                            </div>
                                        </fieldset>

                                    </form>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <c:if test="${not empty stocks }">
                                        <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien15}">
                                            <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=MagasinStocks&isNew=2"><i class="fa fa-edit"></i></button>
                                            </c:if>
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien16}">
                                            <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=MagasinStocks&isNew=5"><i class="fa fa-trash"></i></button>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${not empty magasinEncour }">
                                        <a title="Fermer ce magasin" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                           data-toggle="tooltips"  href="administration?q=${q}&action=model&model=MagasinStocks&isNew=3"><i class="fa fa-eject"></i></a>
                                        </c:if>
                                </div>
                            </div>
                            <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Categorie</th>
                                        <th>Code Art</th>
                                        <th>Article</th>
                                        <th>P.U</th>
                                        <th>Stock</th>
                                        <th>Stock V</th>
                                        <th>P.T</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="total" value="0"></c:set>
                                    <c:set var="qte" value="0"></c:set>
                                    <c:set var="qtev" value="0"></c:set>
                                    <c:forEach items="${stocks}" var="cmd">
                                        <c:set var="qte" value="${qte+cmd.getStock()}"></c:set>
                                        <c:set var="qtev" value="${qtev+cmd.getStockV()}"></c:set>

                                        <c:set var="total" value="${total + cmd.getPrixTotal()}"></c:set>
                                            <tr>
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                    <td>${cmd.getArticle().getCategorie().getNom()}</td>
                                    <td>${cmd.getArticle().getCode()}</td>
                                    <td>${cmd.getArticle().getNom()}</td>
                                    <td>${cmd.getPrix()}</td>
                                    <td   class="td_update" style="cursor:pointer">${cmd.getStock()}</td>
                                    <td class="td_to_update">${cmd.getStockV()}</td>
                                    <td>${cmd.getPrixTotal()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr style="font-weight: bold; color: #00b5b8">
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>TOTAL</td>
                                        <td>${qte}</td>
                                        <td>${qtev}</td>
                                        <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>

                                    </tr>
                                </tfoot>
                            </table>
                            <div class="form-group">
                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <c:if test="${not empty stocks }">
                                        <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien15}">
                                            <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=MagasinStocks&isNew=2"><i class="fa fa-edit"></i></button>
                                            </c:if>
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien16}">
                                            <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=MagasinStocks&isNew=5"><i class="fa fa-trash"></i></button>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${not empty magasinEncour }">
                                        <a title="Fermer ce magasin" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                           data-toggle="tooltips"  href="administration?q=${q}&action=model&model=MagasinStocks&isNew=3"><i class="fa fa-eject"></i></a>
                                        </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</c:if>
