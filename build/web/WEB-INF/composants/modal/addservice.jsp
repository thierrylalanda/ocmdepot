<%-- 
    Document   : addservice
    Created on : 20 oct. 2018, 10:59:31
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${service != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&service=${service.getId()}"></c:set>
</c:if>
<c:if test="${service == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${service != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter un service</h3>
                <c:if test="${service == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form method="post" action="administration?q=${q}&action=model&model=service&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Info service</h6>
                    <fieldset>
                        <c:if test="${sessionScope.root=='EH2S'}">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="societe">
                                            Societe
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  id="societe"  class=" select2-size-xs block form-control round required region_by_societe"  name="societe">
                                            <option value=" "> </option>
                                            <c:forEach items="${societes}" var="soc">
                                                <option <c:if test="${soc.getId()==service.getSite().getRegionid().getSociete().getId()}">selected</c:if> value="${soc.getId()}"> ${soc.getNom()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="societe_region">
                                            Region
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  id="societe_region"  class=" select2-size-xs block form-control round required societe_region site_by_region"  name="region">
                                             <c:forEach items="${regions}" var="reg">
                                            <option <c:if test="${reg.getId()==service.getSite().getRegionid().getId()}">selected</c:if> value="${reg.getId()}"> ${reg.getName()}</option>
                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="region_site">
                                        Site :
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" required id="region_site" class="select2-size-xs block form-control round region_site" name="site">
                                        <c:forEach items="${sites}" var="site">
                                            <option <c:if test="${site.getId()==service.getSite().getId()}">selected</c:if> value="${site.getId()}"> ${site.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="firstName">
                                        service
                                        <span class="danger">*</span>
                                    </label>
                                    <input type="text" value="${service.getName()}"  class="form-control-sm round form-control" required id="firstName" name="nom">
                                </div>
                            </div>

                        </div>
                    </fieldset>

                    <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                           value="Enregistrer">
                </form>
            </div>
            <c:if test="${service == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${service != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>

        </div>
    </div>
</div>

