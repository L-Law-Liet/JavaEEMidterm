package kz.sitedev.LibrarySystem.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/AddBook")
public class AddBookFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        if (httpServletRequest.getMethod().equalsIgnoreCase("post")){
            List<String> errors = new ArrayList<>();
            String title = req.getParameter("title");
            String author = req.getParameter("author");

            if (title.trim().equals("")){
                errors.add("Title is required");
            }
            if (author.trim().equals("")){
                errors.add("Author is required");
            }
            if (!errors.isEmpty()){
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/book.jsp").include(req, resp);
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
