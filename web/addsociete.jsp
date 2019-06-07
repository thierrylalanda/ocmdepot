<%-- 
    Document   : addsociete
    Created on : 27 nov. 2018, 11:45:56
    Author     : messi01
--%>

<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta name="description" content="Stack admin is super flexible, powerful, clean &amp; modern responsive bootstrap 4 admin template with unlimited possibilities.">
        <meta name="keywords" content="admin template, stack admin template, dashboard template, flat admin template, responsive admin template, web app">
        <meta name="author" content="PIXINVENT">
        <title>Login</title>
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
    </head>
    <body class="horizontal-layout horizontal-menu 1-column bg-full-screen-image bg-lighten-2   menu-expanded" data-open="hover"
          data-menu="horizontal-menu" data-col="2-columns">
    <c:if test="${message != null}">
        <message type="${message.getType()}" title="${message.getTitle()}" content="${message.getNotification()}"></message>
    </c:if>



    <div class="app-content content">
        <div class="content-wrapper">
            <div class="content-header row">
            </div>
            <div class="content-body">
                <div class="row">
<div class="col-12 d-flex align-items-center justify-content-center">
                            <div class="col-md-2 col-2"></div>
                            <div class="col-md-8 col-10 box-shadow-2 p-0">
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
                                        <p class="card-subtitle line-on-side text-muted text-center font-small-3 mx-2">
                                            <span>Enregistrer une nouvelle société</span><br>
                                        </p>
                                        <div class="card-body pt-0">
                                            <form class="form" method="post" action="FreeAdmingetconnexion?q=setProfil&newIstance=societe&genericSocity=who" >

                                                <!-- Step 1 -->
                                                <h6>Infos Société</h6>
                                                <fieldset>
                                                    <div class="form-body">
                                                        <div class="row">
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput1">Nom société
                                                                    <span class="required">*</span>
                                                                </label>

                                                                <input type="text" id="complaintinput1" class="form-control-sm form-control round" placeholder="nom de la societe"
                                                                       name="nom"
                                                                       required>

                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="imma">N° Contribuable
                                                                    <span class="required">*</span></label>
                                                                </label>

                                                                <input type="text" id="imma" class="form-control-sm form-control round" placeholder="N° de contribuable"
                                                                       name="imma" required >

                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="sigle">Sigle
                                                                    <span class="required">*</span></label>

                                                                <input type="text" id="sigle" value="${onesociete.getSigle()}" class="form-control-sm form-control round" name="sigle" required>

                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="complaintinput4">CNI
                                                                    <span class="required">*</span></label>

                                                                <input type="text" id="complaintinput4"value="${onesociete.getCniRc()}" required class="form-control-sm form-control round" placeholder=""
                                                                       name="cnirc" >

                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="regimeF">Régime fiscal
                                                                    <span class="required">*</span></label>

                                                                <input type="text" id="regimeF" value="${societe.getRegimeFiscal()}" class="form-control-sm form-control round" name="regimeFiscal" required>

                                                            </div>
                                                            <div class="form-group col-6 mb-2">
                                                                <label for="centreI">Centre Impôt
                                                                    <span class="required">*</span></label>

                                                                <input type="text" id="centreI"value="${onesociete.getCentreImpot()}" required class="form-control-sm form-control round" placeholder=""
                                                                       name="centreImpot" >

                                                            </div>

                                                        </div>
                                                        <div class="row">


                                                            <div class="form-group col-12 mb-2">
                                                                <label for="activite">Activité
                                                                    <span class="required">*</span></label>

                                                                <textarea id="activite" maxlength="255" name="activite" required class="form-control-sm form-control round">${onesociete.getActivite()}</textarea>


                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-12 mb-2">
                                                                <label for="complaintinput2">Adresse
                                                                    <span class="required">*</span>
                                                                </label>

                                                                <input type="text" id="complaintinput2"  class="form-control-sm form-control round" placeholder="adresse"
                                                                       name="adresse"
                                                                       required>

                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-4 mb-2">
                                                                <label for="complaintinput3">Tel
                                                                    <span class="required">*</span></label>

                                                                <input type="number" id="complaintinput3"  class="form-control-sm form-control round" name="tel" required>

                                                            </div>
                                                            <div class="form-group col-4 mb-2">
                                                                <label for="complaintinput5">E-Mail
                                                                    <span class="required">*</span></label>
                                                                <input type="email" id="complaintinput5" class="form-control-sm form-control round" placeholder="adresse mail"
                                                                       name="email" required>

                                                            </div>
                                                            <div class="form-group col-4 mb-2">
                                                                <label for="complaintinput4">Code Postal</label>

                                                                <input type="text" id="complaintinput4" class="form-control-sm form-control round" placeholder="code postal"
                                                                       name="codeposte" >

                                                            </div>
                                                        </div>
                                                        <div class="row">


                                                        </div>
                                                    </div>

                                                </fieldset>

                                                <input type="submit" class="btn btn-outline-secondary  round btn-sm"
                                                       value="Enregistrer">
                                            </form>
                                            <p class="card-subtitle line-on-side text-muted text-center font-small-3 mx-2 my-1">
                                                <span><a href="index.jsp" class="btn btn-outline-primary"><i class="ft-unlock"></i> Se connecter</a></span>
                                            </p>
                                        </div>

                                    </div>
                                </div>
                                <p style="text-align: center;color: #666666">&copy; <script> document.write(new Date().getFullYear());</script> Make By <a href="https://www.eh2s.com" target="_blank" >EH2S</a></p>
                            </div>
                            <div class="col-md-2 col-2"></div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>

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

