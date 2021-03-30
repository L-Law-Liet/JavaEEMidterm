<%@ page import="kz.sitedev.LibrarySystem.models.User" %>
<%@ page import="kz.sitedev.LibrarySystem.models.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--Including--%>
<jsp:include page="layout/header.jsp" />
<%! User user = null; %>
<%--Implicit Object Session--%>
<% user = (User) session.getAttribute("user"); %>
<div class="justify-content-end d-flex mb-4">
    <button class="btn btn-outline-dark"><% out.print("Time on the Server: "+new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date())); %> </button>
</div>
<div class="btn btn-dark w-100 mb-3">
    <h1>Welcome to Library system
        <% if(request.getAttribute("user") != null) { %>
<%--        <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>--%>
<%--        <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>--%>
<%--        <c:set var = "string2" value = "${fn:toUpperCase(user.getName())}" />--%>
<%--        <p>Final string : ${string2}</p>--%>
        <p><%=user.getName()%></p>
        <% } %>
    </h1>
</div>
<% if (user != null && user.getRoleId() == 1){ %>
<div class="my-4 d-flex justify-content-end">
    <div class="mr-3">
        <a href="AddBook" class="btn btn-dark text-white">Add book</a>
    </div>
</div>
<% } %>
<% if (request.getAttribute("books") != null){ %>
<table class="table table-stripped">
    <thead>
    <td>ID</td>
    <td>Title</td>
    <td>Author</td>
    <% if (request.getAttribute("user") != null){
        if (user.getRoleId() != 1){%>
    <td>Action</td>
    <%  }
    }%>
    </thead>
    <tbody>
    <% for (Book book : (List<Book>) request.getAttribute("books")){%>
    <tr>
        <td><%=book.id%></td>
        <td><%=book.title%></td>
        <td><%=book.author%></td>
        <%--Implicit Object Request--%>
        <% if (request.getAttribute("user") != null){
            if (user.getRoleId() != 1){%>
        <td>
            <form action="Borrow">
                <input hidden name="book_id" value="<%=book.id%>">
                <button type="submit" class="btn btn-dark text-white">Borrow</button>
            </form>
        </td>
        <%  }
        } %>
    </tr>
    <%}%>
    </tbody>
</table>
<%}%>
<jsp:include page="layout/footer.jsp" />
