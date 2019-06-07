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
<c:if test="${sessionScope.societe.getGestfournisseur() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
        <c:set var="id" value="&r=7tyui895qze412"></c:set>
        <c:if test="${achat != null}">
            <c:set var="isnew" value="${3}"></c:set>
            <c:set var="id" value="&achat=${achat.getId()}"></c:set>
        </c:if>
        <c:if test="${achat == null}">
            <c:set var="isnew" value="${1}"></c:set>
        </c:if>
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <div class="row autoloader addCommades" autoloader="true">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title" id="from-actions-center-dropdown">Achat des produits</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="card-text">
                                <form class="form form_find_magasin" method="post" action="gestionachat?action=model&model=achat&q=${q}&isNew=0">
                                    <fieldset>
                                        <div class="row">
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label for="code">
                                                        Fournisseur
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <select style="width: 100%"   class="select2-size-xs block form-control round " name="id">
                                                        <option value=""></option>
                                                        <c:forEach items="${fournisseurs}" var="mag">
                                                            <option <c:if test="${sessionScope.fournisseurEncour != null}">disabled <c:if test="${mag.getId()==sessionScope.fournisseurEncour.getId()}">selected</c:if></c:if>  value="${mag.getId()}"> ${mag.getNom()}</option>
                                                        </c:forEach>
                                                    </select>    
                                                </div>
                                            </div>
                                            <div class="form-group col-lg-2 col-sm-12">
                                                <label for="categorie_article">Livraison
                                                    <span class="danger">*</span></label>
                                                <input type="text" <c:if test="${sessionScope.njrl != null}">disabled value="${sessionScope.njrl}"</c:if> class="form-control-sm form-control round singledate" name="njr"/>
                                                </div>
                                            <c:if test="${sessionScope.fournisseurEncour == null}">
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-shopping-cart"></i> Init
                                                    </button>
                                                </div>
                                            </c:if>
                                        </div>
                                    </fieldset>
                                </form>
                                <c:if test="${sessionScope.fournisseurEncour != null}">
                                    <form class="form" method="post" action="gestionachat?action=model&model=achat&q=${q}&isNew=${isnew}${id}">
                                        <fieldset>
                                            <div class="row">

                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="articles_inventaire">Article
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="articles_inventaire_societe"  class="select2-size-xs block form-control round required" name="article">
                                                        <option></option>
                                                        <c:if test="${achat != null}">
                                                            <option selected value="${achat.getArticle().getId()}"> ${achat.getArticle().getNom()}</option>
                                                        </c:if>
                                                        <c:if test="${achat == null}">
                                                            <c:forEach items="${sessionScope.fournisseurEncour.getTarticlesList()}" var="art">
                                                                <option stock="${art.getStock()}" value="${art.getId()}">${art.getCode()}=> ${art.getNom()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite"><span class="danger"></span>Stock</label>
                                                    <input type="text" id="quantite_societe" disabled class="form-control-sm form-control round"/>
                                                </div>

                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Qte
                                                        <span class="danger">*</span></label>
                                                    <input type="number"  required id="quantite" value="${achat.getQuantite()}" step="0.01" class="form-control-sm form-control round required" name="quantite"/>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Prix Divers</label>
                                                    <input type="number"  value="${achat.getPrix()}" class="form-control-sm form-control round" name="prix"/>
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
                                    <c:if test="${not empty achats }">
                                        <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="gestionachat?q=${q}&action=model&model=achat&isNew=5"><i class="fa fa-edit"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="gestionachat?q=${q}&action=model&model=achat&isNew=4"><i class="fa fa-trash"></i></button>

                                        <a title="Enregistrer le bon d'achat" class="btn-sm btn btn-float btn-outline-success btn-round"
                                           data-toggle="tooltips"  href="gestionachat?q=${q}&action=model&model=achat&isNew=2"><i class="fa fa-save"></i></a>
                                        </c:if>
                                        <c:if test="${not empty fournisseurEncour }">
                                        <a title="Initialiser" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                           data-toggle="tooltips"  href="gestionachat?q=${q}&action=model&model=achat&isNew=0"><i class="fa fa-eject"></i></a>
                                        </c:if>
                                </div>
                            </div>
                            <div style="overflow-x: scroll;">
                                <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
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
                                        <c:forEach items="${achats}" var="cmd">
                                            <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>

                                            <c:set var="total" value="${total + (cmd.getPrix() * cmd.getQuantite())}"></c:set>
                                            <tr style="cursor:pointer" data-toggle="tooltip" title="quantité en stock ${cmd.getArticle().getStock()}" >
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                        <td>${cmd.getArticle().getCategorie().getNom()}</td>
                                        <td>${cmd.getArticle().getNom()}</td>
                                        <td>${cmd.getPrix()}</td>
                                        <td>${cmd.getQuantite()}</td>
                                        <td>${cmd.getPrixTotal()}</td>
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

                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</c:if>
