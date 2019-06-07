<%-- 
    Document   : adduser
    Created on : 16 oct. 2018, 18:08:47
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.eh2s.ocm.Entity.Tregions" %>
<%@page import="com.eh2s.ocm.Entity.Tusers" %>
<%@page import="com.eh2s.ocm.Entity.Taffectzone" %>
<%@page import="com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal" %>
<%@page import="com.eh2s.ocm.Entity.Sessions.TregionsFacade" %>
<!DOCTYPE html>
<% 
 TregionsFacadeLocal tregionsfacadelocal  ;
%>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${user != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&user=${user.getId()}"></c:set>
</c:if>
<c:if test="${user == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade text-left <c:if test="${user != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter un nouvel utilisateur</h3>
                <c:if test="${user == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form autocomplete="off" method="post" action="administration?q=${q}&action=model&model=user&isNew=${isnew}${id}" class="steps-validation wizard-circle add_user_form">
                    <!-- Step 1 -->
                    <h6>Infos Utilisateur</h6>
                    <fieldset>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nom">
                                        Nom :
                                        <span class="danger">*</span>
                                    </label>
                                    <input type="text" value="${user.getFirstname()}" class="form-control-sm round form-control required" id="nom" name="nom">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="prenom">
                                        Prenom :
                                    </label>
                                    <input type="text" value="${user.getLastname()}" class="form-control-sm round form-control" id="prenom" name="nom1">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="email">
                                        E-Mail :
                                        <span class="danger">*</span>
                                    </label>
                                    <input type="email" value="${user.getMail()}" class="form-control-sm round form-control required" id="email" name="email">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="phone1">Tel </label>
                                    <input type="tel" value="${user.getPhone()}" class="form-control-sm round form-control" name="tel" id="phone1">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="adresse">Adresse :</label>
                                    <input type="text" value="${user.getAddress1()}" class="form-control-sm round form-control" name="adresse" id="adresse">
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <!-- Step 2 -->
                    <h6>Profil</h6>
                    <fieldset>
                        <c:if test="${sessionScope.root=='EH2S'}">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="societe">
                                            Societe
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  id="societe"  class=" select2-size-xs block form-control round required region_by_societe_to_district groupe_by_societe"  name="societe">
                                            <option value=" "> </option>
                                            <c:forEach items="${societes}" var="ser">
                                                <option <c:if test="${ser.getId() == user.getServiceid().getSite().getRegionid().getSociete().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getNom()}</option>
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
                                            <c:forEach items="${regions}" var="ser">
                                                <option <c:if test="${ser.getId() == user.getServiceid().getSite().getRegionid().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getName()}</option>
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
                                            <c:forEach items="${sites}" var="ser">
                                                <option <c:if test="${ser.getId() == user.getServiceid().getSite().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getName()}</option>
                                            </c:forEach>
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
                                            <c:forEach items="${services}" var="ser">
                                                <option <c:if test="${ser.getId() == user.getServiceid().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getName()} (${ser.getSite().getRegionid().getName()})</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <div class="row">
                            <c:if test="${sessionScope.root!='EH2S'}">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="service">
                                            Service
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%" class="  form-control-sm round  form-control custom-select societe_service" required id="service" name="service">
                                            <c:forEach items="${services}" var="ser">
                                                <option <c:if test="${ser.getId() == user.getServiceid().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getName()} (${ser.getSite().getRegionid().getName()})</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="fonction">Fonction 
                                        <span class="danger">*</span></label>
                                    <input type="text" value="${user.getFonction()}" class="form-control-sm round form-control required" id="fonction" name="fonction">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label for="groupe">
                                        Groupe :
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" class="  form-control-sm round  form-control custom-select societe_groupe" required id="groupe" name="group">
                                        <c:forEach items="${groupes}" var="gr">
                                            <option <c:if test="${gr.getId() == user.getGroupeid().getId()}">selected</c:if> value="${gr.getId()}">${gr.getName()}</option>
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label for="etat">
                                        Etat :
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" class="  form-control-sm round  form-control custom-select" required id="etat" name="etat">

                                        <option <c:if test="${user.getDisable()==1}">selected</c:if> value="1">Activer</option>

                                            <option <c:if test="${user.getDisable()==0}">selected</c:if> value="0">Desactiver</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="username">
                                            Nom utilisateur :
                                            <span class="danger">*</span>
                                        </label>
                                        <input type="text" value="${user.getLogin()}" class="form-control-sm round form-control required new_username" id="username" <c:if test="${user == null}">username=""</c:if> name="login">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="password">
                                            Mot de passe :
                                        <c:if test="${user == null}">
                                            <span class="danger">*</span>
                                        </c:if>

                                    </label>
                                    <input type="password"  class="form-control-sm round form-control <c:if test="${user == null}">required</c:if>" id="password"  name="password">

                                    </div>

                                </div>

                            </div>
                            <div class="row">
                            <c:if test="${sessionScope.societe.getGesttourner()==1}">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="code">
                                            Tourner
                                        </label>
                                        <select style="width: 100%" name="tourner" class="select2-border form-control-sm round block form-control" id="" multiple="multiple"
                                                data-border-color="purple" data-border-variation="accent-2"
                                                data-text-color="white">
                                            <c:if test="${not empty user.getAffectTournerUserList()}">

                                                <c:forEach items="${tourners}" var="cl">
                                                    <c:set var="has" value="no"></c:set>
                                                    <c:forEach items="${user.getAffectTournerUserList()}" var="us">
                                                        <c:if test="${us.getTourner().getId()==cl.getId()}">
                                                            <option selected value="${cl.getId()}">${cl.getNumc()}</option>
                                                            <c:set var="has" value="yes"></c:set>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${has == 'no'}">
                                                        <option value="${cl.getId()}">${cl.getNumc()}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty user.getAffectTournerUserList()}">
                                                <c:forEach items="${tourners}" var="cl">
                                                    <option value="${cl.getId()}">${cl.getNumc()}</option>
                                                </c:forEach>
                                            </c:if>

                                        </select>     
                                    </div>
                                </div>
                            </c:if>
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label for="etat">
                                        Groupe APK :
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" class="  select2-border block form-control round"  multiple="multiple" name="role">
                                    <c:if test="${empty user.getRoleApkList()}">
                                            <c:forEach items="${actionAPK}" var="rol">
                                                <option  value="${rol.getId()}">${rol.getNom()}</option>
                                            </c:forEach>
                                        </c:if> 
                                        <c:if test="${not empty user.getRoleApkList()}">
                                            <c:forEach items="${actionAPK}" var="rol">
                                                <c:set var="has" value="no"></c:set>
                                                <c:forEach items="${user.getRoleApkList()}" var="ur">
                                                    <c:if test="${ur.getRole().getId()==rol.getId()}">
                                                        <option selected  value="${rol.getId()}"> ${rol.getNom()}</option>
                                                        <c:set var="has" value="yes"></c:set>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${has !='yes'}">
                                                    <option  value="${rol.getId()}">${rol.getNom()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </c:if> 
                                    </select>



                                </div>
                            </div>
                        </div>

                    </fieldset>

                    <h6>Parametrage</h6>
                    <fieldset>
                        <div class="row">
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label for="groupe">
                                        Champs d'action
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" class="  form-control-sm round  form-control custom-select"  required id="zone" name="zone">
                                        <c:if test="${user.getTaffectzoneList().size() != 0}">
                                            <option <c:if test="${user.getTaffectzoneList().get(0).getRegion() == null && user.getTaffectzoneList().get(0).getDistrict() == null &&  user.getTaffectzoneList().get(0).getSecteur() == null}">selected</c:if>  value="aucun">Aucun</option>
                                            <option <c:if test="${user.getTaffectzoneList().get(0).getRegion() != null}">selected</c:if> value="region">Region</option>
                                            <option <c:if test="${user.getTaffectzoneList().get(0).getDistrict() != null}">selected</c:if> value="district">Villes</option>
                                            <option <c:if test="${user.getTaffectzoneList().get(0).getSecteur() != null}">selected</c:if> value="secteur">Secteurs</option>
                                        </c:if>
                                        <c:if test="${user.getTaffectzoneList().size() == 0}">
                                            <option value="aucun" selected>Aucun</option>
                                            <option  value="region">Region</option>
                                            <option  value="district">Villes</option>
                                            <option  value="secteur">Secteurs</option>
                                        </c:if>

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label for="etat">
                                        Fermer Incident :
                                        <span class="danger">*</span>
                                    </label>
                                    <select style="width: 100%" class="  form-control-sm round  form-control custom-select" required id="fi" name="fi">

                                        <option <c:if test="${user.getFi()==1}">selected</c:if> value="1">Oui</option>

                                            <option <c:if test="${user.getFi()==0}">selected</c:if> value="0">Non</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="row other">
                                <div class="col-md-6 region_zone_societe">

                                    <div class="form-group">
                                        <label for="region">
                                            Région
                                        </label>
                                        <select style="width: 100%" class="form-control-sm round  form-control select2-size-xs " id="" multiple="multiple" name="region">
                                        <c:forEach items="${regions}" var="reg">
                                            <option  value="${reg.getId()}">${reg.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6 region_zone">
                                <div class="form-group">
                                    <label for="region">
                                        Région
                                    </label>
                                    <select style="width: 100%" class="form-control-sm round  form-control custom-select district_by_region" id="district_by_region" name="">
                                        <option value="0"></option>
                                        <c:forEach items="${regions}" var="reg">
                                            <option  value="${reg.getId()}">${reg.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6 villes region_zone">
                                <div class="form-group">
                                    <label for="zone">
                                        Villes
                                    </label>
                                    <select style="width: 100%" class="form-control-sm round  form-control custom-select region_district secteur_by_district" id="region_district" name="district">

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6 secteurs">

                            </div>
                        </div> 
                    </fieldset>
                    <!-- Step 4 -->
                    <h6>Confirmation</h6>
                    <fieldset>
                        <div class="row">
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Utilisateur</h4>
                                            <p class="card-text">
                                                <label id="nom_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#nom_conf").html("<strong>Nom </strong>: " + $("#nom").val());
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
                                                <label id="phone1_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#phone1_conf").html("<strong>Telephone</strong>  : " + $("#phone1").val());
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
                                            <h4 class="card-title info">Profil</h4>
                                            <p class="card-text">
                                                <c:if test="${sessionScope.root!='EH2S'}">
                                                    <label id="service_conf">
                                                        <script language="javascript">setInterval(function () {
                                                                $("#service_conf").html("<strong>Service</strong>  : " + $("#service").find("option:selected").text());
                                                            }, 500);</script>
                                                    </label>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S'}">
                                                    <label id="service_conf">
                                                        <script language="javascript">setInterval(function () {
                                                                $("#service_conf").html("<strong>Service</strong>  : " + $("#site_service").find("option:selected").text());
                                                            }, 500);</script>
                                                    </label>
                                                </c:if>

                                            </p>
                                            <p class="card-text">
                                                <label id="fonction_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#fonction_conf").html("<strong>Fonction</strong> : " + $("#fonction").val());
                                                        }, 500);</script>
                                                </label>
                                            </p>
                                            <p class="card-text">
                                                <label id="etat_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#etat_conf").html("<strong>Etat</strong>  : " + $("#etat").find("option:selected").text());
                                                        }, 500);</script>
                                                </label>
                                            </p>
                                            <p class="card-text">
                                                <label id="groupe_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#groupe_conf").html("<strong>Groupe</strong>  : " + $("#groupe").find("option:selected").text());
                                                        }, 500);</script>
                                                </label>
                                            </p>
                                            <p class="card-text">
                                                <label id="username_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#username_conf").html("<strong>Nom utilisateur</strong> : " + $("#username").val());
                                                        }, 500);</script>
                                                </label>
                                            </p>
                                            <p class="card-text">
                                                <label id="password_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#password_conf").html("<strong>Mot de Passe</strong>  : " + $("#password").val());
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
                                            <h4 class="card-title info">Parametrage</h4>
                                            <p class="card-text">
                                                <label id="fi_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#fi_conf").html("<strong>Fermer Incident </strong>  : " + $("#fi").find("option:selected").text());
                                                        }, 500);</script>
                                                </label>
                                            </p>
                                            <p class="card-text">
                                                <label id="zone_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#zone_conf").html("<strong>Champ d'action </strong>  : " + $("#zone").find("option:selected").text());
                                                        }, 500);</script>
                                                </label>
                                            </p>
                                            <p class="card-text">
                                                <label id="district_by_region_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#district_by_region_conf").html("<strong>Region </strong>  : " + $("#district_by_region").find("option:selected").text());
                                                        }, 500);</script>
                                                </label>
                                            </p>
                                            <p class="card-text">
                                                <label id="region_district_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            $("#region_district_conf").html("<strong>Ville </strong>  : " + $("#region_district").find("option:selected").text());
                                                        }, 500);</script>
                                                </label>
                                            </p>
                                            <p class="card-text">
                                                <label id="district_secteur_conf">
                                                    <script language="javascript">setInterval(function () {
                                                            var secteur = "";
                                                            $("#district_secteur").find("option:selected").each(function (index) {
                                                                secteur += $(this).text() + " , "
                                                            });
                                                            $("#district_secteur_conf").html("<strong>Secteur </strong>  : " + secteur);
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
            <c:if test="${user == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${user != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>
        </div>
    </div>
</div>
