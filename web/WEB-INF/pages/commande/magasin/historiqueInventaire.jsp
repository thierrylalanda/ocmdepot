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
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien143}">
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
                            <h4 class="card-title">Historique Inventaire</h4>
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
                                        <form class="form" method="post" action="inventaire?action=model&model=historiqueInventaire&q=${q}&isNew=1">
                                            <fieldset>
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <div class="form-group">
                                                            <label for="firstName">
                                                                Interval
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
                                                    <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                        <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                            <i class="fa fa-search"></i>
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
                                            <button title="voir l'inventaire" class="btn-sm btn btn-float btn-outline-success btn-round btn-link-update"
                                                    data-toggle="tooltips" name="id" action="inventaire?action=model&model=historiqueInventaire&q=commande/magasin/detailsInventaire&isNew=2">
                                                <i class="fa fa-eye"></i>
                                            </button>
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
                                <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Magasin</th>
                                            <th>Operateur</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${inventaires}" var="cmd">
                                            <tr>
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                        <td>${cmd.getMagasin().getNom()}</td>
                                        <td>${cmd.getOperateur().getFirstname()}</td>
                                        <td><f:formatDate value="${cmd.getDateInv()}" type="BOTH" dateStyle="MEDIUM"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </c:if>
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
        <c:import url="/WEB-INF/composants/modal/voirInventaire.jsp"/>
    </c:if>
</c:if>

