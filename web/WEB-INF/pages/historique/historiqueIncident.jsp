<%-- 
    Document   : historiqueIncident
    Created on : 18 oct. 2018, 13:35:30
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien64}">
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
                                <h4 class="card-title">Historique Plaintes</h4>
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
                                    <form method="post" action="gxwzy14iyf?q=${q}&action=model&model=TicketmodelByPeriode&isNew=8" class="" novalidate>


                                        <fieldset>
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="firstName">
                                                            Periode
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
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="lastName">
                                                            Statut
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" id="statut" class="select2-size-xs block form-control round" name="statut">
                                                            <option value="">Tous</option>
                                                            <c:forEach items="${statuts}" var="sta">
                                                                <option value="${sta.getCode()}"> ${sta.getNom()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="lastName">

                                                        </label>

                                                        <input style="margin-top: 25px" type="submit" class="btn btn-outline-primary  round btn-sm"
                                                               value="Enregistrer">
                                                    </div>
                                                </div>


                                            </div>
                                        </fieldset>

                                    </form>
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien91}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir l'incident"
                                                        name="id" action="ticket?q=${q}&action=model&model=saveTicket&isNew=2">
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
                                                <th class="datec" pos="1">N°</th>
                                                <th>Client</th>
                                                <th>Object</th>
                                                <th>Responsable</th>
                                                <th>Priorite</th>
                                                <th>Statut</th>
                                                <th>Date creation</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${allincidents}" var="inc">
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
                                            <td>${inc.getDatecc()}</td>
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
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien91}">
        <c:import url="/WEB-INF/composants/modal/voirIncident.jsp"/>
    </c:if>
</c:if>