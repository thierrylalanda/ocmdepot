<%-- 
    Document   : InsolvableIncident
    Created on : 27 oct. 2018, 08:12:02
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien89}">
    <%
        Date now = new Date();
        request.setAttribute("day", now);
    %>
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

    <div class="row">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Incident</h4>
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
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien91}">
                                                <button type="button" title="voir l'incident" class="btn btn-float btn-outline-success btn-round btn-link-update"

                                                        name="id" action="administration?q=${q}&action=model&model=saveTicket&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>
                                                
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien94}">
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element" 
                                                        data-toggle='tooltips' title="Imprimer un incident"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                </c:if>
                                        </div>
                                    </div>
                                    </p>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>N°</th>
                                                <th>Societe</th>
                                                <th>Client</th>
                                                <th>Object</th>
                                                <th>Responsable</th>
                                                <th>Priorite</th>
                                                <th>Statut</th>
                                                <th>Date creation</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${incidentsadmin}" var="inc">
                                                <c:if test="${inc.getState().getCode()==401}">


                                                    <c:set var="diffday" value="${(day.getTime()-inc.getDateHope().getTime())/(1000 * 60 * 60 * 24)}"></c:set>
                                                    <c:set var="diffdaycreate" value="${(day.getTime()-inc.getDateCreate().getTime())/(1000 * 60 * 60 * 24)}"></c:set>
                                                    <c:if test="${inc.getState().getCode()==200}">
                                                        <c:set var="imgsrc" value="assets/images/3.png"></c:set>
                                                        <c:if test="${inc.getIsInDelais()==0}">
                                                            <c:set var="title" value="Plainte resolue dans les delais ${inc.getDiffday()} jr(s) après son ouverture"></c:set>
                                                        </c:if>
                                                        <c:if test="${inc.getIsInDelais()==1}">
                                                            <c:set var="title" value="Plainte resolue hors delais ${inc.getDiffday()} jr(s) après son ouverture"></c:set>
                                                        </c:if>

                                                    </c:if>
                                                    <c:if test="${inc.getState().getCode()==501}">
                                                        <c:set var="imgsrc" value="assets/images/1.png"></c:set>
                                                        <c:set var="title" value="Plainte ${inc.getState().getNom()} il y'a ${Math.round(diffdaycreate)} jr(s)"></c:set>
                                                    </c:if>
                                                    <c:if test="${inc.getState().getCode()==404}">
                                                        <c:set var="imgsrc" value="assets/images/2.png"></c:set>
                                                        <c:set var="title" value="Plainte ${inc.getState().getNom()} il y'a ${Math.round(diffday)} jr(s)"></c:set>
                                                    </c:if>
                                                    <c:if test="${inc.getState().getCode()==502}">
                                                        <c:set var="imgsrc" value="assets/images/4.png"></c:set>
                                                        <c:set var="title" value="Plainte ${inc.getState().getNom()} il y'a ${Math.round(diffday)} jr(s)"></c:set>
                                                    </c:if>
                                                    <c:if test="${inc.getState().getCode()==401}">
                                                        <c:set var="imgsrc" value="assets/images/7.png"></c:set>
                                                        <c:set var="title" value="Plainte ${inc.getState().getNom()} il y'a ${Math.round(diffday)} jr(s)"></c:set>
                                                    </c:if>

                                                    <tr >
                                                        <td><option selected  class="hidden">${inc.getId()}</option> </td>
                                                <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${inc.getId()}</td>    
                                                <td>${inc.getClientid().getSociete().getNom()}</td>
                                                <td>${inc.getClientid().getNom()}</td>    
                                                <td>${inc.getTitle()}</td>
                                                <td>${inc.getUserplainteList()[0].getIduser().getFirstname()}</td>
                                                <td>${inc.getPrioriteid().getName()}</td>
                                                <td data-toggle="tooltip" title="Plainte ${inc.getState().getNom()}"><img src="${imgsrc}" /></td>
                                                <td><f:formatDate value="${inc.getDateCreate()}" type="BOTH" dateStyle="MEDIUM"/></td>
                                                </tr>
                                            </c:if>
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
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien91}">
        <c:import url="/WEB-INF/composants/modal/voirIncident.jsp"/>
    </c:if>
</c:if>