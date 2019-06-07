<%-- 
    Document   : editprofil
    Created on : 26 oct. 2018, 14:42:17
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="card">
    <div class="card-header">
        <h4 class="card-title">Profil</h4>
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
            <form method="post" action="ticketClient?q=${q}&action=model&model=changeProfil&isNew=0&client=${client.getId()}" class="" novalidate>

                <!-- Step 1 -->
                <h6>Informations sur votre Profil</h6>
                <fieldset>
                    <div class="row">
                        <div class="col-lg-6 col-md-12 border-success">
                            <div class="card ">
                                <div class="card-content">
                                    <div class="card-body">
                                        <h4 class="card-title info">Profil</h4>
                                        <p class="card-text">
                                            <strong for="firstName">Nom :</strong>
                                            ${client.getNom()}
                                        </p>
                                        <p class="card-text">
                                            <strong for="firstName">Prenom :</strong> 
                                            ${client.getPrenom()}
                                        </p>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label for="date3">Adresse :</label>
                                                    <input type="text" value="${client.getAdresse()}" class="form-control-sm round form-control"  name="adresse" id="date3">
                                                </div>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="phoneNumber3">Tel </label>
                                                    <input type="tel1" value="${client.getTelephone()}" class="form-control-sm round form-control" required name="tel1" id="phoneNumber3">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="phoneNumber3">Tel2 </label>
                                                    <input type="tel2" value="${client.getTelephone2()}" class="form-control-sm round form-control" name="tel2" id="phoneNumber3">
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <div class="form-group">
                                                    <label for="emailAddress5">
                                                        E-Mail :

                                                    </label>
                                                    <input type="email" value="${client.getMail()}" class="form-control-sm round form-control required" required id="emailAddress5" name="email">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-12 border-success">
                            <div class="card ">
                                <div class="card-content">
                                    <div class="card-body">
                                        <h4 class="card-title info">Localisation</h4>
                                        <div class="row">

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="service">
                                                        Secteur

                                                    </label>
                                                    <select disabled style="width: 100%" class="  form-control-sm round  form-control custom-select" required id="service" name="">

                                                        <option selected >${client.getSecteurid().getName()}</option>

                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="service">
                                                        District

                                                    </label>
                                                    <select disabled style="width: 100%" class="  form-control-sm round  form-control custom-select" required id="service" name="service">

                                                        <option selected >${client.getSecteurid().getDistrictid().getName()}</option>

                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="fonction">Type CLient 

                                                        <input type="text" disabled value="${client.getTypeclientid().getName()}" class="form-control-sm round form-control required" id="fonction" name="">
                                                        </div>
                                                        </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label for="username">
                                                                        Code :

                                                                    </label>
                                                                    <input type="text" disabled value="${client.getCodeclient()}" class="form-control-sm round form-control required" id="username" name="">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label for="password">
                                                                        Mot de passe :

                                                                    </label>
                                                                    <input type="password" value="" class="form-control-sm round form-control" id="password" name="password">

                                                                </div>

                                                            </div>
                                                        </div>
                                                </div>


                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </fieldset>
                                <div class="modal-footer text-left">
                                    <input type="submit" class="btn btn-outline-primary btn-lg round btn-sm "
                                           value="Enregister">

                                </div>

                                </form>

                                </p>
                            </div>

                        </div>
                    </div>
