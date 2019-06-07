<%-- 
    Document   : ventes_mensuelles
    Created on : 13 mars 2019, 13:53:29
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
                <h4 class="card-title">Ventes Mensuelles </h4>
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
                            <form class="form all_entity_categorie_form" novalidate method="post" action="jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=5&periode=5">
                                <div class="form-body">
                                    <div class="row">
                                        <div class="form-group col-lg-2 col-md-12 mb-2">
                                            <label for="firstName">
                                                Annee
                                            </label>
                                            <select style="width: 100%" class=" block form-control round all_year " id="" name="annee">
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-2 col-md-12 mb-2">
                                            <label for="personnel">Entite 
                                                <span class="danger">*</span></label>
                                            <select style="width: 100%" required id="who_entity_categorie" class="select2-size-xs block form-control round" name="who">
                                                <c:if test="${sessionScope.utilisateur.getTaffectzoneList() != null}">


                                                    <c:if test="${sessionScope.utilisateur.getTaffectzoneList().get(0).getRegion() != null}">
                                                        <option value="0">Region </option>
                                                    </c:if>
                                                    <c:if test="${sessionScope.utilisateur.getTaffectzoneList().get(0).getDistrict() != null}">
                                                        <option value="1">Villes </option>
                                                    </c:if>
                                                    <c:if test="${sessionScope.utilisateur.getTaffectzoneList().get(0).getSecteur() != null}">
                                                        <option value="2">Secteur </option>
                                                    </c:if>
                                                </c:if>
                                                <option value="3">Clients </option>
                                                <c:if test="${sessionScope.societe.getGesttourner()==1}">
                                                    <option value="4">Tounées </option>
                                                    <option value="5">Pré-vendeur </option>
                                                </c:if>
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-12 mb-2 all_entity">
                                            <label for="personnel">Region
                                                <span class="danger">*</span></label>
                                            <select style="width: 100%" required id="another_entity_categorie" class="select2-size-xs block form-control round">

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
                    <div id="doughnut_mensuelle" class="height-400 echart-container"></div>


                </div>
            </div>
        </div>
    </div>
</div>

