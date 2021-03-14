<%@ page import="kz.sitedev.LibrarySystem.models.User" %>
<%@ page import="kz.sitedev.LibrarySystem.models.Book" %>
<%@ page import="java.util.List" %>
<jsp:include page="layout/header.jsp" />
<% User user = (User) request.getSession().getAttribute("user"); %>
<div class="btn btn-dark w-100 mb-3">
    <h1>Cabinet
        <% if(request.getAttribute("user") != null) { %>
        <p><%=user.getName()%></p>
        <% } %>
    </h1>
</div>
<table class="table">
<% if (user.getRoleId() != 1){ %>
    <thead>
        <td>ID</td>
        <td>Title</td>
        <td>Author</td>
    </thead>
    <tbody>
    <% for (Book book : (List<Book>) request.getAttribute("books")) { %>
        <tr>
            <td><%=book.id%></td>
            <td><%=book.title%></td>
            <td><%=book.author%></td>
        </tr>
    <% } %>
    </tbody>
<% } else { %>
    <thead>
    <td>ID</td>
    <td>Fullname</td>
    <td>Email</td>
    </thead>
    <tbody>
    <% for (User u : (List<User>) request.getAttribute("users")) { %>
    <tr>
        <td><%=u.getId()%></td>
        <td><%=u.getName()%></td>
        <td><%=u.getEmail()%></td>
    </tr>
    <% } %>
    </tbody>
<% } %>
</table>
<jsp:include page="layout/footer.jsp" />
