<%-- 
    Document   : fermetureIncident
    Created on : 18 oct. 2018, 13:32:14
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien61}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

    <div class="row">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Fermeture Incident</h4>
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
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien91}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir l'incident"
                                                        name="id" action="ticket?q=${q}&action=model&model=saveTicket&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>

                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien63}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" 
                                                        data-toggle='tooltips' title="fermer l'incident"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-book"></i>
                                                </button>
                                            </c:if>
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien94}">
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element" 
                                                        data-toggle='tooltips' title="Imprimer un incident"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i>
                                                </button>
                                            </c:if>
                                        </div>
                                    </div>
                                    </p>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th class="datec" pos="1">Num</th>
                                                <th>Client</th>
                                                <th>Object</th>
                                                <th>Responsable</th>
                                                <th>Priorite</th>
                                                <th>Statut</th>
                                                <th>Date creation</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${ticketFermeture}" var="inc">
                                                <c:if test="${inc.getIsaffect() == 1}">
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

    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien63}">
        <c:import url="/WEB-INF/composants/modal/fermerIncident.jsp"/>
    </c:if>
</c:if>