<%-- 
    Document   : addClient
    Created on : 8 janv. 2019, 16:46:00
    Author     : Administrateur
--%>
<%-- 
    Document   : index
    Created on : 16 oct. 2018, 11:16:48
    Author     : eh2s001
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>

<html class="loading" lang="en" data-textdirection="ltr">
    <head>
        <c:import url="/WEB-INF/header.jsp"/>
    </head>
    <body class="horizontal-layout horizontal-menu 1-column bg-full-screen-image bg-lighten-2   menu-expanded" data-open="hover"
          data-menu="horizontal-menu" data-col="2-columns">

        <c:if test="${message != null}">
        <message type="${message.getType()}" title="${message.getTitle()}" content="${message.getNotification()}"></message>
        </c:if>


    <div class="app-content content">
        <div class="content-wrapper">
            <div class="content-header row">
            </div>
            <div class="content-body">
                <div class="row">
                    <div class="col-md-2 col-2"></div>
                    <div class="col-md-8 col-10 box-shadow-2 p-0">
                        <div class="card border-grey border-lighten-3 m-0">
                            <div class="card-header border-0">
                                <div class="card-title text-center ">
                                    <div class="p-1" >
                                        <img class="float-none" style="width: 100px;height: 100px;margin-bottom: 0px;align-content: center" src="assets/images/ccmanager.png" alt="branding logo">
                                    </div>
                                </div>
                            </div>
                            <div class="card-content">
                                <p class="bg-danger text-center">
                                    <c:if test="${message != null}">
                                    <message type="${message.getType()}" title="${message.getTitle()}" content="${message.getNotification()}"></message>
                                    </c:if>
                                </p>
                                <h3 class="text-center" style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);color:#fd9701">CREATION DE COMPTE</h3>
                                <div class="card-body pt-0">
                                    <form method="post" action="getconnexionClient?q=${q}&creerClient=12548790" class="steps-validation wizard-circle">

                                        <!-- Step 1 -->
                                        <h6>Infos Client</h6>
                                        <fieldset>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="nom">
                                                            Nom :
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <input type="text" value="${client.getNom()}" class="form-control-sm round form-control required" id="nom" name="nom">
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="prenom">
                                                            Prenom :
                                                        </label>
                                                        <input type="text" value="${client.getPrenom()}" class="form-control-sm round form-control " id="prenom" name="nom1">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="email">
                                                            Mail :
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <input type="email" value="${client.getMail()}" class="form-control-sm round form-control required" id="email" name="email">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="code">
                                                            Code client :
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <input type="text" value="${client.getCodeclient()}" class="form-control-sm round form-control required" id="code"   name="codeclient">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="password">
                                                            Mot de passe :
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <input type="password" placeholder="........ " value="" class="form-control-sm round form-control <c:if test="${client == null}">require</c:if>" id="password" name="password">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <div class="form-group">
                                                            <label for="phone1">Telephone <span class="danger">*</span></label>
                                                            <input type="tel" name="tel1" value="${client.getTelephone()}" class="form-control-sm round form-control required" id="phone1">

                                                    </div>

                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="phone2">Telephone 2 </label>
                                                        <input type="tel" name="tel2" value="${client.getTelephone2()}" class="form-control-sm round form-control" id="phone2">

                                                    </div>

                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="adresse">Adresse :</label>
                                                        <input type="text" value="${client.getAdresse()}" class="form-control-sm round form-control" name="adresse" id="adresse">
                                                    </div>
                                                </div>
                                            </div>
                                        </fieldset>
                                        <!-- Step 2 -->
                                        <h6>Localisation</h6>
                                        <fieldset>
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="societe">
                                                            Societe
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%"  id="societe" required class=" select2-size-xs block form-control round required region_by_societe_client typeclient_by_societe_client"  name="societe">
                                                            <option value=""> </option>
                                                            <c:forEach items="${societe}" var="ser">
                                                                <option <c:if test="${ser.getId() == client.getSecteurid().getDistrictid().getRegionid().getSociete().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getNom()}</option>
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
                                                        <select style="width: 100%"  id="societe_region" required class=" select2-size-xs block form-control round required  district_by_region_client"  name="region">
                                                            <c:forEach items="${regions}" var="ser">
                                                                <option <c:if test="${ser.getId() == client.getSecteurid().getDistrictid().getRegionid().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getName()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="region_district">
                                                            Ville
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%"  id="region_district" required class=" select2-size-xs block form-control round required region_district_client"  name="district">
                                                            <option value=" "> </option>
                                                            <c:forEach items="${districts}" var="ser">
                                                                <option <c:if test="${ser.getId() == client.getSecteurid().getDistrictid().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getName()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="district_secteur">
                                                            Secteur
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%"  id="district_secteur" required class=" select2-size-xs block form-control round required district_secteur_client"  name="secteur">
                                                            <c:forEach items="${secteurs}" var="ser">
                                                                <option <c:if test="${ser.getId() == client.getSecteurid().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getName()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="type">
                                                            Type client
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" class="select2-size-xs block form-control round required societe_typeclient_client" required id="type" name="typeclient">
                                                            <c:forEach items="${typeclients}" var="tc">
                                                                <option <c:if test="${tc.getId()== client.getTypeclientid().getId()}">selected</c:if> value="${tc.getId()}">${tc.getName()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </fieldset>

                                        <!-- Step  -->
                                        <h6>Confirmation</h6>
                                        <fieldset>

                                            <div class="row">
                                                <div class="col-lg-6 col-md-12 border-success">
                                                    <div class="card ">
                                                        <div class="card-content">
                                                            <div class="card-body">
                                                                <h4 class="card-title info">Infos CLient</h4>
                                                                <p class="card-text">
                                                                    <label id="nom_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#nom_conf").html("<strong>Nom</srong> : " + $("#nom").val());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="pre_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#pre_conf").html("<strong>Prenom</strong> : " + $("#prenom").val());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="email_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#email_conf").html("<strong>Email</strong> : " + $("#email").val());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="code_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#code_conf").html("<strong>Code</strong> : " + $("#code").val());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="password_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#password_conf").html("<strong>Mot de passe</strong> : " + $("#password").val());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="phone1_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#phone1_conf").html("<strong>Telephone</strong>  : " + $("#phone1").val());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="phone2_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#phone2_conf").html("<strong>Telephone 2</strong> : " + $("#phone2").val());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="adresse_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#adresse_conf").html("<strong>Adresse</strong> : " + $("#adresse").val());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6 col-md-12 border-success">
                                                    <div class="card ">
                                                        <div class="card-content">
                                                            <div class="card-body">
                                                                <h4 class="card-title info">Localisation</h4>
                                                                <p class="card-text">
                                                                    <label id="societe_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#societe_conf").html("<strong>Societe</strong>  : " + $("#societe").find("option:selected").text());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="region_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#region_conf").html("<strong>Région</strong>  : " + $("#societe_region").find("option:selected").text());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="district_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#district_conf").html("<strong>Ville</strong>  : " + $("#region_district").find("option:selected").text());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>
                                                                <p class="card-text">
                                                                    <c:if test="${sessionScope.root!='EH2S'}">
                                                                        <label id="secteur_conf">
                                                                            <script language="javascript">setInterval(function () {
                                                                                    $("#secteur_conf").html("<strong>Secteur</strong> : " + $("#district_secteur").find("option:selected").text());
                                                                                }, 500);</script>
                                                                        </label>
                                                                    </c:if>

                                                                </p>
                                                                <p class="card-text">
                                                                    <label id="type_conf">
                                                                        <script language="javascript">setInterval(function () {
                                                                                $("#type_conf").html("<strong>Type client</strong>  : " + $("#type").find("option:selected").text());
                                                                            }, 500);</script>
                                                                    </label>
                                                                </p>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </fieldset>

                                    </form>

                                </div>
                                <p class="card-subtitle line-on-side text-muted text-center font-small-3 mx-2 my-1">
                                    <span><a href="indexclient.jsp" class="btn btn-outline-danger"><i class="ft-unlock"></i> Se connecter</a></span>
                                </p>
                            </div>
                        </div>
                        <p style="text-align: center;color: #666666">&copy; <script> document.write(new Date().getFullYear());</script> Make By <a href="https://www.eh2s.com" target="_blank" >EH2S</a></p>
                    </div>
                    <div class="col-md-2 col-2"></div>
                </div>

            </div>
        </div>
    </div>


    <c:import url="/WEB-INF/footer.jsp"/>
</body>
</html>



