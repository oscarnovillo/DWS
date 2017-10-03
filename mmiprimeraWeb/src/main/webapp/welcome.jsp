<%-- 
    Document   : newjsp
    Created on : 20-sep-2016, 8:57:44
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>Bienvenido</h1>
        <h1> <c:out value="${nombre}"/>  </h1>
      
    </body>
</html>
