<%-- 
    Document   : SignUp
    Created on : 17 avr. 2015, 16:27:51
    Author     : Pierre
--%>

<%@include file="Header.jsp" %>

<h2>Sign Up</h2>

<%
    String message = (String)request.getAttribute("message");
    
    if(message != null) {
        out.println(message+"<br />");
    }
%>

<form method="POST" action="SignUp">
    <div class="form-group">
        <label for="name">Username :</label>
        <input class="form-control" id="name" name="username" />
    </div>
    
    <div class="form-group">
        <label for="pass">Password :</label>
        <input class="form-control" id="pass" type="password" name="password" />
    </div>
    
    <input class="btn btn-default" type="submit" />
    
</form>

<%@include file="Footer.jsp" %>