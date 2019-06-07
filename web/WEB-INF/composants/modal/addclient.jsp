<%-- 
    Document   : addclient
    Created on : 17 oct. 2018, 18:06:17
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${client != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&client=${client.getId()}"></c:set>
</c:if>
<c:if test="${client == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${client != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter un client</h3>
                <c:if test="${client == null}">
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
                        <form method="post" action="administration?q=${q}&action=model&model=client&isNew=${isnew}${id}" class="steps-validation wizard-circle">

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
                                            </label>
                                            <input type="email" value="${client.getMail()}" class="form-control-sm round form-control " id="email" name="email">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="code">
                                                Code client :
                                                <span class="danger">*</span>
                                            </label>
                                            <input type="text" value="${client.getCodeclient()}" class="form-control-sm round form-control required" id="code"  <c:if test="${client == null}">code=""</c:if>  name="codeclient">
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
                                                <label for="phone1">Telephone</label>
                                                <input type="tel" name="tel1" value="${client.getTelephone()}" class="form-control-sm round form-control" id="phone1">

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

                                <c:if test="${sessionScope.root=='EH2S'}">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="societe">
                                                    Societe
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%"  id="societe" required class=" select2-size-xs block form-control round required region_by_societe_to_district typeclient_by_societe"  name="societe">
                                                    <option value=" "> </option>
                                                    <c:forEach items="${societes}" var="ser">
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
                                                <select style="width: 100%"  id="societe_region" required class=" select2-size-xs block form-control round required  district_by_region"  name="region">
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
                                                <select style="width: 100%"  id="region_district" required class=" select2-size-xs block form-control round required region_district secteur_by_district"  name="district">
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
                                                <select style="width: 100%"  id="district_secteur" required class=" select2-size-xs block form-control round required district_secteur"  name="secteur">
                                                    <c:forEach items="${secteurs}" var="ser">
                                                        <option <c:if test="${ser.getId() == client.getSecteurid().getId()}">selected</c:if> value="${ser.getId()}"> ${ser.getName()}</option>
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
                                                <label for="secteur">
                                                    Secteur
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%" class="  form-control-sm round  form-control custom-select societe_secteur" required id="secteur" name="secteur">
                                                    <c:forEach items="${secteurs}" var="sec">
                                                        <option <c:if test="${sec.getId()== client.getSecteurid().getId()}">selected</c:if> value="${sec.getId()}">${sec.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </c:if>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="type">
                                                Type client
                                                <span class="danger">*</span>
                                            </label>
                                            <select style="width: 100%" class="  form-control-sm round  form-control custom-select societe_typeclient" required id="type" name="typeclient">
                                                <c:forEach items="${typeclients}" var="tc">
                                                    <option <c:if test="${tc.getId()== client.getTypeclientid().getId()}">selected</c:if> value="${tc.getId()}">${tc.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${sessionScope.societe.getGesttourner()==1}">
                                    <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="code">
                                            Tourner
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%" name="tourner" class="select2-border form-control-sm round block form-control societe_tourner" required id="tourner"
                                                data-border-color="purple" data-border-variation="accent-2"
                                                data-text-color="white">
                                        <c:forEach items="${tourners}" var="cl">
                                            <option <c:if test="${cl.getId()==client.getTourner().getId()}">selected</c:if> value="${cl.getId()}">${cl.getNumc()}</option>
                                        </c:forEach>

                                    </select>     
                                    </div>
                                </div>
                                    </div>
                                </c:if>
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
                                                        <label id="type_conf">
                                                            <script language="javascript">setInterval(function () {
                                                                    $("#type_conf").html("<strong>Type client</strong>  : " + $("#type").find("option:selected").text());
                                                                }, 500);</script>
                                                        </label>
                                                    </p>
                                                    <p class="card-text">
                                                        <c:if test="${sessionScope.root!='EH2S'}">
                                                            <label id="secteur_conf">
                                                                <script language="javascript">setInterval(function () {
                                                                        $("#secteur_conf").html("<strong>Secteur</strong> : " + $("#secteur").find("option:selected").text());
                                                                    }, 500);</script>
                                                            </label>
                                                        </c:if>
                                                        <c:if test="${sessionScope.root=='EH2S'}">
                                                            <label id="secteur_conf">
                                                                <script language="javascript">setInterval(function () {
                                                                        $("#secteur_conf").html("<strong>Secteur</strong> : " + $("#district_secteur").find("option:selected").text());
                                                                    }, 500);</script>
                                                            </label>
                                                        </c:if>

                                                    </p>
                                                    <c:if test="${sessionScope.societe.getGesttourner()==1}">
                                                        <p>
                                                            <label id="tourner_conf">
                                                                <script language="javascript">setInterval(function () {
                                                                        $("#tourner_conf").html("<strong>Tourner</strong> : " + $("#tourner").find("option:selected").text());
                                                                    }, 500);</script>
                                                            </label>
                                                        </p>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>


                        </form>
                    </div>
                    <div class="tab-pane" id="link3" role="tabpanel" aria-labelledby="link-tab3" aria-expanded="false">
                        <form enctype="multipart/form-data" action="UploadFileArticles?q=${q}&fileclients=0" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="secteur">
                                            N° Secteur
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%" class="  form-control-sm round  form-control custom-select" name="">
                                            <c:forEach items="${secteurs}" var="sec">
                                                <option value="${sec.getId()}">${sec.getName()} (${sec.getId()})</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="type">
                                            N° Type client
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%" class="  form-control-sm round  form-control custom-select" name="">
                                            <c:forEach items="${typeclients}" var="tc">
                                                <option value="${tc.getId()}">${tc.getName()} (${tc.getId()})</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="file"   class="form-control-sm round form-control" required  name="file">
                                    </div>
                                </div>

                            </div>
                            <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                                   value="Enregistrer">
                        </form>
                        <code>Assurez vous de respecter ce formatage dans votre fichier excel sans la première ligne</code>
                        <img src="assets/formatage/format_client.png" style="width: 100%" />
                    </div>


                </div>

            </div>
            <c:if test="${client == null}">
                
                <div class = "modal-footer" >
                    <a class = "btn btn-outline-secondary btn-sm round" href = "#" data-dismiss="modal" aria-label="Close" > Annuler</a >
                </div>
            </c:if>

            <c:if test="${client != null}">
                <div class = "modal-footer" >
                    <a class = "btn btn-outline-secondary btn-sm round" href = "javascript:history.go(-1)" > <icon class = "fa fa-arrow-left" > </icon></a >
                </div>
            </c:if>
        </div>
    </div>
</div>
