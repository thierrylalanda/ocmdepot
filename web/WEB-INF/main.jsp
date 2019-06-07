<%-- 
    Document   : main
    Created on : 15 oct. 2018, 16:01:45
    Author     : eh2s001
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html class="loading" lang="en" data-textdirection="ltr">
    <head>
        <c:import url="/WEB-INF/header.jsp"/>
    </head>
    <body class="horizontal-layout bg-blue-grey horizontal-menu 2-columns   menu-expanded" data-open="hover"
          data-menu="horizontal-menu" data-col="2-columns">
        <c:if test="${message != null}">
        <message type="${message.getType()}" title="${message.getTitle()}" content="${message.getNotification()}"></message>
        </c:if>

    <c:import url="/WEB-INF/menu/MainMenu.jsp"/>

    <div class="app-content content">
        <div class="content-wrapper">
            <div class="content-header row">
            </div>
            <div class="content-body">
                <div class="row">
                    <c:import url="/WEB-INF/menu/sideMenu.jsp"/>
                   
                    <div class="<c:if test="${sessionScope.client !=  null or (q.split('/')[0] == 'commande' and q.split('/')[1] != 'societe')}">col-md-12</c:if><c:if test="${(sessionScope.client ==  null and q.split('/')[0] != 'commande') or (q.split('/')[0] == 'commande' and q.split('/')[1] == 'societe') or sessionScope.root=='EH2S' }">col-md-10</c:if>">
                        <c:import url="/WEB-INF/pages/${q}.jsp"/>

                    </div>

                </div>

            </div>
        </div>
    </div>
<!--            <div class="loader"> </div>            -->
    <c:import url="/WEB-INF/footer/MainFooter.jsp"/>

    <c:import url="/WEB-INF/footer.jsp"/>
</body>
</html>