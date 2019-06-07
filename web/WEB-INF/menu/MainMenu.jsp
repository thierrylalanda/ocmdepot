<%-- 
    Document   : MainMenu
    Created on : 15 oct. 2018, 16:02:06
    Author     : eh2s001
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<c:if test="${sessionScope.client !=  null}">
    <c:import url="/WEB-INF/menu/client/firstNavBar.jsp"/>
    <c:import url="/WEB-INF/menu/client/secondNavBar.jsp"/>
</c:if>
<c:if test="${sessionScope.client ==  null}">
    <c:import url="/WEB-INF/menu/firstNavBar.jsp"/>
    <c:import url="/WEB-INF/menu/secondNavBar.jsp"/>
</c:if>