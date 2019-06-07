<%-- 
    Document   : inventaireMagasin
    Created on : 24 mars 2019, 20:43:19
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestmagasin() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien142}">
        <c:set var="id" value="&r=7tyui895qze412"></c:set>
        <c:if test="${inventaire != null}">
            <c:set var="isnew" value="${3}"></c:set>
            <c:set var="id" value="&inventaire=${inventaire.getId()}"></c:set>
        </c:if>
        <c:if test="${inventaire == null}">
            <c:set var="isnew" value="${1}"></c:set>
        </c:if>
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <div class="row autoloader addCommades" autoloader="true">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title" id="from-actions-center-dropdown">Inventaire Magasins</h4>
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
                                                            <option <c:if test="${sessionScope.magasinEncour != null}">disabled <c:if test="${mag.getId()==sessionScope.magasinEncour.getId()}">selected</c:if></c:if>  value="${mag.getId()}"> ${mag.getNom()}</option>
                                                        </c:forEach>
                                                    </select>    
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                                <c:if test="${sessionScope.magasinEncour != null}">
                                    <form class="form" method="post" action="inventaire?action=model&model=inventaire&q=${q}&isNew=${isnew}${id}">
                                        <fieldset>
                                            <div class="row">

                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="articles_inventaire">Article
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="articles_inventaire"  class="select2-size-xs block form-control round required" name="article">
                                                        <c:if test="${inventaire != null}">
                                                            <option selected value="${inventaire.getArticle().getId()}"> ${inventaire.getArticle().getNom()}</option>
                                                        </c:if>
                                                        <c:if test="${inventaire == null}">
                                                            <c:forEach items="${articles}" var="art">
                                                                <option value="${art.getId()}">${art.getCode()}=> ${art.getNom()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Qte</label>
                                                    <input type="number"  value="${inventaire.getQtApres()}" id="quantite" class="form-control-sm form-control round" name="quantite"/>
                                                </div>
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-save"></i> Enregistrer
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
                                    <c:if test="${not empty inventaires }">
                                        <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="inventaire?q=${q}&action=model&model=inventaire&isNew=5"><i class="fa fa-edit"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="inventaire?q=${q}&action=model&model=inventaire&isNew=4"><i class="fa fa-trash"></i></button>

                                        <a title="Enregistrer l'inventaire" class="btn-sm btn btn-float btn-outline-success btn-round"
                                           data-toggle="tooltips"  href="inventaire?q=${q}&action=model&model=inventaire&isNew=2"><i class="fa fa-save"></i></a>
                                        </c:if>
                                        <c:if test="${not empty magasinEncour }">
                                        <a title="Fermer ce magasin" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                           data-toggle="tooltips"  href="inventaire?q=${q}&action=model&model=inventaire&isNew=0"><i class="fa fa-eject"></i></a>
                                        </c:if>
                                </div>
                            </div>
                            <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Categorie</th>
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
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                    <td>${cmd.getArticle().getCategorie().getNom()}</td>
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
    </c:if>
</c:if>
