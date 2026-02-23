package com.icbt.servlet;
import com.icbt.dao.UserDaoJdbc;
import com.icbt.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class authServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init() {
        authService = new AuthService(new UserDaoJdbc());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (authService.login(username, password)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", username);
            resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");
            return;
        }

        req.setAttribute("error", "Invalid username or password");
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }
}
