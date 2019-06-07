<%-- 
    Document   : editVente
    Created on : 21 avr. 2019, 16:55:42
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
<c:set var="id" value="&id=${ligneCommande.getId()}"></c:set>
<c:set var="idc" value="&r=ksdydvgtjkdb"></c:set>
<c:set var="ide" value="&r=ksdydvgtjkdb"></c:set>
<c:if test="${commande != null}">
    <c:set var="isnew" value="${4}"></c:set>
    <c:set var="idc" value="&idC=${commande.getId()}"></c:set>
</c:if>
<c:if test="${commande == null}">
    <c:set var="isnew" value="${1}"></c:set>
</c:if>
<c:if test="${commandeemballage != null}">
    <c:set var="isnewe" value="${8}"></c:set>
    <c:set var="ide" value="&idC=${commandeemballage.getId()}"></c:set>
</c:if>
<c:if test="${commandeemballage == null}">
    <c:set var="isnewe" value="${5}"></c:set>
</c:if>
<section>
    <div class="row ">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">Détails Vente N° ${ligneCommande.getId()} Du : <f:formatDate value="${ligneCommande.getDatec()}" type="BOTH" dateStyle="MEDIUM"/></h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                </div>
                <div class="card-content">
                    <div class="card-body">
                        <ul class="nav nav-tabs nav-linetriangle no-hover-bg nav-justified">
                            <li class="nav-item">
                                <a class="nav-link <c:if test="${isarticle=='yes'}">active</c:if>" id="active-tab3" data-toggle="tab" href="#active3" aria-controls="active3"
                                   aria-expanded="true">Articles</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link <c:if test="${isarticle=='no'}">active</c:if>" id="link-tab3" data-toggle="tab" href="#link3" aria-controls="link3"
                                       aria-expanded="false">Emballages</a>
                                </li>





                            </ul>
                            <div class="tab-content px-1 pt-1">
                                <div role="tabpanel" class="tab-pane <c:if test="${isarticle=='yes'}">active</c:if> in" id="active3" aria-labelledby="active-tab3"
                                     aria-expanded="true">
                                    <div class="card-text">
                                    <c:if test="${1==2}">
                                        <form class="form" novalidate method="post" action="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=12&id=${ligneCommande.getId()}">
                                            <div class="form-body">
                                                <div class="row">

                                                    <div class="form-group col-lg-3 col-sm-12">
                                                        <label for="srubriqueIncident">CLient
                                                            <span class="danger">*</span></label>
                                                        <select style="width: 100%" required  class="select2-size-xs block form-control round" name="client">
                                                            <c:forEach items="${clients}" var="cl">
                                                                <option <c:if test="${ligneCommande.getClient().getId() == cl.getId()}">selected</c:if>  value="${cl.getId()}">${cl.getNom()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group col-lg-2 col-sm-12">
                                                        <label for="categorie_article">Livraison
                                                            <span class="danger">*</span></label>
                                                        <input type="text" value='${ligneCommande.getDateliv()}' class="form-control-sm form-control round singledate" name="njr"/>
                                                    </div>

                                                    <div class="form-control-sm form-group col-lg-3 col-sm-12">
                                                        <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                            <i class="fa fa-edit"></i> Modifier
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>

                                        </form>
                                    </c:if>
                                    <form class="form" method="post" action="gestionCommandes?action=model&model=vente&q=${q}&isNew=${isnew}${id}${idc}">
                                        <fieldset>
                                            <div class="row">

                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="articles_inventaire">Article
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="articles_inventaire_societe"  class="select2-size-xs block form-control round required" name="article">
                                                        <option></option>
                                                        <c:forEach items="${articles}" var="art">
                                                            <option <c:if test="${commande.getArticle().getId()==art.getId()}">selected</c:if> stock="${art.getStock()}" value="${art.getId()}">${art.getNom()}=> ${art.getCode()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="quantite"><span class="danger"></span>Stock</label>
                                                    <input type="number" id="quantite_societe" step="0.01" disabled class="form-control-sm form-control round"/>
                                                </div>

                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="quantite">Qte
                                                        <span class="danger">*</span></label>
                                                    <input type="number" step="0.01" value="${commande.getQuantite()}" id="quantite" value="" class="form-control-sm form-control round required" name="quantite"/>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Prix Divers</label>
                                                    <input type="number" ${commande.getPrix()} value="" class="form-control-sm form-control round" name="prix_divers"/>
                                                </div>
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-save"></i> Ajouter
                                                    </button>
                                                </div>
                                            </div>
                                        </fieldset>

                                    </form>
                                </div>
                                <div class="form-group">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->

                                        <button title="voir" class="btn-sm btn btn-float btn-outline-primary btn-round btn-link-update"
                                                data-toggle="tooltips" name="idC" action="gestionCommandes?action=model&model=vente&q=commande/vente/editVente&isNew=3${id}">
                                            <i class="fa fa-edit"></i>
                                        </button>
                                        <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-update" name="idC" action="gestionCommandes?q=${q}&action=model&model=vente&isNew=2${id}"><i class="fa fa-trash"></i></button>

                                    </div>
                                </div>
                                <table style="width: 100%" class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Article</th>
                                            <th>P.U</th>
                                            <th>Qte </th>
                                            <th>P.T</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total" value="0"></c:set>
                                        <c:set var="pt" value="0"></c:set>
                                        <c:set var="pp" value="0"></c:set>
                                        <c:forEach items="${ligneCommande.getTcommandesList()}" var="cmd">
                                            <c:set var="total" value="${total + cmd.getQuantite()}"></c:set>
                                            <c:set var="pt" value="${pt + cmd.getPrixTotal()}"></c:set>
                                            <c:set var="pp" value="${pp + cmd.getQt()}"></c:set>
                                                <tr>
                                                    <td><option selected  class="hidden">${cmd.getId()}</option> </td>
                                        <td>${cmd.getArticle().getCode()}</td>
                                        <td>${cmd.getPrix()}</td>
                                        <td>${cmd.getQuantite()}</td>
                                        <td>${cmd.getPrixTotal()}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr style="font-weight: bold; color: #00b5b8">
                                            <td></td>
                                            <td></td>
                                            <td>TOTAL</td>
                                            <td>${total}</td>
                                            <td><f:formatNumber value="${pt}"type="CURRENCY" currencySymbol=""/></td>

                                        </tr>
                                    </tfoot>
                                </table>
                            </div>


                            <div class="tab-pane <c:if test="${isarticle=='no'}">active</c:if>" id="link3" role="tabpanel" aria-labelledby="link-tab3" aria-expanded="false">

                                    <div class="card-text">
                                        <form class="form" method="post" action="gestionCommandes?action=model&model=vente&q=${q}&isNew=${isnewe}${id}${ide}">
                                        <fieldset>
                                            <div class="row">

                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="articles_inventaire">Emballages
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required  class="select2-size-xs block form-control round required" name="article">
                                                        <option></option>
                                                        <c:forEach items="${emballages}" var="arte">
                                                            <option <c:if test="${commandeemballage.getEmballage().getId()==arte.getId()}">selected</c:if> stock="${arte.getStock()}" value="${arte.getId()}">${arte.getNom()}=> ${arte.getCode()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="quantite">Qte
                                                        <span class="danger">*</span></label>
                                                    <input type="number" value="${commandeemballage.getQuantite()}" value="" class="form-control-sm form-control round required" name="quantite"/>
                                                </div>
                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="quantite">Prix Divers</label>
                                                    <input type="number"  value="" class="form-control-sm form-control round" name="prix_divers"/>
                                                </div>
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-save"></i> Ajouter
                                                    </button>
                                                </div>
                                            </div>
                                        </fieldset>

                                    </form>
                                </div>
                                <div class="form-group">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->

                                        <button title="voir" class="btn-sm btn btn-float btn-outline-primary btn-round btn-link-update"
                                                data-toggle="tooltips" name="idC" action="gestionCommandes?action=model&model=vente&q=commande/vente/editVente&isNew=7${id}">
                                            <i class="fa fa-edit"></i>
                                        </button>
                                        <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-update" name="idC" action="gestionCommandes?q=${q}&action=model&model=vente&isNew=6${id}"><i class="fa fa-trash"></i></button>

                                    </div>
                                </div>
                                <table style="width: 100%" class="table table-dark table-striped table-bordered responsive dataex-select-checkbox">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom</th>
                                            <th>P.U</th>
                                            <th>Qte </th>
                                            <th>P.T</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="totale" value="0"></c:set>
                                        <c:set var="pte" value="0"></c:set>
                                        <c:set var="ppe" value="0"></c:set>
                                        <c:forEach items="${ligneCommande.getCommandeEmballageList()}" var="cmde">
                                            <c:set var="totale" value="${totale + cmde.getQuantite()}"></c:set>
                                            <c:set var="pte" value="${pte + cmde.getPrixTotal()}"></c:set>
                                            <c:set var="ppe" value="${ppe + cmd.getQt()}"></c:set>
                                                <tr>
                                                    <td><option selected  class="hidden">${cmde.getId()}</option> </td>
                                        <td>${cmde.getEmballage().getCode()}</td>
                                        <td>${cmde.getPrix()}</td>
                                        <td>${cmde.getQuantite()}</td>
                                        <td>${cmde.getPrixTotal()}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr style="font-weight: bold; color: #00b5b8">
                                            <td></td>
                                            <td></td>
                                            <td>TOTAL</td>
                                            <td>${totale}</td>
                                            <td><f:formatNumber value="${pte}"type="CURRENCY" currencySymbol=""/></td>

                                        </tr>
                                    </tfoot>
                                </table>
                            </div>


                        </div>
                        <a class="btn btn-outline-secondary btn-sm round" href="gestionCommandes?action=model&model=HistoriqueCommande&q=commande/vente/venteEncours&isNew=0"><icon class="fa fa-arrow-left"></icon></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</section>