package com.icbt.servlet;

import com.icbt.dao.billDaoJdbc;
import com.icbt.dao.reservationDaoJdbc;
import com.icbt.dao.roomRateDaoJdbc;
import com.icbt.dto.billDto;
import com.icbt.model.reservation;
import com.icbt.service.billingService;

import com.icbt.service.reservationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

public class billingServlet extends HttpServlet {

    private billingService billingService;

    @Override
    public void init() {
        reservationService = new reservationService(new reservationDaoJdbc());

        billingService = new billingService(
                new reservationDaoJdbc(),
                new roomRateDaoJdbc(),
                new billDaoJdbc()
        );
    }

    private reservationService reservationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/pages/bill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String reservationNo = req.getParameter("reservationNo");
        String phone = req.getParameter("phone");

        Optional<reservation> r;

        if (reservationNo != null && !reservationNo.isBlank()) {
            r = reservationService.getReservation(reservationNo);
        } else {
            r = reservationService.getReservationByPhone(phone);
        }

        if (r.isEmpty()) {
            req.setAttribute("error", "Reservation not found.");
        } else {
            Optional<billDto> bill = billingService.generateBill(r.get().getReservationNo());
            bill.ifPresent(b -> req.setAttribute("bill", b));
        }

        // ⭐ YOU MISSED THIS
        req.getRequestDispatcher("/pages/bill.jsp").forward(req, resp);
    }
}