package kz.sitedev.LibrarySystem.services;

import kz.sitedev.LibrarySystem.DBConnection;
import kz.sitedev.LibrarySystem.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsService {
    public List<User> getStudents(){
        Connection con = null;
        User user = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            con = DBConnection.init();
            PreparedStatement st = con
                    .prepareStatement("select * from users where role_id <> 1");
            resultSet = st.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("fullname"));
                user.setEmail(resultSet.getString("email"));
                user.setRoleId(resultSet.getInt("role_id"));
                users.add(user);
            }
            st.close();
            con.close();
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int calculateTotalBorrowedBooks(){
        int total = 0;

        Connection con = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.init();
            PreparedStatement st = con
                    .prepareStatement("select count(*) from users_books");
            resultSet = st.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getInt(1);
            }
            st.close();
            con.close();
            return total;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return total;
    }
}
