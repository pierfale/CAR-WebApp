<%-- 
    Document   : Login
    Created on : 17 avr. 2015, 16:17:55
    Author     : Pierre
--%>

<%@include file="Header.jsp" %>

<h1>Login</h1>

<%
    String message = (String)request.getAttribute("message");
    
    if(message != null) {
        out.println(message+"<br />");
    }
%>

<form method="POST" action="Login">
    Username : <input name="username" /><br />
    Password : <input type="password" name="password" /><br />
    <input type="submit" />
</form>

<%@include file="Footer.jsp" %>