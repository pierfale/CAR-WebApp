<%-- 
    Document   : ListBook
    Created on : 17 avr. 2015, 17:37:39
    Author     : Pierre
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="entities.Book"%>

<%@include file="Header.jsp" %>

<h2>Book List</h2>

<p>
    <form class="form-inline" method="POST" action="ListBook">
        <div class="form-group">
            <label>Search a book : </label>
            <input class="form-control" name="search" placeholder="book title" value="<%= request.getAttribute("search") %>"/>
        </div>
        <input class="btn btn-default" type="submit" value="Go" />
    </form>
</p>
        
<%
    List<Book> listBook = (List<Book>)request.getAttribute("listBook");
    if(listBook == null || listBook.size() == 0) {
        out.println("No book found.<br />");
    }
    else {
        out.println("<table class=\"table table-striped table-bordered\">");
        out.println("<tr><th>Title</th><th>Author</th><th>Price</th><th>Action</th></tr>");
        for(Book book : listBook) {
            out.println("<tr><td>"+book.getTitle()+"</td><td>"+book.getAuthor()+"</td><td>"+String.format("%.2f", book.getPrice())+"§</td><td>");
            if(user != null)
                out.println("   <a href=\"AddCart?title="+URLEncoder.encode(book.getTitle(), "UTF-8")+"\">Add to cart</a><");
            out.println("</td></tr>");
        }
        out.println("</table>");
    }
%>

<%@include file="Footer.jsp" %>