<%-- 
    Document   : ListOrder
    Created on : 20 avr. 2015, 21:19:45
    Author     : Pierre
--%>

<%@page import="entities.Order"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="entities.Book"%>

<%@include file="Header.jsp" %>

<h2>Order List</h2>

        
<%
    List<Order> listOrder = (List<Order>)request.getAttribute("listOrder");
    if(listOrder == null || listOrder.size() == 0) {
        out.println("No order found.<br />");
    }
    else {
        out.println("<ul>");
        for(Order order : listOrder) {
            out.println("<li><h3>Order #"+order.getId()+"</h3><br />");
            out.println("<ul>");
            for(Book book : order.getCart()) {
                out.println("<li>"+book.getTitle()+"</li>");
            }
            out.println("</ul></li>");
        }
        out.println("</ul>");
    }
%>

<%@include file="Footer.jsp" %>
