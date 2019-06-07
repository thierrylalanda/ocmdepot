<%-- 
    Document   : footer
    Created on : 16 oct. 2018, 10:51:33
    Author     : eh2s001
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- BEGIN VENDOR JS-->

<script src="StackAdmin/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
<script src="assets/lib/jquery-confirm/jquery-confirm.js" type="text/javascript"></script>
<!-- BEGIN VENDOR JS-->
<!-- BEGIN PAGE VENDOR JS-->
<script src="StackAdmin/app-assets/vendors/js/forms/icheck/icheck.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/extensions/jquery.steps.min.js" type="text/javascript"></script>
<script type="text/javascript" src="StackAdmin/app-assets/vendors/js/ui/jquery.sticky.js"></script>
<script type="text/javascript" src="StackAdmin/app-assets/vendors/js/charts/jquery.sparkline.min.js"></script>

<!-- Tableau de bord-->
<script src="StackAdmin/app-assets/vendors/js/extensions/jquery.knob.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/js/scripts/extensions/knob.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/charts/raphael-min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/charts/morris.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/charts/jvector/jquery-jvectormap-2.0.3.min.js"
type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/charts/jvector/jquery-jvectormap-world-mill.js"
type="text/javascript"></script>
<script src="StackAdmin/app-assets/data/jvector/visitor-data.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/charts/chart.min.js" type="text/javascript"></script>
<!-- Tableau de bord-->

<script src="StackAdmin/app-assets/vendors/js/extensions/unslider-min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/timeline/horizontal-timeline.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/extensions/toastr.min.js" type="text/javascript"></script>
<!-- END PAGE VENDOR JS-->
<c:if test="${q=='aideLogiciel'}">
         <script src="StackAdmin/app-assets/js/scripts/pages/chat-application.js" type="text/javascript"></script>
     </c:if>
         
<!-- BEGIN PAGE VENDOR JS-->
<script src="StackAdmin/app-assets/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/tables/datatable/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/tables/datatable/dataTables.select.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/tables/datatable/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/tables/datatable/buttons.bootstrap4.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/tables/datatable/dataTables.colVis.js" type="text/javascript"></script>
<!-- END PAGE VENDOR JS-->
<script src="assets/lib/eventemitter.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/tables/jszip.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/tables/pdfmake.min.js" type="text/javascript"></script>

<script src="StackAdmin/app-assets/vendors/js/tables/buttons.html5.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/tables/buttons.print.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/tables/buttons.colVis.min.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/forms/listbox/jquery.bootstrap-duallistbox.min.js"
type="text/javascript"></script>
<c:if test="${q=='administration/groupe'}">
    <script src="assets/lib/kendo/js/kendo.all.min.js" type="text/javascript"></script>
    <script src="assets/controleurs/ManageAccess.js" type="text/javascript"></script>
</c:if>
<!-- BEGIN STACK JS-->
<script src="StackAdmin/app-assets/js/core/app-menu.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/js/core/app.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/js/scripts/customizer.js" type="text/javascript"></script>
<!-- END STACK JS-->
<!-- BEGIN PAGE LEVEL JS-->
<script type="text/javascript" src="StackAdmin/app-assets/js/scripts/ui/breadcrumbs-with-stats.js"></script>


<script src="StackAdmin/app-assets/vendors/js/forms/select/select2.full.min.js" type="text/javascript"></script>

<script src="StackAdmin/app-assets/vendors/js/forms/spinner/jquery.bootstrap-touchspin.js"
type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/forms/validation/jqBootstrapValidation.js"
type="text/javascript"></script>

<script src="StackAdmin/app-assets/vendors/js/forms/toggle/switchery.min.js" type="text/javascript"></script>
 <script src="StackAdmin/app-assets/vendors/js/forms/toggle/bootstrap-checkbox.min.js"
  type="text/javascript"></script>
<script src="StackAdmin/app-assets/js/scripts/forms/validation/form-validation.js"
type="text/javascript"></script>
 <script src="StackAdmin/app-assets/js/scripts/forms/switch.js" type="text/javascript"></script>

<script src="StackAdmin/app-assets/vendors/js/pickers/dateTime/moment-with-locales.min.js"
type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/pickers/daterange/daterangepicker.js"
type="text/javascript"></script>

<script src="StackAdmin/app-assets/vendors/js/pickers/dateTime/bootstrap-datetimepicker.min.js"
type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/pickers/pickadate/picker.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/pickers/pickadate/picker.date.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/pickers/pickadate/picker.time.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/pickers/pickadate/legacy.js" type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/pickers/daterange/daterangepicker.js"
type="text/javascript"></script>
<script src="StackAdmin/app-assets/vendors/js/forms/validation/jquery.validate.min.js"
type="text/javascript"></script>

<script src="StackAdmin/app-assets/vendors/js/forms/repeater/jquery.repeater.min.js"
type="text/javascript"></script>
<!-- END PAGE LEVEL JS-->

<script src="StackAdmin/app-assets/js/scripts/pickers/dateTime/bootstrap-datetime.js"
type="text/javascript"></script>

 
<script src="assets/lib/pdfmake.js" type="text/javascript"></script>
<script src="assets/lib/vfs_fonts.js" type="text/javascript"></script>
<script src="assets/js/PrintPdf.js" type="text/javascript"></script>

<script src="angularJS/libs/angular.js" type="text/javascript"></script>
<script src="angularJS/app.js" type="text/javascript"></script>
<script src="assets/js/manageTable.js" type="text/javascript"></script>

<script src="assets/js/manageUser.js" type="text/javascript"></script>
<script src="assets/js/mainscript.js" type="text/javascript"></script>

<script src="assets/js/manageIncident.js" type="text/javascript"></script>

<script src="StackAdmin/src/js/scripts/forms/checkbox-radio.js" type="text/javascript"></script>

<script src="assets/js/manageForm.js" type="text/javascript"></script>

<script src="assets/js/getEntity.js" type="text/javascript"></script>
   
<c:if test="${empty sessionScope.root}">
    <script src="assets/js/notification/notifications.js" type="text/javascript"></script>
</c:if>


<c:if test="${q=='dashboard/dashboard' or q=='dashboard/commande'}">
     
    
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/echarts.js" type="text/javascript"></script>
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/chart/bar.js" type="text/javascript"></script>
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/chart/line.js" type="text/javascript"></script>
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/chart/pie.js" type="text/javascript"></script>
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/chart/funnel.js" type="text/javascript"></script>
       <script src="StackAdmin/app-assets/vendors/js/charts/echarts/chart/gauge.js" type="text/javascript"></script>
    <script src="assets/js/dashboard/Chart.js" type="text/javascript"></script>
    
 
  
  <script src="assets/js/dashboard/gaugeDashboard.js" type="text/javascript"></script>
  <script src="StackAdmin/app-assets/js/scripts/charts/echarts/pie-doughnut/basic-pie.js"
  type="text/javascript"></script>
  
  <script src="StackAdmin/app-assets/js/scripts/charts/echarts/pie-doughnut/doughnut.js"
  type="text/javascript"></script>
   <script src="StackAdmin/app-assets/js/scripts/charts/echarts/pie-doughnut/multiple-doughnut.js"
  type="text/javascript"></script>
   
  
    <script src="StackAdmin/app-assets/js/scripts/pages/dashboard-ecommerce.js" type="text/javascript"></script>
    <script src="assets/js/dashboard/dashboardCommande.js" type="text/javascript"></script>
    <script src="assets/js/dashboard/dashboard.js" type="text/javascript"></script>
    <script src="assets/js/dashboard/PieDashboard.js" type="text/javascript"></script>
    <script src="assets/js/dashboard/dashboardParEntite.js" type="text/javascript"></script>
    <script src="assets/js/dashboard/dashboardTable.js" type="text/javascript"></script>
     
 </c:if>
<c:if test="${q=='index'}">
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/echarts.js" type="text/javascript"></script>
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/chart/bar.js" type="text/javascript"></script>
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/chart/line.js" type="text/javascript"></script>
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/chart/pie.js" type="text/javascript"></script>
    <script src="StackAdmin/app-assets/vendors/js/charts/echarts/chart/funnel.js" type="text/javascript"></script>

    <script src="assets/js/dashboard/dashboadHome.js" type="text/javascript"></script>
</c:if>
<c:if test="${q=='admin/gestionLicence'}">
    <script src="assets/controleurs/ManageAdmin.js" type="text/javascript"></script>
</c:if>
<c:if test="${q=='notifications'}">
    <link href="angularJS/libs/angular-ui-notification.css" rel="stylesheet" type="text/css"/>
    <script src="angularJS/libs/angular.js" type="text/javascript"></script>
    <script src="angularJS/libs/angular-ui.js" type="text/javascript"></script>
    <script src="angularJS/libs/angular-ui-notification.js" type="text/javascript"></script>

    <script src="angularJS/app.js" type="text/javascript"></script>

    <script src="angularJS/controllers/NotificationController.js" type="text/javascript"></script>

</c:if>
    <script src="assets/js/printMarge.js" type="text/javascript"></script>
<c:if test="${q=='administration/societe'}">
    <script src="assets/js/administration.js" type="text/javascript"></script>

</c:if>
<c:if test="${q=='commande/addcommande' or q=='client/addcommande' or q=='commande/traitement' or q=='commande/endCommande'}">
     <script src="assets/js/commande/commande.js" type="text/javascript"></script>
</c:if>
<c:if test="${q=='commande/feuilleRoute' or q=='commande/feuilleRouteTourner' or q=='commande/vente/feuilleRoute'}">
    <script src="assets/lib/xlsx.full.min.js" type="text/javascript"></script>
    <script src="assets/lib/FileSaver.js" type="text/javascript"></script>
    <!-- <script src="assets/js/feuilleDeRoute.js" type="text/javascript"></script> -->
     <script src="assets/js/commande/printFeuilleDeRoute2.js" type="text/javascript"></script> 
     <script src="assets/js/feuilleDeRouteExcel.js" type="text/javascript"></script>
</c:if>
     <c:if test="${q=='aideLogiciel'}">
         <script src="assets/lib/apiai/ApiAi.js" type="text/javascript"></script>
         <script src="assets/js/help/helpRobot.js" type="text/javascript"></script>
         <script defer src="assets/js/help/layout.js" type="text/javascript"></script>
     </c:if>     
<script src="assets/js/getEnityNewClient.js" type="text/javascript"></script> 
<script src="assets/js/commande/gestionMagasin.js" type="text/javascript"></script>
<script src="assets/js/commande/gestionEmballage.js" type="text/javascript"></script>
<script src="assets/js/commande/bonCommande.js" type="text/javascript"></script>
<script src="assets/js/commande/caisse.js" type="text/javascript"></script>
<script src="assets/js/commande/inventaire.js" type="text/javascript"></script>