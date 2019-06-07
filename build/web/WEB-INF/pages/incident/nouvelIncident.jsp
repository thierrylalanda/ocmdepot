<%-- 
    Document   : nouvelIncident
    Created on : 18 oct. 2018, 10:07:27
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien59 or not empty sessionScope.lien92}">
    <c:set var="id" value="&r=7tyui895qze412"></c:set>
    <c:if test="${incident != null}">
        <c:set var="isnew" value="${1}"></c:set>
        <c:set var="id" value="&ticket=${incident.getId()}"></c:set>
    </c:if>
    <c:if test="${incident == null}">
        <c:set var="isnew" value="${0}"></c:set>
    </c:if>
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <div class="row autoloader" autoloader="true">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title" id="from-actions-center-dropdown">Declarer un nouvel Incident</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>

                </div>
                <div class="card-content">
                    <div class="card-body">
                        <div class="card-text">

                        </div>
                        <form class="form" novalidate method="post" action="ticket?action=model&model=saveTicket&q=${q}&isNew=${isnew}${id}" enctype="multipart/form-data">
                            <div class="form-body">
                                <div class="row">
                                    <div class="form-group col-lg-4 col-sm-12">
                                        <label for="complaintinput2">Client
                                            <span class="danger">*</span></label>
                                        <select style="width: 100%" required id="complaintinput2" class="select2-size-xs block form-control round" name="client">
                                            <c:forEach items="${clients}" var="cl">
                                                <option <c:if test="${cl.getId()==incident.getClientid().getId()}">selected</c:if> value="${cl.getId()}"> ${cl.getNom()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-lg-2 col-sm-12"></div>
                                    <div class="form-group col-lg-4 col-sm-12">
                                        <label for="source">Source
                                            <span class="danger">*</span></label>
                                        <select style="width: 100%" required id="source" class="select2-size-xs block form-control round" name="source">
                                            <c:forEach items="${sources}" var="src">
                                                <option <c:if test="${src.getId()==incident.getSourceid().getId()}">selected</c:if> value="${src.getId()}"> ${src.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-lg-4 col-sm-12">
                                        <label for="rubriqueIncident">Rubrique
                                            <span class="danger">*</span></label>
                                        <select style="width: 100%" required id="rubriqueIncident"  class="select2-size-xs block form-control round" name="rubrique">
                                            <option value=""></option>
                                            <c:forEach items="${rubriques}" var="rub">

                                                <option <c:if test="${rub.getId()==incident.getSrubriqueid().getRubriqueid().getId()}">selected</c:if> value="${rub.getId()}"> ${rub.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-lg-2 col-sm-12"></div>
                                    <div class="form-group col-lg-4 col-sm-12">
                                        <label for="srubriqueIncident">Sous rubrique
                                            <span class="danger">*</span></label>
                                        <select style="width: 100%" required id="srubriqueIncident" class="select2-size-xs block form-control round" name="srubrique">
                                            <c:if test="${incident != null}">
                                                <option selected value="${incident.getSrubriqueid().getId()}"> ${incident.getSrubriqueid().getName()}</option>
                                            </c:if>
                                        </select>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="form-group col-lg-12 col-sm-12 mb-2" >
                                        <label for="objet">Objet
                                            <span class="required">*</span></label>
                                        <input type="text" id="objet" class="form-control-sm form-control round" placeholder="objet"
                                               name="object" value='${incident.getTitle()}' required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-lg-12 col-sm-12 mb-2">
                                        <label for="description">Description
                                            <span class="required">*</span></label>
                                        <textarea name="description" id="description" rows="4" class="form-control round" required>${incident.getDescription()}</textarea>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="form-group col-lg-4 col-sm-12">
                                        <label for="priorite">Priorité
                                            <span class="danger">*</span></label>
                                        <select style="width: 100%" required id="priorite" class="select2-size-xs block form-control round" name="priorite">
                                            <c:forEach items="${priorites}" var="pri">
                                                <option <c:if test="${pri.getId()==incident.getPrioriteid().getId()}">selected</c:if> value="${pri.getId()}"> ${pri.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-lg-2 col-sm-12"></div>
                                    <div class="form-group col-lg-4 col-sm-12">
                                        <label for="statut">Statut
                                            <span class="danger">*</span></label>
                                        <select style="width: 100%" required id="statut" class="select2-size-xs block form-control round" name="statut">
                                            <c:set var="st" value="${0}"/>
                                            <c:forEach items="${statuts}" var="sta">
                                                <c:if test="${incident ==null}">
                                                    <c:if test="${sta.getCode()==501}">
                                                        <option <c:if test="${sta.getCode()==incident.getState().getCode()}">selected</c:if> value="${sta.getCode()}"> ${sta.getNom()}</option>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${incident !=null}">
                                                    <c:if test="${incident.getIsaffect() == 0 and sta.getCode()==501}">
                                                        <option  value="${incident.getState().getCode()}"> ${incident.getState().getNom()}</option>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${incident != null and sta.getCode()!= 200 and incident.getIsaffect() != 0}">
                                                    <option <c:if test="${sta.getCode()==incident.getState().getCode()}">selected</c:if> value="${sta.getCode()}"> ${sta.getNom()}</option>

                                                </c:if>

                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien127}">
                                    <div class="row">
                                        <div class="form-group col-lg-4 col-sm-12">
                                            <label for="priorite">Responsable
                                                <span class="danger">*</span></label>
                                            <select style="width: 100%" required id="priorite" class="select2-size-xs block form-control round" name="responsable">
                                                <c:forEach items="${users}" var="us">
                                                    <option <c:if test="${us.getId()==incident.getUserplainteList()[0].getIduser().getId()}">selected</c:if> value="${us.getId()}"> ${us.getFirstname()} ${us.getLastname()}</option>
                                                </c:forEach>
                                                <
                                            </select>
                                        </div>
                                    </div>
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
                                <div class="row">
                                    <div class="form-group col-lg-4 col-sm-12 file-repeater">
                                        <div data-repeater-list="repeater-list">
                                            <div data-repeater-item>
                                                <div class="row mb-1">
                                                    <div class="col-9 col-xl-10">
                                                        <label class="file center-block">
                                                            <input type="file" id="file" name="">
                                                            <span class="file-custom"></span>
                                                        </label>
                                                    </div>
                                                    <div class="col-2 col-xl-1">
                                                        <button type="button" data-repeater-delete class="btn btn-icon btn-outline-danger mr-1 btn-sm"><i class="ft-x"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" data-repeater-create class="btn btn-round btn-outline-primary btn-sm">
                                            <i class="icon-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="form-actions">

                                <button type="submit" class="btn btn-primary round">
                                    <i class="fa fa-check-square-o"></i> Enregistrer
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>