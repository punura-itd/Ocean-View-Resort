package com.icbt.service;

import com.icbt.dao.reservationDao;
import com.icbt.dto.reservationDto;
import com.icbt.model.reservation;
import com.icbt.util.ValidatorUtil;

import java.time.LocalDate;
import java.util.Optional;

public class reservationService {

    private final reservationDao reservationDao;

    public reservationService(reservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    // ✅ Auto-generate reservation number (RES-0001 format)
    private String generateReservationNo() {
        String last = reservationDao.findLastReservationNo();

        if (last == null) {
            return "RES-0001";
        }

        last = last.trim();

        // If the last value is not in expected format, reset safely
        if (!last.matches("^RES-\\d{4}$")) {
            return "RES-0001";
        }

        int num = Integer.parseInt(last.substring(4)); // after "RES-"
        num++;

        return String.format("RES-%04d", num);
    }

    public String addReservation(reservationDto dto) {
        if (dto == null) return "Invalid request.";

        // ✅ Generate here (NOT from form)
        String resNo = generateReservationNo();

        String name  = safe(dto.getGuestName());
        String addr  = safe(dto.getAddress());
        String phone = safe(dto.getContact());
        String room  = safe(dto.getRoomType()).toUpperCase();
        String inStr = safe(dto.getCheckIn());
        String outStr= safe(dto.getCheckOut());

        if (!ValidatorUtil.isNonBlank(name)) return "Guest name is required.";
        if (!ValidatorUtil.isValidPhone(phone)) return "Invalid contact number (e.g., 0771234567).";
        if (!ValidatorUtil.isNonBlank(room)) return "Room type is required.";

        LocalDate checkIn;
        LocalDate checkOut;
        try {
            checkIn = LocalDate.parse(inStr);
            checkOut = LocalDate.parse(outStr);
        } catch (Exception e) {
            return "Invalid date format. Use YYYY-MM-DD.";
        }

        if (!ValidatorUtil.isValidDateRange(checkIn, checkOut)) {
            return "Check-out date must be after check-in date.";
        }

        // Extremely rare, but safe:
        if (reservationDao.existsByReservationNo(resNo)) {
            return "System generated reservation number already exists. Try again.";
        }

        reservation r = new reservation(resNo, name, addr, phone, room, checkIn, checkOut);
        reservationDao.save(r);

        // ✅ Return the generated number so servlet/JSP can show it
        return resNo;
    }

    public Optional<reservation> getReservationByPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) return Optional.empty();
        return reservationDao.findByPhone(phone.trim());
    }

    public Optional<reservation> getReservation(String reservationNo) {
        String resNo = safe(reservationNo);
        if (!ValidatorUtil.isNonBlank(resNo)) return Optional.empty();
        return reservationDao.findByReservationNo(resNo);
    }

    private String safe(String s) {
        return s == null ? "" : s.trim();
    }
}