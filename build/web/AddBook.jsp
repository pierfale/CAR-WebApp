<%-- 
    Document   : AddBook
    Created on : 17 avr. 2015, 13:42:17
    Author     : Pierre
--%>

<%@include file="Header.jsp" %>

<h2>Admin panel</h2>

<form method="POST" action="AddBook">
    
    <div class="form-group">
        <label>Title : </label>
        <input class="form-control" name="title" />
    </div>
    
    <div class="form-group">
        <label>Author : </label>
        <input class="form-control" name="author" />
    </div>
    
    <div class="form-group">
        <label>Price : </label>
        <input class="form-control" name="price" />
    </div>
    
    <input class="btn btn-default" type="submit" />
</form>
