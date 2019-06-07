<%-- 
    Document   : district
    Created on : 25 oct. 2018, 10:10:17
    Author     : eh2s001
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien70}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

    <div class="row">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Reporting District</h4>
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
                                                <div class="form-group col-lg-3 col-md-12 mb-2">
                                                    <label for="personnel">Region
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="region_reporting_district" class="select2-size-xs block form-control round" name="personnel">
                                                        <option > </option>
                                                        <c:forEach items="${regions}" var="per">
                                                            <option value="${per.getId()}"> ${per.getName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-3 col-md-12 mb-2">
                                                    <label for="personnel">District
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="district_reporting" class="select2-size-xs block form-control round" name="personnel">

                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-2 col-md-12">

                                                    <button style="margin-top: 25px" type="submit" class="btn btn-primary btn-sm round">
                                                        <i class="fa fa-check-square-o"></i> Enregistrer
                                                    </button>
                                                </div>
                                            </div>

                                        </div>
                                    </form>
                                    </p>
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->

                                            <button type="button" class="btn btn-float btn-outline-dark btn-round" 
                                                    data-toggle='tooltips' title="Imprimer tous les incidents"
                                                    name="id" onclick="PrintAlltable()"><i class="fa fa-print"></i></button>
                                        </div>
                                    </div>
                                    <c:if test="${incidentsEntity.size()!=0}">
                                        <div class="text-center bg-dark title_table" style="color:white">
                                            ${incidentsEntity[0].getTitre()}
                                        </div>
                                        <br>
                                    </c:if>
                                    <table class="table table-dark table-striped table-bordered  responsive reporting_table">
                                        <thead>

                                            <tr>

                                                <th>District</th>
                                                <th>Qtes plaintes reçues</th>
                                                <th>Reponses dans les delais</th>
                                                <th>Reponses Hors delais</th>
                                                <th>Total Reponses</th>
                                                <th>%plaintes traité dans les delais</th>
                                                <th>%Total plaintes traité</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${incidentsEntity}" var="inc">
                                                <tr>

                                                    <td>${inc.getDistrict().getName()}</td>    
                                                    <td>${inc.getTotalPlainteRecus()}</td>
                                                    <td>${inc.getPlainteDansDelais()}</td>
                                                    <td>${inc.getPlainteHorsDelais()}</td>
                                                    <td>${inc.getTotalPlainteTraiter()}</td>
                                                    <td>${inc.getPourcentagePlainteDansDelais()}%</td>
                                                    <td>${inc.getPourcentagePlainteTraiter()}%</td>
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
</c:if>