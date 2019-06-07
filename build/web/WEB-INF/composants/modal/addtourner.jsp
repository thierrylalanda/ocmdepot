<%-- 
    Document   : addtourner
    Created on : 7 mars 2019, 16:07:45
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${tournerToUpdate != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&tourner=${tournerToUpdate.getId()}"></c:set>
</c:if>
<c:if test="${tournerToUpdate == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${tournerToUpdate != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter une tourner</h3>
                <c:if test="${tournerToUpdate == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form method="post" action="administration?q=${q}&action=model&model=tourner&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos tournée</h6>
                    <fieldset>
                        <div class="row">

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="code">
                                        Numéro
                                        <span class="danger">*</span>
                                    </label>
                                    <input  type="text" value="${tournerToUpdate.getNumc()}"  class="form-control-sm round form-control" required id="code" name="numtr">
                                </div>
                            </div>
                        </div>
                        <c:if test="${tournerToUpdate != null}">
                            <div class="row">
                                <c:if test="${sessionScope.societe.getGestmagasin()==1}">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="code">
                                            Magasin
                                        </label>
                                        <select style="width: 100%" name="magasin" class="select2-border form-control-sm round block form-control"
                                                data-border-color="purple" data-border-variation="accent-2"
                                                data-text-color="white">
                                            <c:forEach items="${magasins}" var="mag">
                                                <option <c:if test="${not empty tournerToUpdate.getMagasin() && tournerToUpdate.getMagasin().getId()== mag.getId()}">selected</c:if> value="${mag.getId()}">${mag.getNom()}</option>
                                            </c:forEach>
                                        </select>     
                                    </div>
                                </div>
                            </c:if>
                            </div>
                            <div class="row">

                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="code">
                                            Utilisateurs
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%" name="user" class="select2-border form-control-sm round block form-control" id="" multiple="multiple"
                                                data-border-color="purple" data-border-variation="accent-2"
                                                data-text-color="white">    
                                            <c:if test="${empty tournerToUpdate.getAffectTournerUserList()}">
                                                <c:forEach items="${users}" var="cl">
                                                    <option  value="${cl.getId()}">${cl.getFirstname()} ${cl.getLastname()}</option>
                                                </c:forEach>
                                            </c:if> 
                                            <c:if test="${not empty tournerToUpdate.getAffectTournerUserList()}">
                                                <c:forEach items="${users}" var="cl">
                                                    <c:set var="has" value="no"></c:set>
                                                    <c:forEach items="${cl.getAffectTournerUserList()}" var="ua">
                                                        <c:if test="${ua.getTourner().getId()==tournerToUpdate.getId()}">
                                                            <option selected  value="${cl.getId()}"> ${cl.getFirstname()} ${cl.getLastname()}</option>
                                                            <c:set var="has" value="yes"></c:set>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${has !='yes'}">
                                                        <option  value="${cl.getId()}">${cl.getFirstname()} ${cl.getLastname()}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if> 


                                        </select>     
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="code">
                                            Clients
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%" name="client" class="select2-border form-control-sm round block form-control" id="border-multiple" multiple="multiple"
                                                data-border-color="purple" data-border-variation="accent-2"
                                                data-text-color="white">
                                            <c:forEach items="${clients}" var="cl">
                                                <option <c:if test="${cl.getTourner().getId()==tournerToUpdate.getId()}">selected</c:if> value="${cl.getId()}">${cl.getNom()}</option>
                                            </c:forEach>

                                        </select>    
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </fieldset>

                    <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                           value="Enregistrer">
                </form>
            </div>
            <c:if test="${tournerToUpdate == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${tournerToUpdate != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>

        </div>
    </div>
</div>

