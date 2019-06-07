<%-- 
    Document   : historiquetourner
    Created on : 8 mars 2019, 11:08:49
    Author     : Administrateur
--%>

<%-- 
    Document   : historique
    Created on : 26 nov. 2018, 17:50:35
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<c:if test="${(sessionScope.societe.getGesttourner() == 1) and (sessionScope.root=='EH2S' or not empty sessionScope.lien139)}">
    <%
        Date now = new Date();
        request.setAttribute("day", now);
    %>
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

    <div class="row autoloader" autoloader="true">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Historique des Tournées</h4>
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
                                    <form method="post" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=7" class="" novalidate>


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
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="lastName">
                                                            Tourné
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" id="statut" class="select2-size-xs block form-control round" name="tourner">
                                                            <option value="0">Tous</option>
                                                            <c:forEach items="${tourners}" var="sta">
                                                                <option value="${sta.getId()}"> ${sta.getNumc()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="lastName">
                                                            Statut
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" id="statut" class="select2-size-xs block form-control round" name="statut">
                                                            <option value="0">Tous</option>
                                                            <c:forEach items="${statutCommandes}" var="sta">
                                                                <option value="${sta.getCode()}"> ${sta.getNom()}</option>
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
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir les détails de la commande"
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien106}">
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element_commande" 
                                                        data-toggle='tooltips' title="Imprimer une commande"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                                data-toggle='tooltips' title="tout Imprimer "
                                                                name="id"><i class="fa fa-print"></i></button>
                                                </c:if>
                                        </div>
                                    </div>
                                    </p>
                                     <div class="text-center bg-dark title_table" style="color:white">${messageTable}</div>
                                    <br>
                                    <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th class="datec" pos="1">N°</th>
                                                <th>Client</th>
                                                <th>Statut</th>
                                                <th>Qte</th>
                                                <th>P.T</th>
                                                <th>Date commande</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="total" value="0"></c:set>
                                            <c:set var="qte" value="0"></c:set>
                                            <c:forEach items="${commandes}" var="all">
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
                                                <c:if test="${all.getEtatc().getCode()==501}">
                                                    <span class="<c:if test="${all.getEtatc().getCode()==501}">badge badge-danger</c:if>">
                                                            Non traité
                                                        </span>
                                                </c:if>
                                                <c:if test="${all.getEtatc().getCode()==502}">
                                                    <span class=" <c:if test="${all.getEtatc().getCode()==502}">badge badge-warning</c:if>">
                                                            Encours de traitement
                                                        </span>
                                                </c:if>
                                                <c:if test="${all.getEtatc().getCode()==401}">
                                                    <span class=" <c:if test="${all.getEtatc().getCode()==401}">badge badge-info</c:if>">
                                                            Attente de facturation
                                                        </span>
                                                </c:if>
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
                                    </table>
                                     <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
                                                <button type="button"  class="btn btn-float btn-outline-success btn-round btn-link-update"
                                                        data-toggle='tooltips' title="voir les détails de la commande"
                                                        name="id" action="gestionCommandes?q=${q}&action=model&model=HistoriqueCommande&isNew=2">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien106}">
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_one_element_commande" 
                                                        data-toggle='tooltips' title="Imprimer une commande"
                                                        name="id" action="ticket?q=${q}&action=model&model=traitementTicket&isNew=2"><i class="fa fa-print"></i></button>
                                                        <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                                data-toggle='tooltips' title="tout Imprimer "
                                                                name="id"><i class="fa fa-print"></i></button>
                                                </c:if>
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
    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien105}">
        <c:import url="/WEB-INF/composants/modal/voirCommande.jsp"/>
    </c:if>
</c:if>

