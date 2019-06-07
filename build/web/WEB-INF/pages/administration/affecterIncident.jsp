<%-- 
    Document   : affecterIncident
    Created on : 18 oct. 2018, 10:09:58
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
<div class="row">
    <div class="col-md-3">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title" id="from-actions-center-dropdown">Affectation des incidents</h4>
                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>

            </div>
            <div class="card-content">
                <div class="card-body">
                    <div class="card-text">

                    </div>
                    <form class="form" novalidate>
                        <div class="form-body">
                            <div class="row">
                                <div class="form-group col-12">
                                    <label for="complaintinput2">Rubrique
                                        <span class="required">*</span></label>
                                    <select style="width: 100%" required id="complaintinput2" class="select2-size-xs block form-control round" name="region">
                                        <option> Centre</option>
                                        <option> Littoral</option>
                                        <option> Nord</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-12">
                                    <label for="responsable">Responsable
                                        <span class="required">*</span></label>
                                    <select style="width: 100%" required id="responsable" class="select2-size-xs block form-control round" name="region">
                                        <option> Centre</option>
                                        <option> Littoral</option>
                                        <option> Nord</option>
                                    </select>
                                </div>
                            </div>

                        </div>
                        <div class="form-actions">

                            <button type="submit" class="btn btn-primary round">
                                <i class="fa fa-check-square-o"></i> Enregistrer
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <!-- Restore & show all table -->
        <section id="restore">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Liste des Responsable des incidents</h4>
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
                                <p class="card-text">.
                                </p>
                                <table class="table table-dark table-striped table-bordered responsive dataex-colvis-restore">
                                    <thead>
                                        <tr>
                                            <th>Responsable</th>
                                            <th>Incident</th>
                                            <th>Option</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Tiger Nixon</td>
                                            <td>
                                                <select style="width: 100%" class="select2-size-xs block form-control round">
                                                    <option> Centre</option>
                                                    <option> Littoral</option>
                                                    <option> Nord</option>
                                                </select>
                                            </td>
                                            <td>
                                                <div class="form-group">
                                                    <div class="btn-group btn-group-sm" role="group" aria-label="Basic example">
                                                        <!-- Outline Round Floating button -->
                                                        <button type="button" class="btn btn-float btn-outline-primary btn-round"><i class="fa fa-edit"></i></button>
                                                     </div>
                                                </div>
                                            </td>

                                        </tr>

                                        <tr>
                                            <td>Donna Snider</td>
                                            <td>
                                                <select style="width: 100%" class="select2-size-xs block form-control round">
                                                    <option> Centre</option>
                                                    <option> Littoral</option>
                                                    <option> Nord</option>
                                                </select>
                                            </td>
                                            <td>
                                                <div class="form-group">
                                                    <div class="btn-group btn-group-sm" role="group" aria-label="Basic example">
                                                        <!-- Outline Round Floating button -->
                                                        <button type="button" class="btn btn-float btn-outline-primary btn-round"><i class="fa fa-edit"></i></button>
                                                    </div>
                                                </div>
                                            </td>

                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--/ Restore & show all table -->
    </div>
</div>