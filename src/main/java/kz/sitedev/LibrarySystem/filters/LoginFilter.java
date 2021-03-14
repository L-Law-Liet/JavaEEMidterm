package kz.sitedev.LibrarySystem.filters;

import kz.sitedev.LibrarySystem.DBConnection;
import kz.sitedev.LibrarySystem.models.User;
import kz.sitedev.LibrarySystem.services.AuthService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/Login")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        if (httpServletRequest.getMethod().equalsIgnoreCase("post")){
            List<String> errors = new ArrayList<>();
            User user = AuthService.getUser(req.getParameter("email"), req.getParameter("password"));

            if (user != null){
                HttpSession session = ((HttpServletRequest) req).getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(300);
            }
            else {
                errors.add("Not Found");
            }
            if (!errors.isEmpty()){
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/login.jsp").include(req, resp);
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
