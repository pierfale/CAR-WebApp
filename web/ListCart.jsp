<%-- 
    Document   : ListCart
    Created on : 19 avr. 2015, 22:38:05
    Author     : Pierre
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="entities.Book"%>
<%@include file="Header.jsp" %>

<h1>Book Cart</h1>
        
<%
    Collection<Book> listBook = (Collection<Book>)request.getAttribute("cart");
    if(listBook == null || listBook.size() == 0) {
        out.println("No item in the cart.<br />");
    }
    else {
        float total = 0.0f;
        for(Book book : listBook) {
            out.println(book.getTitle()+" <a href=\"RemoveCart?title="+URLEncoder.encode(book.getTitle(), "UTF-8")+"\">Remove</a>");
            out.println("<br />");
            total += book.getPrice();
        }
        out.println("Total price : "+total+"<br />");
    }
%>

<%@include file="Footer.jsp" %>