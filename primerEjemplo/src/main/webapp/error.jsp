<%-- 
    Document   : error
    Created on : 09-oct-2017, 11:04:00
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Se ha producido un error</h1>
        <h1><%   
        
        out.println(request.getAttribute("mensajeError"));
        %></h1>
    </body>
</html>
