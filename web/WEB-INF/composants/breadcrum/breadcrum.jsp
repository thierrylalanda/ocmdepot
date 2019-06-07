<%-- 
    Document   : breadcrum
    Created on : 31 oct. 2018, 08:34:25
    Author     : eh2s001
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="content-wrapper">
    <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2">
            <h3 class="content-header-title mb-0">OCMANAGER</h3>
            <div class="row breadcrumbs-top">
                <div class="breadcrumb-wrapper col-12">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="ticket?q=index&action=jkdhfoldg458dgbjdg478962">Accueil</a>
                        </li>
                        <li class="breadcrumb-item active">${q}
                        </li>
                    </ol>
                </div>
            </div>
        </div>
        <div class="content-header-right col-md-6 col-12">
            <div class="media width-250 float-right">
                <div class="media-left media-middle">
                    <div id="sp-bar-total-sales"></div>
                </div>
                <div class="media-body media-right text-right">
                    <h3 class="m-0">${totalticketcli.size()}</h3>
                    <span class="text-muted">Incident</span>
                </div>
            </div>
        </div>
    </div>
</div>