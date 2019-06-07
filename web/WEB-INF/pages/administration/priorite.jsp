<%-- 
    Document   : priorite
    Created on : 17 oct. 2018, 18:29:39
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien54}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <div class="row">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Liste des Priorite</h4>
                                <div class="heading-elements ">
                                    <ul class="list-inline mb-0">
                                        <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                                        <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body card-dashboard">
                                    <p class="card-text">
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien51}">
                                                <button type="button" title="Ajouter une priorite" class="btn btn-float btn-outline-success btn-round"
                                                        data-toggle="modal"
                                                        data-target="#bootstrap">
                                                    <i class="fa fa-plus-circle"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien52}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=priorite&isNew=2"><i class="fa fa-edit"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien53}">
                                                <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=priorite&isNew=5"><i class="fa fa-trash"></i></button>
                                                </c:if>
                                        </div>
                                    </div>
                                    </p>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                    <c:if test="${sessionScope.root=='EH2S'}">
                                                    <th>Societe</th>
                                                    </c:if>
                                                <th>priorite</th>
                                                <th>Niveau</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${priorites}" var="pri">
                                                <tr>
                                                    <td><option selected  class="hidden">${pri.getId()}</option> </td>
                                                    <c:if test="${sessionScope.root=='EH2S'}">
                                                <td>${pri.getSociete().getNom()}</td>
                                            </c:if>
                                            <td>${pri.getName()}</td>
                                            <td>${pri.getNumber()}</td>
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
            <!--/ Restore & show all table -->
        </div>
    </div>
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien51 or not empty sessionScope.lien52}">
        <c:import url="/WEB-INF/composants/modal/addpriorite.jsp"/>
    </c:if>

</c:if>