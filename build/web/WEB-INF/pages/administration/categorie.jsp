<%-- 
    Document   : categorie
    Created on : 26 nov. 2018, 14:02:00
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien112}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <div class="row">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Liste des Categorie</h4>
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
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien109}">
                                                <button type="button" title="Ajouter une categorie" class="btn btn-float btn-outline-success btn-round"
                                                        data-toggle="modal"
                                                        data-target="#bootstrap">
                                                    <i class="fa fa-plus-circle"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien110}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=CategorieArticle&isNew=2"><i class="fa fa-edit"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien111}">
                                                <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=CategorieArticle&isNew=5"><i class="fa fa-trash"></i></button>
                                                </c:if>
                                        </div>
                                    </div>
                                    </p>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>ID</th>
                                                    <c:if test="${sessionScope.root=='EH2S'}">
                                                    <th>Societe</th>
                                                    </c:if>
                                                <th>Categorie</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${categories}" var="src">
                                                <tr>
                                                    <td><option selected  class="hidden">${src.getId()}</option> </td>
                                            <td>${src.getId()} </td>
                                            <c:if test="${sessionScope.root=='EH2S'}">

                                                <td>${src.getSociete().getNom()}</td>
                                            </c:if>  

                                            <td>${src.getNom()}</td>

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
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien109 or not empty sessionScope.lien110}">
        <c:import url="/WEB-INF/composants/modal/addcategorie.jsp"/>
    </c:if>
</c:if>

