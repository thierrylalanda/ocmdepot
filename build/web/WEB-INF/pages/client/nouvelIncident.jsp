<%-- 
    Document   : nouvelIncident
    Created on : 26 oct. 2018, 13:59:54
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${incident != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&ticket=${incident.getId()}"></c:set>
</c:if>
<c:if test="${incident == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="content-wrapper">
    <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2">
            <h3 class="content-header-title mb-0">Incident</h3>
            <div class="row breadcrumbs-top">
                <div class="breadcrumb-wrapper col-12">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="ticketClient?q=client/nouvelIncident&action=jkdhfoldg458dgbjdg478962">Accueil</a>
                        </li>
                        <li class="breadcrumb-item active">Nouvel Incident
                        </li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
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
                    <form class="form" novalidate method="post" action="ticketClient?action=model&model=saveTicket&q=${q}&isNew=${isnew}${id}" enctype="multipart/form-data">
                        <div class="form-body">
                            
                            <div class="row">
                                <div class="form-group col-lg-4 col-sm-12">
                                    <label for="rubriqueIncident">Rubrique
                                        <span class="danger">*</span></label>
                                        <select style="width: 100%" required id="rubriqueIncident"  class="select2-size-xs block form-control round" name="rubrique">
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

