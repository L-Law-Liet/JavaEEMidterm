package kz.sitedev.LibrarySystem.services;

import kz.sitedev.LibrarySystem.DBConnection;
import kz.sitedev.LibrarySystem.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    public static User getUser(String email, String password){
        Connection con = null;
        User user = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.init();
            PreparedStatement st = con
                    .prepareStatement("select * from users where email = ?");

            st.setString(1, email);
            resultSet = st.executeQuery();
            if (resultSet.next()){
                if (BCrypt.checkpw(password, resultSet.getString("password"))){
                    user = new User();
                    user.setEmail(resultSet.getString("email"));
                    user.setName(resultSet.getString("fullname"));
                    user.setId(resultSet.getInt("id"));
                    user.setRoleId(resultSet.getInt("role_id"));
                }
                else {
                    return null;
                }
            }
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
