<%-- 
    Document   : jstl
    Created on : 17-oct-2017, 8:37:00
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:set var="contador" scope="session" value="${0}"> </c:set>
        <c:out value="${contador}" />
        <br>  parametro <c:out value="${param.atributo}" />
        <c:out value="${atributo}" />
        
        <c:forEach items="${sessionScope}" var="valor">
            <c:out value="${valor.value}=${valor.key}" />
        </c:forEach>
        
       
    </body>
</html>
