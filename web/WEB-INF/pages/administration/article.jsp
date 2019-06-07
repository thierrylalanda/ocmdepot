<%-- 
    Document   : article
    Created on : 26 nov. 2018, 14:04:54
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien116}">
    <c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
    <div class="row">

        <div class="col-md-12">
            <!-- Restore & show all table -->
            <section id="restore">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Liste les articles</h4>
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
<!--                                    <form class="form" method="post" action="administration?action=model&model=Articles&q=${q}&isNew=1">
                                         
                                            <fieldset>
                                                <div class="row">

                                                    <div class="form-group col-lg-3 col-sm-12">
                                                        <label for="articles_categorie">Article
                                                            <span class="danger">*</span></label>
                                                        <select style="width: 100%" required  class="select2-size-xs block form-control round required" name="article">
                                    <c:forEach items="${articles}" var="art">
                                       <option value="${art.getId()}">${art.getCode()}=> ${art.getNom()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-lg-1 col-sm-12">
                                <label for="quantite">Qte
                                    <span class="danger">*</span></label>
                                <input type="number" required id="quantite" class="form-control-sm form-control round required" name="stock"/>
                            </div>
                            <div class="form-control-sm form-group col-lg-2 col-sm-12">
                                <button type="submit" class="btn btn-primary round btn-sm" style="margin-top: 25px">
                                    <i class="fa fa-edit"></i>
                                </button>
                            </div>
                        </div>
                    </fieldset>

</form>--> <c:if test="${sessionScope.root=='EH2S' or  not empty sessionScope.lien114 && 1==2}">
                                        <form method="post" enctype="multipart/form-data" action="administration?q=${q}&action=model&model=Articles&isNew=1" class="">

                                            <!-- Step 1 -->
                                            <h6>Infos Articles</h6>
                                            <fieldset>
                                                <div class="row">
                                                    <div class="form-group col-lg-3 col-sm-12">
                                                        <label for="articles_categorie">Article
                                                            <span class="danger">*</span></label>
                                                        <select style="width: 100%" required  class="select2-size-xs block form-control round required" name="article">
                                                            <c:forEach items="${articles}" var="art">
                                                                <option value="${art.getId()}">${art.getCode()}=> ${art.getNom()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label for="stock">
                                                                Stock
                                                            </label>
                                                            <input type="number" value="${article.getStock()}" step="0.05"  class="form-control-sm round form-control"  name="stock"
                                                                   title="Currency" pattern="^\d+(?:\.\d{1,2})?$">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label for="prix">
                                                                Prix U
                                                            </label>
                                                            <input type="number" value="${article.getPrix()}" min="0" step="0.05" class="form-control-sm round form-control" name="prix"
                                                                   title="Currency" pattern="^\d+(?:\.\d{1,2})?$">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label for="prix">
                                                                Prix Achat
                                                            </label>
                                                            <input type="number" value="${article.getPrixAchat()}" min="0" step="0.05" class="form-control-sm round form-control" name="prix_achat"
                                                                   title="Currency" pattern="^\d+(?:\.\d{1,2})?$">
                                                        </div>
                                                    </div>
                                                    <c:if test="${sessionScope.societe.getGestmarge()==0}">
                                                        <div class="col-md-2">
                                                            <div class="form-group">

                                                                <input type="submit" style="margin-top:25px" class="btn btn-outline-secondary  round btn-sm"
                                                                       value="Enregistrer">
                                                            </div>


                                                        </div>
                                                    </c:if>
                                                </div>

                                            </fieldset>
                                            <c:if test="${sessionScope.societe.getGestmarge()==1}">
                                                <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                                                       value="Enregistrer">
                                            </c:if>

                                        </form>
                                    </c:if>
                                    <p class="card-text">
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien113}">
                                                <button type="button" title="Ajouter un article" class="btn btn-float btn-outline-success btn-round"
                                                        data-toggle="modal"
                                                        data-target="#bootstrap">
                                                    <i class="fa fa-plus-circle"></i></button>
                                                </c:if>

                                            <c:if test="${sessionScope.root=='EH2S' or  not empty sessionScope.lien114}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=Articles&isNew=2"><i class="fa fa-edit"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien115}">
                                                <button type="button" class="btn btn-float btn-outline-danger btn-round btn-link-delete" name="id" action="administration?q=${q}&action=model&model=Articles&isNew=5"><i class="fa fa-trash"></i></button>
                                                </c:if>
                                            <button type="button" class="btn btn-float btn-outline-dark btn-round print_marge" 
                                                    data-toggle='tooltips' title="Tout Imprimer"
                                                    name="id"><i class="fa fa-print"></i></button>

                                        </div>
                                    </div></p>
                                    <!--dataex-colvis-restore-->
                                    <div class="text-center bg-dark title_table" style="color:white">Liste des produits</div>
                                    <br>
                                    <div style="overflow-x: scroll;">
                                        <table style="width: 100%" class="table table-dark table-striped table-bordered   dataex-colvis-restore">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                        <c:if test="${sessionScope.root=='EH2S'}">
                                                        <th>Societe</th>
                                                        </c:if>
                                                        <th>Fournisseur</th>
                                                    <th>Categorie</th>
                                                    <th>code</th>
                                                    <th>Article</th>
                                                    <th>Prix</th>
                                                    <th>Stock</th>
                                                    <th>Prix Achat</th>
                                                    <th>Marge</th>
                                                    <th>Prix Total</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="tstock" value="0"></c:set>
                                                <c:set var="tprix" value="0"></c:set>
                                                <c:forEach items="${articles}" var="src">
                                                    <c:set var="tstock" value="${tstock+src.getStock()}"></c:set>
                                                    <c:set var="tprix" value="${tprix+(src.getPrix()*src.getStock())}"></c:set>
                                                    <tr <c:if test="${sessionScope.societe.getGestmarge()==1}">
                                                            style="cursor:pointer; <c:if test="${src.getStock() <= src.getSeuil()}">background-color: #f82</c:if>"  data-html="true"  
                                                            data-toggle="tooltip" title="<p> <strong>quantité en stock : </strong>  ${src.getStock()} </p><p> <strong>Seuil : </strong> ${src.getSeuil()}</p> <p> <strong>Marge Directe : </strong> ${src.getMargeFournisseur()}</p>"
                                                        </c:if> >
                                                        <td><option selected  class="hidden">${src.getId()}</option> </td>

                                                <c:if test="${sessionScope.root=='EH2S'}">
                                                    <td>
                                                        ${src.getCategorie().getSociete().getNom()}
                                                    </td>
                                                </c:if>
                                                    <td>${src.getFournisseur().getNom()}</td>
                                                <td>${src.getCategorie().getNom()}</td>
                                                <td>${src.getCode()}</td>
                                                <td>${src.getNom()}</td>
                                                <td>${src.getPrix()}</td>
                                                <td><f:formatNumber value="${src.getStock()}"type="CURRENCY" currencySymbol=""/></td>
                                                <td>${src.getPrixAchat()}</td>
                                                <td>${src.getMargeFournisseur()}</td>
                                                <td><f:formatNumber value="${src.getPrix()*src.getStock()}"type="CURRENCY" currencySymbol=""/></td>
                                                </tr>
                                            </c:forEach>

                                            </tbody>
                                            <tfoot>
                                                <tr style="font-weight: bold; color: #00b5b8">
                                                    <td></td>
                                                    <c:if test="${sessionScope.root=='EH2S'}">
                                                        <td></td>
                                                        </c:if>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td>TOTAL</td>
                                                    <td><f:formatNumber value="${tstock}"type="NUMBER" currencySymbol=""/></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><f:formatNumber value="${tprix}"type="NUMBER" currencySymbol=""/></td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                    <div class="form-group">
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <!-- Outline Round Floating button -->
                                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien113}">
                                                <button type="button" title="Ajouter un article" class="btn btn-float btn-outline-success btn-round"
                                                        data-toggle="modal"
                                                        data-target="#bootstrap">
                                                    <i class="fa fa-plus-circle"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien113}">
                                                <button  class="btn btn-float btn-outline-success btn-round"
                                                         data-toggle='tooltips' title="voir les détails de la commande" id="seedetailscommande">
                                                    <i class="fa fa-eye"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or  not empty sessionScope.lien114}">
                                                <button type="button" class="btn btn-float btn-outline-primary btn-round btn-link-update" name="id" action="administration?q=${q}&action=model&model=Articles&isNew=2"><i class="fa fa-edit"></i></button>
                                                </c:if>
                                                <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien115}">
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
        <c:import url="/WEB-INF/composants/modal/addarticle.jsp"/>
    </c:if>
</c:if>
