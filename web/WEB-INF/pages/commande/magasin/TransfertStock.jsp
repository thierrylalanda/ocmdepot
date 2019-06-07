<%-- 
    Document   : TransfertStock
    Created on : 31 mars 2019, 15:21:03
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestmagasin() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien140}">
        <c:set var="id" value="&r=7tyui895qze412"></c:set>
        <c:if test="${transfert != null}">
            <c:set var="isnew" value="${1}"></c:set>
            <c:set var="id" value="&id=${transfert.getId()}"></c:set>
        </c:if>
        <c:if test="${transfert == null}">
            <c:set var="isnew" value="${0}"></c:set>
        </c:if>
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <div class="row autoloade" autoloader="true">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title" id="from-actions-center-dropdown">Transfert de stock</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="card-text">
                                <form class="form form_find_magasin" method="post" action="aoyurdfe148jg?action=model&model=transfertStock&q=${q}&isNew=0">
                                    <fieldset>
                                        <div class="row">
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label for="code">
                                                        Magasin Emetteur
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <select style="width: 100%" required class="select2-size-xs block form-control round " name="mg1">
                                                        <option value=""></option>
                                                        <c:forEach items="${magasins}" var="mag">
                                                            <option <c:if test="${sessionScope.magasinEmetteur != null}">disabled <c:if test="${mag.getId()==sessionScope.magasinEmetteur.getId()}">selected</c:if></c:if>  value="${mag.getId()}"> ${mag.getNom()}</option>
                                                        </c:forEach>
                                                    </select>    
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label for="code">
                                                        Magasin Recepteur
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <select style="width: 100%"  required  class="select2-size-xs block form-control round " name="mg2">
                                                        <option value=""></option>
                                                        <c:forEach items="${magasins}" var="mag">
                                                            <option <c:if test="${sessionScope.magasinRecepteur != null}">disabled <c:if test="${mag.getId()==sessionScope.magasinRecepteur.getId()}">selected</c:if></c:if>  value="${mag.getId()}"> ${mag.getNom()}</option>
                                                        </c:forEach>
                                                    </select>    
                                                </div>
                                            </div>
                                            <c:if test="${empty sessionScope.magasinEmetteur }">
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-save"></i> Initialisation
                                                    </button>
                                                </div>
                                            </c:if>
                                        </div>
                                    </fieldset>
                                </form>
                                <c:if test="${sessionScope.magasinRecepteur != null}">
                                    <form class="form" method="post" action="aoyurdfe148jg?action=model&model=transfertStock&q=${q}&isNew=${isnew}${id}">
                                        <fieldset>
                                            <div class="row">

                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="articles_inventaire">Article
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="article_to_transfert" class="select2-size-xs block form-control round required" name="article">
                                                        <c:if test="${inventaire != null}">
                                                            <option selected value="${inventaire.getArticle().getId()}"> ${inventaire.getArticle().getCode()}=>${inventaire.getArticle().getNom()} </option>
                                                        </c:if>
                                                        <c:if test="${inventaire == null}">
                                                            <c:forEach items="${sessionScope.magasinEmetteur.getStockMgList()}" var="art">
                                                                <option stockv="${art.getStockV()}" stock="${art.getStock()}" value="${art.getArticle().getId()}">${art.getArticle().getCode()}=> ${art.getArticle().getNom()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Qte</label>
                                                    <input type="number"  value="${inventaire.getQtApres()}" id="quantite_to_transfert" class="form-control-sm form-control round" name="quantite"/>
                                                </div>
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-send"></i> Transferer
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
                                    <c:if test="${not empty TransfertStock }">
                                         <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="aoyurdfe148jg?q=${q}&action=model&model=transfertStock&isNew=7"><i class="fa fa-edit"></i></button>
                                          <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="aoyurdfe148jg?q=${q}&action=model&model=transfertStock&isNew=2"><i class="fa fa-trash"></i></button>
                                         <a title="Enregistrer l'inventaire" class="btn-sm btn btn-float btn-outline-success btn-round"
                                           data-toggle="tooltips"  href="aoyurdfe148jg?q=${q}&action=model&model=transfertStock&isNew=3"><i class="fa fa-save"></i></a>
                                    </c:if>
                                        <c:if test="${not empty sessionScope.magasinEmetteur }">
                                        <a title="Fermer ce magasin" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                           data-toggle="tooltips"  href="inventaire?q=${q}&action=model&model=TransfertStock&isNew=0"><i class="fa fa-eject"></i></a>
                                        </c:if>
                                </div>
                            </div>
                            <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Categorie</th>
                                        <th>Article</th>
                                        <th>P.U</th>
                                        <th>Qte</th>
                                        <th>P.T</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="total" value="0"></c:set>
                                    <c:set var="qte" value="0"></c:set>
                                    <c:forEach items="${TransfertStock}" var="cmd">
                                        <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>
                                        <c:set var="total" value="${total+(cmd.getQuantite()*cmd.getArticle().getPrix())}"></c:set>
                                            <tr>
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                    <td>${cmd.getArticle().getCategorie().getNom()}</td>
                                    <td>${cmd.getArticle().getNom()}</td>
                                    <td  style="cursor:pointer">${cmd.getArticle().getPrix()}</td>
                                    <td >${cmd.getQuantite()}</td>
                                    <td>${cmd.getQuantite()*cmd.getArticle().getPrix()}</td>
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
