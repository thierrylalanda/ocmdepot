<%-- 
    Document   : traiterIncident
    Created on : 23 oct. 2018, 13:48:06
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${traiter != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&ticket=${incident.getId()}"></c:set>
</c:if>
<c:if test="${traiter == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${traiter != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter une description de resolution</h3>


                </div>
                <div class="modal-body">
                    <form method="post" id="formcomment" enctype="multipart/form-data" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=0${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos Traitement</h6>
                    <fieldset>
                        <div id="accordionWrap1" role="tablist" aria-multiselectable="true">
                            <div class="card collapse-icon panel mb-0 box-shadow-0 border-0">
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
                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group col-lg-12 col-sm-12 file-repeater">
                                <div data-repeater-list="list_commentaire">
                                    <div data-repeater-item>
                                        <div class="row mb-1">
                                            <div class="col-9 col-xl-10">
                                                <div class="form-group">
                                                    <label for="lastName">
                                                        Description
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <textarea name="commentaire" id="description" rows="4" class="form-control round" required></textarea>
                                                </div>
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
                        <div class="row">
                            <div class="form-group col-lg-4 col-sm-12 file-repeater">
                                <div data-repeater-list="repeater-list">
                                    <div data-repeater-item>
                                        <div class="row mb-1">
                                            <div class="col-9 col-xl-10">
                                                <div class="form-group">
                                                    <label for="lastName">
                                                        Piece jointe
                                                        <span class="danger">*</span>
                                                    </label>

                                                    <input type="file" id="file" name="">

                                                </div>
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
                        <div class="row">

                            <div class="form-group col-lg-4 col-sm-12">
                                <label for="statut">Statut
                                    <span class="danger">*</span></label>
                                <select style="width: 100%" required id="statut" class="select2-size-xs block form-control round" name="statut">
                                    <c:forEach items="${statuts}" var="sta">
                                        <c:if test="${sta.getCode()==501 or sta.getCode()==502}">
                                            <option class="response_incident" <c:if test="${sta.getCode()==incident.getState().getCode()}">selected</c:if> value="${sta.getCode()}"> ${sta.getNom()}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-lg-4 col-sm-12 responsabme_fermeture">
                                
                            </div>
                        </div>
                    </fieldset>

                    <input type="submit" class="btn btn-outline-secondary  round btn-sm" id="submitForm"
                           value="Enregistrer">
                </form>
            </div>

            <div class="modal-footer">
                <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
            </div>

        </div>
    </div>
</div>

