<%-- 
    Document   : index
    Created on : 03-oct-2017, 11:00:53
    Author     : user
--%>


<%@page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World desde el jsp!</h1>
        <%
            
            Map<String, String[]> parameters = request.getParameterMap();
            for (String parameter : parameters.keySet()) {

                String[] values = parameters.get(parameter);
                
        %>
        <h1 >
            <%
                out.println(StringEscapeUtils.escapeHtml4(values[0]));
                //out.println("<p>"+values[0]+"</p>");

            %>
        </h1>
        <%  }                      
        %>
    </body>
</html>
