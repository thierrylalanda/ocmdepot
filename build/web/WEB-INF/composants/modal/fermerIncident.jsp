<%-- 
    Document   : fermerIncident
    Created on : 23 oct. 2018, 13:48:27
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${ferme != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&ticket=${incident.getId()}"></c:set>
</c:if>
<c:if test="${ferme == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${ferme != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter une description de fermeture</h3>


                </div>
                <div class="modal-body">
                    <form method="post" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=0${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos Traitement</h6>
                    <fieldset>
                        <c:if test="${incident.getTtraitementTicketList().size() != 0}">
                            <c:forEach items="${incident.getTtraitementTicketList()}" var="trait">
                                <div class="row">
                                    <div class="form-group col-lg-12 col-sm-12">

                                        <label for="lastName">
                                            Description
                                        </label>
                                        <textarea name="commentaire" disabled id="description" rows="4" class="form-control round">${trait.getCommentaire()}</textarea>


                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>

                        <div class="row">
                            <div class="form-group col-lg-12 col-sm-12">

                                <label for="lastName">
                                    Description de Fermeture
                                </label>
                                <textarea name="descriptionFer"  rows="4" class="form-control round"></textarea>


                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group col-lg-12 col-sm-12">
                                <c:forEach items="${incident.getPieceJointeList()}" var="pj">
                                    <a target="_blanck" href="ticket?getfile=ghdjsdsj4594264oiuy&id=${pj.getIdPj()}">Pièce jointe n⁰ ${pj.getIdPj()}</a>&nbsp;&nbsp;
                                </c:forEach>
                            </div>
                        </div>
                        <div class="row">

                            <div class="form-group col-lg-4 col-sm-12">
                                <label for="statut">Statut
                                    <span class="danger">*</span></label>
                                <select style="width: 100%" required id="statut" class="select2-size-xs block form-control round" name="statut">
                                    <c:forEach items="${statuts}" var="sta">
                                        <option <c:if test="${sta.getCode()==incident.getState().getCode()}">selected</c:if> value="${sta.getCode()}"> ${sta.getNom()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </fieldset>

                    <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                           value="Enregistrer">
                </form>
            </div>

            <div class="modal-footer">
                <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
            </div>

        </div>
    </div>
</div>