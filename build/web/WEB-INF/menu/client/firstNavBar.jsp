<%-- 
    Document   : firstNavBar
    Created on : 16 oct. 2018, 11:00:57
    Author     : eh2s001
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- fixed-top-->
<nav class="header-navbar navbar-expand-md navbar navbar-with-menu navbar-static-top navbar-dark bg-gradient-x-grey-blue navbar-border navbar-brand-center">
    <div class="navbar-wrapper">
        <div class="navbar-header">
            <ul class="nav navbar-nav flex-row">
                <li class="nav-item mobile-menu d-md-none mr-auto"><a class="nav-link nav-menu-main menu-toggle hidden-xs" href="#"><i class="ft-menu font-large-1"></i></a></li>
                <li class="nav-item">
                    <a class="navbar-brand" href="index.html">
                        <c:if test="${sessionScope.societe.getLogoBase64() == null}">
                            <img class="brand-logo" alt="CCMANAGER" style="width: 180px;height: 30px" src="assets/images/ccmanager.png" alt="branding logo">
                        </c:if>
                        <c:if test="${sessionScope.societe.getLogoBase64() != null}">
                            <img class="brand-logo" alt="CCMANAGER" style="width: 180px;height: 40px;margin-top: -10px" src="data:image/png;base64,${sessionScope.societe.getLogoBase64()}" alt="branding logo">
                        </c:if>

                    </a>
                </li>
                <li class="nav-item d-md-none">
                    <a class="nav-link open-navbar-container" data-toggle="collapse" data-target="#navbar-mobile"><i class="fa fa-ellipsis-v"></i></a>
                </li>
            </ul>
        </div>
        <div class="navbar-container content ">
            <div class="collapse navbar-collapse" id="navbar-mobile">
                <ul class="nav navbar-nav mr-auto float-left">
                    <li class="nav-item d-none d-md-block"><a class="nav-link nav-menu-main menu-toggle hidden-xs" href="#"><i class="ft-menu"></i></a></li>

                    <li class="nav-item d-none d-md-block"><a class="nav-link nav-link-expand" href="#"><i class="ficon ft-maximize"></i></a></li>

                </ul>
                <ul class="nav navbar-nav float-right">
                    <li class="dropdown dropdown-language nav-item">
                        <a class="dropdown-toggle nav-link" id="dropdown-flag" href="#" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><i class="flag-icon flag-icon-fr"></i><span class="selected-language"></span></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown-flag">
                            <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-fr"></i> French</a>
                            <a class="dropdown-item" href="#"><i class="flag-icon flag-icon-gb"></i> English</a>
                        </div>
                    </li>
                    <li class="dropdown dropdown-notification nav-item">
                        <a class="nav-link nav-link-label" href="#" data-toggle="dropdown"><i class="ficon ft-bell"></i>
                            <span class="badge badge-pill badge-default badge-danger badge-default badge-up notif_number"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-media dropdown-menu-right main_notification">

                        </ul>
                    </li>
                    <li class="dropdown dropdown-user nav-item">
                        <a class="dropdown-toggle nav-link dropdown-user-link" href="#" data-toggle="dropdown">
                            <span class="avatar avatar-online">
                                <img src="assets/images/user.png" alt="avatar"><i></i></span>
                            <span class="user-name">${sessionScope.client.getNom()}</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right notification" entity="client" id="${sessionScope.client.getId()}">
                            <a class="dropdown-item" href="ticketClient?q=client/editprofil&action=jkdhfoldg458dgbjdg478962">
                                <i class="ft-user"></i>Mon Profil
                            </a>
                            <div class="dropdown-divider " ></div>
                            <a class="dropdown-item" href="getconnexionClient"><i class="ft-power"></i> Logout</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<!-- ////////////////////////////////////////////////////////////////////////////-->
