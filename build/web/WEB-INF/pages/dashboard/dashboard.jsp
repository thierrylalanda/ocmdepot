<%-- 
    Document   : dashboard
    Created on : 18 oct. 2018, 13:37:44
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--stats-->
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien72}">
    <div class="row">
        <div class="col-xl-3 col-lg-6 col-12">
            <div class="card">
                <div class="card-content">
                    <div class="card-body">
                        <div class="media">
                            <div class="media-body text-left w-100">
                                <h3 class="success">${Math.round((ticketresolut.size()/totalticket.size())*100)}%</h3>
                                <span>Plaintes Résolues</span>
                            </div>
                            <div class="media-right media-middle">
                                <i class="fa fa-plus-circle success font-large-2 float-right"></i>
                            </div>
                        </div>
                        <div class="progress progress-sm mt-1 mb-0">
                            <div class="progress-bar bg-success" role="progressbar" style="width: ${Math.round((ticketresolut.size()/totalticket.size())*100)}%" aria-valuenow="25"
                                 aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-3 col-lg-6 col-12">
            <div class="card">
                <div class="card-content">
                    <div class="card-body">
                        <div class="media">
                            <div class="media-body text-left w-100">
                                <h3 class="danger">${Math.round((ticketNonResolut.size()/totalticket.size())*100)}%</h3>
                                <span>Plaintes non Resolues</span>
                            </div>
                            <div class="media-right media-middle">
                                <i class="fa fa-minus-circle danger font-large-2 float-right"></i>
                            </div>
                        </div>
                        <div class="progress progress-sm mt-1 mb-0">
                            <div class="progress-bar bg-danger" role="progressbar" style="width: ${Math.round((ticketNonResolut.size()/totalticket.size())*100)}%" aria-valuenow="25"
                                 aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xl-3 col-lg-6 col-12">
            <div class="card">
                <div class="card-content">
                    <div class="card-body">
                        <div class="media">
                            <div class="media-body text-left w-100">
                                <h3 class="warning"> ${Math.round((ticketInsolvable.size()/totalticket.size())*100)}%</h3>
                                <span>Plaintes Insolvables</span>
                            </div>
                            <div class="media-right media-middle">
                                <i class="fa fa-trash warning font-large-2 float-right"></i>
                            </div>
                        </div>
                        <div class="progress progress-sm mt-1 mb-0">
                            <div class="progress-bar bg-warning" role="progressbar" style="width: ${Math.round((ticketInsolvable.size()/totalticket.size())*100)}%" aria-valuenow="25"
                                 aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien73}">
    <!-- Basic Area Chart -->
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">Etat Annuel</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                    <div class="heading-elements">
                        <ul class="list-inline mb-0">
                            <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                            <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                            <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card-content collapse show">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl-12 col-lg-12">
                                <form class="form basic_form_year" novalidate  method="post" action="jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=0&periode=1">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="form-group col-lg-2 col-md-12 mb-2">
                                                <label for="personnel">Annee
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required  class="all_year select2-size-xs block form-control round" name="annee">

                                                </select>
                                            </div>
                                            <div class="form-group col-lg-2 col-md-12 mb-2">
                                                <label for="personnel">Region
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required id="region_reporting_client_tb" class="select2-size-xs block form-control round" name="id">
                                                    <option > </option>
                                                    <c:forEach items="${regions}" var="per">
                                                        <option value="${per.getId()}"> ${per.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-lg-2 col-md-12">

                                                <button style="margin-top: 25px" type="submit" class="btn btn-primary btn-sm round">
                                                    <i class="fa fa-search-minus"></i>
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                        <div id="basic-area" class="height-600 echart-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ Analytics spakline & chartjs  -->
</c:if>
<!-- Bounce Rate & List -->
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien74}">
    <div class="row match-height">

        <div class="col-xl-12 col-lg-12">
            <div class="card">
                <div class="card-header border-0">
                    <h4 class="card-title">Indice performance</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                    <div class="heading-elements">
                        <ul class="list-inline mb-0">
                            <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                            <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                            <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card-content">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl-12 col-lg-12">
                                <form class="form indice_form" novalidate method="post" action="jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=3&periode=5">
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
                                            <div class="form-group col-lg-2 col-md-12 mb-2">
                                                <label for="personnel">Entite
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required id="region_reporting_client_tb" class="select2-size-xs block form-control round" name="entity">
                                                    <option value="0">Personnel </option>
                                                    <option value="1">Client </option>
                                                </select>
                                            </div>
                                            <div class="form-group col-lg-2 col-md-12 mb-2">
                                                <label for="personnel">Region
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required id="region_reporting_client_tb" class="select2-size-xs block form-control round" name="id">
                                                    <option > </option>
                                                    <c:forEach items="${regions}" var="per">
                                                        <option value="${per.getId()}"> ${per.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-lg-2 col-md-12">

                                                <button style="margin-top: 25px" type="submit" class="btn btn-primary btn-sm round">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                        <div id="audience-list-scroll" class="table-responsive height-400 position-relative">
                            <table class="table mb-0">
                                <thead>
                                    <tr>
                                        <th colspan="4" class="text-center text-white text-uppercase text-indice-performance bg-blue-grey"></th>
                                    </tr>
                                </thead>
                                <thead>
                                    <tr>
                                        <th>Nom</th>
                                        <th>Nbre de plainte</th>
                                        <th>Traité</th>
                                        <th>% pourcentage</th>
                                    </tr>
                                </thead>
                                <tbody id="body_dashboard_table">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<!-- Bounce Rate & List -->
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien75}">
    <div class="card">
        <div class="card-header">

        </div>
        <div class="card-content collapse show">
            <div class="card-body">
                <div class="row">
                    <div class="col-xl-12 col-lg-12">
                        <form class="form rubrique_form" novalidate method="post" action="jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=4&periode=5">
                            <div class="form-body">
                                <div class="row">
                                    <div class="col-md-4"></div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="firstName">
                                                Periode
                                                <span class="danger">*</span>
                                            </label>
                                            <div class='input-group input-group-xs'>
                                                <input type='text' class="form-control-sm form-control localeRange" name="interval" />
                                                <div class="input-group-append">
                                                    <span class="input-group-text">
                                                        <span class="fa fa-calendar"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-2 col-md-12">

                                        <button style="margin-top: 35px" type="submit" class="btn btn-primary btn-sm round">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>
                <div class="row match-height">
                    <div class="col-xl-12 col-lg-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Plaintes Resolu par rubrique</h4>
                                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body">

                                    <div id="basic-pie" class="height-400 echart-container"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-12 col-lg-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Plaintes Non resolu par rubrique</h4>
                                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body">
                                    <div id="plainte_traite_rubrique" class="height-400 echart-container"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</c:if>
<!-- Column + Pie Chart -->
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien76}">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">District et Secteur</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                    <div class="heading-elements">
                        <ul class="list-inline mb-0">
                            <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                            <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                            <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card-content collapse show">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl-12 col-lg-12">
                                <form class="form entity_form" novalidate method="post" action="jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=5&periode=5">
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
                                            <div class="form-group col-lg-2 col-md-12 mb-2">
                                                <label for="personnel">Entite
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required id="region_reporting_client_tb" class="select2-size-xs block form-control round" name="entity">
                                                    <option value="0">Secteur </option>
                                                    <option value="1">District </option>
                                                </select>
                                            </div>
                                            <div class="form-group col-lg-2 col-md-12 mb-2">
                                                <label for="personnel">Region
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required id="region_reporting_client_tb" class="select2-size-xs block form-control round" name="id">
                                                    <option > </option>
                                                    <c:forEach items="${regions}" var="per">
                                                        <option value="${per.getId()}"> ${per.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-lg-2 col-md-12">

                                                <button style="margin-top: 25px" type="submit" class="btn btn-primary btn-sm round">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                        <div id="column-pie" class="height-600 echart-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien77}">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">Statut</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                    <div class="heading-elements">
                        <ul class="list-inline mb-0">
                            <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                            <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                            <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card-content collapse show">
                    <div class="card-body">
                        <div class="row">

                            <div class="col-lg-12 col-md-12">
                                <div id="column-chart" class="height-400 echart-container"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien78}">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">Source</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                    <div class="heading-elements">
                        <ul class="list-inline mb-0">
                            <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                            <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                            <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card-content collapse show">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-lg-12 col-md-12">
                                <div id="pie-chart" class="height-400 echart-container"></div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>