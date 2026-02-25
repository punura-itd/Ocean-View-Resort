package com.icbt.servlet;

import com.icbt.dao.reservationDaoJdbc;
import com.icbt.dto.reservationDto;
import com.icbt.model.reservation;
import com.icbt.service.reservationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

public class reservationServlet extends HttpServlet {

    private reservationService reservationService;

    @Override
    public void init() {
        reservationService = new reservationService(new reservationDaoJdbc());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {

            reservationDto dto = new reservationDto();
            dto.setGuestName(req.getParameter("guestName"));
            dto.setAddress(req.getParameter("address"));
            dto.setContact(req.getParameter("contact"));
            dto.setRoomType(req.getParameter("roomType"));
            dto.setCheckIn(req.getParameter("checkIn"));
            dto.setCheckOut(req.getParameter("checkOut"));

            // ✅ Service should return reservation number like RES-0001 on success
            String result = reservationService.addReservation(dto);

            if (result != null && result.startsWith("RES-")) {
                req.setAttribute("success", "Reservation saved successfully! Reservation No: " + result);
            } else {
                req.setAttribute("error", result);
            }

            // ✅ Your real file name (from your screenshot)
            req.getRequestDispatcher("/pages/addReservation.jsp").forward(req, resp);
            return;
        }

        if ("view".equalsIgnoreCase(action)) {
            String reservationNo = req.getParameter("reservationNo");

            Optional<reservation> r = reservationService.getReservation(reservationNo);
            if (r.isEmpty()) {
                req.setAttribute("error", "Reservation not found.");
            } else {
                req.setAttribute("reservation", r.get());
            }

            req.getRequestDispatcher("/pages/viewReservation.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");
    }
}