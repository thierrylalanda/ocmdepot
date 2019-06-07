<%-- 
    Document   : topClassement
    Created on : 13 mars 2019, 13:53:48
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row ">

    <div class="col-xl-12 col-lg-12">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title">Classement</h4>
                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                <div class="heading-elements">
                    <ul class="list-inline mb-0">
                        <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                        <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                        <li><a data-action="close"><i class="ft-x"></i></a></li>
                    </ul>
                </div>
            </div>
            <div class="card-content collapse show">

                <div class="card-body">
                    <div class="row">
                        <div class="col-xl-12 col-lg-12">
                            <form class="form form_top_entity" novalidate method="post" action="jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=5&periode=5">
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-md-2">
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
                                                    Entité
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%" id="top_entity" class="select2-size-xs block form-control round" name="is">
                                                    <option value="0" >Nationale</option>
                                                    <option value="1">Regionale</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-2 region_entity hidden">
                                            <div class="form-group">
                                                <label for="lastName">
                                                    Region
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%" id="statut" class="select2-size-xs block form-control round" name="region">

                                                    <c:forEach items="${regions}" var="sta">
                                                        <option value="${sta.getId()}"> ${sta.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-2 region_entity_region"  >
                                            <div class="form-group">
                                                <label for="lastName">
                                                    Entité
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%"class="select2-size-xs block form-control round who" name="who">
                                                    <option value="3" >Region</option>
                                                    <option value="5">Ville</option>
                                                    <option value="4">Secteur</option>
                                                    <option value="7">Client</option>
                                                    <option value="6">Pré-vendeur</option>
                                                    <option value="2">Articles</option>
                                                    <option value="1">Categories</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-2 region_entity hidden"  >
                                            <div class="form-group">
                                                <label for="lastName">
                                                    Entité
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%"  class="select2-size-xs block form-control round who" name="who">
                                                    <option value="5">Ville</option>
                                                    <option value="4">Secteur</option>
                                                    <option value="7">Client</option>
                                                    <option value="6">Pré-vendeur</option>
                                                    <option value="3">Articles</option>
                                                    <option value="1">Categories</option>

                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label for="lastName">
                                                    Top
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%" class="select2-size-xs  form-control round" name="top">
                                                    <option value="5">5</option>
                                                    <option value="10">10</option>
                                                    <option value="20">20</option>
                                                    <option value="50">50</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-1 col-md-12">

                                            <button style="margin-top: 25px" type="submit" class="btn btn-primary btn-sm round">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row ">

                        <div class="col-xl-12 col-lg-12">
                            <div id="top_entity_dashboard_graph" class="height-400 echart-container"></div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="card-footer">

                <div class="row ">
                    <div class="col-xl-8 col-lg-12">
                        <div class="top_vendeurs_table">
                            <table class="table mb-0 table_top_entity"></table>
                        </div>
                    </div>
                    <!--                    <div class="col-xl-8 col-lg-12">
                                            <div id="top_entity_dashboard_graph2" class="height-400 echart-container"></div>
                                        </div>-->
                </div>
            </div>
        </div>
    </div>
</div>
<!--<div class="row match-height">
    <div class="col-xl-12 col-lg-12">
        <div class="card">
            <div class="card-header border-0">
                <h4 class="card-title">Top classement des produits</h4>
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
                                            <label for="personnel">Catégorie
                                                <span class="danger">*</span></label>
                                            <select style="width: 100%" required id="region_reporting_client_tb" class="select2-size-xs block form-control round" name="entity">
                                                <option value="1">Tous </option>
                                                <option value="0">Personnel </option>
                                                <option value="1">Client </option>
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
                                    <th colspan="3" class="text-center text-white text-uppercase text-indice-performance bg-blue-grey"></th>
                                    <th class="text-center text-white text-uppercase  bg-blue-grey">
                                        <select>
                                            <option>5</option>
                                            <option>10</option>
                                            <option>50</option>
                                            <option>Tous</option>
                                        </select>
                                    </th>
                                </tr>
                            </thead>
                            <thead>
                                <tr>
                                    <th>Nom</th>
                                    <th>Quantite</th>
                                    <th>Montant</th>
                                    <th>%</th>
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
</div>-->
