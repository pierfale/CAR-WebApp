<%-- 
    Document   : Header.jsp
    Created on : 17 avr. 2015, 17:53:18
    Author     : Pierre
--%>

<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= request.getAttribute("title") != null ? request.getAttribute("title") : "Book sell" %></title>
    </head>
    <body>
        <header>
            <h1>Book Sell</h1>
        </header>
        
        <%
           User user = (User)request.getAttribute("user");
           
           if(user != null) {
               out.println("Welcome "+user.getUsername()+"<br />");
           }
           else {
               out.println("<a href=\"Login\">Login</a> | <a href=\"SignUp\">SignUp</a><br />");
           }
              
        %>   