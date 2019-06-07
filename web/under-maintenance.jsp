<%-- 
    Document   : under-maintenance
    Created on : 1 nov. 2018, 12:29:59
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
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/ccmanager.ico">
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
                        <div class="col-12 d-flex align-items-center justify-content-center">
                            <div class="col-md-4 col-10 box-shadow-2 p-0">
                                <div class="card border-grey border-lighten-3 m-0">
                                    <div class="card-header border-0">
                                        <div class="card-title text-center">
                                            <div class="p-1">
                                               <img class="float-none" style="width: 100px;height: 100px;margin-bottom: 0px;margin-top : 0px;align-content: center" src="assets/images/ccmanager.png" alt="branding logo">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-content">
                                        <p class="bg-danger text-center">
                                            <c:if test="${message!= null}">
                                                ${message.getMessage()}
                                            </c:if>
                                        </p>
                                        
                                        <div class="card-body pt-0">
                                            <h2 class="text-center">Cette page est en maintenance</h2>
                                            <p>Veuillez vous reconnecter plus tard</p>
                                        </div>
                                        
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

