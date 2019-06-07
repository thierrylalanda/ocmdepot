<%-- 
    Document   : aideLogiciel
    Created on : 26 mars 2019, 08:50:57
    Author     : Administrateur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row">

    <div class="col-md-12">
        <!-- Restore & show all table -->
        <section id="restore">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">AIDE AU LOGICIEL</h4>
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
                                <div class="row chat-application">

                                    <div class="col-lg-8 col-xs-12">
                                        <div class="app-content content">

                                            <div class="content">
                                                <div class="content-wrapper">
                                                    <div class="content-header row">
                                                    </div>
                                                    <div class="content-body">
                                                        <section class="chat-app-window">
                                                            <div class="badge badge-default mb-1">Chat History</div>
                                                            <div class="chats">
                                                                <div class="chats" id="chats">
                                                                    <div class="chat chat-left">
                                                                        <div class="chat-avatar">
                                                                            <a class="avatar" data-toggle="tooltip" href="#" data-placement="right" title=""
                                                                               data-original-title="">
                                                                                <img src="assets/images/ccmanager.png" alt="avatar"
                                                                                     />
                                                                            </a>
                                                                        </div>
                                                                        <div class="chat-body">
                                                                            <div class="chat-content">
                                                                                <p>Que pouvons nous faire pour vous ?</p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </section>
                                                        <section class="chat-app-form">
                                                            <form class="chat-app-input d-flex">
                                                                <fieldset class="form-group position-relative has-icon-left col-10 m-0">
                                                                    <div class="form-control-position">
                                                                        <i class="icon-emoticon-smile"></i>
                                                                    </div>
                                                                    <input type="text" class="form-control" id="q" placeholder="posez votre question ici">
                                                                    <div class="form-control-position control-position-right">
                                                                        <i class="ft-image"></i>
                                                                    </div>
                                                                </fieldset>
                                                                <fieldset class="form-group position-relative has-icon-left col-2 m-0">
                                                                    <button type="button" class="btn btn-primary" id="sendRequest"><i class="fa fa-paper-plane-o d-lg-none"></i>
                                                                        <span class="d-none d-lg-block">Send</span>
                                                                    </button>
                                                                </fieldset>
                                                            </form>
                                                        </section>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</div>
</div>
</section>
</div>
</div>