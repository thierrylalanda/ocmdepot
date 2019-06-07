<%-- 
    Document   : addEmballage
    Created on : 11 avr. 2019, 16:04:26
    Author     : Administrateur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${emballage != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&emballage=${emballage.getId()}"></c:set>
</c:if>
<c:if test="${emballage == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${emballage != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter un emballage</h3>
                <c:if test="${emballage == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <ul class="nav nav-tabs nav-linetriangle no-hover-bg nav-justified">
                    <li class="nav-item">
                        <a class="nav-link active" id="active-tab3" data-toggle="tab" href="#active3" aria-controls="active3"
                           aria-expanded="true">Formulaire</a>
                    </li>

                </ul>
                <div class="tab-content px-1 pt-1">
                    <div role="tabpanel" class="tab-pane active in" id="active3" aria-labelledby="active-tab3"
                         aria-expanded="true">
                        <form method="post" enctype="multipart/form-data" action="administration?q=${q}&action=model&model=Emballage&isNew=${isnew}${id}" class="">

                            <!-- Step 1 -->
                            <h6>Infos Emballage</h6>
                            <fieldset>

                                <c:if test="${sessionScope.root=='EH2S'}">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="societe">
                                                    Societe
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%"  id="societe"  class=" select2-size-xs block form-control round required categorie_by_societe"  name="societe">
                                                    <option></option>
                                                    <c:forEach items="${societes}" var="soc">
                                                        <option <c:if test="${soc.getId()==emballage.getSociete().getId()}">selected</c:if> value="${soc.getId()}"> ${soc.getNom()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="row">
                                    <div class="col-md-8">

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="article">
                                                        Nom
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <input type="text" value="${emballage.getNom()}"  class="form-control-sm round form-control" required id="article" name="nom">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="code">
                                                        Code
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <input type="text" value="${emballage.getCode()}"  class="form-control-sm round form-control" required id="code" name="code">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="prix">
                                                        Prix U
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <input type="number" value="${emballage.getPrix()}" min="0" step="0.01" class="form-control-sm round form-control" required id="prix" name="prix"
                                                           title="Currency" pattern="^\d+(?:\.\d{1,2})?$" onblur="
                                                               this.parentNode.parentNode.style.backgroundColor = /^\d+(?:\.\d{1,2})?$/.test(this.value) ? 'inherit' : 'red'
                                                           ">
                                                </div>
                                            </div>
                                            <c:if test="${emballage == null}">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="stock">
                                                            Stock
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <input type="number" value="${emballage.getStock()}"  min="0" class="form-control-sm round form-control" required id="stock" name="stock"
                                                               >
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                        <div class="row">

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="unite">
                                                        Seuil
                                                    </label>
                                                    <input type="number" min="0" value="${emballage.getSeuil()}" placeholder="seuil limite"  class="form-control-sm round form-control" id="seuil" name="seuil"
                                                           >
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </fieldset>

                            <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                                   value="Enregistrer">
                        </form>
                    </div>


                </div>


            </div>
            <c:if test="${emballage == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${emballage != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>

        </div>
    </div>
</div>


