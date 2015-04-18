<%-- 
    Document   : SignUp
    Created on : 17 avr. 2015, 16:27:51
    Author     : Pierre
--%>

<%@include file="Header.jsp" %>

<h1>Sign Up</h1>

<%
    String message = (String)request.getAttribute("message");
    
    if(message != null) {
        out.println(message+"<br />");
    }
%>

<form method="POST" action="SignUp">
    Login : <input name="username" /> <br />
    Password : <input type="password" name="password" /> <br />
    <input type="submit" />
</form>

<%@include file="Footer.jsp" %>