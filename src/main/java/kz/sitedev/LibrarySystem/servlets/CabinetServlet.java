package kz.sitedev.LibrarySystem.servlets;

import kz.sitedev.LibrarySystem.DBConnection;
import kz.sitedev.LibrarySystem.models.Book;
import kz.sitedev.LibrarySystem.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/Cabinet")
public class CabinetServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User userCheck = (User) request.getSession().getAttribute("user");
        if (userCheck == null){
            response.sendRedirect(request.getContextPath()+"/Hello");
            return;
        }
        HttpSession session = request.getSession();
        User auth = (User) session.getAttribute("user");
        if (auth != null){
            Connection con = null;
            ResultSet resultSet = null;
            if (auth.getRoleId() == 1){
                List<User> users = new ArrayList<>();
                try {
                    con = DBConnection.init();
                    PreparedStatement st = con
                            .prepareStatement("select * from users where role_id <> 1");
                    resultSet = st.executeQuery();
                    while (resultSet.next()){
                        User user = new User();
                        user.setId(resultSet.getInt("id"));
                        user.setName(resultSet.getString("fullname"));
                        user.setEmail(resultSet.getString("email"));
                        user.setRoleId(resultSet.getInt("role_id"));
                        users.add(user);
                    }
                    st.close();
                    con.close();
                    request.setAttribute("users", users);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                List<Book> books = new ArrayList<>();
                try {
                    con = DBConnection.init();
                    PreparedStatement st = con
                            .prepareStatement("select b.title title, b.author author, b.id id from users u " +
                                    "join users_books ub on u.id = ub.user_id " +
                                    "join books b on ub.book_id = b.id" +
                                    " where u.id = ?");
                    st.setInt(1, auth.getId());
                    resultSet = st.executeQuery();
                    while (resultSet.next()){
                        Book book = new Book();
                        book.id = resultSet.getInt("id");
                        book.author = resultSet.getString("author");
                        book.title = resultSet.getString("title");
                        books.add(book);
                    }
                    st.close();
                    con.close();
                    request.setAttribute("books", books);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        request.getRequestDispatcher("/cabinet.jsp").forward(request, response);
    }
}
