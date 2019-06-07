<%-- 
    Document   : addarticle
    Created on : 26 nov. 2018, 14:10:24
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="id" value="&r=7tyui895qze412"></c:set>
<c:if test="${article != null}">
    <c:set var="isnew" value="${1}"></c:set>
    <c:set var="id" value="&article=${article.getId()}"></c:set>
</c:if>
<c:if test="${article == null}">
    <c:set var="isnew" value="${0}"></c:set>
</c:if>
<div class="modal fade in text-left <c:if test="${article != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter un article</h3>
                <c:if test="${article == null}">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </c:if>

            </div>
            <div class="modal-body">
                <ul class="nav nav-tabs nav-linetriangle no-hover-bg nav-justified">
                    <li class="nav-item">
                        <a class="nav-link active" id="active-tab3" data-toggle="tab" href="#active3" aria-controls="active3"
                           aria-expanded="true">Formulaire</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="link-tab3" data-toggle="tab" href="#link3" aria-controls="link3"
                           aria-expanded="false">Fichier</a>
                    </li>





                </ul>
                <div class="tab-content px-1 pt-1">
                    <div role="tabpanel" class="tab-pane active in" id="active3" aria-labelledby="active-tab3"
                         aria-expanded="true">
                        <form method="post" enctype="multipart/form-data" action="administration?q=${q}&action=model&model=Articles&isNew=${isnew}${id}" class="">

                            <!-- Step 1 -->
                            <h6>Infos Articles</h6>
                            <fieldset>

                                <c:if test="${sessionScope.root=='EH2S'}">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="societe">
                                                    Societe
                                                    <span class="danger">*</span>
                                                </label>
                                                <select style="width: 100%"  id="societe"  class=" select2-size-xs block form-control round required categorie_by_societe"  name="societe">
                                                    <option></option>
                                                    <c:forEach items="${societes}" var="soc">
                                                        <option <c:if test="${soc.getId()==article.getCategorie().getSociete().getId()}">selected</c:if> value="${soc.getId()}"> ${soc.getNom()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="categorie">
                                                        Categorie :
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <select style="width: 100%"  id="categorie_societe"  class=" select2-size-xs block form-control round required categorie_societe"  name="cat">
                                                        <c:forEach items="${categories}" var="cat">
                                                            <option <c:if test="${cat.getId()==article.getCategorie().getId()}">selected</c:if> value="${cat.getId()}"> ${cat.getNom()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="article">
                                                        Article
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <input type="text" value="${article.getNom()}"  class="form-control-sm round form-control" required id="article" name="nom">
                                                </div>
                                            </div>
                                            <c:if test="${sessionScope.societe.getGestemballage()==1}">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="article">
                                                            Emballage
                                                        </label>
                                                        <select style="width: 100%" class="select2-size-xs block form-control round"  name="emballage">
                                                            <option value=""></option>
                                                            <c:forEach items="${emballages}" var="emb">
                                                                <option <c:if test="${not empty article.getEmballage()}"><c:if test="${article.getEmballage().getId()==emb.getId()}">selected</c:if></c:if> value="${emb.getId()}"> ${emb.getNom()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="code">
                                                        Code
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <input type="text" value="${article.getCode()}"  class="form-control-sm round form-control" required id="code" name="code">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="prix">
                                                        Prix U
                                                        <span class="danger">*</span>
                                                    </label>
                                                    <input type="number" value="${article.getPrix()}" min="0" step="0.01" class="form-control-sm round form-control" required id="prix" name="prix"
                                                           title="Currency" pattern="^\d+(?:\.\d{1,2})?$" onblur="
                                                                   this.parentNode.parentNode.style.backgroundColor = /^\d+(?:\.\d{1,2})?$/.test(this.value) ? 'inherit' : 'red'
                                                           ">
                                                </div>
                                            </div>
                                            <c:if test="${1==2}">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="stock">
                                                            Stock
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <input type="number" value="${article.getStock()}" step="0.05"  class="form-control-sm round form-control" required id="stock" name="stock"
                                                               title="Currency" pattern="^\d+(?:\.\d{1,2})?$" onblur="
                                                                       this.parentNode.parentNode.style.backgroundColor = /^\d+(?:\.\d{1,2})?$/.test(this.value) ? 'inherit' : 'red'
                                                               ">
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="unite">
                                                        Unité
                                                    </label>
                                                    <input type="text" value="${article.getUnite()}" placeholder="KG,CL ..."  class="form-control-sm round form-control" id="unite" name="unite">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="unite">
                                                        Seuil
                                                    </label>
                                                    <input type="number" min="0" value="${article.getSeuil()}" placeholder="seuil limite"  class="form-control-sm round form-control" id="seuil" name="seuil"
                                                           title="Currency" pattern="^\d+(?:\.\d{1,2})?$" onblur="
                                                                   this.parentNode.parentNode.style.backgroundColor = /^\d+(?:\.\d{1,2})?$/.test(this.value) ? 'inherit' : 'red'
                                                           ">
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${sessionScope.societe.getGestmarge()==1}">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="unite">
                                                            Marge fournisseur
                                                        </label>
                                                        <input type="number" value="${article.getMargeFournisseur()}" placeholder=""  class="form-control-sm round form-control" id="margefournisseur" min="0" step="0.01" name="margefournisseur"
                                                               title="Currency" pattern="^\d+(?:\.\d{1,2})?$" onblur="
                                                                       this.parentNode.parentNode.style.backgroundColor = /^\d+(?:\.\d{1,2})?$/.test(this.value) ? 'inherit' : 'red'
                                                               ">
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="unite">
                                                            Marge Client
                                                        </label>
                                                        <input type="number" value="${article.getMargeClient()}" placeholder=""  class="form-control-sm round form-control" id="margeclient" min="0" step="0.01" name="margeclient"
                                                               title="Currency" pattern="^\d+(?:\.\d{1,2})?$" onblur="
                                                                       this.parentNode.parentNode.style.backgroundColor = /^\d+(?:\.\d{1,2})?$/.test(this.value) ? 'inherit' : 'red'
                                                               ">
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="row">
                                            <div class="col-12">
                                                <label class="file center-block">
                                                    Image du Produit
                                                </label><br>
                                                <c:if test="${article.getPhoto() != null}">
                                                    <img class="photo_display" src="data:image/png;base64,${article.getPhoto()}" style="max-height: 60px;max-width: 70px" />
                                                </c:if>
                                                <c:if test="${article.getPhoto() == null}">
                                                    <img class="photo_display" src="assets/images/no_image.png" style="max-height: 60px;max-width: 70px" />
                                                </c:if>
                                                <br><br>
                                                <div>
                                                    <div class="row mb-1">
                                                        <div class="col-6 col-xl-6">
                                                            <input type="file" id="photo_article" class="form-control-sm form-control round" placeholder="signature" name="logo">

                                                        </div>

                                                    </div>
                                                    <code>Type:png,jpeg,jpg<br>Taille max :500Ko </code>
                                                </div>
                                            </div>
                                        </div>

                                        <c:if test="${sessionScope.societe.getGestfournisseur()== 1}">

                                            <div class="row">
                                                <div class="col-12">
                                                    <div class="form-group">
                                                        <label for="categorie">
                                                            Fournisseurs :
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <select style="width: 100%" class="select2-size-xs block form-control round required"  name="fournisseur">
                                                            <c:forEach items="${fournisseurs}" var="four">
                                                                <option value=""></option>
                                                                <option <c:if test="${article.getFournisseur().getId()==four.getId()}">selected</c:if> value="${four.getId()}"> ${four.getNom()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-12">
                                                    <div class="form-group">
                                                        <label for="prix">
                                                            Prix Achat
                                                            <span class="danger">*</span>
                                                        </label>
                                                        <input type="number" value="${article.getPrixAchat()}" min="0" step="0.01" class="form-control-sm round form-control" required name="prix_achat"
                                                               title="Currency" pattern="^\d+(?:\.\d{1,2})?$" onblur="
                                                                       this.parentNode.parentNode.style.backgroundColor = /^\d+(?:\.\d{1,2})?$/.test(this.value) ? 'inherit' : 'red'
                                                               ">
                                                    </div>
                                                </div>

                                            </div>
                                        </c:if>
                                    </div>

                                </div>
                            </fieldset>

                            <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                                   value="Enregistrer">
                        </form>
                    </div>
                    <div class="tab-pane" id="link3" role="tabpanel" aria-labelledby="link-tab3" aria-expanded="false">

                        <form enctype="multipart/form-data" action="UploadFileArticles?q=${q}&fileArticles=0" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="categorie">
                                            N° Categorie :
                                            <span class="danger">*</span>
                                        </label>
                                        <select style="width: 100%"  id="categorie_societe"  class=" select2-size-xs block form-control round required categorie_societe"  name="cat">
                                            <c:forEach items="${categories}" var="cat">
                                                <option  value="${cat.getId()}"> ${cat.getNom()} (${cat.getId()})</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="file"   class="form-control-sm round form-control" required  name="file">
                                    </div>
                                </div>

                            </div>
                            <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                                   value="Enregistrer">
                        </form>
                        <code>Assurez vous de respecter ce formatage de votre fichier excel sans cette première ligne</code>
                        <img src="assets/formatage/format_articles.png" style="width: 100%" />
                    </div>


                </div>


            </div>
            <c:if test="${article == null}">
                <div class="modal-footer">
                    <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                           value="Annuler">

                </div>
            </c:if>

            <c:if test="${article != null}">
                <div class="modal-footer">
                    <a class="btn btn-outline-secondary btn-sm round" href="javascript:history.go(-1)"><icon class="fa fa-arrow-left"></icon></a>
                </div>
            </c:if>

        </div>
    </div>
</div>

