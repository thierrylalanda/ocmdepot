<%-- 
    Document   : receptionVente
    Created on : 21 mai 2019, 12:22:33
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.root=='EH2S' or 1==1}">

    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <section>
        <div class="row autoloader" autoloader="true">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Réception des ventes</h4>
                        <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>


                    </div>
                    <div class="card-content">
                        <div class="card-body">
                            <form method="post" action="historiques?q=${q}&action=model&model=historiqueVente&isNew=1" class="" novalidate>


                                <fieldset>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="firstName">
                                                    Periode
                                                    <span class="danger">*</span>
                                                </label>
                                                <div class='input-group input-group-xs'>
                                                    <input type='text' class="form-control-xs form-control localeRange" name="interval" />
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">
                                                            <span class="fa fa-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label for="lastName">
                                                    Client
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%" id="statut" class="select2-size-xs block form-control round " name="client">
                                                    <option value="0">Tous</option>
                                                    <c:forEach items="${clients}" var="cl">
                                                        <option value="${cl.getId()}"> ${cl.getNom()}</option>

                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-2 hidden">
                                            <div class="form-group">
                                                <label for="lastName">
                                                    Statut
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%" id="statut" class="select2-size-xs block form-control round " name="statut">
                                                    <option value="0">Tous</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="lastName">

                                                </label>

                                                <input style="margin-top: 25px" type="submit" class="btn btn-outline-primary  round btn-sm"
                                                       value="Enregistrer">
                                            </div>
                                        </div>


                                    </div>
                                </fieldset>

                            </form>
                            <div class="form-group">
                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <!-- Outline Round Floating button -->
                                    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                        <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                data-toggle='tooltips' title="voir les détails de la vente"
                                                name="id" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                            <i class="fa fa-eye"></i></button>
                                        </c:if>

                                    <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-reception" 
                                            data-toggle='tooltips' title="Receptionner une commande"
                                            name="id" action="gestionCommandes?q=${q}&action=model&model=PropositionCommande&isNew=8"><i class="fa fa-check-circle"></i></button>
                                    <button type="button" class="btn btn-float btn-outline-primary btn-round print_one_element_commande petit_format" 
                                            data-toggle='tooltips' title="Imprimer une commande sous petit format"
                                            name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                    <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element_commande" 
                                            data-toggle='tooltips' title="Imprimer une commande"
                                            name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>

                                    <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                            data-toggle='tooltips' title="tout Imprimer "
                                            name="id"><i class="fa fa-print"></i></button>

                                </div>
                            </div>
                            <div style="overflow-x: scroll">
                                <table style="width: 100%" class="table table-dark table-striped table-bordered dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th class="datec" pos="1">N°</th>
                                            <th>Client</th>
                                            <th>Statut</th>
                                            <th>P.T</th>
                                            <th>Remise</th>
                                            <th>Date Liv</th>
                                            <th>Date</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total" value="0"></c:set>
                                        <c:set var="qte" value="0"></c:set>
                                        <c:set var="rem" value="0"></c:set>
                                        <c:forEach items="${commandes}" var="all">
                                            <c:if test="${all.getStatut()== 0}">
                                                <c:set var="total" value="${total + all.getPrixtotal()}"></c:set>
                                                <c:set var="qte" value="${qte+all.getQtotal()}"></c:set>
                                                <c:set var="rem" value="${rem+all.getRemise()}"></c:set>
                                                    <tr>
                                                        <td><option selected  class="hidden">${all.getId()}</option> </td>
                                            <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${all.getId()}</td>    
                                            <td>${all.getClient().getNom()}</td> 
                                            <td>
                                                <c:if test="${all.getEtatc().getCode()==201}">
                                                    <span class=" <c:if test="${all.getEtatc().getCode()==201}">badge badge-primary</c:if>">
                                                            Encours de livraison
                                                        </span>
                                                </c:if>
                                                <c:if test="${all.getEtatc().getCode()==200}">
                                                    <span class=" <c:if test="${all.getEtatc().getCode()==200}">badge badge-success</c:if>">
                                                            Livrée
                                                        </span>
                                                </c:if>
                                                <c:if test="${all.getEtatc().getCode()==300}">
                                                    <c:if test="${not empty all.getLigneAccountList()}">
                                                        <c:if test="${all.getLigneAccountList().get(0).getMontantRestant()!=0.0}">

                                                            <span class=" badge badge-warning">
                                                                Encours Paiement
                                                            </span>
                                                        </c:if>
                                                        <c:if test="${all.getLigneAccountList().get(0).getMontantRestant()==0.0}">

                                                            <span class=" badge badge-success">
                                                                Payer
                                                            </span>
                                                        </c:if>
                                                    </c:if>
                                                </c:if>
                                            </td> 
                                            <td><f:formatNumber value="${all.getPrixtotal()}"type="CURRENCY" currencySymbol=""/></td> 
                                            <td>${all.getRemise()}</td>
                                            <td>${all.getDateliv()}</td>
                                            <td>${all.getDatecc()}</td>

                                            </tr>
                                        </c:if>
                                    </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr style="font-weight: bold; color: #00b5b8">
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td>TOTAL</td>
                                            <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                            <td>${rem}</td>
                                            <td></td>
                                            <td></td>

                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>
<c:if test="${sessionScope.root=='EH2S' or 1==1}">
    <c:import url="/WEB-INF/composants/modal/voirCommande.jsp"/>
</c:if>
