<%@ page import="kz.sitedev.LibrarySystem.models.User" %>
<%@ page import="java.util.List" %>
<table class="table table-stripped">
    <thead>
    <td>ID</td>
    <td>Name</td>
    <td>Email</td>
    </thead>
    <tbody>x
    <% for (User student : (List<User>) request.getAttribute("students")){%>
    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%></td>
        <td><%=student.getEmail()%></td>
    </tr>
    <%}%>
    </tbody>
</table>