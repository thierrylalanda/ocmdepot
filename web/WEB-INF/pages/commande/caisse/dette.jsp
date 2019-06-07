<%-- 
    Document   : dette
    Created on : 6 mai 2019, 00:05:48
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
            <c:set var="isnew" value="${0}"></c:set>
        </c:if>
        <c:if test="${ligne == null}">
            <c:set var="isnew" value="${4}"></c:set>
        </c:if>

        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <section>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">NOUVELLE DETTE</h4>
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
                                                        <h4 class="card-title info">DETTE</h4>
                                                        <form class="form" method="post" action="gestioncaisse?action=model&model=facturationAccount&q=${q}&isNew=${isnew}${id}">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <label for="lastName">
                                                                            Client
                                                                            <span class="danger">*</span>
                                                                        </label>
                                                                        <select style="width: 100%" class="select2-size-xs block form-control round " name="client">
                                                                            <c:forEach items="${clients}" var="cl">
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
                                                                <div class="form-control-sm form-group col-lg-2 col-sm-12 btn_caisse">
                                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                                        <i class="fa fa-money"></i> Encaisser
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
