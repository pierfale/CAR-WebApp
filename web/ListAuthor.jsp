<%-- 
    Document   : ListAuthor
    Created on : 20 avr. 2015, 19:22:12
    Author     : Pierre
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="entities.Book"%>

<%@include file="Header.jsp" %>

<h2>Book List sort by author</h2>

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
        
        String currentAuthor = "";
        
        out.println("<ul>");
        for(Book book : listBook) {
            
            if(!book.getAuthor().equals(currentAuthor)) {
                if(!currentAuthor.equals("")) {
                    out.println("</ul></li>");
                }
                out.println("<li><h3>"+book.getAuthor()+"</h3><br /><ul>");
                currentAuthor = book.getAuthor();
            }
            out.println("<li>"+book.getTitle()+" : "+String.format("%.2f", book.getPrice())+"�</li>");
            if(user != null)
                out.println("<a href=\"AddCart?title="+URLEncoder.encode(book.getTitle(), "UTF-8")+"\">Add to cart</a>");
        }
        out.println("</ul></li></ul>");

    }
%>

<%@include file="Footer.jsp" %>
