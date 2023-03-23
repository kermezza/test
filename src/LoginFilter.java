import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.ResultSet;

@WebFilter("/Servlet")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user =SQLcommande.UserLogin(email,password);
        if (user != null) {
            chain.doFilter(request, response);
        }else {
            request.setAttribute("error", "Your email or password is wrong!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request,response);
        }

    }
}
