<%-- 
    Document   : adddistrict
    Created on : 20 oct. 2018, 11:10:36
    Author     : messi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${groupe != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&group=${groupe.getId()}"></c:set>
</c:if>
<c:if test="${groupe == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${groupe != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter un Groupe</h3>
                <c:if test="${groupe == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form method="post" id="form_access" action="administration?q=${q}&action=model&model=group&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>Infos groupe</h6>
                    <fieldset>
                        <c:if test="${sessionScope.root=='EH2S'}">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="societe">
                                            Societe
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  id="societe"  class=" select2-size-xs block form-control round required region_by_societe_to_district"  name="societe">
                                            <option value=" "> </option>
                                            <c:forEach items="${societes}" var="soc">
                                                <option <c:if test="${soc.getId()==service.getSite().getRegionid().getSociete().getId()}">selected</c:if> value="${soc.getId()}"> ${soc.getNom()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="societe_region">
                                            Region
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  id="societe_region"  class=" select2-size-xs block form-control round required  site_by_region"  name="region">
                                            <c:forEach items="${regions}" var="reg">
                                                <option <c:if test="${reg.getId()==service.getSite().getRegionid().getId()}">selected</c:if> value="${reg.getId()}"> ${reg.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="societe">
                                            Site
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  id="region_site"  class=" select2-size-xs block form-control round required region_site service_by_site"  name="site">
                                            <option value=" "> </option>
                                           
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="site_service">
                                            Service
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  id="site_service"  class=" select2-size-xs block form-control round required site_service user_by_service"  name="service">
                                         
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <div class="row">
<div class="col-md-3">
                                    <div class="form-group">
                                        <label for="societe_region">
                                            Type
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  class=" select2-size-xs block form-control round required"  name="type">
                                           <option value="1"> Admin Général</option>
                                           <option value="-1"> Admin Local</option>
					<option value="0"> Autre</option>
                                        </select>
                                    </div>
                                </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="firstName">
                                        Groupe
                                        <span class="danger">*</span>
                                    </label>
                                    <input type="text" value="${groupe.getName()}"  class="form-control-sm round form-control " required id="firstName" name="nom">
                                </div>
                            </div>
                            <c:if test="${groupe != null}">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="xsmall-multiple">
                                            Utilisateur

                                        </label>
                                        <select style="width: 100%" class="form-control-sm round select2-size-xs form-control service_user" name="users" id="xsmall-multiple" multiple="multiple">
                                            <c:forEach items="${groupe.getTusersList()}" var="per">
                                                <option value="${per.getId()}" selected>${per.getFirstname()}</option>
                                            </c:forEach>
                                            <c:forEach items="${tous}" var="al">
                                                <option value="${al.getId()}" >${al.getFirstname()}</option>
                                            </c:forEach>

                                        </select>
                                    </div>

                                </div>
                            </c:if>
                        </div>
                        <c:if test="${groupe != null}">
                            <section class="with-minimal-height">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="card">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-md-12 col-xs-12">
                                                        <div id="example" id_groupe="${groupe.getId()}">

                                                            <div class="demo-section k-content">
                                                                <div>
                                                                    <h4>Actions à effectuer</h4>
                                                                    <div id="treeview"></div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </c:if>
                    </fieldset>

                    <input type="submit" <c:if test="${groupe != null}">id="btn_form_access"</c:if>  class="btn btn-outline-secondary  round btn-sm"
                           value="Enregistrer">
                    </form>

                <c:if test="${groupe == null}">
                    <div class="modal-footer">
                        <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                               value="Annuler">

                    </div>
                </c:if>

                <c:if test="${groupe != null}">
                    <div class="modal-footer">
                        <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
