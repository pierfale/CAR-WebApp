<%-- 
    Document   : ListBook
    Created on : 17 avr. 2015, 17:37:39
    Author     : Pierre
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Book"%>

<%@include file="Header.jsp" %>

<h1>Book List</h1>
        
<%
    List<Book> listBook = (List<Book>)request.getAttribute("listBook");
    if(listBook == null) {
        out.println("No book found.<br />");
    }
    else {
        for(Book book : listBook) {
            out.println(book.getTitle()+" </br>");
        }
    }
%>

<%@include file="Footer.jsp" %>