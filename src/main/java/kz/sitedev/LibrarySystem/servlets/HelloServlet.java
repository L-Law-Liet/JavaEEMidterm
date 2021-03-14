package kz.sitedev.LibrarySystem.servlets;

import kz.sitedev.LibrarySystem.DBConnection;
import kz.sitedev.LibrarySystem.models.Book;
import kz.sitedev.LibrarySystem.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/Hello")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection con = null;
        User user = null;
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<>();
        try {
            con = DBConnection.init();
            PreparedStatement st = con.prepareStatement("select * from books");

            resultSet = st.executeQuery();
            if (resultSet != null){
                while(true){
                    try {
                        if (!resultSet.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        Book book = new Book();
                        book.id = resultSet.getInt("id");
                        book.title = resultSet.getString("title");
                        book.author = resultSet.getString("author");
                        books.add(book);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("books", books);
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}