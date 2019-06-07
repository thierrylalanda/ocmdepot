<%-- 
    Document   : facturation
    Created on : 20 avr. 2019, 18:44:28
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or 1==1}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

    <div class="row autoloader" autoloader="true">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">FACTURATION</h4>
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
                                    <form method="post" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=1" class="" novalidate>


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
                                                <div class="col-md-3 hidden">
                                                    <div class="form-group">
                                                        <label for="lastName">
                                                            Statut
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" id="statut" class="select2-size-xs block form-control round " name="statut">
                                                            <option value="0">Tous</option>
                                                            <c:forEach items="${statutCommandes}" var="sta">
                                                                <option <c:if test="${sta.getCode()==200}">selected</c:if> value="${sta.getCode()}"> ${sta.getNom()}</option>
                                                            </c:forEach>
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
                                                        data-toggle='tooltips' title="Facturer la commande"
                                                        name="id" action="gestioncaisse?q=commande/caisse/facturer&action=model&model=facturationAccount&isNew=1">
                                                    <i class="fa fa-money"></i></button>
                                                </c:if>

                                            <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir les détails de la commande"
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>

                                            <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element_commande" 
                                                        data-toggle='tooltips' title="Imprimer La Facture"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                        data-toggle='tooltips' title="tout Imprimer "
                                                        name="id"><i class="fa fa-print"></i></button>
                                                </c:if>
                                            <button type="button" data-toggle='tooltips' title="Tout Encaisser " class="btn btn-float btn-outline-primary btn-round btn-link-encaisser-facture" name="id" action="gestioncaisse?q=${q}&action=model&model=addAccount&isNew=4">
                                               Tout Encaisser</button>
                                        </div>
                                    </div>
                                    </p>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th class="datec" pos="1">N°</th>
                                                <th>Client</th>
                                                <th>Statut</th>
                                                <th>Qte</th>
                                                <th>P.T</th>
                                                <th>Echeance</th>
                                                <th>Date commande</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="total" value="0"></c:set>
                                            <c:set var="qte" value="0"></c:set>
                                            <c:forEach items="${commandes}" var="all">
                                                <c:if test="${all.getEtatc().getCode()==200 or all.getEtatc().getCode()==201 &&(empty all.getLigneAccountList())}">
                                                    <c:set var="total" value="${total + all.getPrixtotal()}"></c:set>
                                                    <c:set var="qte" value="${qte+all.getQtotal()}"></c:set>
                                                        <tr >
                                                            <td><option selected  class="hidden">${all.getId()}</option> </td>
                                                <td style="cursor: pointer" data-toggle="tooltip" title="${title}">${all.getId()}</td>    
                                                <td>${all.getClient().getNom()}</td> 
                                                <td>
                                                    <c:if test="${all.getEtatc().getCode()==200}">
                                                        <span class=" <c:if test="${all.getEtatc().getCode()==200}">badge badge-success</c:if>">
                                                                Livrée
                                                            </span>
                                                    </c:if>
                                                    <c:if test="${all.getEtatc().getCode()==201}">
                                                        <span class=" <c:if test="${all.getEtatc().getCode()==201}">badge badge-primary</c:if>">
                                                                Encours de livraison
                                                            </span>
                                                    </c:if>
                                                </td> 
                                                <td>${all.getQtotal()}</td> 
                                                <td><f:formatNumber value="${all.getPrixtotal()}"type="CURRENCY" currencySymbol=""/></td> 
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
                                                <td>${qte}</td>
                                                <td><f:formatNumber value="${total}"type="CURRENCY" currencySymbol=""/></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="Facturer la commande"
                                                        name="id" action="gestioncaisse?q=commande/caisse/facturer&action=model&model=facturationAccount&isNew=1">
                                                    <i class="fa fa-money"></i></button>
                                                </c:if>

                                            <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir les détails de la commande"
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>

                                            <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element_commande" 
                                                        data-toggle='tooltips' title="Imprimer La Facture"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                        data-toggle='tooltips' title="tout Imprimer "
                                                        name="id"><i class="fa fa-print"></i></button>
                                                </c:if>
                                            <button type="button" data-toggle='tooltips' title="Tout Encaisser " class="btn btn-float btn-outline-primary btn-round btn-link-encaisser-facture" name="id" action="gestioncaisse?q=${q}&action=model&model=addAccount&isNew=4">
                                               Tout Encaisser</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--/ Restore & show all table -->
        </div>
    </div>
    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
        <c:import url="/WEB-INF/composants/modal/voirCommande.jsp"/>
    </c:if>
</c:if>

