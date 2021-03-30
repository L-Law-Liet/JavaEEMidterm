<%@ page import="kz.sitedev.LibrarySystem.models.User" %>
<%@ page import="kz.sitedev.LibrarySystem.models.Book" %>
<%@ page import="java.util.List" %>
<jsp:forward page="layout/header.jsp" />

<%--statement--%>
<%! User user = null; %>
    <% user = (User) request.getSession().getAttribute("user"); %>
<div class="btn btn-dark w-100 mb-3">
    <h1>Add a book</h1>
</div>
<%
    if(request.getAttribute("errors") != null) {
%>
<div class="alert-danger">
    <ul class="m-3 p-3">
        <%
            for (String error: (List<String>)request.getAttribute("errors")){
        %>
        <li>
            <%=error%>
        </li>
        <%
            }
        %>
    </ul>
</div>
<%
    }
%>
<form action="AddBook" method="post">
    <div class="mb-3">
        <label class="form-label">Title</label>
        <input name="title" type="text" class="form-control">
    </div>
    <div class="mb-3">
        <label class="form-label">Author</label>
        <input name="author" type="text" class="form-control">
    </div>
    <a href="Hello" class="btn btn-secondary">Back</a>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<jsp:include page="layout/footer.jsp" />
