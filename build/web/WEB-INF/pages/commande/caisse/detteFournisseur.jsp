<%-- 
    Document   : detteFournisseur
    Created on : 6 mai 2019, 01:29:24
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestcaisse() == 1}">
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
        <c:set var="id" value="&r=7tyui895qze412"></c:set>
        <c:if test="${ligne != null}">
            <c:set var="isnew" value="${3}"></c:set>
            <c:set var="id" value="&ligne=${ligne.getId()}"></c:set>
        </c:if>
        <c:if test="${ligne == null}">
            <c:set var="isnew" value="${5}"></c:set>
        </c:if>

        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <section>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">SORTIE POUR FOURNISSEURS</h4>
                            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                        </div>
                        <div class="card-content">

                            <div class="card-body">
                                <div class="card-text">

                                    <div class="row">
                                        <div class="col-lg-6 col-md-12 border-success">
                                            <div class="card ">
                                                <div class="card-content">
                                                    <div class="card-body">
                                                        <h4 class="card-title info">DETTES FOURNISSEUR</h4>
                                                        <form class="form" method="post" action="gestioncaisse?action=model&model=facturationAccount&q=${q}&isNew=${isnew}${id}&forpaiement=0">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <label for="lastName">
                                                                            Fournisseur
                                                                            <span class="danger">*</span>
                                                                        </label>
                                                                        <select style="width: 100%" class="select2-size-xs block form-control round " name="fournisseur">
                                                                            <c:forEach items="${fournisseurs}" var="cl">
                                                                                <option  value="${cl.getId()}"> ${cl.getNom()}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group col-lg-6 col-sm-12">
                                                                    <label for="quantite">Montant Net<span class="danger">*</span></label>
                                                                    <input type="number"  value="" min="0" required  class="form-control-sm form-control round" name="montant_net"/>
                                                                </div>
                                                                <div class="form-group col-lg-6 col-sm-12">
                                                                    <label for="quantite">Avance<span class="danger">*</span></label>
                                                                    <input type="number"  value="" min="0" required  class="form-control-sm form-control round" name="avance"/>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-group col-lg-6 col-sm-12">
                                                                    <label for="quantite">Commentaire<span class="danger">*</span></label>
                                                                    <textarea name="commentaire" class="form-control-sm form-control round" required></textarea>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                                    <button type="submit" class="btn btn-primary round btn-sm btn_caisse" style="margin-top: 25px">
                                                                        <i class="fa fa-money"></i> Enregistrer
                                                                    </button>
                                                                </div>
                                                            </div>


                                                        </form>

                                                    </div>
                                                </div>
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
</c:if>

