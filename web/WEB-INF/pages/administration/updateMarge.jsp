<%-- 
    Document   : updateMarge
    Created on : 2 mai 2019, 09:33:11
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S'}">
    <%
        Date now = new Date();
        request.setAttribute("day", now);
    %>
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

        <div class="row autoloader" autoloader="true">

            <div class="col-md-12">
                <!-- Restore & show all table -->
                <section id="restore">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Marge des commandes</h4>
                                    <div class="heading-elements ">
                                        <ul class="list-inline mb-0">
                                            <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                                            <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                                            <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="card-content collapse show">
                                    <div class="card-body card-dashboard">
                                        <p class="card-text">
                                        <form method="post" action="administration?q=${q}&action=model&model=updateMarge&isNew=0" class="" novalidate>


                                            <fieldset>
                                                <div class="row">

                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label for="lastName">
                                                                Societe
                                                                <span class="danger">*</span>
                                                            </label>
                                                            <select style="width: 100%" id="statut" class="select2-size-xs block form-control round" name="id">

                                                                <c:forEach items="${societes}" var="sta">
                                                                    <option value="${sta.getId()}"> ${sta.getNom()}</option>

                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label for="lastName">

                                                            </label>

                                                            <input style="margin-top: 25px" type="submit" class="btn btn-outline-primary  round btn-sm"
                                                                   value="Enregistrer">
                                                        </div>
                                                    </div>


                                                </div>
                                            </fieldset>

                                        </form>
                                        </p>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!--/ Restore & show all table -->
            </div>
        </div>
    </c:if>


