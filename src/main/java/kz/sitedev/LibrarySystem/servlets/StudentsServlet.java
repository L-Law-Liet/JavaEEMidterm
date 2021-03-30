package kz.sitedev.LibrarySystem.servlets;

import kz.sitedev.LibrarySystem.DBConnection;
import kz.sitedev.LibrarySystem.models.User;
import kz.sitedev.LibrarySystem.services.StudentsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(value = "/Students")
public class StudentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentsService service = new StudentsService();
        request.setAttribute("students", service.getStudents());
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }
}
