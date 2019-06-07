<%-- 
    Document   : index
    Created on : 16 oct. 2018, 11:10:00
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${1==2}">
    <div class="row">
    <div class="col-xl-3 col-lg-6 col-12">
        <div class="card">
            <div class="card-content">
                <div class="media align-items-stretch">
                    <div class="p-2 text-center bg-primary bg-darken-2">
                        <i class="icon-globe font-large-2 white"></i>
                    </div>
                    <div class="p-2 bg-gradient-x-primary white media-body">
                        <h5>Total Tickets</h5>
                        <h5 class="text-bold-400 mb-0"><i class="ft-plus"></i> ${totalticketcli.size()}</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-lg-6 col-12">
        <div class="card">
            <div class="card-content">
                <div class="media align-items-stretch">
                    <div class="p-2 text-center bg-success bg-darken-2">
                        <i class="fa fa-plus-circle font-large-2 white"></i>
                    </div>
                    <div class="p-2 bg-gradient-x-success white media-body">
                        <h5>Resolu</h5>
                        <h5 class="text-bold-400 mb-0"><i class="ft-arrow-up"></i>${ticketresolutcli.size()}</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-lg-6 col-12">
        <div class="card">
            <div class="card-content">
                <div class="media align-items-stretch">
                    <div class="p-2 text-center bg-danger bg-darken-2">
                        <i class="ft ft-minus-circle font-large-2 white"></i>
                    </div>
                    <div class="p-2 bg-gradient-x-danger white media-body">
                        <h5>Non Resolu</h5>
                        <h5 class="text-bold-400 mb-0"><i class="ft-arrow-down-right"></i>${ticketNonResolutcli.size()}</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-lg-6 col-12">
        <div class="card">
            <div class="card-content">
                <div class="media align-items-stretch">
                    <div class="p-2 text-center bg-warning bg-darken-2">
                        <i class="icon-trash font-large-2 white"></i>
                    </div>
                    <div class="p-2 bg-gradient-x-warning white media-body">
                        <h5>Insolvable</h5>
                        <h5 class="text-bold-400 mb-0"><i class="ft-arrow-down"></i>${ticketInsolvablecli.size()}</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- Basic Area Chart -->
<div class="row match-height">
    <div class="col-xl-12 col-lg-12">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title">Etat Annuel</h4>
                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                <div class="heading-elements">
                    <ul class="list-inline mb-0">
                        <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                        <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                    </ul>
                </div>
            </div>
            <div class="card-content collapse show">
                <div class="card-body">

                    <div id="basic-area" class="height-600 echart-container"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-4 col-lg-12 hidden">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title">Notifications</h4>
                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                <div class="heading-elements">
                    <ul class="list-inline mb-0">
                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                    </ul>
                </div>
            </div>
            <div class="card-content px-1">
                <div  class=" height-600  notification_page">



                </div>
            </div>
        </div>
    </div>
</div>

<div class="row match-height hidden">
    <div class="col-xl-12 col-lg-12">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title">Auto formation du logiciel</h4>
                <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
                <div class="heading-elements ">
                    <ul class="list-inline mb-0">
                        <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                        <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                        <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                    </ul>
                </div>
            </div>
            <div class="card-content collapse show">
                <div class="card-body">
                    <div class="embed-responsive embed-responsive-4by3">
                        <iframe src="https://player.vimeo.com/video/118589137?title=0&byline=0"></iframe>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</c:if>
<div class="row match-height">
    <div class="col-xl-12 col-lg-12">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title text-lg-center"><strong>Engineering Hard & Soft Solutions</strong></h3>
            <a class="heading-elements-toggle"><i class="fa fa-ellipsis-v font-medium-3"></i></a>
            <div class="heading-elements">
                <ul class="list-inline mb-0">
                    <li><a data-action="collapse"><i class="ft-minus"></i></a></li>
                    <li><a data-action="reload"><i class="ft-rotate-cw"></i></a></li>
                    <li><a data-action="expand"><i class="ft-maximize"></i></a></li>
                </ul>
            </div>
        </div>
        <div class="card-content collapse show">
            <div class="card-body">

                <div  class="height-500 echart-container">

                    <img class="float-none" style="height: 400px;margin-bottom: 0px;margin-left: 150px;align-content: center" src="assets/images/logo ehs-01.jpg" alt="branding logo">
                    <div class="card-footer">
                        <h5 class="card-footer text-center">Email: contact@eh2s.com  Standard: (237)658-20-71-41 Site: <a href="https://www.eh2s.com/" target="_blank" >www.eh2s.com</a></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
