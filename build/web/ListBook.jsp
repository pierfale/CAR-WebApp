<%-- 
    Document   : ListBook
    Created on : 17 avr. 2015, 17:37:39
    Author     : Pierre
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="entities.Book"%>

<%@include file="Header.jsp" %>

<h1>Book List</h1>

<form method="POST" action="ListBook">
    Search a book : <input name="search" placeholder="book title" value="<%= request.getAttribute("search") %>"/> <input type="submit" value="Go" />
</form>
        
<%
    List<Book> listBook = (List<Book>)request.getAttribute("listBook");
    if(listBook == null || listBook.size() == 0) {
        out.println("No book found.<br />");
    }
    else {
        for(Book book : listBook) {
            out.println(book.getTitle()+" "+String.format("%.2f", book.getPrice()));
            if(user != null)
            out.println("<a href=\"AddCart?title="+URLEncoder.encode(book.getTitle(), "UTF-8")+"\">Add to cart</a>");
            out.println("<br />");
        }
    }
%>

<%@include file="Footer.jsp" %>