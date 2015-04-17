<%-- 
    Document   : Login
    Created on : 17 avr. 2015, 16:17:55
    Author     : Pierre
--%>

<%@include file="Header.jsp" %>

<%
    String message = (String)request.getAttribute("message");
    
    if(message != null) {
        out.println(message+"<br />");
    }
%>

<form method="POST" action="Login">
    <input name="username" />
    <input type="password" name="password" />
    <input type="submit" />
</form>

<%@include file="Footer.jsp" %>