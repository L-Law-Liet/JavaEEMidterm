package kz.sitedev.LibrarySystem.servlets;

import kz.sitedev.LibrarySystem.DBConnection;
import kz.sitedev.LibrarySystem.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(value = "/AddBook")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = DBConnection.init();
            PreparedStatement st = con
                    .prepareStatement("insert into books(title, author) values(?, ?)");

            st.setString(1, request.getParameter("title"));
            st.setString(2, request.getParameter("author"));
            st.executeUpdate();

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath()+"/Hello");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userCheck = (User) request.getSession().getAttribute("user");
        if (userCheck == null){
            response.sendRedirect(request.getContextPath()+"/Hello");
            return;
        }
        request.getRequestDispatcher("/book.jsp").forward(request, response);
    }
}
