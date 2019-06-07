<%-- 
    Document   : addsource
    Created on : 22 oct. 2018, 11:28:39
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${source != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&source=${source.getId()}"></c:set>
</c:if>
<c:if test="${source == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${source != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter une source</h3>
                <c:if test="${rubrique == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form method="post" action="administration?q=${q}&action=model&model=source&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos Source</h6>
                    <fieldset>
                        <c:if test="${sessionScope.root=='EH2S'}">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="societe">
                                            Societe
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  id="societe"  class=" select2-size-xs block form-control round required"  name="societe">
                                            <c:forEach items="${societes}" var="soc">
                                                <option <c:if test="${soc.getId()==source.getSociete().getId()}">selected</c:if> value="${soc.getId()}"> ${soc.getNom()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="firstName">
                                        Nom
                                        <span class="danger">*</span>
                                    </label>
                                    <input type="text" value="${source.getName()}"  class="form-control-sm round form-control" required id="firstName" name="nom">
                                </div>
                            </div>

                        </div>
                    </fieldset>

                    <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                           value="Enregistrer">
                </form>
            </div>
            <c:if test="${source == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${source != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>

        </div>
    </div>
</div>
