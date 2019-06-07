<%-- 
    Document   : addfournisseur
    Created on : 15 avr. 2019, 08:39:06
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${fournisseur != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&fournisseur=${fournisseur.getId()}"></c:set>
</c:if>
<c:if test="${fournisseur == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade text-left <c:if test="${fournisseur != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Fournisseur</h3>
                <c:if test="${fournisseur == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form autocomplete="off" method="post" action="administration?q=${q}&action=model&model=Fournisseur&isNew=${isnew}${id}" class="" novalidate>
                    <!-- Step 1 -->
                    <h6>Infos Fournisseur</h6>
                    <fieldset>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nom">
                                        Code :
                                        <span class="danger">*</span>
                                    </label>
                                    <input type="text" value="${fournisseur.getCode()}" class="form-control-sm round form-control required" name="code">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nom">
                                        Nom :
                                        <span class="danger">*</span>
                                    </label>
                                    <input type="text" value="${fournisseur.getNom()}" class="form-control-sm round form-control required" name="nom">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nom">
                                        Tel :
                                    </label>
                                    <input type="text" value="${fournisseur.getTelephone()}" class="form-control-sm round form-control" name="telephone">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nom">
                                        E-mail :
                                    </label>
                                    <input type="mail" value="${fournisseur.getMail()}" class="form-control-sm round form-control" name="mail">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nom">
                                        Adresse :
                                    </label>
                                    <input type="text" value="${fournisseur.getAdresse()}" class="form-control-sm round form-control" name="adresse">
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                           value="Enregistrer">
                </form>
            </div>
            <c:if test="${fournisseur == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${fournisseur != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>
        </div>
    </div>
</div>

