<%-- 
    Document   : addstatut
    Created on : 22 oct. 2018, 12:35:25
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${statut != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&statut=${statut.getCode()}"></c:set>
</c:if>
<c:if test="${statut == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${statut != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter un statut</h3>
                <c:if test="${statut == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form method="post" action="administration?q=${q}&action=model&model=statut&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos Priorite</h6>
                    <fieldset>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="firstName">
                                        Statut
                                        <span class="danger">*</span>
                                    </label>
                                    <input type="text" value="${statut.getNom()}"  class="form-control-sm round form-control" required id="firstName" name="nom">
                                </div>
                            </div>
                            <div class="col-md-6 <c:if test="${statut != null}">hidden</c:if>">
                                <div class="form-group">
                                    <label for="firstName">
                                        Code
                                        <span class="danger">*</span>
                                    </label>
                                    <input  type="text" value="${statut.getCode()}"  class="form-control-sm round form-control" required id="firstName" name="codestatut">
                                </div>
                            </div>
                        </div>
                    </fieldset>

                    <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                           value="Enregistrer">
                </form>
            </div>
            <c:if test="${statut == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${statut != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>

        </div>
    </div>
</div>
