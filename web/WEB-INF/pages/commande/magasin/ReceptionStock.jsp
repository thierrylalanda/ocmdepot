<%-- 
    Document   : ReceptionStock
    Created on : 31 mars 2019, 15:21:17
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestmagasin() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien141}">
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
                        <h4 class="card-title" id="from-actions-center-dropdown">Réception Transfert</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="card-text">
                                <form class="form form_find_magasin" method="post" action="aoyurdfe148jg?action=model&model=transfertStock&q=${q}&isNew=8">
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
                            </div>
                            <div class="form-group">
                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <c:if test="${not empty ligneTransferts }">
                                         <button title="Détails Transfert" type="button" class="btn btn-float btn-outline-success btn-round btn-link-update" name="id" action="aoyurdfe148jg?q=commande/magasin/detailsTransfert&action=model&model=inventaire&isNew=9&ligne=${ligneTransferts.getId()}"><i class="fa fa-eye"></i></button>
                                         <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="aoyurdfe148jgt?q=${q}&action=model&model=transfertStock&isNew=4"><i class="fa fa-trash"></i></button>
                                            
                                        <a title="Réceptionner le transfert" class="btn-sm btn btn-float btn-outline-success btn-round"
                                           data-toggle="tooltips"  href="aoyurdfe148jg?q=${q}&action=model&model=transfertStock&isNew=6"><i class="fa fa-save"></i></a>
                                    </c:if>
                                        <c:if test="${not empty magasinEncour }">
                                        <a title="Fermer ce magasin" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                           data-toggle="tooltips"  href="aoyurdfe148jg?q=${q}&action=model&model=transfertStock&isNew=8"><i class="fa fa-eject"></i></a>
                                            </c:if>
                                </div>
                            </div>
                            <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Emetteur</th>
                                        <th>Operateur</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="total" value="0"></c:set>
                                    <c:set var="qte" value="0"></c:set>
                                    <c:set var="qtev" value="0"></c:set>
                                    <c:forEach items="${ligneTransferts}" var="cmd">
                                       
                                            <tr>
                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                    <td>${cmd.getMg1().getNom()}</td>
                                    <td>${cmd.getArticle().getOperateur()}</td>
                                    <td><f:formatDate value="${cmd.getDateTransf()}" type="BOTH" dateStyle="MEDIUM"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</c:if>

