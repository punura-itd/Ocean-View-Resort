package com.icbt.servlet;

import com.icbt.dao.reservationDaoJdbc;
import com.icbt.dto.reservationDto;
import com.icbt.model.reservation;
import com.icbt.service.reservationService;
import java.util.List;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("list".equalsIgnoreCase(action)) {
            String keyword = req.getParameter("keyword");
            req.setAttribute("reservations", reservationService.searchReservations(keyword));
            req.setAttribute("keyword", keyword);
            req.getRequestDispatcher("/pages/reservationList.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        // ADD (already working)
        if ("add".equalsIgnoreCase(action)) {
            reservationDto dto = new reservationDto();
            dto.setGuestName(req.getParameter("guestName"));
            dto.setAddress(req.getParameter("address"));
            dto.setContact(req.getParameter("contact"));
            dto.setRoomType(req.getParameter("roomType"));
            dto.setCheckIn(req.getParameter("checkIn"));
            dto.setCheckOut(req.getParameter("checkOut"));

            String result = reservationService.addReservation(dto);

            if (result != null && result.startsWith("RES-")) {
                req.setAttribute("success", "Reservation saved successfully! Reservation No: " + result);
            } else {
                req.setAttribute("error", result);
            }

            req.getRequestDispatcher("/pages/addReservation.jsp").forward(req, resp);
            return;
        }
        if ("list".equalsIgnoreCase(action)) {
            String keyword = req.getParameter("keyword");
            req.setAttribute("reservations", reservationService.searchReservations(keyword));
            req.setAttribute("keyword", keyword);
            req.getRequestDispatcher("/pages/reservationList.jsp").forward(req, resp);
            return;
        }

        // VIEW (by reservation no OR phone)
        if ("view".equalsIgnoreCase(action)) {
            String reservationNo = req.getParameter("reservationNo");
            String phone = req.getParameter("phone");

            Optional<reservation> r;

            if (reservationNo != null && !reservationNo.trim().isEmpty()) {
                r = reservationService.getReservation(reservationNo);
            } else {
                r = reservationService.getReservationByPhone(phone);
            }

            if (r.isEmpty()) {
                req.setAttribute("error", "Reservation not found.");
            } else {
                req.setAttribute("reservation", r.get());
            }

            req.getRequestDispatcher("/pages/viewReservation.jsp").forward(req, resp);
            return;
        }

        // EDIT (load edit page with data)
        if ("edit".equalsIgnoreCase(action)) {
            String reservationNo = req.getParameter("reservationNo");

            Optional<reservation> r = reservationService.getReservation(reservationNo);
            if (r.isEmpty()) {
                req.setAttribute("error", "Reservation not found.");
                req.getRequestDispatcher("/pages/viewReservation.jsp").forward(req, resp);
            } else {
                req.setAttribute("reservation", r.get());
                req.getRequestDispatcher("/pages/editReservation.jsp").forward(req, resp);
            }
            return;
        }

        // UPDATE (save edits)
        if ("update".equalsIgnoreCase(action)) {
            reservationDto dto = new reservationDto();
            dto.setReservationNo(req.getParameter("reservationNo"));
            dto.setGuestName(req.getParameter("guestName"));
            dto.setAddress(req.getParameter("address"));
            dto.setContact(req.getParameter("contact"));
            dto.setRoomType(req.getParameter("roomType"));
            dto.setCheckIn(req.getParameter("checkIn"));
            dto.setCheckOut(req.getParameter("checkOut"));

            String result = reservationService.updateReservation(dto);

            if ("OK".equals(result)) {
                req.setAttribute("success", "Reservation updated successfully!");
            } else {
                req.setAttribute("error", result);
            }

            // reload updated record on edit page
            Optional<reservation> r = reservationService.getReservation(dto.getReservationNo());
            r.ifPresent(value -> req.setAttribute("reservation", value));

            req.getRequestDispatcher("/pages/editReservation.jsp").forward(req, resp);
            return;
        }

        // DELETE / CANCEL
        if ("delete".equalsIgnoreCase(action)) {
            String reservationNo = req.getParameter("reservationNo");

            String result = reservationService.cancelReservation(reservationNo);

            if ("OK".equals(result)) {
                req.setAttribute("success", "Reservation cancelled successfully!");
            } else {
                req.setAttribute("error", result);
            }

            req.getRequestDispatcher("/pages/viewReservation.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");
    }
}