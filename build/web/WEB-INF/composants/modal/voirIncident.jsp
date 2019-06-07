<%-- 
    Document   : voirIncident
    Created on : 23 oct. 2018, 10:37:07
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${incident != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&incident=${incident.getId()}"></c:set>
</c:if>
<c:if test="${incident == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${incident != null && traiter==null && ferme==null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ticket N° ${incident.getId()}  </h3>
                <c:if test="${secteur == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <button  class="btn t btn-outline-dark btn-sm round pull-right" 
                         data-toggle='tooltips' title="Imprimer la fiche de cette incident"
                         onclick="getDataToPrint(${incident.getId()})"><i class="fa fa-print"></i></button>
                <form method="post" action="administration?q=${q}&action=model&model=secteur&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos Ticket
                    </h6>

                    <fieldset>
                        <div class="row">
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Client</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Nom :</strong>
                                                ${incident.getClientid().getNom()} ${incident.getClientid().getPrenom()}
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Tel :</strong> 
                                                ${incident.getClientid().getTelephone()}
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Email :</strong> 
                                                ${incident.getClientid().getMail()}
                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Details Incidents</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Objet :</strong>
                                                ${incident.getTitle()}
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Statut :</strong>
                                                <span class=" <c:if test="${incident.getState().getCode()==200}">badge badge-success</c:if>
                                                      <c:if test="${incident.getState().getCode()==502}">badge badge-primary</c:if>
                                                      <c:if test="${incident.getState().getCode()==501}">badge badge-info</c:if>
                                                      <c:if test="${incident.getState().getCode()==404}">badge badge-danger</c:if>
                                                      <c:if test="${incident.getState().getCode()==401}">badge badge-warning</c:if>">
                                                    ${incident.getState().getNom()}
                                                </span>

                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Priorite :</strong>
                                                <span class="badge bg-blue-grey">
                                                    ${incident.getPrioriteid().getName()}
                                                </span>

                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Source :</strong>
                                                <span class="">
                                                    ${incident.getSourceid().getName()}
                                                </span>

                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Decriptions</h4>

                                            <p class="card-text">
                                            <div id="accordionWrap1" role="tablist" aria-multiselectable="true">
                                                <div class="card collapse-icon panel mb-0 box-shadow-0 border-0">
                                                    <div id="heading11" role="tab" class="card-header border-bottom-blue-grey border-bottom-lighten-2">
                                                        <a data-toggle="collapse" data-parent="#accordionWrap1" href="#accordion11" aria-expanded="true"
                                                           aria-controls="accordion11" class="h6 blue">Description de l'incident</a>
                                                    </div>
                                                    <div id="accordion11" role="tabpanel" aria-labelledby="heading11" class="collapse show"
                                                         aria-expanded="true">
                                                        <div class="card-body">
                                                            <p class="card-text">
                                                                ${incident.getDescription()}</p>
                                                        </div>
                                                    </div>

                                                    <c:if test="${incident.getTtraitementTicketList().size() != 0}">
                                                        <c:set var="i" value="${0}"></c:set>
                                                        <c:forEach items="${incident.getTtraitementTicketList()}" var="trait">
                                                            <div id="heading12${i}" role="tab" class="card-header border-bottom-blue-grey border-bottom-lighten-2">
                                                                <a data-toggle="collapse" data-parent="#accordionWrap1" href="#accordion12${i}" aria-expanded="false"
                                                                   aria-controls="accordion12" class="h6 blue collapsed">Description resolution</a>
                                                            </div>
                                                            <div id="accordion12${i}" role="tabpanel" aria-labelledby="heading12" class="collapse"
                                                                 aria-expanded="false">
                                                                <div class="card-body">
                                                                    <p class="card-text">${trait.getCommentaire()}</p>
                                                                </div>
                                                            </div>
                                                            <c:set var="i" value="${i+1}"></c:set>
                                                        </c:forEach>

                                                    </c:if>
                                                    <c:if test="${incident.getDescriptionFermeture() != null}">
                                                        <div id="heading12lo" role="tab" class="card-header border-bottom-blue-grey border-bottom-lighten-2">
                                                            <a data-toggle="collapse" data-parent="#accordionWrap1" href="#accordion12lo" aria-expanded="false"
                                                               aria-controls="accordion12" class="h6 blue collapsed">Description Fermeture</a>
                                                        </div>
                                                        <div id="accordion12lo" role="tabpanel" aria-labelledby="heading12" class="collapse"
                                                             aria-expanded="false">
                                                            <div class="card-body">
                                                                <p class="card-text">${incident.getDescriptionFermeture() }</p>
                                                            </div>
                                                        </div>


                                                    </c:if>
                                                </div>
                                            </div>
                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12 border-primary">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Autres Informations</h4>
                                            <p class="card-text">
                                                <strong for="firstName">crée le :</strong>
                                                ${incident.getDatecc()}
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Par :</strong>
                                                ${incident.getCreator().getFirstname()}
                                            </p>
                                            <c:if test="${incident.getUserplainteList().size()!= 0}">
                                                <p class="card-text">
                                                    <strong for="firstName">Responsable :</strong> 
                                                    ${incident.getUserplainteList()[0].getIduser().getFirstname()}
                                                </p>
                                            </c:if>

                                            <c:if test="${incident.getDateModif()!= null}">
                                                <p class="card-text">
                                                    <strong for="firstName">Date Modification :</strong> 
                                                    ${incident.Datemodification()}
                                                </p>
                                            </c:if>

                                            <p class="card-text">
                                                <strong for="firstName">Delais :</strong> 
                                                ${incident.getDateliv()}
                                            </p>
                                            <c:if test="${incident.getDateFer()!= null}">
                                                <p class="card-text">
                                                    <strong for="firstName">Date Fermeture :</strong>
                                                    <span class=" <c:if test="${incident.getState().getCode()==200}">badge badge-success</c:if>
                                                          <c:if test="${incident.getState().getCode()==401}">badge badge-warning</c:if>">
                                                        ${incident.Fatefermeture()}
                                                    </span>
                                                </p>
                                            </c:if>
                                            <c:if test="${incident.getTtraitementTicketList().size()!= 0}">
                                                <p class="card-text">
                                                    <strong for="firstName">Traité par :</strong> 
                                                    ${incident.getTtraitementTicketList()[0].getUser().getFirstname()}
                                                </p>
                                            </c:if>
                                            <p class="card-text">
                                                <strong for="firstName">Rubrique :</strong> 
                                                ${incident.getSrubriqueid().getRubriqueid().getName()}
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Sous Rubrique :</strong> 
                                                ${incident.getSrubriqueid().getName()}
                                            </p>
                                            <c:if test="${1==1}">

                                                <c:if test="${incident.getIsInDelais()==0}">
                                                    <p class="card-text">
                                                        <span class="badge badge-success">
                                                            <strong for="firstName">Resolu Dans les delais</strong> 
                                                        </span>
                                                    </p>
                                                </c:if>
                                                <c:if test="${incident.getIsInDelais()==1}">
                                                    <p class="card-text">
                                                        <span class="badge badge-warning">
                                                            <strong for="firstName">Resolu hors delais</strong> 
                                                        </span>
                                                    </p>
                                                </c:if>
                                                <c:if test="${incident.getIsaffect()== 0}">
                                                    <p class="card-text">
                                                        <span class="badge badge-danger">
                                                            <strong for="firstName">Non affecter</strong> 
                                                        </span>
                                                    </p>
                                                </c:if>
                                                <div class="row">
                                                    <c:if test="${incident != null}">
                                                        <div class="form-group col-lg-12 col-sm-12">
                                                            <c:forEach items="${incident.getPieceJointeList()}" var="pj">
                                                                <a target="_blanck" href="ticket?getfile=ghdjsdsj4594264oiuy&id=${pj.getIdPj()}">Pièce jointe n⁰ ${pj.getIdPj()}</a>&nbsp;&nbsp;
                                                            </c:forEach>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </c:if>

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </fieldset>


                </form>
            </div>

            <c:if test="${incident != null}">
                <div class="modal-footer">
                    <button  class="btn t btn-outline-dark btn-sm round" 
                             data-toggle='tooltips' title="Imprimer la fiche de cette incident"
                             onclick="getDataToPrint(${incident.getId()})"><i class="fa fa-print"></i></button>
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>

        </div>
    </div>
</div>
