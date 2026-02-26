package com.icbt.servlet;

import com.icbt.dao.billDaoJdbc;
import com.icbt.dao.reservationDaoJdbc;
import com.icbt.dao.roomRateDaoJdbc;
import com.icbt.dto.billDto;
import com.icbt.service.billingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

public class billingServlet extends HttpServlet {

    private billingService billingService;

    @Override
    public void init() {
        billingService = new billingService(
                new reservationDaoJdbc(),
                new roomRateDaoJdbc(),
                new billDaoJdbc()
        );
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String reservationNo = req.getParameter("reservationNo");

        Optional<billDto> bill = billingService.generateBill(reservationNo);

        if (bill.isEmpty()) {
            req.setAttribute("error", "Bill cannot be generated. Check reservation number, dates, or room rates.");
        } else {
            req.setAttribute("bill", bill.get());
        }

        req.getRequestDispatcher("/pages/bill.jsp").forward(req, resp);
    }
}