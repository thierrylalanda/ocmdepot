<%-- 
    Document   : header
    Created on : 16 oct. 2018, 10:51:20
    Author     : eh2s001
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta name="description" content="Logiciel de suivi de plainte client">
<meta name="keywords" content="application,logiciel,client,plainte,complaint,">
<meta name="author" content="Lalanda">

<title>OCM</title>
<link rel="apple-touch-icon" href="StackAdmin/app-assets/images/ico/apple-icon-120.png">
<link rel="shortcut icon" type="image/x-icon" href="assets/images/ccmanager.ico">
<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i%7COpen+Sans:300,300i,400,400i,600,600i,700,700i"
      rel="stylesheet">
<!-- BEGIN VENDOR CSS-->
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/vendors.css">
<link href="assets/lib/jquery-confirm/jquery-confirm.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/extensions/toastr.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/pickers/daterange/daterangepicker.css">
  <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/forms/icheck/icheck.css">
   <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/forms/icheck/custom.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/pickers/datetime/bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/pickers/pickadate/pickadate.css">

<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/forms/selects/select2.min.css">

<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/charts/jquery-jvectormap-2.0.3.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/extensions/unslider.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/weather-icons/climacons.min.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/ui/prism.min.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/fonts/meteocons/style.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/charts/morris.css">

<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/tables/datatable/datatables.min.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/tables/datatable/select.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/tables/extensions/buttons.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/tables/datatable/buttons.bootstrap4.min.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/vendors/css/tables/extensions/dataTables.colVis.css">
<!-- END VENDOR CSS-->
<!-- BEGIN STACK CSS-->
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/app.css">
<!-- END STACK CSS-->
<!-- BEGIN Page Level CSS-->
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/core/menu/menu-types/horizontal-menu.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/core/colors/palette-gradient.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/fonts/simple-line-icons/style.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/core/colors/palette-gradient.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/pages/timeline.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/core/colors/palette-gradient.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/plugins/forms/checkboxes-radios.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/plugins/forms/validation/form-validation.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/plugins/forms/switch.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/plugins/forms/dual-listbox.css">

<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/plugins/forms/wizard.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/plugins/pickers/daterange/daterange.css">
<link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/plugins/extensions/toastr.css">
<c:if test="${q=='aideLogiciel'}">
    <link rel="stylesheet" type="text/css" href="StackAdmin/app-assets/css/pages/chat-application.css">
</c:if>
<c:if test="${q=='administration/groupe'}">
    <link href="assets/lib/kendo/css/kendo.common.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/lib/kendo/css/kendo.default.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/lib/kendo/css/kendo.default.mobile.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/lib/kendo/css/css.css" rel="stylesheet" type="text/css"/>
</c:if>

     

<!-- END Page Level CSS-->
<!-- BEGIN Custom CSS-->
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<!-- END Custom CSS-->