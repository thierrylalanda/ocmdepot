<%-- 
    Document   : oderStock
    Created on : 11 avr. 2019, 15:18:14
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or 1==1}">
    <c:if test="${sessionScope.societe.getGestemballage()==1}">
        <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
        <div class="row">

            <div class="col-md-12">
                <!-- Restore & show all table -->
                <section id="restore">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Liste les Emballages</h4>
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
                                        <form method="post" enctype="multipart/form-data" action="administration?q=${q}&action=model&model=Emballage&isNew=1" class="">

                                            <!-- Step 1 -->
                                            <h6>Infos Emballages</h6>
                                            <fieldset>
                                                <div class="row">
                                                    <div class="form-group col-lg-3 col-sm-12">
                                                        <label for="articles_categorie">Emballages
                                                            <span class="danger">*</span></label>
                                                        <select style="width: 100%" required  class="select2-size-xs block form-control round required" name="emballage">
                                                            <c:forEach items="${emballages}" var="art">
                                                                <option value="${art.getId()}">${art.getCode()}=> ${art.getNom()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label for="stock">
                                                                Stock
                                                            </label>
                                                            <input type="number" value="${emballage.getStock()}" class="form-control-sm round form-control"  name="stock"
                                                                   >
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label for="prix">
                                                                Prix U
                                                            </label>
                                                            <input type="number" value="${emballage.getPrix()}" min="0" step="0.05" class="form-control-sm round form-control" name="prix"
                                                                   title="Currency" pattern="^\d+(?:\.\d{1,2})?$">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">

                                                            <input type="submit" style="margin-top:25px" class="btn btn-outline-secondary  round btn-sm"
                                                                   value="Enregistrer">
                                                        </div>


                                                    </div>
                                                </div>

                                            </fieldset>


                                        </form>
                                        <p class="card-text">
                                        <div class="form-group">
                                            <div class="btn-group" role="group" aria-label="Basic example">
                                                <!-- Outline Round Floating button -->
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien113}">
                                                    <button type="button" title="Ajouter un emballage" class="btn btn-float btn-outline-success btn-round"
                                                            data-toggle="modal"
                                                            data-target="#bootstrap">
                                                        <i class="fa fa-plus-circle"></i></button>
                                                    </c:if>
                                                   <c:if test="${sessionScope.root=='EH2S' or  not empty sessionScope.lien114}">
                                                    <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=Emballage&isNew=2"><i class="fa fa-edit"></i></button>
                                                    </c:if>
                                                    <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien115}">
                                                    <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=Emballage&isNew=5"><i class="fa fa-trash"></i></button>
                                                    </c:if>
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                        data-toggle='tooltips' title="Tout Imprimer"
                                                        name="id"><i class="fa fa-print"></i></button>

                                            </div>
                                        </div></p>
                                        <!--dataex-colvis-restore-->
                                        <div class="text-center bg-dark title_table" style="color:white">Liste des Emballages</div>
                                        <br>
                                        <table class="table table-dark table-striped table-bordered responsive  dataex-colvis-restore">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>code</th>
                                                    <th>Nom</th>
                                                    <th>Prix</th>
                                                    <th>Stock</th>
                                                    <th>P.T</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="total" value="0"></c:set>
                                                <c:set var="qte" value="0"></c:set>
                                                <c:forEach items="${emballages}" var="src">
                                                    <c:set var="qte" value="${qte+src.getStock()}"></c:set>

                                                    <c:set var="total" value="${total + src.getPrixTotal()}"></c:set>
                                                    <tr <c:if test="${sessionScope.societe.getGestmarge()==1}">
                                                            style="cursor:pointer; <c:if test="${src.getStock() <= src.getSeuil()}">background-color: #f82</c:if>"  data-html="true"  
                                                            data-toggle="tooltip" title="<p> <strong>quantité en stock : </strong>  ${src.getStock()} </p><p> <strong>Seuil : </strong> ${src.getSeuil()}</p>"
                                                        </c:if> >
                                                        <td><option selected  class="hidden">${src.getId()}</option> </td>


                                                <td>${src.getCode()}</td>
                                                <td>${src.getNom()}</td>
                                                <td><f:formatNumber value="${src.getPrix()}"type="CURRENCY" currencySymbol=""/></td>
                                                <td>${src.getStock()}</td>
                                                <td><f:formatNumber value="${src.getPrixTotal()}"type="CURRENCY" currencySymbol=""/></td>
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
                                                </tr>
                                            </tfoot>
                                        </table>
                                        <div class="form-group">
                                            <div class="btn-group" role="group" aria-label="Basic example">
                                                <!-- Outline Round Floating button -->
                                                <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                                    <button type="button" title="Ajouter un Emballage" class="btn btn-float btn-outline-success btn-round"
                                                            data-toggle="modal"
                                                            data-target="#bootstrap">
                                                        <i class="fa fa-plus-circle"></i></button>
                                                    </c:if>
                                                    <c:if test="${sessionScope.root=='EH2S' or  1==1}">
                                                    <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=Articles&isNew=2"><i class="fa fa-edit"></i></button>
                                                    </c:if>
                                                    <c:if test="${sessionScope.root=='EH2S' or 1==1}">
                                                    <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=Articles&isNew=5"><i class="fa fa-trash"></i></button>
                                                    </c:if>
                                                <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                        data-toggle='tooltips' title="Tout Imprimer"
                                                        name="id"><i class="fa fa-print"></i></button>
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
        <c:if test="${sessionScope.root=='EH2S' or  not empty sessionScope.lien113 or not empty sessionScope.lien114}">
            <c:import url="/WEB-INF/composants/modal/addEmballage.jsp"/>
        </c:if>
    </c:if>
</c:if>

