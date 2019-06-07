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
                    <a class="navbar-brand" href="#">
                        <c:if test="${sessionScope.societe.getLogoBase64() == null}">
                            <img class="brand-logo" alt="CCMANAGER" style="width: 180px;height: 30px;border-radius: 5px" src="assets/images/ccmanager.png" alt="branding logo">
                        </c:if>
                        <c:if test="${sessionScope.societe.getLogoBase64() != null}">
                            <img class="brand-logo" alt="CCMANAGER" style="width: 180px;height: 40px;margin-top: -10px;border-radius: 5px" src="data:image/png;base64,${sessionScope.societe.getLogoBase64()}" alt="branding logo">
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
                            <c:if test="${1==2}">
                        <li class="dropdown nav-item mega-dropdown"><a class="dropdown-toggle nav-link" href="#" data-toggle="dropdown">Informations</a>
                            <ul class="mega-dropdown-menu dropdown-menu row">
                                <li class="col-md-2">
                                    <h6 class="dropdown-menu-header text-uppercase mb-1"><i class="fa fa-newspaper-o"></i> Actualités</h6>
                                    <div id="mega-menu-carousel-example">
                                        <div>
                                            <img class="rounded img-fluid mb-1" src="StackAdmin/app-assets/images/slider/slider-2.png"
                                                 alt="First slide"><a class="news-title mb-0" href="#">Poster Frame PSD</a>
                                            <p class="news-content">
                                                <span class="font-small-2">January 26, 2016</span>
                                            </p>
                                        </div>
                                    </div>
                                </li>
                                <li class="col-md-3">
                                    <h6 class="dropdown-menu-header text-uppercase"><i class="fa fa-random"></i> Paramètres</h6>

                                </li>
                                <li class="col-md-3">
                                    <h6 class="dropdown-menu-header text-uppercase"><i class="fa fa-list-ul"></i> Accordion</h6>
                                    <div id="accordionWrap" role="tablist" aria-multiselectable="true">
                                        <div class="card border-0 box-shadow-0 collapse-icon accordion-icon-rotate">
                                            <div class="card-header p-0 pb-2 border-0" id="headingOne" role="tab"><a data-toggle="collapse" data-parent="#accordionWrap" href="#accordionOne"
                                                                                                                     aria-expanded="true" aria-controls="accordionOne">Accordion Item #1</a></div>
                                            <div class="card-collapse collapse show" id="accordionOne" role="tabpanel" aria-labelledby="headingOne"
                                                 aria-expanded="true">
                                                <div class="card-content">
                                                    <p class="accordion-text text-small-3">Caramels dessert chocolate cake pastry jujubes bonbon.
                                                        Jelly wafer jelly beans. Caramels chocolate cake liquorice
                                                        cake wafer jelly beans croissant apple pie.</p>
                                                </div>
                                            </div>
                                            <div class="card-header p-0 pb-2 border-0" id="headingTwo" role="tab"><a class="collapsed" data-toggle="collapse" data-parent="#accordionWrap"
                                                                                                                     href="#accordionTwo" aria-expanded="false" aria-controls="accordionTwo">Accordion Item #2</a></div>
                                            <div class="card-collapse collapse" id="accordionTwo" role="tabpanel" aria-labelledby="headingTwo"
                                                 aria-expanded="false">
                                                <div class="card-content">
                                                    <p class="accordion-text">Sugar plum bear claw oat cake chocolate jelly tiramisu
                                                        dessert pie. Tiramisu macaroon muffin jelly marshmallow
                                                        cake. Pastry oat cake chupa chups.</p>
                                                </div>
                                            </div>
                                            <div class="card-header p-0 pb-2 border-0" id="headingThree" role="tab"><a class="collapsed" data-toggle="collapse" data-parent="#accordionWrap"
                                                                                                                       href="#accordionThree" aria-expanded="false" aria-controls="accordionThree">Accordion Item #3</a></div>
                                            <div class="card-collapse collapse" id="accordionThree" role="tabpanel" aria-labelledby="headingThree"
                                                 aria-expanded="false">
                                                <div class="card-content">
                                                    <p class="accordion-text">Candy cupcake sugar plum oat cake wafer marzipan jujubes
                                                        lollipop macaroon. Cake dragée jujubes donut chocolate
                                                        bar chocolate cake cupcake chocolate topping.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="col-md-4">
                                    <h6 class="dropdown-menu-header text-uppercase mb-1"><i class="fa fa-envelope-o"></i>Nous Contacter</h6>
                                    <form class="form form-horizontal">
                                        <div class="form-body">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label" for="inputName1">Nom</label>
                                                <div class="col-sm-9">
                                                    <div class="position-relative has-icon-left">
                                                        <input class="form-control" type="text" id="inputName1" placeholder="votre nom">
                                                        <div class="form-control-position pl-1"><i class="fa fa-user-o"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label" for="inputEmail1">Email</label>
                                                <div class="col-sm-9">
                                                    <div class="position-relative has-icon-left">
                                                        <input class="form-control" type="email" id="inputEmail1" placeholder="adresse mail">
                                                        <div class="form-control-position pl-1"><i class="fa fa-envelope-o"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label" for="inputMessage1">Message</label>
                                                <div class="col-sm-9">
                                                    <div class="position-relative has-icon-left">
                                                        <textarea class="form-control" id="inputMessage1" rows="2" placeholder="message"></textarea>
                                                        <div class="form-control-position pl-1"><i class="fa fa-commenting-o"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12 mb-1">
                                                    <button class="btn btn-primary float-right" type="button"><i class="fa fa-paper-plane-o"></i> Envoyer</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </c:if>
                    <li class="nav-item d-none d-md-block"><a class="nav-link nav-link-expand" href="#"><i class="ficon ft-maximize"></i></a></li>
                            <c:if test="${sessionScope.root=='EH2S' or not empty sessionScope.lien96}">
                        <li class="nav-item nav-search"><a class="nav-link nav-link-search" href="#"><i class="ficon ft-search"></i></a>
                            <div class="search-input">
                                <form class="search_form" method="post" action="gxwzy14iyf?q=searchresult&action=model&model=TicketmodelByPeriode&isNew=9">
                                    <input class="input" type="text" name="id" placeholder="rechercher un incident ...">
                                </form>

                            </div>
                        </li>
                    </c:if>
                    <c:if test="${empty sessionScope.root}">
                        <li class="nav-item d-none d-md-block " ><a class="nav-link nav-link-expand" href="ticket?q=index&action=jkdhfoldg458dgbjdg478962"><i class="fa fa-home"></i></a>
                            </li> 
                    </c:if>
                    <li class="nav-item d-none d-md-block"><a class="nav-link nav-link-expand" href="gestionCommandes?action=model&model=PasserCommandes&q=aideLogiciel&isNew=9"><i class="ficon ft-help-circle"></i></a></li>
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
                            <span class="user-name">${sessionScope.utilisateur.getLogin()}</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right notification" entity="user" id="${sessionScope.utilisateur.getId()}">
                            <c:if test="${not empty sessionScope.lien90}">
                                <a class="dropdown-item" href="administration?action=nbygruyr458puggc125&q=editprofil">
                                    <i class="ft-user"></i>Mon Profil
                                </a>
                                <div class="dropdown-divider " ></div>
                            </c:if>

                            <a class="dropdown-item" href="getconnexion"><i class="ft-power"></i> Logout</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<!-- ////////////////////////////////////////////////////////////////////////////-->
