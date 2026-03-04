package com.icbt.servlet;

import com.icbt.dao.dashboardDaoJdbc;
import com.icbt.dto.dashboardStatsDto;
import com.icbt.service.dashboardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class dashboardServlet extends HttpServlet {

    private dashboardService dashboardService;

    @Override
    public void init() {
        dashboardService = new dashboardService(new dashboardDaoJdbc());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        dashboardStatsDto stats = dashboardService.getDashboardStats();
        req.setAttribute("stats", stats);

        req.getRequestDispatcher("/pages/dashboard.jsp").forward(req, resp);
    }
}
