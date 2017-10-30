<%-- 
    Document   : pintarListaAlumnos
    Created on : Oct 28, 2017, 8:02:42 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="utils.Constantes" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ALUMNOS</h1>
        pruebaCTE: <%= Constantes.PRUEBA %> <c:out value="${Constantes.PRUEBA}" ></c:out> <br>
        <c:forEach items="${alumnos}" var="alumno">  
            ${alumno.nombre},<fmt:formatDate value="${alumno.fecha_nacimiento}" pattern="dd-MMM-yyyy"/>,
            ${alumno.mayor_edad}
            <br />  
        </c:forEach> 
    </body>
</html>
