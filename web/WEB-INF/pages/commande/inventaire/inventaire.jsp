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
<c:if test="${sessionScope.societe.getGestmagasin() == 0}">
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien136}">
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
                        <h4 class="card-title" id="from-actions-center-dropdown">Mouvements</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="card-text">

                                <form class="form form_inventaire" method="post" action="inventaire?action=model&model=inventaire&q=${q}&isNew=${isnew}${id}&type=${type}">
                                    <fieldset>
                                        <div class="row">
                                            <c:if test="${not empty type}">
                                                <c:if test="${type==1}">
                                                    <div class="form-group col-lg-3 col-sm-12">
                                                        <label for="articles_inventaire">Emballage
                                                            <span class="danger">*</span></label>
                                                        <select style="width: 100%" required id="articles_inventaire_societe"  class="select2-size-xs block form-control round required" name="article">
                                                            <c:if test="${inventaire != null}">
                                                                <option selected value="${inventaire.getEmballage().getId()}"> ${inventaire.getEmballage().getNom()}</option>
                                                            </c:if>
                                                            <c:if test="${inventaire == null}">
                                                                <c:forEach items="${emballages}" var="art">
                                                                    <option value="${art.getId()}" stock="${art.getStock()}">${art.getCode()}=> ${art.getNom()}</option>
                                                                </c:forEach>
                                                            </c:if>
                                                        </select>
                                                    </div>
                                                </c:if>
                                                <c:if test="${type==0}">
                                                    <div class="form-group col-lg-3 col-sm-12">
                                                        <label for="articles_inventaire">Article
                                                            <span class="danger">*</span></label>
                                                        <select style="width: 100%" required id="articles_inventaire_societe"  class="select2-size-xs block form-control round required" name="article">
                                                            <c:if test="${inventaire != null}">
                                                                <option selected value="${inventaire.getArticle().getId()}"> ${inventaire.getArticle().getNom()}</option>
                                                            </c:if>
                                                            <c:if test="${inventaire == null}">
                                                                <c:forEach items="${articles}" var="art">
                                                                    <option value="${art.getId()}" stock="${art.getStock()}">${art.getCode()}=> ${art.getNom()}</option>
                                                                </c:forEach>
                                                            </c:if>
                                                        </select>
                                                    </div>
                                                </c:if>
                                            </c:if>

                                            <div class="form-group col-lg-1 col-sm-12">
                                                <label for="quantite"><span class="danger"></span>Stock</label>
                                                <input type="text" id="quantite_societe" disabled class="form-control-sm form-control round"/>
                                            </div>
                                            <div class="form-group col-lg-1 col-sm-12">
                                                <label for="quantite"><span class="danger">*</span>Qte</label>
                                                <input type="number"  value="" required step="0.01" class="form-control-sm form-control round" name="quantite"/>
                                            </div>
                                            <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                    <i class="fa fa-save"></i> Enregistrer
                                                </button>
                                            </div>
                                        </div>
                                    </fieldset>

                                </form>
                                <form class="form form_commentaire_inventaire hidden" id="" method="post" action="inventaire?q=${q}&action=model&model=inventaire&isNew=2&type=${type}">
                                    <fieldset>
                                        <div class="row">

                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="articles_inventaire">Commentaire
                                                    <span class="danger">*</span></label>
                                                <textarea name="commentaire" required rows="4" class="form-control round">RAS</textarea>

                                            </div>

                                            <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                    <i class="fa fa-save"></i> Enregistrer
                                                </button>
                                            </div>
                                            <div class="form-group col-lg-1 col-sm-12 hidden">
                                                <label for="quantite"><span class="danger"></span>type</label>
                                                <input type="number" value="1" name="type" class="form-control-sm form-control round"/>
                                            </div>
                                        </div>
                                    </fieldset>

                                </form>
                            </div>
                            <div class="form-group">
                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <c:if test="${not empty inventaires }">
                                        <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="inventaire?q=${q}&action=model&model=inventaire&isNew=5&type=${type}"><i class="fa fa-edit"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="inventaire?q=${q}&action=model&model=inventaire&isNew=4&type=${type}"><i class="fa fa-trash"></i></button>
                                        <a title="Enregistrer l'inventaire" class="btn-sm btn btn-float btn-outline-success btn-round btn_toggle_form_inventaire"
                                           data-toggle="tooltips"  href="#"><i class="fa fa-save"></i></a>
                                        </c:if>
                                        <c:if test="${not empty magasinEncour }">
                                        <a title="Fermer ce magasin" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                           data-toggle="tooltips"  href="inventaire?q=${q}&action=model&model=inventaire&isNew=0&type=${type}"><i class="fa fa-eject"></i></a>
                                        </c:if>
                                </div>
                            </div>
                            <div style="overflow-x: scroll">
                                <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                                <c:if test="${type==0}">
                                                <th>Categorie</th>
                                                </c:if>
                                            <th>Code</th>
                                            <th>Article</th>
                                            <th>Qte init</th>
                                            <th>Qte Entrée</th>
                                            <th>Ecart</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total" value="0"></c:set>
                                        <c:set var="qte" value="0"></c:set>
                                        <c:set var="qtev" value="0"></c:set>
                                        <c:set var="quantite" value="0"></c:set>
                                        <c:forEach items="${inventaires}" var="cmd">
                                            <c:set var="qte" value="${qte+cmd.getQtAvant()}"></c:set>
                                            <c:set var="qtev" value="${qtev+cmd.getQtApres()}"></c:set>
                                            <c:set var="quantite" value="${quantite+cmd.getQuantite()}"></c:set>
                                            <c:set var="total" value="${total + cmd.getEcartQt()}"></c:set>
                                                <tr>
                                                    <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                                <c:if test="${type==0}">
                                            <td>${cmd.getArticle().getCategorie().getNom()}</td>
                                            <td>${cmd.getArticle().getCode()}</td>
                                            <td>${cmd.getArticle().getNom()}</td>
                                        </c:if>
                                        <c:if test="${type==1}">
                                            <td>${cmd.getEmballage().getCode()}</td>
                                            <td>${cmd.getEmballage().getNom()}</td>
                                        </c:if>
                                        <td  style="cursor:pointer">${cmd.getQtAvant()}</td>
                                        <td >${cmd.getQuantite()}</td>
                                        <td>${cmd.getEcartQt()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr style="font-weight: bold; color: #00b5b8">
                                            <td></td>
                                            <c:if test="${type==0}">
                                                <td></td>
                                            </c:if>
                                            <td></td>
                                            <td>TOTAL</td>
                                            <td>${qte}</td>
                                            <td>${quantite}</td>
                                            <td>${total}</td>

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
