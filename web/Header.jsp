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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    </head>
    <body>
        
        <div class="row">
            <div class="col-md-10 col-md-offset-1">.
                <header>
                    <h1>Book Sell</h1>
                    <hr>
                </header>


                <%
                   User user = (User)request.getAttribute("user");

                   if(user != null) {
                       out.println("Welcome "+user.getUsername()+" <a class=\"btn btn-danger btn-xs\" href=\"Logout\">Logout</a>");
                       if(user.getRank() == User.Rank.ADMIN)
                           out.println("<a class=\"btn btn-warning btn-xs\" href=\"AddBook\">Admin panel</a><br />");
                       else
                           out.println("<br />"); 

                       out.println("<a class=\"btn btn-default btn-xs\" href=\"ListCart\">Cart ("+request.getAttribute("cartSize")+" item(s))</a> <a class=\"btn btn-default btn-xs\" href=\"ListOrder\">Order history</a><br />");
                   }
                   else {
                       out.println("<a class=\"btn btn-default btn-xs\" href=\"Login\">Login</a> <a class=\"btn btn-default btn-xs\" href=\"SignUp\">SignUp</a><br />");
                   }

                %>   

                <hr>
                
                <nav>
                    <ul class="nav secondary-nav">
                        <li class="dropdown">
                        <li><a class="menu" href="ListBook">List of books</a></li>
                        <li><a class="menu" href="ListAuthor">List of authors</a></li>
                    </ul>
                </nav>
                
                <hr>
                
        