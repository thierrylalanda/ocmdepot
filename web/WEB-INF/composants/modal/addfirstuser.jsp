<%-- 
    Document   : addfirstuser
    Created on : 12 nov. 2018, 15:46:47
    Author     : lalanda
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            <form method="post" action="administration?q=${q}&action=model&model=user&isNew=${isnew}${id}" class="steps-validation wizard-circle">

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
                                    <span class="danger">*</span>
                                </label>
                                <input type="text" value="${user.getLastname()}" class="form-control-sm round form-control required" id="prenom" name="nom1">
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
                <h6>Localisation</h6>
                <fieldset>
                    <div class="row">

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="service">
                                    Service
                                    <span class="danger">*</span>
                                </label>
                                <select style="width: 100%" class="  form-control-sm round  form-control custom-select" required id="service" name="service">
                                    <c:forEach items="${services}" var="ser">
                                        <option <c:if test="${ser.getId() == user.getServiceid().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getName()} (${ser.getSite().getRegionid().getName()})</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="fonction">Fonction 
                                    <span class="danger">*</span></label>
                                <input type="text" value="${user.getFonction()}" class="form-control-sm round form-control required" id="fonction" name="fonction">
                            </div>
                        </div>
                    </div>
                </fieldset>
                <!-- Step 3 -->
                <h6>Profile</h6>
                <fieldset>
                    <div class="row">
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
                                <input type="text" value="${user.getLogin()}" class="form-control-sm round form-control required" id="username" name="login">
                            </div>
                            <div class="form-group">
                                <label for="password">
                                    Mot de passe :
                                    <span class="danger">*</span>
                                </label>
                                <input type="password" value="${user.getPassword()}" class="form-control-sm round form-control required" id="password" name="password">

                            </div>

                        </div>
                        <div class="col-md-6">

                            <div class="form-group">
                                <label for="groupe">
                                    Groupe :
                                    <span class="danger">*</span>
                                </label>
                                <select style="width: 100%" class="  form-control-sm round  form-control custom-select" required id="groupe" name="group">
                                    <c:forEach items="${groupes}" var="gr">
                                        <option <c:if test="${gr.getId() == user.getGroupeid().getId()}">selected</c:if> value="${gr.getId()}">${gr.getName()}</option>
                                    </c:forEach>

                                </select>
                            </div>
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
                                        <h4 class="card-title info">Localisation</h4>
                                        <p class="card-text">
                                            <label id="service_conf">
                                                <script language="javascript">setInterval(function () {
                                                        $("#service_conf").html("<strong>Service</strong>  : " + $("#service").find("option:selected").text());
                                                    }, 500);</script>
                                            </label>
                                        </p>
                                        <p class="card-text">
                                            <label id="fonction_conf">
                                                <script language="javascript">setInterval(function () {
                                                        $("#fonction_conf").html("<strong>Fonction</strong> : " + $("#fonction").val());
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

