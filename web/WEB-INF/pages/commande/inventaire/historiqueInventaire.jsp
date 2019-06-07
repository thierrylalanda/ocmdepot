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
<c:if test="${sessionScope.societe.getGestmagasin() == 0}">
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien137}">
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
                                    <form class="form" method="post" action="inventaire?action=model&model=historiqueInventaire&q=${q}&isNew=1&type=${type}">
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
                                </div>
                                <div class="form-group">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->
                                        <button title="voir l'inventaire" class="btn-sm btn btn-float btn-outline-success btn-round btn-link-update"
                                                data-toggle="tooltips" name="id" action="inventaire?action=model&model=historiqueInventaire&q=commande/inventaire/detailsInventaire&isNew=2&type=${type}">
                                            <i class="fa fa-eye"></i>
                                        </button>
                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                data-toggle='tooltips' title="tout Imprimer "
                                                name="id"><i class="fa fa-print"></i></button>

                                    </div>
                                </div>
                                <div style="overflow-x: scroll">
                                    <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>N°</th>
                                                <th>Operateur</th>
                                                <th>Date</th>
                                                <th>Total article</th>
                                                <th>Commentaire</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${not empty inventaires }">

                                                <c:forEach items="${inventaires}" var="cmd">

                                                    <c:if test="${cmd.getType()==type}">
                                                        <c:if test="${cmd.getIsInv() ==0}">
                                                            <tr>
                                                                <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                                        <td>${cmd.getId()}</td>
                                                        <td>${cmd.getOperateur().getFirstname()}</td>
                                                        <td><f:formatDate value="${cmd.getDateInv()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy HH:mm:ss"/></td>
                                                        <td>${cmd.getInventaireList().size()}</td>
                                                        <td>${cmd.getDescription()}</td>
                                                        </tr>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                       
                                        </tbody>
                                    </table>
                                </div>
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

