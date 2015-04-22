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

<h2>Book Cart</h2>

<%
    String message = (String)request.getAttribute("message");
    
    if(message != null) {
        out.println(message+"<br />");
    }

    Collection<Book> listBook = (Collection<Book>)request.getAttribute("cart");
    if(listBook == null || listBook.size() == 0) {
        out.println("No item in the cart.<br />");
    }
    else {
        float total = 0.0f;
        out.println("<table class=\"table table-striped table-bordered\">");
        out.println("<tr><th>Title</th><th>Price</th><th>Action</th></tr>");
        for(Book book : listBook) {
            out.println("<tr><td>"+book.getTitle()+"</td><td>"+String.format("%.2f", book.getPrice())+"§</td><td> <a href=\"RemoveCart?title="+URLEncoder.encode(book.getTitle(), "UTF-8")+"\">Remove</a></td></tr>");
            total += book.getPrice();
        }
        out.println("<tr class=\"info\"><td>Total price</td><td>"+String.format("%.2f", total)+"§</td><td></td>");
        out.println("</table>");
        out.println("<a class=\"btn btn-primary\" href=\"Order\">Validate</a>");
    }
%>

<%@include file="Footer.jsp" %>