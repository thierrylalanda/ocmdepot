<%-- 
    Document   : commande
    Created on : 9 janv. 2019, 09:19:01
    Author     : Administrateur
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
                    <div class="media align-items-stretch">
                        <div class="p-2 text-center bg-success">
                            <i class="icon-check font-large-2 white"></i>
                        </div>
                        <div class="py-1 px-2 media-body">
                            <h5 class="success">Livrées</h5>
                            <h5 class="text-bold-400">${commandesAcheve.size()}</h5>
                            <div class="progress mt-1 mb-0" style="height: 12px;">
                                <div class="progress-bar bg-success" role="progressbar" style="width: ${commandesAcheve.size()}%" aria-valuenow="${commandesAcheve.size()}"
                                     aria-valuemin="0" aria-valuemax="100">
                                    ${Math.round((commandesAcheve.size()/commandesAll.size())*100)}%
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-3 col-lg-6 col-12">
            <div class="card">
                <div class="card-content">
                    <div class="media align-items-stretch">
                        <div class="p-2 text-center bg-primary">
                            <i class="icon-basket-loaded font-large-2 white"></i>
                        </div>
                        <div class="py-1 px-2 media-body">
                            <h5 class="primary">En Livraison</h5>
                            <h5 class="text-bold-400">${commandesLivraison.size()}</h5>
                            <div class="progress mt-1 mb-0" style="height: 12px;">
                                <div class="progress-bar bg-primary" role="progressbar" style="width: ${commandesLivraison.size()}%" aria-valuenow="${commandesLivraison.size()}"
                                     aria-valuemin="0" aria-valuemax="100">
                                    ${Math.round((commandesLivraison.size()/commandesAll.size())*100)}%
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xl-3 col-lg-6 col-12">
            <div class="card">
                <div class="card-content">
                    <div class="media align-items-stretch">
                        <div class="p-2 text-center bg-warning">
                            <i class="icon-wallet font-large-2 white"></i>
                        </div>
                        <div class="py-1 px-2 media-body">
                            <h5 class="warning">Traitement</h5>
                            <h5 class="text-bold-400">${commandesEncours.size()+commandesValideClient.size()}</h5>
                            <div class="progress mt-1 mb-0" style="height: 12px;">
                                <div class="progress-bar bg-warning" role="progressbar" style="width: ${commandesEncours.size()+commandesValideClient.size()}%" aria-valuenow="${commandesEncours.size()+commandesValideClient.size()}"
                                     aria-valuemin="0" aria-valuemax="100">
                                    ${Math.round(((commandesEncours.size()+commandesValideClient.size())/commandesAll.size())*100)}%
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-3 col-lg-6 col-12">
            <div class="card">
                <div class="card-content">
                    <div class="media align-items-stretch">
                        <div class="p-2 text-center bg-danger">
                            <i class="icon-plus font-large-2 white"></i>
                        </div>
                        <div class="py-1 px-2 media-body">
                            <h5 class="danger">Nouvelle</h5>
                            <h5 class="text-bold-400">${commandesNew.size()}</h5>
                            <div class="progress mt-1 mb-0" style="height: 12px;">
                                <div class="progress-bar bg-danger" role="progressbar" style="width: ${commandesNew.size()}%" aria-valuenow="${commandesNew.size()}"
                                     aria-valuemin="0" aria-valuemax="100">
                                    ${Math.round((commandesNew.size()/commandesAll.size())*100)}%
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<!--<div class="row">
    <div class="col-xl-12 col-lg-12">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">Tableau de bord des ventes </h4>
                </div>
                <div class="card-content">
                  <div class="card-body">
                    <ul class="nav nav-tabs nav-top-border no-hover-bg">
                      <li class="nav-item">
                        <a class="nav-link active" id="homeIcon-tab1" data-toggle="tab" href="#ventes_annuelles" aria-controls="homeIcon1"
                        aria-expanded="true"><i class="fa fa-align-justify"></i>Annuel</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link " id="profileIcon-tab1" data-toggle="tab" href="#ventes_mensuelles"
                        aria-controls="profileIcon1" aria-expanded="false"><i class="fa fa-header"></i>Mensuel</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" id="aboutIcon1-tab" data-toggle="tab" href="#top_classement" aria-controls="aboutIcon1"
                        aria-expanded="false"><i class="fa fa-send-o"></i> Top Classement</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" id="aboutIcon1-tab" data-toggle="tab" href="#autres" aria-controls="aboutIcon1"
                        aria-expanded="false"><i class="fa fa-check-circle"></i>Autres</a>
                      </li>
                    </ul>
                    <div class="tab-content px-1 pt-1">
                      <div role="tabpanel" class="tab-pane active in" id="ventes_annuelles" aria-labelledby="homeIcon-tab1"
                      aria-expanded="true">
                         
                      </div>
                      <div class="tab-pane " id="ventes_mensuelles" role="tabpanel" aria-labelledby="profileIcon-tab1"
                      aria-expanded="false">
                     
                      </div>
                      <div class="tab-pane" id="top_classement" role="tabpanel" aria-labelledby="aboutIcon1-tab"
                      aria-expanded="false">
                       
                      </div>
                      <div class="tab-pane" id="autres" role="tabpanel" aria-labelledby="aboutIcon1-tab"
                      aria-expanded="false">
                       
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
</div>-->

<c:import url="/WEB-INF/pages/dashboard/ventesAnnuelles.jsp"/>
<%--<c:import url = "/WEB-INF/pages/dashboard/ventesMensuelles.jsp" /> --%>
<c:import url="/WEB-INF/pages/dashboard/topClassement.jsp"/>
<%--<c:import url="/WEB-INF/pages/dashboard/autres.jsp"/>--%>
<!-- <div class="row">
             Basic Gauge Chart 
            <div class="col-md-4 col-sm-12">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">Ventes Mois passé</h4>
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
                    <div id="basic-gauge" class="height-400 echart-container"></div>
                  </div>
                </div>
              </div>
            </div>
             Customized Gauge Chart 
            <div class="col-md-8 col-sm-12">
              <div class="card">
                <div class="card-header">
                  <h4 class="card-title">Ventes du mois</h4>
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
                    <div id="customized-gauge" class="height-400 echart-container"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
-->