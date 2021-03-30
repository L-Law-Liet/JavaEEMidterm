<%@ page import="kz.sitedev.LibrarySystem.models.User" %>
<%@ page import="java.util.List" %>
<%--Including--%>
<jsp:include page="layout/header.jsp" />
<%! User user = null; %>
<%--Implicit Object Session--%>
<% user = (User) session.getAttribute("user"); %>
<div class="btn btn-dark w-100 mb-3">
    <h1>All Students</h1>
</div>
<% if (request.getAttribute("students") != null){ %>
<jsp:include page="students-table.jsp" />
<div class="d-flex justify-content-end">
    <button class="btn btn-outline-dark">
        <jsp:useBean id="service" class="kz.sitedev.LibrarySystem.services.StudentsService" />
       <% out.print("Total Borrowed Books: " + service.calculateTotalBorrowedBooks( (List<User>) request.getAttribute("students") )); %>
    </button>
</div>
<%}%>
<jsp:include page="layout/footer.jsp" />
