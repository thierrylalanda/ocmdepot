<%-- 
    Document   : societe
    Created on : 16 oct. 2018, 13:41:52
    Author     : eh2s001
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S'}">

    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <div class="row match-height">

        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title" id="from-actions-center-dropdown">Information de la societe</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>

                </div>
                <div class="card-content">
                    <div class="card-body">
                        <div class="card-text">
                            <div class="form-group">
                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <c:if test="${sessionScope.root=='EH2S'}">
                                        <button type="button" title="Ajouter une sociéte" class="btn btn-float btn-outline-success btn-round"
                                                data-toggle="modal"
                                                data-target="#bootstrap">
                                            <i class="fa fa-plus-circle"></i></button>
                                        </c:if>
                                        <c:if test="${sessionScope.root=='EH2S'}">
                                        <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=getsociete&isNew=2"><i class="fa fa-edit"></i></button>
                                        </c:if>
                                        <c:if test="${sessionScope.root=='EH2S'}">
                                        <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=deleteSociete&isNew=5"><i class="fa fa-trash"></i></button>
                                        </c:if>
                                </div>
                            </div>
                        </div>
                        <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Societe</th>
                                    <th>E-mail</th>
                                    <th>Telephone</th>
                                    <th>Mode</th>
                                    <th>Expire</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${societes}" var="soc">
                                    <tr>
                                        <td><option selected  class="hidden">${soc.getId()}</option> </td>
                                <td>${soc.getNom()}</td>
                                <td>${soc.getEmail()}</td>
                                <td>${soc.getTel()}</td>
                                <td>
                                    <c:if test="${soc.getMaintenance()== 1}">
                                        <span class="badge badge-warning">Inactive</span>
                                    </c:if>
                                    <c:if test="${soc.getMaintenance()==0}">
                                        <span class="badge badge-success">Active</span>
                                    </c:if>
                                </td>
                                <c:if test="${soc.getSouscriptionLicenceList().size() != 0}">
                                    <td>
                                        <f:formatDate value="${soc.getSouscriptionLicenceList().get(0).getDateFinTest()}" type="BOTH" dateStyle="MEDIUM"/>
                                    </td>
                                </c:if>
                                <c:if test="${soc.getSouscriptionLicenceList().size() == 0}">
                                    <td></td>
                                </c:if>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/composants/modal/addsociete.jsp"/>
    <c:import url="/WEB-INF/composants/modal/addfirstuser.jsp"/>
</c:if>