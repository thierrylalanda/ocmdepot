<%-- 
    Document   : addEmballageCommande
    Created on : 11 avr. 2019, 16:52:54
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal fade in text-left <c:if test="${emballage != null}">autoshow</c:if>" data-backdrop="static" id="emballage_show" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">Ajouter un emballage</h3>

                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>

                </div>
                <div class="modal-body">
                    <ul class="nav nav-tabs nav-linetriangle no-hover-bg nav-justified">
                        <li class="nav-item">
                            <a class="nav-link active" id="active-tab3" data-toggle="tab" href="#active3" aria-controls="active3"
                               aria-expanded="true">Formulaire</a>
                        </li>

                    </ul>
                    <div class="tab-content px-1 pt-1">
                        <div role="tabpanel" class="tab-pane active in" id="active3" aria-labelledby="active-tab3"
                             aria-expanded="true">
                            <form method="post"  id="form_add_emballage" id_ligne="${sessionScope.lignecommande.getId()}" class="form_add_emballage">

                                <!-- Step 1 -->
                                <h6>Infos Emballage</h6>
                                <fieldset>
                                    <div class="row">
                                        <div class="form-group col-lg-4 col-sm-12 col-md-4">
                                            <label for="articles_categorie">Emballage
                                                <span class="danger">*</span></label>
                                            <select style="width: 100%" required  class="select2-size-xs block form-control round required" name="emballage">
                                            <c:forEach items="${emballages}" var="art">
                                                <option nom="${art.getNom()}" stock="${art.getStock()}" prix="${art.getPrix()}" value="${art.getId()}">${art.getNom()}=> ${art.getCode()} (${art.getStock()})</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-lg-2 col-md-3 col-sm-12">
                                        <div class="form-group">
                                            <label for="article">
                                                Qte
                                                <span class="danger">*</span>
                                            </label>
                                            <input type="number"  class="form-control-sm round form-control" required name="qte">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <input type="submit" style="margin-top:25px" class="btn btn-outline-secondary  round btn-sm"
                                                   value="Ajouter">
                                        </div>

                                    </div> 

                                </div>

                            </fieldset>
                        </form>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="article">
                                        Transport
                                    </label>
                                    <input type="number" min="0" value="0" class="form-control-sm round form-control" required name="transport">
                                </div>
                            </div>
                        </div>
                        <button type="button" onclick="saveEmballage('emballage_show')" title="Enregistrer" class="btn btn-sm  btn-outline-success round"
                                >
                            Enregistrer</button>
                        <br>
                        <div style="overflow-x: scroll;">
                            <table style="width: 100%"  class="table table-dark table-striped table-bordered table_add_emballage">
                                <thead>
                                    <tr>
                                        <th>Article</th>
                                        <th>P.U</th>
                                        <th>Qte</th>
                                        <th>P.T</th>
                                        <th>#</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                                <tfoot>

                                </tfoot>
                            </table>
                        </div>
                    </div>


                </div>


            </div>
            <div class="modal-footer">
                <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                       value="Annuler">

            </div>

        </div>
    </div>
</div>
