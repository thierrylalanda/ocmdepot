<%-- 
    Document   : fournisseur
    Created on : 15 avr. 2019, 08:36:05
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<c:if test="${sessionScope.societe.getGestfournisseur()== 1 and (1==1)}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <div class="row">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Gestion des fournisseurs </h4>
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
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <p class="card-text">
                                            <div class="form-group">
                                                <div class="btn-group" role="group" aria-label="Basic example">
                                                    <!-- Outline Round Floating button -->
                                                    <button type="button" title="Ajouter un fournisseur" class="btn btn-float btn-outline-success btn-round"
                                                            data-toggle="modal"
                                                            data-target="#bootstrap">
                                                        <i class="fa fa-plus-circle"></i></button>
                                                    <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=Fournisseur&isNew=2"><i class="fa fa-edit"></i></button>
                                                    <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=Fournisseur&isNew=5"><i class="fa fa-trash"></i></button>
                                                    <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                            data-toggle='tooltips' title="Imprimer"
                                                            name="id"><i class="fa fa-print"></i></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </p><br>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Code</th>
                                                <th>Nom</th>
                                                <th>E-Mail</th>
                                                <th>Tel</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${fournisseurs != null}">
                                                <c:forEach items="${fournisseurs}" var="four">
                                                    <tr>
                                                        <td><option selected  class="hidden">${four.getId()}</option> </td>
                                                <td>${four.getCode()}</td>
                                                <td>${four.getNom()}</td>
                                                <td>${four.getMail()}</td>
                                                <td>${four.getTelephone()}</td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
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
    <c:import url="/WEB-INF/composants/modal/addfournisseur.jsp"/>
</c:if>



