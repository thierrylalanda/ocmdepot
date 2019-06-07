<%-- 
    Document   : reporting
    Created on : 24 oct. 2018, 14:02:06
    Author     : messi
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
                            <h4 class="card-title">Reporting</h4>
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
                                <form class="form" novalidate method="post" action="gxwzy14iyf?q=${q}&action=model&model=TicketmodelByPeriode&isNew=${isNew}&iq=${iq}&periode=2">
                                    <div class="form-body">
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
                                            <c:if test="${iq=='region'}">
                                                <div class="form-group col-lg-3 col-md-12 mb-2">
                                                    <label for="personnel">Region
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="personnel" class="select2-size-xs block form-control round" name="entity">
                                                        <c:forEach items="${regions}" var="reg">
                                                            <option value="${reg.getId()}"> ${reg.getName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:if>
                                            <c:if test="${iq=='personnel'}">
                                                <div class="form-group col-lg-3 col-md-12 mb-2">
                                                    <label for="personnel">Responsable
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="personnel" class="select2-size-xs block form-control round" name="entity">
                                                        <c:forEach items="${users}" var="us">
                                                            <option value="${us.getId()}"> ${us.getFirstname()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:if>
                                            <c:if test="${iq=='rubrique'}">
                                                <div class="form-group col-lg-3 col-md-12 mb-2">
                                                    <label for="personnel">Rubrique
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="personnel" class="select2-size-xs block form-control round" name="entity">
                                                        <c:forEach items="${rubriques}" var="rub">
                                                            <option value="${rub.getId()}"> ${rub.getName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:if>
                                            <c:if test="${iq=='secteur'}">
                                                <div class="form-group col-lg-3 col-md-12 mb-2">
                                                    <label for="personnel">Secteur
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="personnel" class="select2-size-xs block form-control round" name="entity">
                                                        <c:forEach items="${secteurs}" var="sec">
                                                            <option value="${sec.getId()}"> ${sec.getName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:if>
                                            <c:if test="${iq=='district'}">
                                                <div class="form-group col-lg-3 col-md-12 mb-2">
                                                    <label for="personnel">District
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="personnel" class="select2-size-xs block form-control round" name="entity">
                                                        <c:forEach items="${districts}" var="dis">
                                                            <option value="${dis.getId()}"> ${dis.getName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:if>
                                            <c:if test="${iq=='source'}">
                                                <div class="form-group col-lg-3 col-md-12 mb-2">
                                                    <label for="personnel">Source
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="personnel" class="select2-size-xs block form-control round" name="entity">
                                                        <c:forEach items="${sources}" var="src">
                                                            <option value="${src.getId()}"> ${src.getName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:if>
                                            <c:if test="${iq=='client'}">
                                                <div class="form-group col-lg-3 col-md-12 mb-2">
                                                    <label for="personnel">Client
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="personnel" class="select2-size-xs block form-control round" name="entity">
                                                        <c:forEach items="${clients}" var="per">
                                                            <option value="${per.getId()}"> ${per.getNom()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:if>
                                            <div class="form-group col-lg-2 col-md-12">

                                                <button style="margin-top: 25px" type="submit" class="btn btn-primary btn-sm round">
                                                    <i class="fa fa-search"></i> Rechercher
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                </form>
                                </p>
                                <table class="table table-dark table-striped table-bordered responsive dataex-select-checkbox">
                                    <thead>
                                        <tr>
                                            <th>N°</th>
                                            <th>Region</th>
                                            <th>Client</th>
                                            <th>Responsable</th>
                                            <th>Rubrique</th>
                                            <th>Date Creation</th>
                                            <th>Object</th>
                                            <th>Statut</th>
                                            <th>Date Resolution</th>
                                            <th>Date Cloture</th>
                                            <th>Dans les delais</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${incidentsEntity}" var="inc">
                                            <tr>
                                                <td>${inc.getId()}</td>
                                                <td>${inc.getClientid().getSecteurid().getDistrictid().getRegionid().getName()}</td>  
                                                <td>${inc.getClientid().getNom()}</td>    
                                                <td>${inc.getUserplainteList()[0].getIduser().getFirstname()}</td>
                                                <td>${inc.getSrubriqueid().getRubriqueid().getName()}</td>
                                                <td><f:formatDate value="${inc.getDateCreate()}" type="BOTH" timeStyle="MEDIUM"/></td>
                                                <td>${inc.getTitle()}</td>
                                                <td>${inc.getState().getNom()}</td>
                                                <td><f:formatDate value="${inc.getDateHope()}" type="BOTH" timeStyle="MEDIUM"/></td>
                                                <td><f:formatDate value="${inc.getDateFer()}" type="BOTH" timeStyle="MEDIUM"/></td>
                                                <td>
                                                    <c:if test="${inc.getIsInDelais()==0}">
                                                        Oui
                                                    </c:if>
                                                    <c:if test="${inc.getIsInDelais()==1}">
                                                        Nom
                                                    </c:if>
                                                    <c:if test="${inc.getIsInDelais()== -1}">
                                                        Non resolu
                                                    </c:if>
                                                </td>
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

