<%-- 
    Document   : addcommandeclient
    Created on : 7 déc. 2018, 08:06:14
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${commande != null}">
    <c:set var="isnew" value="${5}"></c:set>
    <c:set var="id" value="&id=${commande.getArticle().getId()}"></c:set>
</c:if>
<c:if test="${commande == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${commande != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Nouvelle Commande</h3>
                <c:if test="${commande == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form class="form" method="post" action="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=${isnew}${id}&client=${sessionScope.client.getId()}">

                    <div class="form-body">
                        <h6>Ajouter un produit à la commande</h6>
                        <fieldset>
                            <div class="row">
                                <div class="form-group col-lg-3 col-sm-12">
                                    <label for="categorie_article">Livraison
                                        <span class="danger">*</span></label>
                                    <input type="text" <c:if test="${sessionScope.njr != null}">disabled value="${sessionScope.njr}"</c:if> class="form-control-sm form-control round singledate" <c:if test="${sessionScope.njr == null}"> </c:if> required name="njr"/>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset>
                                <div class="row">
                                    <div class="form-group col-lg-3 col-sm-12">
                                        <label for="categorie_article">Categorie
                                            <span class="danger">*</span></label>
                                            <select style="width: 100%" <c:if test="${commande != null}">disabled</c:if> <c:if test="${commande == null}">required</c:if> id="categorie_article"  class="select2-size-xs block form-control round" name="cat">
                                            <option value=""></option>
                                        <c:forEach items="${categories}" var="cat">
                                            <option <c:if test="${cat.getId()==commande.getArticle().getCategorie().getId()}">selected</c:if> value="${cat.getId()}"> ${cat.getNom()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-lg-3 col-sm-12">
                                    <label for="articles_categorie">Article
                                        <span class="danger">*</span></label>
                                    <select style="width: 100%" required id="articles_categorie" class="select2-size-xs block form-control round" name="nom">
                                        <c:if test="${commande != null}">
                                            <option selected value="${commande.getArticle().getId()}"> ${commande.getArticle().getNom()}</option>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="form-group col-lg-2 col-sm-12">
                                    <label for="quantite">Qte
                                        <span class="danger">*</span></label>
                                    <input type="number" required id="quantite" value="${commande.getQuantite()}" class="form-control-sm form-control round" name="quantite"/>
                                </div>
                                <div class="form-group col-lg-3 col-sm-12">
                                    <label for="description">Commentaire</label>
                                    <textarea id="description" class="form-control-sm form-control round" name="commantaire">${commande.getCommantaire()}</textarea>
                                </div>
                            </div>
                        </fieldset>
                        <div class="form-control-sm form-group col-lg-2 col-sm-12">
                            <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                <i class="fa fa-check-square-o"></i> Enregistrer
                            </button>
                        </div>
                    </div>
                </form>
            </div>


        </div>
        <c:if test="${commande == null}">
            <div class="modal-footer">
                <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                       value="Annuler">

            </div>
        </c:if>

        <c:if test="${commande != null}">
            <div class="modal-footer">
                <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
            </div>
        </c:if>

    </div>
</div>
</div>

