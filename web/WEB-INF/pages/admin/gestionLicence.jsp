<%-- 
    Document   : gestionLicence
    Created on : 1 nov. 2018, 12:09:12
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${societe.getId() != null}">
    <c:set var="isnew" value="${1}"></c:set>
</c:if>
<c:if test="${societe.getId()== null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
<div class="row match-height">

    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title" id="from-actions-center-dropdown">Modifier les Gamme</h4>
                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>

            </div>
            <div class="card-content">
                <div class="card-body">
                    <div class="card-text">


                    </div>
                    <form class="form" novalidate method="post" action="administration?q=${q}&action=model&model=typelicence&isNew=0" >
                        <div class="form-body">
                            <div class="row">

                            </div>
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label for="mode">
                                        Gamme
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" required id="mode" required class="select2-size-xs block form-control round" name="id">
                                        <c:forEach items="${typelicences}" var="gamme">
                                            <option value="${gamme.getId()}">${gamme.getType()}</option>
                                        </c:forEach>

                                    </select>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="mode">
                                        Mode
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" required id="mode" required class="select2-size-xs block form-control round" name="test">
                                        <option value="-1">Test</option>
                                        <option value="0">Licence</option>

                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="nbr">
                                        Nbre utilisateur
                                    </label>
                                    <input type="number" id="nbr" value="" class="form-control-sm form-control round"
                                           name="max"/>
                                </div>
                            </div>

                        </div>
                        <div class="form-actions">

                            <button type="submit" class="btn btn-primary round">
                                <i class="fa fa-check-square-o"></i> Enregistrer
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title" id="from-actions-center-dropdown">Modifier la licence de la societe</h4>
                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>

            </div>
            <div class="card-content">
                <div class="card-body">
                    <div class="card-text">


                    </div>
                    <form class="form" novalidate method="post" action="administration?q=${q}&action=model&model=souscriptionlicence&isNew=0" >
                        <div class="form-body">
                            <div class="row row_insert_day">

                                <div class="form-group col-md-6">
                                    <label for="mode">
                                        Societe
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" required id="mode" required class="select2-size-xs block form-control round" name="societe">
                                        <c:forEach items="${societes}" var="soc">
                                            <option value="${soc.getId()}">${soc.getNom()}</option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="mode">
                                        Gamme
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" required id="mode" required class="select2-size-xs block form-control round gamme_licence" name="type">
                                        <c:forEach items="${typelicences}" var="gamme">
                                            <option class="mode${gamme.getTestMode()}" value="${gamme.getId()}">${gamme.getType()}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>


                        </div>
                        <div class="form-actions">

                            <button type="submit" class="btn btn-primary round">
                                <i class="fa fa-check-square-o"></i> Enregistrer
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">

    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title" id="from-actions-center-dropdown">Modifier </h4>
                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>

            </div>
            <div class="card-content">
                <div class="card-body">
                    <div class="card-text">


                    </div>
                    <c:if test="${souscrirelicence == null}">
                        <table class="table table-dark  table-striped table-bordered  responsive reporting_table">

                            <thead>
                                <tr>
                                    <th>Societe</th>
                                    <th>Gamme</th>
                                    <th>Mode</th>
                                    <th>Nbre Utilisateur</th>
                                    <th>Date Souscription</th>
                                    <th>option</th>
                                </tr>
                            </thead>
                            <tbody id="">
                                <c:forEach items="${souscriptions}" var="sous">
                                    <tr>
                                        <td>${sous.getSociete().getNom()}</td>
                                        <td>${sous.getType().getType()}</td>
                                        <td>
                                            <c:if test="${sous.getType().getTestMode() == -1}">
                                                Test
                                            </c:if>
                                            <c:if test="${sous.getType().getTestMode() != -1}">
                                                Licence
                                            </c:if>
                                        </td>
                                        <td>${sous.getType().getInitUser()}</td>
                                        <td><f:formatDate value="${sous.getDateSous()}" type="BOTH" timeStyle="MEDIUM"></f:formatDate></td>
                                            <td>
                                                <button class="btn btn-primary round btn-sm">
                                                    <a data-toggle="tooltip" title="Modifier le nombre d'utilisateur" href="administration?q=${q}&action=model&model=souscriptionlicence&isNew=2&id=${sous.getId()}">  <i class="fa fa-edit"></i></a>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${souscrirelicence != null}">
                        <form class="form" novalidate method="post" action="administration?q=${q}&action=model&model=souscriptionlicence&isNew=1&souscription=${souscrirelicence.getId()}" >
                            <div class="form-body">
                                <div class="row">

                                    <div class="row row_insert_day">

                                        <div class="form-group col-md-6">
                                            <label for="mode">
                                                Societe
                                            </label>
                                            <select style="width: 100%"  required class="select2-size-xs block form-control round" name="societe">
                                                <option selected value="${souscrirelicence.getSociete().getId()}">${souscrirelicence.getSociete().getNom()}</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="mode">
                                                Gamme
                                            </label>
                                            <select style="width: 100%"  disabled class="select2-size-xs block form-control round" name="type">
                                                <c:forEach items="${typelicences}" var="gamme">
                                                    <option class="mode${gamme.getTestMode()}" value="${gamme.getId()}">${gamme.getType()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="nbr">
                                                Nbre utilisateur
                                                <span class="danger">*</span>
                                            </label>
                                            <input type="number" value="${souscrirelicence.getType().getInitUser()}" required class="form-control-sm form-control round" placeholder="max"
                                                   name="user" >
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="form-actions">

                                <button type="submit" class="btn btn-primary round">
                                    <i class="fa fa-check-square-o"></i> Enregistrer
                                </button>
                            </div>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
