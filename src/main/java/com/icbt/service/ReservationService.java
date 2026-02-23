package com.icbt.service;

import com.icbt.dao.ReservationDao;
import com.icbt.model.Reservation;
import com.icbt.util.ValidatorUtil;

import java.util.Optional;

public class ReservationService {

    private final ReservationDao reservationDao;

    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public boolean addReservation(Reservation r) {
        if (r == null) return false;
        if (!ValidatorUtil.isNonBlank(r.getReservationNo())) return false;
        if (!ValidatorUtil.isNonBlank(r.getGuestName())) return false;
        if (!ValidatorUtil.isNonBlank(r.getRoomType())) return false;
        if (!ValidatorUtil.isValidPhone(r.getContact())) return false;
        if (!ValidatorUtil.isValidDateRange(r.getCheckIn(), r.getCheckOut())) return false;

        if (reservationDao.existsByReservationNo(r.getReservationNo())) {
            return false; // duplicate reservation number
        }

        reservationDao.save(r);
        return true;
    }

    public Optional<Reservation> getReservation(String reservationNo) {
        if (!ValidatorUtil.isNonBlank(reservationNo)) return Optional.empty();
        return reservationDao.findByReservationNo(reservationNo.trim());
    }
}
