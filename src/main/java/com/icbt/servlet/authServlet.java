package com.icbt.servlet;
import com.icbt.dao.userDaoJdbc;
import com.icbt.dto.loginDto;
import com.icbt.service.authService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class authServlet extends HttpServlet {
    private authService authService;


    @Override
    public void init() {
        authService = new authService(new userDaoJdbc());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        loginDto dto = new loginDto(username, password);

        if (authService.login(dto)) {

            HttpSession session = req.getSession();
            session.setAttribute("user", username);

            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        req.setAttribute("error", "Invalid login");
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }
}
