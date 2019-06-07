<%-- 
    Document   : addsociete
    Created on : 12 nov. 2018, 15:24:16
    Author     : lalanda
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${onesociete != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&id=${onesociete.getId()}"></c:set>
</c:if>
<c:if test="${onesociete == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${onesociete != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter une société</h3>
                <c:if test="${onesociete == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <form class="form admin-add-societe-form" enctype="multipart/form-data" novalidate method="post" action="administration?q=${q}&action=model&model=societe&isNew=${isnew}${id}" >
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

                                                    <input type="text" id="complaintinput1" value="${onesociete.getNom()}" class="form-control-sm form-control round" placeholder="nom de la societe"
                                                           name="nom"
                                                           required>

                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-6 mb-2">
                                                    <label for="sigle">Sigle
                                                        <span class="required">*</span></label>

                                                    <input type="text" id="sigle" value="${onesociete.getSigle()}" class="form-control-sm form-control round" name="sigle" required>

                                                </div>
                                                <div class="form-group col-6 mb-2">
                                                    <label for="complaintinput4">CNI
                                                        <span class="required">*</span></label>

                                                    <input type="text" id="complaintinput4"value="${onesociete.getCniRc()}" required class="form-control-sm form-control round" placeholder=""
                                                           name="cnirc" >

                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-6 mb-2">
                                                    <label for="regimeF">Régime fiscal
                                                        <span class="required">*</span></label>

                                                    <input type="text" id="regimeF" value="${onesociete.getRegimeFiscal()}" class="form-control-sm form-control round" name="regimeFiscal" required>

                                                </div>
                                                <div class="form-group col-6 mb-2">
                                                    <label for="centreI">Centre Impôt
                                                        <span class="required">*</span></label>

                                                    <input type="text" id="centreI"value="${onesociete.getCentreImpot()}" required class="form-control-sm form-control round" placeholder=""
                                                           name="centreImpot" >

                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="form-group col-6 mb-2">
                                                    <label for="matricule">N° Contribuable</label>

                                                    <input type="text" id="matricule"value="${onesociete.getImmatriculation()}" class="form-control-sm form-control round" placeholder="N° de contribuable" name="imma" >

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

                                                    <input type="text" id="complaintinput2" value="${onesociete.getAdresse()}" class="form-control-sm form-control round" placeholder="adresse"
                                                           name="adresse"
                                                           required>

                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-6 mb-2">
                                                    <label for="complaintinput3">Tel
                                                        <span class="required">*</span></label>

                                                    <input type="tel" id="complaintinput3" value="${onesociete.getTel()}" class="form-control-sm form-control round" name="tel" required>

                                                </div>
                                                <div class="form-group col-6 mb-2">
                                                    <label for="complaintinput4">Code Postal</label>

                                                    <input type="text" id="complaintinput4"value="${onesociete.getCodePostal()}" class="form-control-sm form-control round" placeholder="code postal"
                                                           name="codeposte" >

                                                </div>
                                            </div>
                                            <div class="row">

                                                <div class="form-group col-6 mb-2">
                                                    <label for="complaintinput5">E-Mail
                                                        <span class="required">*</span></label>
                                                    <input type="email" id="complaintinput5"value="${onesociete.getEmail()}" class="form-control-sm form-control round" placeholder="adresse mail"
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

                                                    <textarea id="activite" maxlength="255" name="activite" rows="10" required class="form-control-sm form-control round">${onesociete.getActivite()}</textarea>


                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="row skin skin-line">
                                    <h5 class="info">Maintenance</h5>
                                    <div class="col-md-12 col-sm-12">
                                        <fieldset>
                                            <input type="radio" value="1" <c:if test="${onesociete.getMaintenance()==1}">checked</c:if> name="maintenance" id="input-radio-6">
                                                <label for="input-radio-1">En Maintenance</label>
                                            </fieldset>
                                            <fieldset>
                                                <input type="radio" value="0" <c:if test="${onesociete.getMaintenance()==0}">checked</c:if>  name="maintenance" id="input-radio-7">
                                                <label for="input-radio-2">Hors Maintenance</label>
                                            </fieldset>

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
                                                                <option value="0" <c:if test="${onesociete.getGestStock()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${onesociete.getGestStock()==1}">selected</c:if>>Oui</option>
                                                                </select>

                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Client anonyme
                                                                    <span class="required">*</span></label>
                                                                <select class="form-control-sm form-control round"
                                                                        name="autoclient" required>
                                                                    <option value="0" <c:if test="${onesociete.getAutoSaveClient()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${onesociete.getAutoSaveClient()==1}">selected</c:if>>Oui</option>
                                                                </select>

                                                            </div>
                                                        </div>

                                                        <div class="row">

                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Ristourne et Marge Direct
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestmarge" required>
                                                                    <option value="0" <c:if test="${onesociete.getGestmarge()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${onesociete.getGestmarge()==1}">selected</c:if>>Oui</option>
                                                                </select>

                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion des casiers
                                                                    <span class="required">*</span></label>
                                                                <select class="form-control-sm form-control round"
                                                                        name="gestcassier" required>
                                                                    <option value="0" <c:if test="${onesociete.getGestcassier()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${onesociete.getGestcassier()==1}">selected</c:if>>Oui</option>
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
                                                                    <option value="0" <c:if test="${onesociete.getGesttourner()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${onesociete.getGesttourner()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion des fournisseurs
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestfournisseur" required>
                                                                    <option value="0" <c:if test="${onesociete.getGestfournisseur()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${onesociete.getGestfournisseur()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion des magasins
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestmagasin" required>
                                                                    <option value="0" <c:if test="${onesociete.getGestmagasin()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${onesociete.getGestmagasin()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion Caisse
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestcaisse" required>
                                                                    <option value="0" <c:if test="${onesociete.getGestcaisse()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${onesociete.getGestcaisse()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput5">Gestion Emballage
                                                                    <span class="required">*</span></label>
                                                                <select  class="form-control-sm form-control round"
                                                                         name="gestemballage" required>
                                                                    <option value="0" <c:if test="${onesociete.getGestemballage()==0}">selected</c:if> >Non</option>
                                                                <option value="1" <c:if test="${onesociete.getGestemballage()==1}">selected</c:if>>Oui</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                        <c:if test="${onesociete.getLogo() == null}">
                                                            <div class="col-md-4">
                                                                <label class="file center-block">
                                                                    Logo de la societe
                                                                </label><br>
                                                                <img  src="assets/images/no_image.png" style="max-width: 80px;max-height: 80px" />

                                                            </div>
                                                        </c:if>
                                                        <c:if test="${onesociete.getLogo() != null}">
                                                            <div class="col-md-4">
                                                                <label class="file center-block">
                                                                    Logo de la societe
                                                                </label><br>
                                                                <img  src="data:image/png;base64,${onesociete.getLogoBase64()}" style="max-width: 80px;max-height: 80px" />

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
            <c:if test="${onesociete == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${onesociete != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>

        </div>
    </div>
</div>


