<%-- 
    Document   : addcategorie
    Created on : 26 nov. 2018, 14:00:05
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${categorie != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&categorie=${categorie.getId()}"></c:set>
</c:if>
<c:if test="${categorie == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${categorie != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter une categorie</h3>
                <c:if test="${categorie == null}">
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
                    <li class="nav-item">
                        <a class="nav-link" id="link-tab3" data-toggle="tab" href="#link3" aria-controls="link3"
                           aria-expanded="false">Fichier</a>
                    </li>

                </ul>
                <div class="tab-content px-1 pt-1">
                    <div role="tabpanel" class="tab-pane active in" id="active3" aria-labelledby="active-tab3"
                         aria-expanded="true">
                        <form method="post" action="administration?q=${q}&action=model&model=CategorieArticle&isNew=${isnew}${id}" class="" novalidate>

                            <!-- Step 1 -->
                            <h6>Infos Categorie</h6>
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
                                                    <option></option>
                                                    <c:forEach items="${societes}" var="soc">
                                                        <option <c:if test="${soc.getId()==categorie.getSociete().getId()}">selected</c:if> value="${soc.getId()}"> ${soc.getNom()}</option>
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
                                                Categorie
                                                <span class="danger">*</span>
                                            </label>
                                            <input type="text" value="${categorie.getNom()}"  class="form-control-sm round form-control" required id="firstName" name="nom">
                                        </div>
                                    </div>

                                </div>
                            </fieldset>

                            <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                                   value="Enregistrer">
                        </form>
                    </div>
                    <div class="tab-pane" id="link3" role="tabpanel" aria-labelledby="link-tab3" aria-expanded="false">
                        
                          <form enctype="multipart/form-data" action="UploadFileArticles?q=${q}&fileCategorie=0" method="post">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="file"   class="form-control-sm round form-control" required id="firstName" name="nom">
                                </div>
                            </div>

                        </div>
                        <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                               value="Enregistrer">
                    </form>
                        <code>Assurez vous de respecter ce formatage dans votre fichier excel sans la première ligne</code>
                        <img src="assets/formatage/format_categorie.png" style="" />
                    </div>
                  

                </div>

            </div>
            <c:if test="${categorie == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${categorie != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>

        </div>
    </div>
</div>
