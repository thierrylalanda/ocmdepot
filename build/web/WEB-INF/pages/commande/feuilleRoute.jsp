<%-- 
    Document   : reporting
    Created on : 28 nov. 2018, 16:07:57
    Author     : messi01
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien104}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

    <div class="row">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Feuille de route</h4>
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
                                    <form class="form feuille_route" novalidate method="post" action="gxwzy14iyf?q=${q}&action=model&model=TicketmodelByPeriode&isNew=${isNew}&iq=${iq}&periode=2">
                                        <div class="form-body">
                                            <div class="row">
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <label for="periode">
                                                            Periode
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <div class='input-group input-group-xs'>
                                                            <input id="periode" type='text' class="form-control-xs form-control localeRange" name="interval" />
                                                            <div class="input-group-append">
                                                                <span class="input-group-text">
                                                                    <span class="fa fa-calendar"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="articles_categorie">Statut
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="articles_categorie" class="select2-size-xs block form-control round" name="etat">
                                                        <c:forEach  items="${statutCommandes}"  var="etat">
                                                            <c:if test="${etat.getCode() == 201 or etat.getCode() == 200 or etat.getCode() == 501}">
                                                                <option value="${etat.getCode()}"> ${etat.getNom()}</option>
                                                            </c:if>
                                                        </c:forEach>

                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="client">CLient</label>
                                                        <select style="width: 100%" multiple="multiple" id="client" class="select2-size-xs block form-control round" name="client">
                                                            <c:forEach  items="${clients}"  var="cl">
                                                                <option value="${cl.getId()}"> ${cl.getNom()}</option>
                                                            </c:forEach>

                                                    </select> 
                                                </div>
                                                <div class="form-group col-lg-2 col-md-12" >

                                                    <button style="margin-top: 25px" type="submit" class="btn btn-primary btn-sm round">
                                                        <i class="fa fa-download"></i> télécharger
                                                    </button>
                                                </div>
                                            </div>

                                        </div>
                                    </form>

                                    </p>
                                    <div class="form-group ">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->

                                            <button type="button" class="btn btn-float btn-outline-dark btn-round print_feuille_route hidden" 
                                                    data-toggle='tooltips' title="Imprimer la feuille de route au format PDF"
                                                    name="id"><i class="fa fa-print"></i></button>
                                            <button type="button" class="btn btn-float btn-outline-success btn-round print_feuille_route_excel hidden" 
                                                    data-toggle='tooltips' title="Imprimer la feuille de route au format Excel"
                                                    name="id"><i class="fa fa-file-excel-o"></i></button>
                                        </div>
                                    </div>
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

