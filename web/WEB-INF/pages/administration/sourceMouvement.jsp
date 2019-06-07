<%-- 
    Document   : sourceMouvement
    Created on : 18 avr. 2019, 09:42:07
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
<div class="row">

    <div class="col-md-12">
        <!-- Restore & show all table -->
        <section id="restore">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Liste des Sources Mouvement Caisse</h4>
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
                                        <button type="button" title="Ajouter une source" class="btn btn-float btn-outline-success btn-round"
                                                data-toggle="modal"
                                                data-target="#bootstrap">
                                            <i class="fa fa-plus-circle"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=sourceMouvement&isNew=2"><i class="fa fa-edit"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=sourceMouvement&isNew=5"><i class="fa fa-trash"></i></button>
                                    </div>
                                </div>
                                </p>
                                <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sourcesMouvement}" var="act">
                                            <tr>
                                                <td><option selected  class="hidden">${act.getId()}</option> </td>

                                        <td>${act.getNom()}</td>
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
<c:import url="/WEB-INF/composants/modal/sourceMouvement.jsp"/>
