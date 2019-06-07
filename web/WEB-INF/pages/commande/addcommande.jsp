<%-- 
    Document   : addcommande
    Created on : 26 nov. 2018, 16:14:07
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien97}">
    <c:set var="id" value="&r=7tyui895qze412"></c:set>
    <c:if test="${commande != null}">
        <c:set var="isnew" value="${5}"></c:set>
        <c:set var="id" value="&id=${commande.getArticle().getId()}"></c:set>
    </c:if>
    <c:if test="${commande == null}">
        <c:set var="isnew" value="${0}"></c:set>
    </c:if>
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <div class="row addCommades">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title" id="from-actions-center-dropdown">Nouvelle Pre-Vente</h4>
                    <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                        <c:if test="${sessionScope.societe.getGestStock()==1}">
                        <h3> <span class="badge badge-danger alert_stock" style="cursor:pointer"></span> </h3> 
                    </c:if>

                </div>
                <div class="card-content">
                    <div class="card-body">
                        <div class="card-text">
                            <c:if test="${sessionScope.societe.getGesttourner() == 1}">
                                <form class="form" method="post" action="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=8">
                                    <fieldset>
                                        <div class="row">
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label for="code">
                                                        Tourner
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <select style="width: 100%"   id="find_client_tourner" class="select2-size-xs block form-control round " name="numc">
                                                        <option value=""></option>
                                                        <c:forEach items="${tourners}" var="tr">
                                                            <option class="find_client_tourner" <c:if test="${sessionScope.tourner != null}">disabled</c:if> <c:if test="${tr.getId()==sessionScope.tourner.getId()}">selected</c:if> value="${tr.getNumc()}"> ${tr.getNumc()}</option>
                                                        </c:forEach>
                                                    </select>    
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                                <c:if test="${sessionScope.tourner != null}">
                                    <form class="form" method="post" action="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=${isnew}${id}">
                                        <fieldset>
                                            <div class="row">
                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="complaintinput2">Client
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%"  id="complaintinput2" class="select2-size-xs block form-control round " name="client">
                                                        <c:forEach items="${sessionScope.tourner.getTclientsList()}" var="cl">
                                                            <option <c:if test="${sessionScope.cmdclient != null}">disabled</c:if> <c:if test="${cl.getId()==sessionScope.cmdclient.getId()}">selected</c:if> value="${cl.getId()}"> ${cl.getNom()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="form-group col-lg-2 col-sm-12">
                                                    <label for="categorie_article">Livraison
                                                        <span class="danger">*</span></label>
                                                    <input type="text" <c:if test="${sessionScope.njr != null}">disabled value="${sessionScope.njr}"</c:if> class="form-control-sm form-control round singledate" name="njr"/>
                                                    </div>
                                                </div>
                                            </fieldset>    
                                            <fieldset>
                                                <div class="row">

                                                    <div class="form-group col-lg-3 col-sm-12">
                                                        <label for="articles_categorie">Article
                                                            <span class="danger">*</span></label>
                                                        <select style="width: 100%" required id="articles_inventaire_societe"  class="select2-size-xs block form-control round required" name="article">
                                                        <c:if test="${commande != null}">
                                                            <option selected value="${commande.getArticle().getId()}"> ${commande.getArticle().getNom()}</option>
                                                        </c:if>
                                                        <c:forEach items="${articles}" var="art">
                                                            <option stock="${art.getStock()}" value="${art.getId()}">${art.getCode()}=> ${art.getNom()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <c:if test="${sessionScope.societe.getGestStock()==1}">
                                                    <div class="form-group col-lg-1 col-sm-12">
                                                        <label for="quantite"><span class="danger"></span>Stock</label>
                                                        <input type="number" stock="${art.getStock()}" id="quantite_societe" disabled class="form-control-sm form-control round"/>
                                                    </div>
                                                </c:if>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Qte
                                                        <span class="danger">*</span></label>
                                                    <input type="number" required id="quantite" value="${commande.getQuantite()}" class="form-control-sm form-control round required" name="quantite"/>
                                                </div>
                                                <div class="form-group col-lg-1 col-sm-12">
                                                    <label for="quantite">Prix Divers</label>
                                                    <input type="number"  value="${commande.getPrix()}" class="form-control-sm form-control round" name="prix"/>
                                                </div>
                                                <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                    <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                        <i class="fa fa-shopping-cart"></i> Ajouter
                                                    </button>
                                                </div>
                                            </div>
                                        </fieldset>

                                    </form>
                                </c:if>
                            </c:if>
                            <c:if test="${sessionScope.societe.getGesttourner() == 0}">
                                <form class="form" method="post" action="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=${isnew}${id}">
                                    <fieldset>
                                        <div class="row">
                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="complaintinput2">Client
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%"  id="complaintinput2" class="select2-size-xs block form-control round " name="client">
                                                    <c:forEach items="${clients}" var="cl">
                                                        <option <c:if test="${sessionScope.cmdclient != null}">disabled</c:if> <c:if test="${cl.getId()==sessionScope.cmdclient.getId()}">selected</c:if> value="${cl.getId()}"> ${cl.getNom()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group col-lg-2 col-sm-12">
                                                <label for="categorie_article">Livraison
                                                    <span class="danger">*</span></label>
                                                <input type="text" <c:if test="${sessionScope.njr != null}">disabled value="${sessionScope.njr}"</c:if> class="form-control-sm form-control round singledate" name="njr"/>
                                                </div>
                                            </div>
                                        </fieldset>    
                                        <fieldset>
                                            <div class="row">

                                                <div class="form-group col-lg-3 col-sm-12">
                                                    <label for="articles_categorie">Article
                                                        <span class="danger">*</span></label>
                                                    <select style="width: 100%" required id="articles_inventaire_societe"  class="select2-size-xs block form-control round required" name="article">
                                                    <c:if test="${inventaire != null}">
                                                        <option selected value="${commande.getArticle().getId()}"> ${commande.getArticle().getNom()}</option>
                                                    </c:if>
                                                    <c:if test="${inventaire == null}">
                                                        <c:forEach items="${articles}" var="art">
                                                            <option stock="${art.getStock()}" value="${art.getId()}">${art.getCode()}=> ${art.getNom()}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>

                                            </div>
                                            <div class="form-group col-lg-1 col-sm-12">
                                                <label for="quantite"><span class="danger"></span>Stock</label>
                                                <input type="number" stock="${art.getStock()}" id="quantite_societe" disabled class="form-control-sm form-control round"/>
                                            </div>

                                            <div class="form-group col-lg-1 col-sm-12">
                                                <label for="quantite">Qte
                                                    <span class="danger">*</span></label>
                                                <input type="number" required id="quantite" value="${commande.getQuantite()}" class="form-control-sm form-control round required" name="quantite"/>
                                            </div>
                                            <div class="form-group col-lg-1 col-sm-12">
                                                <label for="quantite">Prix Divers</label>
                                                <input type="number"  value="${commande.getPrix()}" class="form-control-sm form-control round" name="prix"/>
                                            </div>
                                            <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                    <i class="fa fa-shopping-cart"></i> Ajouter
                                                </button>
                                            </div>
                                        </div>
                                    </fieldset>

                                </form>
                            </c:if>
                        </div>


                        <div class="form-group">
                            <c:if test="${lastcommande == null}">
                                <div class="btn-group btn-sm" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->


                                    <button type="button" title="Modifier le produit" class="btn-sm btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=7"><i class="fa fa-edit"></i></button>
                                    <!--                                    <button type="button" title="Ajouter un produit" class="btn btn-float btn-outline-success btn-round"
                                                                                data-toggle="modal"
                                            data-target="#bootstrap">
                                                                            <i class="fa fa-plus-circle"></i></button>-->
                                    <button type="button" title="supprimer le produit" class="btn-sm btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=6"><i class="fa fa-trash"></i></button>
                                        <c:if test="${commandes != null}">
                                            <c:if test="${not empty commandes}">

                                            <a title="Enregistrer la commande" class="btn-sm btn btn-float btn-outline-success btn-round"
                                               data-toggle="tooltips" href="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=1">
                                                <i class="fa fa-save"></i>
                                            </a>

                                        </c:if>
                                    </c:if>
                                    <a title="Annuler la commande" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                       data-toggle="tooltips"  href="gestionCommandes?action=model&model=PasserCommandes&q=${q}&isNew=4">
                                        <i class="fa fa-eject"></i>
                                    </a>
                                    <c:if test="${sessionScope.societe.getGestemballage()==1}">
                                        <c:if test="${commandes != null}">
                                            <button type="button" title="Ajouter des emballages" class="btn btn-float btn-outline-success btn-round"
                                                    data-toggle="modal"
                                                    data-target="#emballage_show">
                                                Suivant</button>
                                            </c:if>
                                        </c:if>
                                </div>
                            </c:if>
                        </div>
                        <c:if test="${ sessionScope.societe.getGestcassier() == 1}">
                            <c:if test="${not empty sessionScope.lignecommande.getCassier()}">
                                <div class="text-center bg-dark title_table" style="color:white">Total casier : ${sessionScope.lignecommande.getCassier()}</div><br>
                            </c:if>
                        </c:if>
                        <div style="overflow-x: scroll;">
                            <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                <c:if test="${lastcommande == null}">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Categorie</th>
                                            <th>Article</th>
                                            <th>P.U</th>
                                            <th>Qte</th>
                                            <th class="hidden">Qte</th>
                                            <th>P.T</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total" value="0"></c:set>
                                        <c:set var="qte" value="0"></c:set>
                                        <c:forEach items="${commandes}" var="cmd">
                                            <c:if test="${cmd.getArticle().getCode().substring(0,5) != 'VRACC' && cmd.getArticle().getCode().substring(0,3)!= 'VIP' && cmd.getArticle().getCode().substring(0,4) != 'VRAP' }">

                                                <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>
                                            </c:if>
                                            <c:set var="total" value="${total + (cmd.getPrix() * cmd.getQuantite())}"></c:set>
                                            <tr <c:if test="${sessionScope.societe.getGestStock()==1}">style="cursor:pointer" data-toggle="tooltip" title="quantité en stock ${cmd.getArticle().getStock()}"</c:if> >
                                                <td><option selected  class="hidden">${cmd.getArticle().getId()}</option> </td>
                                        <td>${cmd.getArticle().getCategorie().getNom()}</td>
                                        <td>${cmd.getArticle().getNom()}</td>
                                        <td>${cmd.getPrix()}</td>
                                        <td <c:if test="${cmd.getArticle().getCode().substring(0,3)== 'VIP' || cmd.getArticle().getCode().substring(0,5) == 'VRACC'|| cmd.getArticle().getCode().substring(0,4) == 'VRAP'}">style="color:#53b9ad" </c:if>
                                                                                                                                                                                                        <c:if test="${cmd.getArticle().getCode().endsWith('C') || cmd.getArticle().getCode().endsWith('CP')}">style="color:#b32f16" </c:if> class="td_update" style="cursor:pointer">${cmd.getQuantite()}</td>
                                        <td class="hidden td_to_update"><input id="${cmd.getArticle().getId()}" class="qt_update form-control-sm block form-control round" type="number" value=""></td>
                                        <td>${cmd.getPrixTotal()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr style="font-weight: bold; color: #00b5b8">
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td>TOTAL</td>
                                            <td>${qte}</td>
                                            <td class="hidden"></td>
                                            <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>

                                        </tr>
                                    </tfoot>
                                </c:if>
                                <c:if test="${lastcommande != null}">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>N°</th>
                                            <th>Client</th>
                                            <th>Statut</th>
                                            <th>Qte</th>

                                            <th>P.T</th>
                                            <th class="datec" pos="6">Date commande</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total" value="0"></c:set>
                                        <c:set var="qte" value="0"></c:set>
                                        <c:forEach items="${lastcommande}" var="all">

                                            <c:set var="total" value="${total + all.getPrixtotal()}"></c:set>
                                            <c:set var="qte" value="${qte+all.getQtotal()}"></c:set>
                                                <tr >
                                                    <td><option selected  class="hidden">${all.getId()}</option> </td>
                                        <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${all.getId()}</td>    
                                        <td>${all.getClient().getNom()}</td> 
                                        <td>
                                            <span class="badge badge-danger">
                                                Non traité
                                            </span>
                                        </td> 
                                        <td>${all.getQtotal()}</td> 
                                        <td><f:formatNumber value="${all.getPrixtotal()}"type="CURRENCY" currencySymbol=""/></td> 
                                        <td>${all.getDatecc()}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr style="font-weight: bold; color: #00b5b8">
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td>TOTAL</td>
                                            <td>${qte}</td>
                                            <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                            <td></td>
                                        </tr>
                                    </tfoot>
                                </c:if>
                            </table>
                        </div>
                        <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien117}">
                            <c:if test="${lastcommande != null}">
                                <div class="form-group col-md-4">
                                    <div class="btn-group btn-sm" role="group" aria-label="Basic example">
                                        <!-- Outline Round Floating button -->

                                        <a title="Receptionner les anciennes commandes" class="btn-sm btn btn-float btn-outline-warning btn-round"
                                           data-toggle="tooltips" href="gestionCommandes?action=model&model=PropositionCommande&q=${q}&isNew=5&id=${lastcommande.get(0).getId()}">
                                            <i class="fa fa-save">Receptionner les anciennes commandes</i> 
                                        </a>


                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:import url="/WEB-INF/composants/modal/addEmballageCommande.jsp"/>