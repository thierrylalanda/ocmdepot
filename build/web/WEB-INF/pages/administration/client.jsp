<%-- 
    Document   : client
    Created on : 17 oct. 2018, 18:07:10
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien38}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <div class="row">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Liste des Clients</h4>
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
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien35}">
                                                <button type="button" title="Ajouter un client" class="btn btn-float btn-outline-success btn-round"
                                                        data-toggle="modal"
                                                        data-target="#bootstrap">
                                                    <i class="fa fa-user-circle"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien36}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=client&isNew=2"><i class="fa fa-edit"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien37}">
                                                <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=client&isNew=5"><i class="fa fa-trash"></i></button>
                                                </c:if>
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                        data-toggle='tooltips' title="Imprimer"
                                                        name="id"><i class="fa fa-print"></i></button>
                                        </div>
                                    </div>
                                    </p>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                    <c:if test="${sessionScope.root=='EH2S'}">
                                                    <th>societe</th>
                                                    </c:if>
                                                <th>Code</th>
                                                <th>Nom</th>
                                                <th>Email</th>
                                                <th>Adresse</th>
                                                <th>Telephone</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${clients}" var="cl">
                                                <tr>
                                                    <td><option selected  class="hidden">${cl.getId()}</option> </td>
                                                    <c:if test="${sessionScope.root=='EH2S'}">
                                                <td>${cl.getSociete().getNom()}</td>
                                            </c:if>    
                                            <td>${cl.getCodeclient()}</td>
                                            <td>${cl.getNom()}</td>
                                            <td>${cl.getMail()}</td>
                                            <td>${cl.getAdresse()}</td>
                                            <td>${cl.getTelephone()}</td>
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
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien35 or not empty sessionScope.lien36}">
        <c:import url="/WEB-INF/composants/modal/addclient.jsp"/>
    </c:if>
</c:if>
