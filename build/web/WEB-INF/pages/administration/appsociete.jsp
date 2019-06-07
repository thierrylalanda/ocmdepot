<%-- 
    Document   : appsociete
    Created on : 12 nov. 2018, 15:54:03
    Author     : lalanda
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${not empty sessionScope.lien1}">
    <c:if test="${societe != null}">
        <c:set var="isnew" value="${1}"></c:set>
        <c:set var="id" value="&id=${societe.getId()}"></c:set>
    </c:if>
    <c:if test="${societe== null}">
        <c:set var="isnew" value="${0}"></c:set>
        <c:set var="id" value="&id=${0}"></c:set>
    </c:if>
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

                        </div>
                        <form class="form" enctype="multipart/form-data" novalidate method="post" action="administration?q=${q}&action=model&model=societe&isNew=${isnew}${id}" >
                            <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                                   value="Enregistrer" />

                            <fieldset>
                                <br>
                                <div class="row">
                                    <div class="col-lg-6 col-md-12 border-success">
                                        <div class="card ">
                                            <div class="card-content">
                                                <div class="card-body">
                                                    <h4 class="card-title info">Infos Société</h4>
                                                    <div class="row">
                                                        <div class="form-group col-12 mb-2">
                                                            <label for="complaintinput1">Nom societe
                                                                <span class="required">*</span>
                                                            </label>

                                                            <input type="text" id="complaintinput1" value="${sessionScope.societe.getNom()}" class="form-control-sm form-control round" placeholder="nom de la societe"
                                                                   name="nom"
                                                                   required>

                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-6 mb-2">
                                                            <label for="sigle">Sigle
                                                                <span class="required">*</span></label>

                                                            <input type="text" id="sigle" value="${sessionScope.societe.getSigle()}" class="form-control-sm form-control round" name="sigle" required>

                                                        </div>
                                                        <div class="form-group col-6 mb-2">
                                                            <label for="complaintinput4">CNI
                                                                <span class="required">*</span></label>

                                                            <input type="text" id="complaintinput4"value="${sessionScope.societe.getCniRc()}" required class="form-control-sm form-control round" placeholder=""
                                                                   name="cnirc" >

                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-6 mb-2">
                                                            <label for="regimeF">Régime fiscal
                                                                <span class="required">*</span></label>

                                                            <input type="text" id="regimeF" value="${sessionScope.societe.getRegimeFiscal()}" class="form-control-sm form-control round" name="regimeFiscal" required>

                                                        </div>
                                                        <div class="form-group col-6 mb-2">
                                                            <label for="centreI">Centre Impôt
                                                                <span class="required">*</span></label>

                                                            <input type="text" id="centreI"value="${sessionScope.societe.getCentreImpot()}" required class="form-control-sm form-control round" placeholder=""
                                                                   name="centreImpot" >

                                                        </div>

                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-6 mb-2">
                                                            <label for="matricule">N° Contribuable</label>

                                                            <input type="text" id="matricule"value="${sessionScope.societe.getImmatriculation()}" class="form-control-sm form-control round" placeholder="N° de contribuable" name="imma" >

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
                                                    <h4 class="card-title info">Adresse</h4>
                                                    <div class="row">
                                                        <div class="form-group col-12 mb-2">
                                                            <label for="complaintinput2">Adresse
                                                                <span class="required">*</span>
                                                            </label>

                                                            <input type="text" id="complaintinput2" value="${sessionScope.societe.getAdresse()}" class="form-control-sm form-control round" placeholder="adresse"
                                                                   name="adresse"
                                                                   required>

                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-6 mb-2">
                                                            <label for="complaintinput3">Tel
                                                                <span class="required">*</span></label>

                                                            <input type="tel" id="complaintinput3" value="${sessionScope.societe.getTel()}" class="form-control-sm form-control round" name="tel" required>

                                                        </div>
                                                        <div class="form-group col-6 mb-2">
                                                            <label for="complaintinput4">Code Postal</label>

                                                            <input type="text" id="complaintinput4"value="${sessionScope.societe.getCodePostal()}" class="form-control-sm form-control round" placeholder="code postal"
                                                                   name="codeposte" >

                                                        </div>
                                                    </div>
                                                    <div class="row">

                                                        <div class="form-group col-6 mb-2">
                                                            <label for="complaintinput5">E-Mail
                                                                <span class="required">*</span></label>
                                                            <input type="email" id="complaintinput5"value="${sessionScope.societe.getEmail()}" class="form-control-sm form-control round" placeholder="adresse mail"
                                                                   name="email" required>

                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6 col-md-12 border-success">
                                        <div class="card ">
                                            <div class="card-content">
                                                <div class="card-body">
                                                    <h4 class="card-title info">Activité</h4>
                                                    <div class="row">
                                                        <div class="form-group col-12 mb-2">
                                                            <label for="activite">Activité
                                                                <span class="required">*</span></label>

                                                            <textarea id="activite" maxlength="255" name="activite" rows="10" required class="form-control-sm form-control round">${sessionScope.societe.getActivite()}</textarea>


                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-12 border-primary">
                                        <div class="card ">
                                            <div class="card-content">
                                                <div class="card-body">
                                                    <h4 class="card-title info">Autres Informations</h4>
                                                    <div class="row">

                                                        <div class="form-group col-6 mb-2">
                                                            <label for="complaintinput5">Gérer mon stock
                                                                <span class="required">*</span></label>
                                                            <select  class="form-control-sm form-control round"
                                                                     name="geststock" required>
                                                                <option value="0" <c:if test="${sessionScope.societe.getGestStock()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${sessionScope.societe.getGestStock()==1}">selected</c:if>>Oui</option>
                                                                </select>

                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Client anonyme
                                                                    <span class="required">*</span></label>
                                                                <select class="form-control-sm form-control round"
                                                                        name="autoclient" required>
                                                                    <option value="0" <c:if test="${sessionScope.societe.getAutoSaveClient()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${sessionScope.societe.getAutoSaveClient()==1}">selected</c:if>>Oui</option>
                                                                </select>

                                                            </div>
                                                        </div>

                                                        <div class="row">

                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Ristourne et Marge Direct
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestmarge" required>
                                                                    <option value="0" <c:if test="${sessionScope.societe.getGestmarge()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${sessionScope.societe.getGestmarge()==1}">selected</c:if>>Oui</option>
                                                                </select>

                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion des casiers
                                                                    <span class="required">*</span></label>
                                                                <select class="form-control-sm form-control round"
                                                                        name="gestcassier" required>
                                                                    <option value="0" <c:if test="${sessionScope.societe.getGestcassier()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${sessionScope.societe.getGestcassier()==1}">selected</c:if>>Oui</option>
                                                                </select>

                                                            </div>
                                                        </div>
                                                        <!--                                                                 Gestion :   <input type="checkbox" class="switch" id="switch" data-icon-cls="fa" data-off-icon-cls="fa-thumbs-o-down"
                                                                                                                                          data-on-icon-cls="fa-thumbs-o-up" data-group-cls="btn-group-sm"
                                                                                                                                                           />-->

                                                        <div class="row">
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion des tournées
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gesttourner" required>
                                                                    <option value="0" <c:if test="${sessionScope.societe.getGesttourner()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${sessionScope.societe.getGesttourner()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion des fournisseurs
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestfournisseur" required>
                                                                    <option value="0" <c:if test="${sessionScope.societe.getGestfournisseur()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${sessionScope.societe.getGestfournisseur()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion des magasins
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestmagasin" required>
                                                                    <option value="0" <c:if test="${sessionScope.societe.getGestmagasin()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${sessionScope.societe.getGestmagasin()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion Caisse
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestcaisse" required>
                                                                    <option value="0" <c:if test="${sessionScope.societe.getGestcaisse()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${sessionScope.societe.getGestcaisse()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion Emballage
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestemballage" required>
                                                                    <option value="0" <c:if test="${sessionScope.societe.getGestemballage()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${sessionScope.societe.getGestemballage()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                        <c:if test="${sessionScope.societe.getLogo() == null}">
                                                            <div class="col-md-4">
                                                                <label class="file center-block">
                                                                    Logo de la societe
                                                                </label><br>
                                                                <img  src="assets/images/no_image.png" style="max-width: 80px;max-height: 80px" />

                                                            </div>
                                                        </c:if>
                                                        <c:if test="${sessionScope.societe.getLogo() != null}">
                                                            <div class="col-md-4">
                                                                <label class="file center-block">
                                                                    Logo de la societe
                                                                </label><br>
                                                                <img  src="data:image/png;base64,${sessionScope.societe.getLogoBase64()}" style="max-width: 80px;max-height: 80px" />

                                                            </div>
                                                        </c:if>
                                                        <div class="form-group col-12 mb-2">
                                                            <label for="complaintinput6">Logo</label>

                                                            <input type="file" id="complaintinput6" class="form-control-sm form-control round" placeholder="signature" name="logo">

                                                        </div>
                                                    </div>


                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </fieldset>

                            <br>
                            <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                                   value="Enregistrer">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
