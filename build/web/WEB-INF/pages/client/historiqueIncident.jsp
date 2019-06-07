<%-- 
    Document   : historiqueIncident
    Created on : 26 oct. 2018, 14:32:52
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="content-wrapper">
    <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2">
            <h3 class="content-header-title mb-0">Historique Incident</h3>
            <div class="row breadcrumbs-top">
                <div class="breadcrumb-wrapper col-12">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="ticketClient?q=client/nouvelIncident&action=jkdhfoldg458dgbjdg478962">Accueil</a>
                        </li>
                        <li class="breadcrumb-item active">Historique Incident
                        </li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">

    <div class="col-md-12">
        <!-- Restore & show all table -->
        <section id="restore">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Historique Incident</h4>
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
                                <form method="post" action="ticketClient?q=${q}&action=model&model=getClientsHistorique" class="" novalidate>


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
                                                    <select style="width: 100%"  id="statut" class="select2-size-xs block form-control round" name="statut">
                                                        <option value="">Tous</option>
                                                        <c:forEach items="${statuts}" var="sta">
                                                            <option  value="${sta.getCode()}"> ${sta.getNom()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label for="lastName">

                                                    </label>

                                                    <input style="margin-top: 25px" type="submit" class="btn btn-outline-primary  round btn-sm"
                                                           value="Rechercher">
                                                </div>
                                            </div>


                                        </div>
                                    </fieldset>

                                </form>
                                <div class="form-group">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->
                                        <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                data-toggle='tooltips' title="voir l'incident"
                                                name="id" action="ticketClient?q=${q}&action=model&model=saveTicket&isNew=2">
                                            <i class="fa fa-eye"></i></button>
                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element" 
                                                data-toggle='tooltips' title="Imprimer un incident"
                                                name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                    </div>
                                </div>
                                </p>
                                <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Num</th>
                                            <th>Client</th>
                                            <th>Object</th>
                                            <th>Responsable</th>
                                            <th>Priorite</th>
                                            <th>Statut</th>
                                            <th>Date creation</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${incidents}" var="inc">
                                            <c:if test="${inc.getState().getCode() != 501 && inc.getClientid().getId()== sessionScope.client.getId()}">
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
                                                    <c:set var="title" value="Plainte ${inc.getState().getNom()} il y'a ${inc.getDiffday()} jr(s)"></c:set>
                                                </c:if>
                                                <c:if test="${inc.getState().getCode()==404}">
                                                    <c:set var="imgsrc" value="assets/images/2.png"></c:set>
                                                    <c:set var="title" value="Plainte ${inc.getState().getNom()} il y'a ${inc.getDiffday()} jr(s)"></c:set>
                                                </c:if>
                                                <c:if test="${inc.getState().getCode()==502}">
                                                    <c:set var="imgsrc" value="assets/images/4.png"></c:set>
                                                    <c:set var="title" value="Plainte ${inc.getState().getNom()} il y'a ${inc.getDiffday()} jr(s)"></c:set>
                                                </c:if>
                                                <c:if test="${inc.getState().getCode()==401}">
                                                    <c:set var="imgsrc" value="assets/images/7.png"></c:set>
                                                    <c:set var="title" value="Plainte ${inc.getState().getNom()} il y'a ${inc.getDiffday()} jr(s)"></c:set>
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
<c:import url="/WEB-INF/composants/modal/voirIncident.jsp"/>
