<%-- 
    Document   : updateBon
    Created on : 28 mai 2019, 14:04:20
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.societe.getGestfournisseur() == 1}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <section>
        <div class="row ">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Détails Bon de Commande N° ${achat.getId()} Du : <f:formatDate value="${achat.getDateCommande()}" type="BOTH" dateStyle="MEDIUM"/> fait par : ${achat.getOperateur().getFirstname()}</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <div class="card-text">
                                <form class="form" method="post" action="gestionachat?action=model&model=bonAchat&q=${q}&isNew=6&id=${achat.getId()}">
                                    <fieldset>
                                        <div class="row">

                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="articles_inventaire">Fournisseur
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required  class="select2-size-xs block form-control round required" name="fournisseur">
                                                    <c:forEach items="${fournisseurs}" var="four">
                                                        <option <c:if test="${four.getId() == achat.getFournisseur().getId()}">selected</c:if> value="${four.getId()}">${four.getNom()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-lg-1 col-sm-12">
                                                <label for="quantite"><span class="danger"></span>Livraison</label>
                                                <input type="text" value='${achat.getDateliv()}'  name="njr" class="form-control-sm form-control round singledate"/>
                                            </div>
                                            <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                                <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                                    <i class="fa fa-edit"></i> Modifier
                                                </button>
                                            </div>
                                        </div>
                                    </fieldset>

                                </form>
                                <form class="form" method="post" action="gestionachat?action=model&model=bonAchat&q=${q}&isNew=3&achat=${achat.getId()}">
                                    <fieldset>
                                        <div class="row">

                                            <div class="form-group col-lg-3 col-sm-12">
                                                <label for="articles_inventaire">Article
                                                    <span class="danger">*</span></label>
                                                <select style="width: 100%" required id="articles_inventaire_societe"  class="select2-size-xs block form-control round required" name="article">
                                                    <option value=""></option>
                                                    <c:forEach items="${achat.getFournisseur().getTarticlesList()}" var="art">
                                                        <option stock="${art.getStock()}" value="${art.getId()}">${art.getCode()}=> ${art.getNom()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-lg-1 col-sm-12">
                                                <label for="quantite"><span class="danger"></span>Stock</label>
                                                <input type="text" id="quantite_societe" disabled class="form-control-sm form-control round"/>
                                            </div>

                                            <div class="form-group col-lg-1 col-sm-12">
                                                <label for="quantite">Qte
                                                    <span class="danger">*</span></label>
                                                <input type="number" required id="quantite" value="" step="0.01" class="form-control-sm form-control round required" name="quantite"/>
                                            </div>
                                            <div class="form-group col-lg-1 col-sm-12">
                                                <label for="quantite">Prix Divers</label>
                                                <input type="number"  value="" step="0.01" class="form-control-sm form-control round" name="prix"/>
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
                                    <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                            data-toggle='tooltips' title="tout Imprimer "
                                            name="id"><i class="fa fa-print"></i></button>

                                </div>
                            </div>
                            <div style="overflow-x: scroll;">
                                <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>Code</th>
                                            <th>Prix</th>
                                            <th>Qte</th>
                                            <th>Prix Total</th>
                                            <th>Total Reçue</th>
                                            <th>Total</th>
                                            <th>#</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total" value="0"></c:set>
                                        <c:set var="qte" value="0"></c:set>
                                        <c:set var="qter" value="0"></c:set>
                                        <c:set var="Totalr" value="0"></c:set>
                                        <c:if test="${achat.getStatut()==0}">
                                            <c:forEach items="${achat.getCommandeFournisseurList()}" var="cmd">
                                                <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>
                                                <c:set var="qter" value="${qter+cmd.getQuantiteRecu()}"></c:set>
                                                <c:set var="total" value="${total + cmd.getPrixTotal()}"></c:set>
                                                <c:set var="totalr" value="${totalr + (cmd.getQuantiteRecu() * cmd.getPrix())}"></c:set>
                                                    <tr>
                                                <form action="gestionachat?action=model&model=bonAchat&q=${q}&isNew=4&achat=${achat.getId()}&commande=${cmd.getId()}" method="post" class="form form${cmd.getId()}">  
                                                <td>${cmd.getArticle().getCode()}</td>
                                                <td><input class="form-control-sm form-control round" type="number" min="0"  value="${cmd.getPrix()}" name="prix"/></td>
                                                <td ><input class="form-control-sm form-control round" type="number" min="0"  value="${cmd.getQuantite()}" name="quantite"/></td>
                                                <td >${cmd.getPrixTotal()}</td>
                                                <td >${cmd.getQuantiteRecu()}</td>
                                                <td >${cmd.getQuantiteRecu() * cmd.getPrix()}</td>
                                                <td>
                                                    <div class="btn-group" role="group" aria-label="Basic example">
                                                        <!-- Outline Round Floating button -->
                                                        <button type="button" class="btn btn-float btn-outline-primary btn-round btn-sm modifier_produit" 
                                                                data-toggle='tooltips' title="Modifier le produit"
                                                                name="${cmd.getId()}"><i class="fa fa-edit"></i></button>

                                                        <button type="button" class="btn btn-float btn-outline-danger btn-round btn-sm supprimer_produit" 
                                                                data-toggle='tooltips' title="Supprimer le produit "
                                                                name="${cmd.getId()}"><i class="fa fa-trash"></i></button>

                                                    </div>
                                                </td>
                                            </form>
                                            </tr>

                                            <c:if test="${cmd.getEtat() ==1}">
                                                <!--                                            <tr>
                                                                                                <td>${cmd.getArticle().getCode()}</td>
                                                                                                <td>${cmd.getPrix()}</td>
                                                                                                <td >${cmd.getQuantite()}</td>
                                                                                                <td ><f:formatNumber value="${cmd.getPrixTotal()}"type="CURRENCY" currencySymbol=""/></td>
                                                                                                
                                                                                                <td>${cmd.getQuantiteRecu()}</td>
                                                                                                <td></td>
                                                                                                </form>
                                                                                            </tr>-->
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                <c:if test="${achat.getStatut()==1}">
                                        <c:forEach items="${achat.getCommandeFournisseurList()}" var="cmd">
                                            <c:set var="qte" value="${qte+cmd.getQuantite()}"></c:set>
                                            <c:set var="qter" value="${qter+cmd.getQuantiteRecu()}"></c:set>
                                            <c:set var="total" value="${total + cmd.getPrixTotal()}"></c:set>
                                            <c:set var="totalr" value="${totalr + (cmd.getQuantiteRecu() * cmd.getPrix())}"></c:set>
                                                <tr>
                                                <form action="gestionachat?action=model&model=bonAchat&q=${q}&isNew=4&achat=${achat.getId()}&commande=${cmd.getId()}" method="post" class="form form${cmd.getId()}">  
                                                <td>${cmd.getArticle().getCode()}</td>
                                                <td>${cmd.getPrix()}</td>
                                                <td >${cmd.getQuantite()}</td>
                                                <td >${cmd.getPrixTotal()}</td>
                                                <td >${cmd.getQuantiteRecu()}</td>
                                                <td >${cmd.getQuantiteRecu() * cmd.getPrix()}</td>
                                                <td></td>
                                            </form>
                                            </tr>

                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                    <tfoot>
                                        <tr style="font-weight: bold; color: #00b5b8">
                                            <td></td>
                                            <td>TOTAL</td>
                                            <td>${qte}</td>
                                            <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                            <td>${qter}</td>
                                            <td><f:formatNumber value="${totalr}"type="CURRENCY" currencySymbol=""/></td>
                                            <td></td>

                                        </tr>
                                    </tfoot>
                                </table>
                                             <a class="btn btn-outline-secondary btn-sm round" href="gestionachat?action=model&model=bonAchat&q=commande/achat/allCommandeUpdate&isNew=0&societe=yes"><icon class="fa fa-arrow-left"></icon></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>
