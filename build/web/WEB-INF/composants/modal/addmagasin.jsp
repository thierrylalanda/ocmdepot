<%-- 
    Document   : addmagasin
    Created on : 24 mars 2019, 19:53:08
    Author     : Administrateur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.eh2s.ocm.Entity.Tregions" %>
<%@page import="com.eh2s.ocm.Entity.Tusers" %>
<%@page import="com.eh2s.ocm.Entity.Taffectzone" %>
<%@page import="com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal" %>
<%@page import="com.eh2s.ocm.Entity.Sessions.TregionsFacade" %>
<!DOCTYPE html>
<%
    TregionsFacadeLocal tregionsfacadelocal;
%>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${magasin != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&magasin=${magasin.getId()}"></c:set>
</c:if>
<c:if test="${magasin == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade text-left <c:if test="${magasin != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter un nouveau magasin</h3>
                <c:if test="${magasin == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form autocomplete="off" method="post" action="administration?q=${q}&action=model&model=Magasin&isNew=${isnew}${id}" class="" novalidate>
                    <!-- Step 1 -->
                    <h6>Infos Magasin</h6>
                    <fieldset>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="code">
                                        Region
                                    </label>
                                    <select style="width: 100%" name="reg" class="select2-border form-control-sm round block form-control"
                                            data-border-color="purple" data-border-variation="accent-2"
                                            data-text-color="white">
                                        <c:forEach items="${regions}" var="reg">
                                            <option <c:if test="${not empty magasin && magasin.getRegion().getId()== reg.getId()}">selected</c:if> value="${reg.getId()}">${reg.getName()}</option>
                                        </c:forEach>
                                    </select>     
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nom">
                                        Nom :
                                        <span class="danger">*</span>
                                    </label>
                                    <input type="text" value="${magasin.getNom()}" class="form-control-sm round form-control required" name="nom">
                                </div>
                            </div>
                        </div>
                        <c:if test="${not empty magasin}">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="code">
                                            Magasignier
                                        </label>
                                        <select style="width: 100%" name="magasignier" class="select2-border form-control-sm round block form-control"
                                                data-border-color="purple" data-border-variation="accent-2"
                                                data-text-color="white">
                                            <option value=""></option>
                                            <c:forEach items="${users}" var="user">
                                                <option <c:if test="${user.getId()== magasin.getMagasigner().getId()}">selected</c:if> value="${user.getId()}">${user.getFirstname()}</option>
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
            <c:if test="${magasin == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${magasin != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>
        </div>
    </div>
</div>
