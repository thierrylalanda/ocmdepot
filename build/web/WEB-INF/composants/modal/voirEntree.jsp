<%-- 
    Document   : voirEntree
    Created on : 18 avr. 2019, 10:41:21
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal fade in text-left <c:if test="${entree != null}">autoshow</c:if>" data-backdrop="static" id="bootstrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
     aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h3 class="modal-title" id="myModalLabel35">ENTREE N° ${entree.getId()}</h3>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">

                <form method="post" action="administration?q=${q}&action=model&model=secteur&isNew=${isnew}${id}" class="" novalidate>

                    <!-- Step 1 -->
                    <h6>INFOS ENTREE
                    </h6>

                    <fieldset>
                        <div class="row">
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">ENTREE</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Montant :</strong>
                                                <f:formatNumber value="${entree.getAvance()}"type="CURRENCY" currencySymbol=""/>
                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Date Entree :</strong>
                                                <span class="badge bg-blue-grey">
                                                    <f:formatDate value="${entree.getDate()}" type="BOTH" dateStyle="MEDIUM" pattern="dd/MM/yyy"/>
                                                </span>

                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12 border-success">
                                <div class="card ">
                                    <div class="card-content">
                                        <div class="card-body">
                                            <h4 class="card-title info">Details Entréé</h4>
                                            <p class="card-text">
                                                <strong for="firstName">Operateur :</strong>
                                                <span class="badge bg-blue-grey">
                                                    ${entree.getOperateur().getFirstname()}
                                                </span>

                                            </p>
                                            <p class="card-text">
                                                <strong for="firstName">Commentaire :</strong>
                                                <br>
                                                    ${entree.getCommentaire()}
                                                

                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>


                </form>

            </div>
            <div class="modal-footer">
                <input type="reset" class="btn btn-outline-secondary btn-lg round btn-sm" data-dismiss="modal"
                       value="Fermer">

            </div>


        </div>
    </div>
</div>

