<%-- 
    Document   : index
    Created on : 16 oct. 2018, 11:16:48
    Author     : eh2s001
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta name="description" content="Stack admin is super flexible, powerful, clean &amp; modern responsive bootstrap 4 admin template with unlimited possibilities.">
        <meta name="keywords" content="admin template, stack admin template, dashboard template, flat admin template, responsive admin template, web app">
        <meta name="author" content="PIXINVENT">
        <title>OCM</title>
        <link rel="apple-touch-icon" href="StackAdmin/app-assets/images/ico/apple-icon-120.png">
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/ccmanager.png">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i%7COpen+Sans:300,300i,400,400i,600,600i,700,700i"
              rel="stylesheet">
        <!-- BEGIN VENDOR CSS-->
        <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/vendors.css">
        <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/forms/icheck/icheck.css">
        <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/forms/icheck/custom.css">
        <!-- END VENDOR CSS-->
        <!-- BEGIN STACK CSS-->
        <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/app.css">
        <!-- END STACK CSS-->
        <!-- BEGIN Page Level CSS-->
        <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/core/menu/menu-types/horizontal-menu.css">
        <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/core/colors/palette-gradient.css">
        <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/pages/login-register.css">
        <!-- END Page Level CSS-->
        <!-- BEGIN Custom CSS-->
        <!-- END Custom CSS-->
    </head>
    <body class="horizontal-layout horizontal-menu 1-column bg-full-screen-image bg-lighten-2 menu-expanded blank-page blank-page"
          data-open="hover" data-menu="horizontal-menu" data-col="1-column">
        <!-- ////////////////////////////////////////////////////////////////////////////-->
        <div class="app-content content">
            
            <div class="content-wrapper">
                <div class="content-header row">
                </div>
                <div class="content-body">
                    <section class="flexbox-container">
                        
                        <div class="col-12 d-flex  justify-content-center" >
                            <div class="col-md-4 col-10 box-shadow-2 p-0">
                                <div class="card border-grey border-lighten-3 m-0">
                                    <div class="card-header border-0">
                                        <div class="card-title text-center ">
                                            <div class="p-1" >
                                                <img class="float-none" style="width: 100px;height: 100px;margin-bottom: 0px;align-content: center" src="assets/images/ccmanager.png" alt="branding logo">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-content">
                                        <p class="bg-danger text-center">
                                            <c:if test="${message!= null}">
                                                ${message.getMessage()}
                                            </c:if>
                                        </p>
                                        <p class="card-subtitle line-on-side text-muted text-center font-small-3 mx-2">
                                            <span>Connexion à votre compte utilisateur</span><br>

                                        </p>
                                        <h3 class="text-center" style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);color:#fd9701">GESTION DES COMMANDES ET PLAINTES CLIENTS</h3>
                                        <div class="card-body pt-0">
                                            <form class="form-horizontal" action="getconnexion?q=index" method="post">
                                                <fieldset class="form-group floating-label-form-group">
                                                    <label for="user-name">Nom utilisateur</label>
                                                    <input type="text" class="form-control" id="user-name" required name="user" placeholder="Nom Utilisateur">
                                                </fieldset>
                                                <fieldset class="form-group floating-label-form-group mb-1">
                                                    <label for="user-password">Mot de passe</label>
                                                    <input type="password" class="form-control" required id="user-password" name="password" placeholder="mot de passe">
                                                </fieldset>
                                                <div class="form-group row">
                                                    <div class="col-md-6 col-12 text-center text-sm-left">
                                                        <fieldset>
                                                            <input type="checkbox" id="remember-me" class="chk-remember">
                                                            <label for="remember-me"> Se Souvenir de moi</label>
                                                        </fieldset>
                                                    </div>
                                                    <div class="col-md-6 col-12 float-sm-left text-center text-sm-right"><a href="recover-password.jsp" class="card-link">Mot de Passe oublié?</a></div>
                                                    <div class="col-md-6 col-12 float-sm-left text-center text-sm-right"></div>
                                                    <div  class="col-md-6 col-12 float-sm-left text-center text-sm-right"><a href="addsociete.jsp" class="card-link">créer une société</a></div>
                                                </div>
                                                <button type="submit" class="btn btn-outline-primary btn-block"><i class="ft-unlock"></i> Se Connecter</button>
                                            </form>

                                        </div>
                                        <p class="card-subtitle line-on-side text-muted text-center font-small-3 mx-2 my-1">
                                            <span><a href="indexclient.jsp" class="btn btn-outline-danger btn-block"><i class="ft-user"></i> Client</a></span>
                                        </p>

                                    </div>
                                </div>
                                <p style="text-align: center;color: #666666">&copy; <script> document.write(new Date().getFullYear());</script> Make By <a href="https://www.eh2s.com" target="_blank" >EH2S</a></p>
                            </div>
                        </div>
                    </section>
                     
                </div>
            </div>
           
        </div>
        <!-- ////////////////////////////////////////////////////////////////////////////-->
        <!-- BEGIN VENDOR JS-->
        <script src="StackAdmin/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
        <!-- BEGIN VENDOR JS-->
        <!-- BEGIN PAGE VENDOR JS-->
        <script type="text/javascript" src="StackAdmin/app-assets/vendors/js/ui/jquery.sticky.js"></script>
        <script type="text/javascript" src="StackAdmin/app-assets/vendors/js/charts/jquery.sparkline.min.js"></script>
        <script src="StackAdmin/app-assets/vendors/js/forms/icheck/icheck.min.js" type="text/javascript"></script>
        <!-- END PAGE VENDOR JS-->
        <!-- BEGIN STACK JS-->
        <script src="StackAdmin/app-assets/js/core/app-menu.js" type="text/javascript"></script>
        <script src="StackAdmin/app-assets/js/core/app.js" type="text/javascript"></script>
        <script src="StackAdmin/app-assets/js/scripts/customizer.js" type="text/javascript"></script>
        <!-- END STACK JS-->
        <!-- BEGIN PAGE LEVEL JS-->
        <script type="text/javascript" src="StackAdmin/app-assets/js/scripts/ui/breadcrumbs-with-stats.js"></script>
        <script src="StackAdmin/app-assets/js/scripts/forms/form-login-register.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL JS-->
        <script>

            sessionStorage.clear();

        </script>
    </body>
</html>

